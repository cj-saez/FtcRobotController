package org.firstinspires.ftc.teamcode.broncos;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp

public class Driven extends OpMode
{
    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor slideDrive;
    DcMotor armRotation;
    DcMotor armExtension;
    DcMotor claw;


    @Override
    public void init() {
        this.frontLeft = hardwareMap.get(DcMotor.class, "fl");
        this.frontRight = hardwareMap.get(DcMotor.class, "fr");
        this.backLeft = hardwareMap.get(DcMotor.class, "bl");
        this.backRight = hardwareMap.get(DcMotor.class, "br");
        this.slideDrive = hardwareMap.get(DcMotor.class, "Slide");
        this.armRotation = hardwareMap.get(DcMotor.class, "arm");
        this.armExtension = hardwareMap.get(DcMotor.class, "extension");
        this.claw = hardwareMap.get(DcMotor.class, "claw");

// whats up ????????
        //#wecandothis




    }

    @Override
    public void loop() {
        double drive = gamepad1.left_stick_y;
        double strafe = -gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        double slideDrive = gamepad1.right_stick_y;
        double armRotation = gamepad1.left_trigger;
        double armExtension = gamepad1.right_trigger;
        double claw = gamepad1.touchpad_finger_1_x;

        double denominator = Math.max(Math.abs(drive) + Math.abs(strafe) + Math.abs(turn), .45);
        double frontLeftPower = (drive + strafe + turn) / denominator;
        double frontRightPower = (drive - strafe - turn) / denominator;
        double backLeftPower = (drive - strafe + turn) / denominator;
        double backRightPower = (drive + strafe - turn) / denominator;
        double slideDrivePower = slideDrive / denominator;
        double armRotationPower = armRotation / denominator;
        double armExtensionPower = armExtension / denominator;
        double clawPower = claw / denominator;



        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
        this.slideDrive.setPower(slideDrivePower);
        this.armRotation.setPower(armRotationPower);
        this.armExtension.setPower(armExtensionPower);
        this.claw.setPower(clawPower);

//
//
//
    }
}
