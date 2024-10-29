package org.firstinspires.ftc.teamcode.broncos;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class Driven extends OpMode
{
    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor slideDrive;
    Servo armRotation;
    Servo armExtension;
    Servo claw;


    @Override
    public void init() {
        this.frontLeft = hardwareMap.get(DcMotor.class, "fl");
        this.frontRight = hardwareMap.get(DcMotor.class, "fr");
        this.backLeft = hardwareMap.get(DcMotor.class, "bl");
        this.backRight = hardwareMap.get(DcMotor.class, "br");
        this.slideDrive = hardwareMap.get(DcMotor.class, "Slide");
        this.armRotation = hardwareMap.get(Servo.class, "arm");
        this.armExtension = hardwareMap.get(Servo.class, "extension");
        this.claw = hardwareMap.get(Servo.class, "claw");

// whats up ????????
        //#wecandothis
        slideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideDrive.setTargetPosition(0);
        slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideDrive.setPower(1);

        armRotation.setPosition(1);
        armExtension.setPosition(0);

    }

    @Override
    public void loop() {
        double drive = gamepad1.left_stick_y;
        double strafe = -gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        double claw = gamepad1.touchpad_finger_1_x;

        double denominator = Math.max(Math.abs(drive) + Math.abs(strafe) + Math.abs(turn), .45);
        double frontLeftPower = (drive + strafe + turn) / denominator;
        double frontRightPower = (drive - strafe - turn) / denominator;
        double backLeftPower = (drive - strafe + turn) / denominator;
        double backRightPower = (drive + strafe - turn) / denominator;


        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

//        this.armExtension.setPosition(armExtension);
        this.claw.setPosition(claw);

//
if(gamepad1.dpad_up == true){
    slideDrive.setTargetPosition(-2100);
    armRotation.setPosition(0.45);
    armExtension.setPosition(0.2);
}else if(gamepad1.dpad_down == true){
    slideDrive.setTargetPosition(0);
}


//
    }
}
