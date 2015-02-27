package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.subsystems.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class MoveToAutoZoneCommand extends Command {
	public MoveToAutoZoneCommand() { this(2.5); }
	public MoveToAutoZoneCommand(double timeout) {
		this.setTimeout(timeout);
		this.requires(Subsystems.drive);
	}
	protected void initialize() {
		Subsystems.drive.move(0.0, 1.0);
	}
	protected void execute() {}
	protected boolean isFinished() { return this.isTimedOut(); }
	protected void end() { Subsystems.drive.stop(); }
	protected void interrupted() { this.end(); }
}
