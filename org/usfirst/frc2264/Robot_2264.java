package org.usfirst.frc2264;

import org.usfirst.frc2264.autonomous.AutonomousCommand;
import org.usfirst.frc2264.commands.CommandBase;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot_2264 extends IterativeRobot {
	private Command autonomousCommand;
	
	public void robotInit() {
		// Initialise the autonomous command.
		this.autonomousCommand = new AutonomousCommand();
		CommandBase.init();
	}
	// Autonomous mode functions
	public void autonomousInit() {
		this.autonomousCommand.start();
	}
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	// Teleoperated mode
	public void teleopInit() {
		this.autonomousCommand.cancel(); // End autonomous mode command.
		// TODO
	}
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
