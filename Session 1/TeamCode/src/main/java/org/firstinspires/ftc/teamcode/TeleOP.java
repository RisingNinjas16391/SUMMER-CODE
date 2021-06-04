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

        robot.rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
        double forward  = gamepad1.left_stick_y;
        double strafe   = gamepad1.left_stick_x;
        double turn     = gamepad1.right_stick_x;
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

        // YOU CAN TOUCH THIS
        if (gamepad1.left_bumper) {
            robot.intake.setPower(-1);
        } else if (gamepad1.right_bumper) {
            robot.intake.setPower(1);
        } else {
            robot.intake.setPower(0);
        }

        //Add code below
    }

    public void displayTelemetry() {
        telemetry.addLine("Drive Encoder ticks")
                .addData("Front Right", robot.rightFront.getCurrentPosition())
                .addData("Front Left", robot.leftFront.getCurrentPosition())
                .addData("Back Right", robot.rightRear.getCurrentPosition())
                .addData("Back Left", robot.leftRear.getCurrentPosition());
        telemetry.update();
    }
}