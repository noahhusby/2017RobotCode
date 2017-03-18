// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc.team1701.robot;

import org.usfirst.frc.team1701.robot.commands.AutonomousCommand;
import org.usfirst.frc.team1701.robot.subsystems.Climber;
import org.usfirst.frc.team1701.robot.subsystems.CompressorSystem;
import org.usfirst.frc.team1701.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1701.robot.subsystems.GearArm;
import org.usfirst.frc.team1701.robot.subsystems.Intake;
import org.usfirst.frc.team1701.robot.subsystems.Lights;
import org.usfirst.frc.team1701.robot.subsystems.ShooterSystem;
import org.usfirst.frc.team1701.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Command autonomousCommand;

	public static OI oi;
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static ShooterSystem shooterSystem;
    public static Vision vision;
    public static Intake intake;
    public static GearArm gearArm;
    public static Lights lights;
    public static Climber climber;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private NetworkTable cameraTable;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();
		setupNetworktable();
		
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        shooterSystem = new ShooterSystem();
        vision = new Vision();
        intake = new Intake();
        gearArm = new GearArm();
        lights = new Lights();
        climber = new Climber();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		
		
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();

		// instantiate the command used for the autonomous period
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        autonomousCommand = new AutonomousCommand();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        try {
             System.out.println("Instanciating NavX");
    	     RobotMap.navx = new AHRS(SerialPort.Port.kUSB);
    	} catch( Exception ex ) {
    	     System.out.println(ex);  
    	}
    	if ( RobotMap.navx != null ) {
    	   System.out.println("NavX is running");
    	   SmartDashboard.putBoolean("NavX Detected", true);
    	}else {
    	   System.out.println("NavX is not running");
    	   SmartDashboard.putBoolean("NavX Detected", false);
    	}
    	
    	SmartDashboard.putNumber("Autonomous Mode: ", -1);
	}
	
	public void setupNetworktable() {
		try {
//			NetworkTable.setServerMode();
//			NetworkTable.setIPAddress("roboRIO-1701-FRC.local");
			cameraTable = NetworkTable.getTable("vision");
		} catch (Exception e) {
			;
		}
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
