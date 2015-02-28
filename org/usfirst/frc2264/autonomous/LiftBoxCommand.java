package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.subsystems.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class LiftBoxCommand extends Command {
	public LiftBoxCommand() { this(1.5); }
	public LiftBoxCommand(double timeout) {
		this.requires(Subsystems.lift);
		this.setTimeout(timeout);
	}
	protected void initialize() {
	}
	protected void execute() {
		Subsystems.lift.set(1.0);
	}
	protected boolean isFinished() { return this.isTimedOut(); }
	protected void end() { Subsystems.lift.stop(); }
	protected void interrupted() { this.end(); }
}
