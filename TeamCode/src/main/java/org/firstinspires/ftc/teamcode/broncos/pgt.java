package org.firstinspires.ftc.teamcode.broncos;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class pgt extends OpMode {
    ElapsedTime time;
    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor slideDrive;
    DcMotor slideDrive2;

    DcMotor armRotation;
    Servo armExtension;
    Servo claw;

    int slidePosition = 0;
    int slidePosition2 = 0;
    int armRotationPos = 0;
    double servoPosition = 0.5;
    int roboMode;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "fl");
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight = hardwareMap.get(DcMotor.class, "fr");
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft = hardwareMap.get(DcMotor.class, "bl");
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight = hardwareMap.get(DcMotor.class, "br");
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideDrive = hardwareMap.get(DcMotor.class, "Slide");
        slideDrive2 = hardwareMap.get(DcMotor.class, "Slide2");
        armRotation = hardwareMap.get(DcMotor.class, "arm");
        armExtension = hardwareMap.get(Servo.class, "extension");
        claw = hardwareMap.get(Servo.class, "claw");
        time = new ElapsedTime();

        slideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideDrive.setTargetPosition(0);
        slideDrive.setPower(1);
        slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        slideDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideDrive2.setTargetPosition(0);
        slideDrive2.setPower(1);
        slideDrive2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armRotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRotation.setTargetPosition(0);
        armRotation.setPower(1);
        armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armExtension.setPosition(0.65);
        claw.setPosition(0.36);
        roboMode = 1;
    }

    @Override
    public void start() {
        time.reset();
    }

    @Override
    public void loop() {
        double drive = -gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.left_stick_y;

        double denominator = Math.max(Math.abs(drive) + Math.abs(strafe) + Math.abs(turn), .45) * 1.4;
        double frontLeftPower = (drive - strafe + turn) / denominator;
        double frontRightPower = (drive - strafe - turn) / denominator;
        double backLeftPower = (drive + strafe + turn) / denominator;
        double backRightPower = (drive + strafe - turn) / denominator;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

        boolean triggerPressedLast = false;
        if (gamepad1.right_trigger >= 0.5 && !triggerPressedLast) {
            roboMode = (roboMode == 1) ? 2 : 1;
        }
        triggerPressedLast = gamepad1.right_trigger >= 0.5;

        if (roboMode == 1) {
            manualControl();
        } else if (roboMode == 2) {
            presetPositions();
        }
    }

    private void manualControl() {
        telemetry.addData("slidePosition", slidePosition);
        telemetry.addData("slidePosition2", slidePosition2);
        telemetry.update();

        if (gamepad1.dpad_up && slidePosition > -2320 && slidePosition2 < 2320) {
            setSlidePower(-1, 1);
        } else if (gamepad1.dpad_down && slidePosition <= 0 && slidePosition2 >= 0) {
            setSlidePower(0.4, -0.4);
        } else if (!gamepad1.dpad_up && !gamepad1.dpad_down) {
            holdSlidePosition();
        }

        if (gamepad1.a) {
            armRotation.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            armRotation.setPower(0.15);
        } else if (gamepad1.y) {
            armRotation.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            armRotation.setPower(-0.6);
        } else {
            holdArmPosition();
        }

        if (gamepad1.x) {
            armExtension.setPosition(0.635);
        } else if (gamepad1.b) {
            armExtension.setPosition(0.48);
        }

        if (gamepad1.right_bumper) {
            claw.setPosition(0.7);
        } else if (gamepad1.left_bumper) {
            claw.setPosition(0.36);
        }
    }

    private void presetPositions() {
        if (gamepad1.y) {
            setPresetPosition(-2000, 2000, -50, 0.48);
        } else if (gamepad1.a) {
            setPresetPosition(-1000, 1000, -50, 0.635);
        } else if (gamepad1.x) {
            setPresetPosition(-1, 1, -50, 0.54);
        } else if (gamepad1.b) {
            setPresetPosition(-100, 100, -50, 0.6);
        } else if (gamepad1.dpad_right) {
            setPresetPosition(-30, 30, -50, 0.65);
        }

        if (gamepad1.right_bumper) {
            claw.setPosition(0.7);
        } else if (gamepad1.left_bumper) {
            claw.setPosition(0.36);
        }
    }

    private void setSlidePower(double power1, double power2) {
        slideDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideDrive.setPower(power1);
        slidePosition = slideDrive.getCurrentPosition();

        slideDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideDrive2.setPower(power2);
        slidePosition2 = slideDrive2.getCurrentPosition();
    }

    private void holdSlidePosition() {
        slideDrive.setTargetPosition(slidePosition);
        slideDrive.setPower(1);
        slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        slideDrive2.setTargetPosition(slidePosition2);
        slideDrive2.setPower(1);
        slideDrive2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void holdArmPosition() {
        armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotation.setTargetPosition(armRotationPos);
        armRotation.setPower(1);
    }

    private void setPresetPosition(int slidePos1, int slidePos2, int armPos, double extensionPos) {
        slideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideDrive.setTargetPosition(slidePos1);
        slideDrive.setPower(1);
        slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        slideDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideDrive2.setTargetPosition(slidePos2);
        slideDrive2.setPower(1);
        slideDrive2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armRotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRotation.setTargetPosition(armPos);
        armRotation.setPower(0.5);
        armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armExtension.setPosition(extensionPos);
    }
}

