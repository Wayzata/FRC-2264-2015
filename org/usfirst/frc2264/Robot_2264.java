package org.usfirst.frc2264;

import org.usfirst.frc2264.autonomous.AutonomousCommandGroup;
import org.usfirst.frc2264.teleoperated.TeleoperatedCommand;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot_2264 extends IterativeRobot {
	private Command autonomousCommand;
	private Command teleopCommand;
	
	public void robotInit() {}
	// Autonomous mode functions
	public void autonomousInit() {
		this.autonomousCommand = new AutonomousCommandGroup();
		this.autonomousCommand.start();
	}
	public void autonomousPeriodic() {
		Scheduler.getInstance().run(); // runs the next command
	}
	// Teleoperated mode
	public void teleopInit() {
		if(this.autonomousCommand != null)
			this.autonomousCommand.cancel();
		this.teleopCommand = new TeleoperatedCommand();
		this.teleopCommand.start();
	}
	public void teleopPeriodic() {
		Scheduler.getInstance().run(); // runs the next command
	}
}
