package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Driver Controlled", group="Iterative Opmode")
public class DriverControlled extends OpMode {
    /* Class variables */
    //

    //Instance Creation
    ElapsedTime runtime = new ElapsedTime();   // Start counting the time
    Drive drive         = new Drive();         // A class for drive functions
    Controls controls   = new Controls(this);  // A class for consolidating the controling funcitons

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        //Sets the motor mode
        robot.teleopConfig();

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        //
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        //Input variables
        //double drive  = -1 * gamepad1.left_stick_y;
        //double strafe = gamepad1.left_stick_x;
        //double turn   = gamepad1.right_stick_x;

        // Wheel control
        wheelControl();

        // Manipulator Control
        manipulatorControl();
        
        //Displays runtime
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        //This should never do anything
    }

    private void wheelControl() {
        // Gamepad 1 inputs
        double drive  = controls.drivePower();
        double strafe = controls.strafePower();
        double turn   = controls.turnPower();

        //Mecanum Drive Method
        drive.mecanumDrive(drive, strafe, turn);
    }

    private void manipulatorControl() {
        //Gamepad 2 functions
        double  tilt    = controls.tiltPower();
        boolean grabber = controls.grabberControl();

        //Tilt control
        intake.tiltArm(0.00);

        //Grabber control
    }

}

// End of the MecanumTest class