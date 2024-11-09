package org.firstinspires.ftc.teamcode.broncos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Strafe Right", group="Autonomous")
public class strafeOnlyAutonomous extends OpMode {

    // Declare motor variables
    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;
    Servo armRotation;
    Servo armExtension;

double armservo = 0.8;
double extensionservo = 1;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void init() {
        // Initialize motors
        this.frontLeft = hardwareMap.get(DcMotor.class, "fl");
        this.frontRight = hardwareMap.get(DcMotor.class, "fr");
        this.backLeft = hardwareMap.get(DcMotor.class, "bl");
        this.backRight = hardwareMap.get(DcMotor.class, "br");
        this.armRotation = hardwareMap.get(Servo.class, "arm");
        this.armExtension = hardwareMap.get(Servo.class, "extension");

        // Set the motors to run without encoders (for simplicity in this example)
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        double servoPosition = 0.5;
//        double servoPositionExtension = 0.9;

        armRotation.setPosition(0.8);
        armExtension.setPosition(1);



    }

    @Override
    public void start() {
        // Start the timer
        runtime.reset();
    }



    @Override
    public void loop() {
        // Wait for 5 seconds before executing the strafe movement




        if (runtime.seconds() < 5) {

            armRotation.setPosition(0.8);
            armExtension.setPosition(1);

            return;
        }


if(runtime.seconds()>=5 && runtime.seconds() <5.15){
    frontLeft.setPower(1);  // Move forward (left side)
    backLeft.setPower(1);   // Move forward (left side)
    frontRight.setPower(-0.4); // Move backward (right side)
    backRight.setPower(-0.4);// negative b/c wire is switched


}

        // After 5 seconds, start strafing right for 4 seconds
        if (runtime.seconds() >= 6 && runtime.seconds() < 9) {


            // To strafe right, set motors to opposite directions (left motors forward, right motors backward)
            frontLeft.setPower(-0.8);  // Move backward (left side)
            backLeft.setPower(0.8);   // Move forward (left side)
            frontRight.setPower(0.8); // Move backward (right side)
            backRight.setPower(-0.8);  // Move forward (right side)
        } else if(runtime.seconds() >= 9 && runtime.seconds() < 9.15) {

            // move back into parking
            frontLeft.setPower(-1);  // Move forward (left side)
            backLeft.setPower(-1);   // Move forward (left side)
            frontRight.setPower(0.4); // Move backward (right side)
            backRight.setPower(0.4);

        } else {


            frontLeft.setPower(0.0);
            backLeft.setPower(0.0);
            frontRight.setPower(0.0);
            backRight.setPower(0.0);
        }
    }//loop//

    @Override
    public void stop() {
        // Stop all motors when the OpMode is finished
        frontLeft.setPower(0.0);
        backLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backRight.setPower(0.0);
        armExtension.setPosition(1);
        armRotation.setPosition(1);
    }
}