package org.firstinspires.ftc.teamcode.broncos;

import android.os.DropBoxManager;
import android.provider.Settings;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
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


    int slidePosition;
    //  double rotationPosition;
    double servoPosition = 0.5;
    double servoPositionExtension = 0.9;


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
       // slideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // slideDrive.setTargetPosition(0);
       // slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       // slideDrive.setPower(1);

        slidePosition = slideDrive.getCurrentPosition();

        slideDrive.setTargetPosition(0);
        slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideDrive.setPower(1);

        // rotationPosition = armRotation.getPosition();
        armRotation.setPosition(1);
        armExtension.setPosition(1);
        claw.setPosition(0);

        telemetry.addData("servo position Extension", servoPositionExtension);
        telemetry.update();

    }
@Override
public void start(){
        time.reset();
}



    @Override
    public void loop() {

        ///////////////////DRIVE START////////////////////////////////
        double drive = gamepad1.left_stick_y;
        double strafe = -gamepad1.left_stick_x;
        double turn = -gamepad1.right_stick_x;


        double denominator = Math.max(Math.abs(drive) + Math.abs(strafe) + Math.abs(turn), .45) * 1.4;
        double frontLeftPower = (drive + strafe + turn) / denominator;
        double frontRightPower = (drive - strafe - turn) / denominator;
        double backLeftPower = (drive - strafe + turn) / denominator;
        double backRightPower = (drive + strafe - turn) / denominator;
        double slidedrivepower = 1.0;



        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
///////////////////DRIVE END////////////////////////////////////

//
//

////////////////////linear Slides START///////////////////////////////
     //  telemetry.addData("slidePosition", slidePosition);
    //   telemetry.update();

       if (gamepad1.dpad_up == true && slidePosition > -2310) {
           slideDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
           slideDrive.setPower(-1);
           slidePosition = slideDrive.getCurrentPosition();
       } else if (gamepad1.dpad_down == true && slidePosition < 1) {
           slideDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
           slideDrive.setPower(0.4);
           slidePosition = slideDrive.getCurrentPosition();
       } else if (gamepad1.dpad_up == false && gamepad1.dpad_down == false) {
           slideDrive.setTargetPosition(slidePosition);
           slideDrive.setPower(1);
           slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       }
/////////////////LINEAR SLIDES END////////////////////////////////

//
        //

 ////////////////////ARM ROTATION START////////////////////////////////
      //  servoPosition = Math.max(0.0, Math.min(1.0, servoPosition));
      //  armRotation.setPosition(servoPosition);
      //  rotationPosition = armRotation.getPosition();

        //telemetry.addData("armRotation", rotationPosition);
       // telemetry.update();

        //armRotation.setPosition(1);

        if(gamepad1.a == true)  {

          servoPosition += 0.002;

        //    telemetry.addData("armRotation2", rotationPosition);
        //    telemetry.update();
        }else if(gamepad1.y == true) {

            servoPosition -= 0.002;
        }

        servoPosition = Math.max(0.0, Math.min(1.0, servoPosition));
        armRotation.setPosition(servoPosition);

       // telemetry.addData("servo position", servoPosition);
       // telemetry.update();
////////////////////ARM ROTATION END////////////////////////////////


//
        //



///////////////////////////ARM EXTENSION START////////////////////////////////


        if(gamepad1.x == true)  {

            servoPositionExtension += 0.003;

            //    telemetry.addData("armRotation2", rotationPosition);
            //    telemetry.update();
        }else if(gamepad1.b == true) {

            servoPositionExtension -= 0.003;
        }


        servoPositionExtension = Math.max(0.45, Math.min(1, servoPositionExtension));
        armExtension.setPosition(servoPositionExtension);

       // telemetry.addData("servo position Extension", servoPositionExtension);
      //  telemetry.update();


///////////////////////////ARM EXTENSION END////////////////////////////////


//
        //

//////////////////////CLAW START////////////////////////////////

        if(gamepad1.right_bumper == true) {
            claw.setPosition(0.55);
        }else if(gamepad1.left_bumper == true) {
            claw.setPosition(0);
        }
//////////////////////CLAW END////////////////////////////////
//
    }


}
