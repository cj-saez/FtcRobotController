package org.firstinspires.ftc.teamcode.broncos;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
////hthyuusugvshhhhuiihfegfhhejiwehhds
public class Driven extends OpMode {
    ElapsedTime time;
    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor slideDrive;
    DcMotor slideDrive2;

    DcMotor armRotation;
    DcMotor hangLift;
    Servo armExtension;
    Servo claw;


    int slidePosition = 0;
    int slidePosition2 = 0;

    int armRotationPos = 0;
    int hangLiftPosition = 0;
    //  double rotationPosition;
    double servoPosition = 0.5;
//    double servoPositionExtension = 0.68;
    int roboMode;


    //
//              ////  //
//    ////
//    ////
//    ////
//    ////
//    //  //  //
//    ////
//    ////
//    ////
//    ////
//    //  //
////
////
////
////
//  //  //
////
////
////
////
//  //
//
//
//

    @Override
    public void init() {
        this.frontLeft = hardwareMap.get(DcMotor.class, "fl");
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.frontRight = hardwareMap.get(DcMotor.class, "fr");
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.backLeft = hardwareMap.get(DcMotor.class, "bl");
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.backRight = hardwareMap.get(DcMotor.class, "br");
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.slideDrive = hardwareMap.get(DcMotor.class, "Slide");
        this.slideDrive2 = hardwareMap.get(DcMotor.class, "Slide2");

        this.hangLift = hardwareMap.get(DcMotor.class, "HangLift");

        armRotation = hardwareMap.get(DcMotor.class, "arm");

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
        slidePosition2 = slideDrive2.getCurrentPosition();

        armRotationPos = armRotation.getCurrentPosition();
        hangLiftPosition = hangLift.getCurrentPosition();


        slideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideDrive.setTargetPosition(0);
        slideDrive.setPower(1);
        slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        slideDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideDrive2.setTargetPosition(0);
        slideDrive2.setPower(1);
        slideDrive2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armRotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRotation.setPower(1);
        armRotation.setTargetPosition(0);
        armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        hangLift.setTargetPosition(0);
//        hangLift.setPower(0.5);
//        hangLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //rotationPosition = armRotation.getPosition();
        armExtension.setPosition(0.65);
        claw.setPosition(0.36);

        roboMode = 1;
//        telemetry.addData("servo position Extension", servoPositionExtension);
//        telemetry.update();

    }
@Override
public void start(){
        time.reset();
}

////
////
////
////
////
//  //  //
////
////
////
////
//  //  //
////
////
////
////
//  //
//
//
//
//
  //
    @Override
    public void loop() {

        ///////////////////DRIVE START////////////////////////////////
        double drive = gamepad1.left_stick_y;
        double strafe = -gamepad1.left_stick_x;
        double turn = -gamepad1.right_stick_x;

//        armExtension.setPosition(0.68);




        double denominator = Math.max(Math.abs(drive) + Math.abs(strafe) + Math.abs(turn), .45) * 1.4;
        double frontLeftPower = (drive + strafe + turn) / denominator;
        double frontRightPower = (drive - strafe - turn) / denominator;
        double backLeftPower = (drive - strafe + turn) / denominator;
        double backRightPower = (drive + strafe - turn) / denominator;
     //   double slidedrivepower = 1.0;



        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
///////////////////DRIVE END////////////////////////////////////
////  //
//    ////
//    ////
//    ////
//    ////
//    //  //  //
//    ////
//    ////
//    ////
//    ////
//    //  //  //  //
//    ////
//    ////
//    ////
//    ////
//    //  //  //
//    ////
//    ////
//    ////
//    ////
//    //  //
    //
  ///////////////././././././././././././.change mode///////////,.,.,.,,/,/,/../././././././../././././../././././././../
    if (gamepad1.left_trigger >= 0.5 && roboMode==1){
        roboMode=2;
    }else if(gamepad1.left_trigger >= 0.5 && roboMode==2){
        roboMode=1;
    }
//.////////////././././././././././././././change mode ^/./././././..../././/.../....//././/./././.././././.././././
//
//

        /////////////////////////////  |   ////////////////////////
        //////////////////////////    |    /////////////////////
        /////////////////////////   \ | / //////////////////////
        /////////////////// MODE 1   \ /  ////////////////////////////
        ////////////////////////////     ////////////////////////
        /////////////////////////////////////////////////////////
        ///////////////// normal drive all manual ////////////////
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////



 while(roboMode == 1) { /////manual control


////////////////////linear Slides START///////////////////////////////
       telemetry.addData("slidePosition", slidePosition);
        telemetry.update();

     telemetry.addData("slidePosition22222", slidePosition2);
     telemetry.update();


     if (gamepad1.dpad_up == true && slidePosition > -2320 && slidePosition2 < 2320) {
         slideDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         slideDrive.setPower(-1);
         slidePosition = slideDrive.getCurrentPosition();

         slideDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         slideDrive2.setPower(1);
         slidePosition2 = slideDrive2.getCurrentPosition();

     } else if (gamepad1.dpad_down == true && slidePosition <= 0 &&  slidePosition2 >= 0) {
         slideDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         slideDrive.setPower(0.4);
         slidePosition = slideDrive.getCurrentPosition();

         slideDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         slideDrive2.setPower(-0.4);
         slidePosition2 = slideDrive2.getCurrentPosition();

     } else if (gamepad1.dpad_up == false && gamepad1.dpad_down == false) {
         slideDrive.setTargetPosition(slidePosition);
         slideDrive.setPower(1);
         slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

         slideDrive2.setTargetPosition(slidePosition2);
         slideDrive2.setPower(1);
         slideDrive2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     }


/////////////////LINEAR SLIDES END////////////////////////////////


     ////////////////////ARM ROTATION START////////////////////////////////
     telemetry.addData("armRotation", armRotationPos);
     telemetry.update();
     //armRotation.setPosition(1);
     if (gamepad1.a == true) {
         armRotation.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         armRotation.setPower(0.05);
         armRotationPos = armRotation.getCurrentPosition();
     } else if (gamepad1.y == true) {
         armRotation.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         armRotation.setPower(-0.6);
         armRotationPos = armRotation.getCurrentPosition();
     } else if (gamepad1.a == false && gamepad1.y == false) {
//            armRotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         armRotation.setTargetPosition(armRotationPos);
         armRotation.setPower(1);
     }
//        telemetry.addData("armRotation", armRotationPos);
//        telemetry.update();

////////////////////ARM ROTATION END////////////////////////////////


//
     //

///////////////////////////ARM EXTENSION START////////////////////////////////
     if (gamepad1.x == true) {

         armExtension.setPosition(0.635);

//            servoPositionExtension += 0.003;
//            armExtension.setPosition(servoPositionExtension);

         //    telemetry.addData("armRotation2", rotationPosition);
         //    telemetry.update();
     } else if (gamepad1.b == true) {

         armExtension.setPosition(0.48);

//            servoPositionExtension -= 0.003;
//            armExtension.setPosition(servoPositionExtension);

     }
//        servoPositionExtension = Math.max(0.39, Math.min(0.69, servoPositionExtension));
//        armExtension.setPosition(servoPositionExtension);
//        System.out.println(servoPositionExtension);

     // telemetry.addData("servo position Extension", servoPositionExtension);
     //  telemetry.update(
///////////////////////////ARM EXTENSION END////////////////////////////////
//
     //


//////////////////////CLAW START////////////////////////////////
     if (gamepad1.right_bumper == true) {
         claw.setPosition(0.7);
     } else if (gamepad1.left_bumper == true) {
         claw.setPosition(0.36);
     }
//////////////////////CLAW END////////////////////////////////

     //

/////////////////////////hangLift Start///////////////////////////
//     if (gamepad1.dpad_left == true) {
//
//         hangLift.setPower(1);
//     } else if (gamepad1.dpad_right == true) {
//
//         hangLift.setPower(-1);
//     } else if (gamepad1.dpad_left != true && gamepad1.dpad_right != true) ;
//     {
//         hangLift.setPower(0);
//
//     }


//////////////////////hangLift END ////////////////////////////



        } ////////././././. ROBO MODE 1  ^  /././././././//////////
       ////////
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////
       /////////////////////////////  |   ////////////////////////
        //////////////////////////    |    /////////////////////
        /////////////////////////   \ | / //////////////////////
        /////////////////// MODE 2   \ /  ////////////////////////////
        ////////////////////////////     ////////////////////////
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////

        while (roboMode == 2){

         if (gamepad1.y == true){// top position// \//  top bucket
//
             // slides up //
             slideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
             slideDrive.setTargetPosition(-2000);
             slideDrive.setPower(1);
             slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             slideDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
             slideDrive2.setTargetPosition(2000);
             slideDrive2.setPower(1);
             slideDrive2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // slides up //
             // Rotation up //
//             armRotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//             armRotation.setTargetPosition(0);//figure out position of up
//             armRotation.setPower(0.5);
//             armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // Rotation up //
             // Extension out //
             armExtension.setPosition(0.48);
                // Extension //

         }//gamempad Y top bucket

//////////////////////\\\\\\\\\\\\\\\\\\\\///////////////////////////////\\\\\\\\\\\\\\\\\
//////////////////////\\\\\\\\\\\\\\\\\\\\///////////////////////////////\\\\\\\\\\\\\\\\\
//////////////////////\\\\\\\\\\\\\\\\\\\\///////////////////////////////\\\\\\\\\\\\\\\\\

            if (gamepad1.a == true){ // bottom bucket/////

                // slides up //
                slideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slideDrive.setTargetPosition(-1000);
                slideDrive.setPower(1);
                slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slideDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slideDrive2.setTargetPosition(1000);
                slideDrive2.setPower(1);
                slideDrive2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // slides up //
                // Rotation up //
//             armRotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//             armRotation.setTargetPosition(0);//figure out position of up
//             armRotation.setPower(0.5);
//             armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // Rotation up //
                // Extension out //
                armExtension.setPosition(0.635);
                // Extension //

            } /// bottom bucket game pad a /////
//////////////////////\\\\\\\\\\\\\\\\\\\\///////////////////////////////\\\\\\\\\\\\\\\\\
//////////////////////\\\\\\\\\\\\\\\\\\\\///////////////////////////////\\\\\\\\\\\\\\\\\
//////////////////////\\\\\\\\\\\\\\\\\\\\///////////////////////////////\\\\\\\\\\\\\\\\\

            if (gamepad1.x = true){ // tape marks //

                // slides down //
                slideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slideDrive.setTargetPosition(-1);
                slideDrive.setPower(1);
                slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slideDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slideDrive2.setTargetPosition(1);
                slideDrive2.setPower(1);
                slideDrive2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // slides up //
                // Rotation up //
               armRotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
               armRotation.setTargetPosition(50);//figure out position of tape mark
               armRotation.setPower(0.5);
               armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // Rotation up //
                // Extension out //
                armExtension.setPosition(0.54);
                // Extension //

            }/// tape mark////
//////////////////////\\\\\\\\\\\\\\\\\\\\///////////////////////////////\\\\\\\\\\\\\\\\\
//////////////////////\\\\\\\\\\\\\\\\\\\\///////////////////////////////\\\\\\\\\\\\\\\\\
//////////////////////\\\\\\\\\\\\\\\\\\\\///////////////////////////////\\\\\\\\\\\\\\\\\

            if (gamepad1.b == true){// submersible //

                slideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slideDrive.setTargetPosition(-30);
                slideDrive.setPower(1);
                slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slideDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slideDrive2.setTargetPosition(30);
                slideDrive2.setPower(1);
                slideDrive2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // slides up //
                // Rotation up //
                armRotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                armRotation.setTargetPosition(50);//figure out position of in the sub
                armRotation.setPower(0.4);
                armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // Rotation up //
                // Extension out //
                armExtension.setPosition(0.6);
                // Extension //

            } /// submersible ////
//////////////////////\\\\\\\\\\\\\\\\\\\\///////////////////////////////\\\\\\\\\\\\\\\\\
//////////////////////\\\\\\\\\\\\\\\\\\\\///////////////////////////////\\\\\\\\\\\\\\\\\
//////////////////////\\\\\\\\\\\\\\\\\\\\///////////////////////////////\\\\\\\\\\\\\\\\\

            if (gamepad1.dpad_right == true) {// normal position ////./.

                slideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slideDrive.setTargetPosition(-30);
                slideDrive.setPower(1);
                slideDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slideDrive2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slideDrive2.setTargetPosition(30);
                slideDrive2.setPower(1);
                slideDrive2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // slides up //
                // Rotation up //
                armRotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                armRotation.setTargetPosition(100);//figure out position of in the sub
                armRotation.setPower(0.4);
                armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // Rotation up //
                // Extension out //
                armExtension.setPosition(0.65);
                // Extension //



            }/////./..////// normal position////////
//////////////////////\\\\\\\\\\\\\\\\\\\\///////////////////////////////\\\\\\\\\\\\\\\\\
//////////////////////\\\\\\\\\\\\\\\\\\\\///////////////////////////////\\\\\\\\\\\\\\\\\
//////////////////////\\\\\\\\\\\\\\\\\\\\///////////////////////////////\\\\\\\\\\\\\\\\\

///////claw////./././////////////
            if (gamepad1.right_bumper == true) {
                claw.setPosition(0.7);
            } else if (gamepad1.left_bumper == true) {
                claw.setPosition(0.36);
            }
///////// claw////./././///////////

        }//////././/.././ ROBO MODE 2 end /.//./...//.//////////

    }


}
