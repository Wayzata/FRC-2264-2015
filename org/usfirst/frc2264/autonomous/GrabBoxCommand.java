package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.subsystems.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class GrabBoxCommand extends Command {
	public GrabBoxCommand() { this(0.1); }
	public GrabBoxCommand(double timeout) {
		this.requires(Subsystems.claw);
		this.setTimeout(timeout);
	}
	protected void initialize() {
	}
	protected void execute() {
		Subsystems.claw.startClosing();
	}
	protected boolean isFinished() { return this.isTimedOut(); }
	protected void end() { Subsystems.claw.stop(); }
	protected void interrupted() { this.end(); }
}

