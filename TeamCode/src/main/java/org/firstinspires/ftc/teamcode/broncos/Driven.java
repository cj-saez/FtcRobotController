package org.firstinspires.ftc.teamcode.broncos;

import android.os.DropBoxManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp

public class Driven extends OpMode
{
    ElapsedTime time;
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
        time = new ElapsedTime();

// whats up ????????
        //#wecandothis
        slideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideDrive.setTargetPosition(0);
        slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //slideDrive.setPower(1);


        armRotation.setPosition(1);
        armExtension.setPosition(0);

    }
@Override
public void start(){
        time.reset();
}



    @Override
    public void loop() {
        double drive = gamepad1.left_stick_y;
        double strafe = -gamepad1.left_stick_x;
        double turn = -gamepad1.right_stick_x;
        double claw = gamepad1.touchpad_finger_1_x;


        double denominator = Math.max(Math.abs(drive) + Math.abs(strafe) + Math.abs(turn), .45);
        double frontLeftPower = (drive + strafe + turn) / denominator;
        double frontRightPower = (drive - strafe - turn) / denominator;
        double backLeftPower = (drive - strafe + turn) / denominator;
        double backRightPower = (drive + strafe - turn) / denominator;
        double slidedrivepower = 1.0;


        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);


//        this.armExtension.setPosition(armExtension);

//
       int slideMoved;
        slideMoved = slideDrive.getCurrentPosition();
        telemetry.addData("slideMoved", slideMoved);
        telemetry.update();

        if(gamepad1.dpad_up == true) {

            slideDrive.setPower(-slidedrivepower);
        } else if (gamepad1.dpad_up == false) {

            slideDrive.setTargetPosition(slideMoved);
            slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        if(gamepad1.dpad_down == true) {

            slideDrive.setPower(slidedrivepower);
        }





//
    }


}
