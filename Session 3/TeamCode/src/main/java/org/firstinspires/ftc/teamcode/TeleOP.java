package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.io.File;

import static java.lang.Math.abs;

@TeleOp(name = "TeleOp", group = "Teleop")
public class TeleOP extends LinearOpMode {

    Hardware robot = new Hardware();   //Uses heavily modified untested hardware
    double launchSpeed = 0;

    @Override
    public void runOpMode() {

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        robot.rightFront.setPower(0);
        robot.leftFront .setPower(0);
        robot.rightRear .setPower(0);
        robot.leftRear  .setPower(0);
        robot.rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftFront .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightRear .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftRear  .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);      //When finished remove
        robot.leftFront .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rightRear .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.leftRear  .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.leftFront .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.rightRear .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.leftRear  .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Press Start to Begin");    //
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            editHere();
            displayTelemetry();
            // Pace this loop so jaw action is reasonable speed.
            sleep(25);
        }
    }

    public void editHere() {
        // drive train control
        double forward      = gamepad1.left_stick_y;
        double strafe       = gamepad1.left_stick_x;
        double turn         = gamepad1.right_stick_x;

        double[] driveValues = {
                forward + strafe + turn,
                forward - strafe - turn,
                forward - strafe + turn,
                forward + strafe - turn
        };
        robot.rightFront.setPower(driveValues[0]);
        robot.leftFront .setPower(driveValues[1]);
        robot.rightRear .setPower(driveValues[2]);
        robot.leftRear  .setPower(driveValues[3]);

        // Flywheel control
        if (gamepad1.x) {
            launchSpeed = 1.00;
        } else if (gamepad1.y) {
            launchSpeed = 0.90;
        } else if (gamepad1.b) {
            launchSpeed = 0.80;
        } else if (gamepad1.a) {
            launchSpeed = 0;
        }
        robot.flywheel  .setPower(launchSpeed);

        if (gamepad1.right_bumper) {
            robot.kicker.setPower(0.50);
        } else if (gamepad1.left_bumper) {
            robot.kicker.setPower(-0.25);
        } else {
            robot.kicker.setPower(0);
        }

        // Add code below
    }

    public void displayTelemetry() {
        telemetry.addLine("Drive Encoder ticks")
                .addData("Front Left", robot.rightFront.getCurrentPosition())
                .addData("Front Right", robot.leftFront.getCurrentPosition())
                .addData("Back Left", robot.rightRear.getCurrentPosition())
                .addData("Back Right", robot.leftRear.getCurrentPosition());
        telemetry.addLine("Launcher")
                .addData("Speed", robot.flywheel.getVelocity())
        .addData("Kicker Location", robot.kicker.getCurrentPosition());
        telemetry.update();
    }
}