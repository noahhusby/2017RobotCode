/**
 * commands/RunClimb.java
 *
 * Created by Nicholas Hubbard on 11/10/2017.
 *
 * Copyright (c) 2017 Team 1701 (Robocubs)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 
 * Neither the name of the project's author nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.usfirst.frc.team1701.robot.commands;
import org.usfirst.frc.team1701.robot.Robot;
import org.usfirst.frc.team1701.robot.RobotMap;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class RunClimb extends Command {
	private final double STOP_CURRENT = 32.0;
	private boolean isFinished = false;
	private double maxCurrent;
	public RunClimb() {
		requires(Robot.intake);
	}
	protected void initialize() {
		maxCurrent = 0;
	}
	protected void execute() {
		if(RobotMap.lightsLED2.get() == Relay.Value.kForward) {
			RobotMap.lightsLED2.set(Relay.Value.kOn);
		} else {
			RobotMap.lightsLED2.set(Relay.Value.kReverse);
		}
		if(Robot.climber.getCurrent() < STOP_CURRENT) {
			Robot.climber.turnOn();
		} else {
			Robot.climber.turnOff();
			isFinished = true;
			System.out.println("Done climbing.");
		}
		if(Robot.climber.getCurrent() > maxCurrent) {
			maxCurrent = Robot.climber.getCurrent();
			SmartDashboard.putNumber("Climber current: ", maxCurrent);
		}
	}
	protected boolean isFinished() {
		return isFinished;
	}
	protected void end() {}
	protected void interrupted() {}
}