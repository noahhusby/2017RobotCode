// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc.team1701.robot.subsystems;

import org.usfirst.frc.team1701.robot.RobotMap;
import org.usfirst.frc.team1701.robot.commands.TeleopDrive;

import com.ctre.CANTalon;

//import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final SpeedController left_1 = RobotMap.driveTrainLeft_1;
	private final SpeedController left_2 = RobotMap.driveTrainLeft_2;
	private final SpeedController right_1 = RobotMap.driveTrainRight_1;
	private final SpeedController right_2 = RobotMap.driveTrainRight_2;
	private boolean reversed = false;
	private final double DIST_ADJUST_CONST = 1052.6;
	private boolean precise = false;
	// private final RobotDrive robotDrive41 = RobotMap.driveTrainRobotDrive41;

	public boolean isPrecise() {
		return precise;
	}

	public void setPrecise(boolean precise) {
		this.precise = precise;
	}

	// whichever Talon has the encoder on each side
	private final CANTalon leftEncTalon = (CANTalon) left_2;// this is Talon 3
	private final CANTalon rightEncTalon = (CANTalon) right_1;// this is Talon
																// 12

	private final double WHEEL_CIRCUMFERENCE = 3.9 * Math.PI;
	private final int PULSES_PER_ROTATION = 1440;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void setup() {
		leftEncTalon.configEncoderCodesPerRev(PULSES_PER_ROTATION);
		rightEncTalon.configEncoderCodesPerRev(PULSES_PER_ROTATION);
	}

	public int getLeftVelocity() {
		return leftEncTalon.getEncVelocity();
	}

	public double getLeftDistance() {
		return leftEncTalon.getEncPosition() * WHEEL_CIRCUMFERENCE / DIST_ADJUST_CONST;
	}

	public void resetLeftEncoder() {
		leftEncTalon.setEncPosition(0);
	}

	public int getRightVelocity() {
		return rightEncTalon.getEncVelocity();
	}

	public double getRightDistance() {
		return -rightEncTalon.getEncPosition() * WHEEL_CIRCUMFERENCE / DIST_ADJUST_CONST;
	}

	public void resetRightEncoder() {
		rightEncTalon.setEncPosition(0);
	}

	public void toggleReversed() {
		if (reversed) {
			reversed = false;
		} else
			reversed = true;
	}
	/*
	 * public double getGyroReading(){ return driveGyro.getAngle(); } public
	 * void resetGyro(){ driveGyro.reset(); }
	 */

	public void leftDriveControl(double inputSpeed) {
		/*
		 * two motors are used to control one wheel per side (total 4 motors),
		 * so they have to have the same input
		 * 
		 * can instead make one motor a slave to the other (one follows the
		 * others signal always)
		 */
		left_1.set(inputSpeed);
		left_2.set(inputSpeed);
	}

	public void rightDriveControl(double inputSpeed) {
		/*
		 * two motors are used to control one wheel per side (total 4 motors),
		 * so they have to have the same input
		 */
		right_1.set(inputSpeed);
		right_2.set(inputSpeed);
	}

	public void teleopControl(double forwardsBackwardsAxis, double turningAxis) {
		
		//		double rightInput = forwardsBackwardsAxis;
//		double leftInput = -forwardsBackwardsAxis;
//		
//		if (reversed) {
//			leftInput *= -1;
//			rightInput *= -1;
//		}
//		
//		leftInput += turningAxis;
//		rightInput += turningAxis;
//
//		// if (reversed) {
//		//
//		// leftInput *= -1;
//		// rightInput *= -1;
//		// }
//
//		if (leftInput > 1) {
//			rightInput -= leftInput - 1;
//			leftInput -= leftInput - 1;
//		} else if (leftInput < -1) {
//			rightInput += leftInput + 1;
//			leftInput += leftInput + 1;
//		}
//		if (rightInput > 1) {
//			leftInput -= rightInput - 1;
//			rightInput -= rightInput - 1;
//		} else if (rightInput < -1) {
//			leftInput += rightInput + 1;
//			rightInput += rightInput + 1;
//		}

		if (reversed) {
			forwardsBackwardsAxis *= -1;
		}
		
		if(precise){
			forwardsBackwardsAxis *= .5;
			turningAxis *= .75;
		}

		// System.out.println("LeftInput: " + leftInput);
		// System.out.println("RightInput: " + rightInput);
//		leftDriveControl(leftInput);
//		rightDriveControl(rightInput);
		
		RobotMap.driveTrainRM.arcadeDrive(forwardsBackwardsAxis, turningAxis);
	}

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new TeleopDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}