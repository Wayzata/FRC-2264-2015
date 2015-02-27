package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.subsystems.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class MoveToAutoZoneCommand extends Command {
	private double speed;
	
	public MoveToAutoZoneCommand() { this(1.0, 0.5); }
	public MoveToAutoZoneCommand(double timeout, double speed) {
		this.requires(Subsystems.drive);
		this.speed = speed;
		this.setTimeout(timeout);
	}
	protected void initialize() {
	}
	protected void execute() {
		Subsystems.drive.move(0.0, this.speed);
	}
	protected boolean isFinished() { return this.isTimedOut(); }
	protected void end() { Subsystems.drive.stop(); }
	protected void interrupted() { this.end(); }
}
