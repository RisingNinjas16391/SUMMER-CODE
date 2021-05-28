package org.firstinspires.ftc.teamcode;
/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import android.util.Log;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * This is NOT an opmode.
 * <p>
 * This class can be used to define all the specific hardware for a single robot.
 */
public class Hardware {
    private final ElapsedTime period = new ElapsedTime();
    /* Public OpMode members. */
    public DcMotor rightFront;
    public DcMotor leftFront;
    public DcMotor rightRear;
    public DcMotor leftRear;
    public DcMotorEx flywheel;
    public DcMotor kicker;
    /* local OpMode members. */
    HardwareMap hwMap = null;

    /* Constructor */
    public Hardware() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        //driveTrain


        //util motors
        rightFront  = hwMap.get(DcMotor.class, "right front");
        leftFront   = hwMap.get(DcMotor.class, "left front");
        rightRear   = hwMap.get(DcMotor.class, "right rear");
        leftRear    = hwMap.get(DcMotor.class, "left rear");
        flywheel    = hwMap.get(DcMotorEx.class, "flywheel");
        kicker      = hwMap.get(DcMotor.class, "kicker");
        rightFront  .setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        leftFront   .setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        rightRear   .setDirection(DcMotor.Direction.REVERSE);
        leftRear    .setDirection(DcMotor.Direction.FORWARD);
        flywheel    .setDirection(DcMotor.Direction.REVERSE);
        kicker      .setDirection(DcMotor.Direction.FORWARD);
        rightFront  .setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront   .setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear   .setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear    .setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheel    .setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        kicker      .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront  .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront   .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear   .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear    .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        kicker      .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        flywheel    .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }
}