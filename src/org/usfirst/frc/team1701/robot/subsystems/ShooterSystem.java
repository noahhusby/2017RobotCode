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

import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;
import org.usfirst.frc.team1701.robot.commands.StopHighShooter;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterSystem extends Subsystem {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final Encoder shootingEncoder = RobotMap.shooterSystemShootingEncoder;
	private final DoubleSolenoid shooterLoader = RobotMap.shooterSystemShooterLoader;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	private final SpeedController mainShoot = RobotMap.shoot1;
	private final SpeedController hopperMotor = RobotMap.hopperMotor;
	private final double PULSES = 360.0;
	private final int TARGET_SPEED = 5500;
	private boolean shooterActive;
	private boolean hopperReverse = true;

	public boolean isShooterActive() {
		return shooterActive;
	}

	public void setShooterActive(boolean shooterActive) {
		this.shooterActive = shooterActive;
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void setup() {
		shootingEncoder.setDistancePerPulse(1.0);
		shooterActive = false;
	}

	public void fire() {
		shooterActive = true;
		mainShoot.set(1.0);
		double speed = shootingEncoder.getRate();
		speed = (int) (speed * 400 + .05) / 100;
		SmartDashboard.putNumber("Shooter Speed: ", speed);
		if(speed >= TARGET_SPEED)
			hopperReverse = false;
		
		if (hopperReverse) {
			// will not load balls until shooter is at speed
			hopperMotor.set(1); // run in reverse until up to speed
		} else {
			hopperMotor.set(-1); // once its reached the right speed, run the
									// hopper forwards to feed the shooter

			if (RobotMap.lightsLED2.get() == Relay.Value.kForward) {
				RobotMap.lightsLED2.set(Relay.Value.kOn);
			} else {
				RobotMap.lightsLED2.set(Relay.Value.kReverse);
			}
		}
	}

	public void hopperRun(double speed) {
		if (!shooterActive) {
			hopperMotor.set(-1 * speed);// or set up Talon to run reversed
		}
	}

	public void hopperStop() {
		hopperMotor.set(0.0);
	}

	public void stop() {
		mainShoot.set(0);
		hopperMotor.set(0);
		shooterActive = false;
		hopperReverse = true;
		if (RobotMap.lightsLED2.get() == Relay.Value.kForward || RobotMap.lightsLED2.get() == Relay.Value.kOn) {
			RobotMap.lightsLED2.set(Relay.Value.kForward);
		} else {
			RobotMap.lightsLED2.set(Relay.Value.kOff);
		}
	}

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		setDefaultCommand(new StopHighShooter());

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
