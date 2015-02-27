package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.subsystems.Subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveToAutoZoneCommand extends Command {
	private double wait, end;
	public MoveToAutoZoneCommand() { this(2.5); }
	public MoveToAutoZoneCommand(double wait) {
		super("Autonomous > Move to Auto Zone");
		this.wait = wait;
		this.requires(Subsystems.drive);
	}
	protected void initialize() {
		this.end = Timer.getFPGATimestamp() + this.wait;
		Subsystems.drive.move(0.0, 1.0);
	}
	protected void execute() {}
	protected boolean isFinished() { return Timer.getFPGATimestamp() >= this.end; }
	protected void end() { Subsystems.drive.stop(); }
	protected void interrupted() {}
}
