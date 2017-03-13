// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc.team1701.robot.commands;

import org.opencv.core.Mat;
import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class See extends Command {
	// GripPipelineWithEdits grip;
	// Mat frame;
	private NetworkTable vision;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public See() {

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
		requires(Robot.vision);

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	public void setupNetworktable() {
		try {
			NetworkTable.setTeam(1701);
			// NetworkTable.setClientMode();
			// NetworkTable.setIPAddress("roboRIO-1701-FRC.local");
			vision = NetworkTable.getTable("vision");
			// vision.initialize();
		} catch (Exception e) {
			System.out.println("Exception caught while setting up NetworkTable in See.");
			System.out.println(e.getMessage());
		}
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Vision.server.startAutomaticCapture();
		// Vision.server.startAutomaticCapture().setResolution(480, 360);
		setupNetworktable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		try {
			SmartDashboard.putNumber("GearTargetHWError: ", vision.getNumber("gearTargetHWError", -1));
		} catch (Exception e) {
			System.out.println("Exception caught trying to get gearTargetFound.");
			// System.out.println(e.getMessage());
			e.printStackTrace();
			// vision.initialize();
		}
		SmartDashboard.putNumber("Gear Distance: ", Robot.gearArm.getGearDistance());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
