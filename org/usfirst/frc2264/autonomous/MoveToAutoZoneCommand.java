package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.commands.CommandBase;
import org.usfirst.frc2264.subsystems.Subsystems;

import edu.wpi.first.wpilibj.Timer;

public class MoveToAutoZoneCommand extends CommandBase {
	private boolean die = false;
	public MoveToAutoZoneCommand() {
		super("Autonomous > Move to Auto Zone");
		this.requires(Subsystems.drive);
	}
	protected void initialize() {}
	protected void execute() {
		Subsystems.drive.move(0.0, 1.0);
		Timer.delay(2.5);
		Subsystems.drive.stop();
		this.die = true;
	}
	protected boolean isFinished() { return this.die; }
	protected void end() { Subsystems.drive.stop(); }
	protected void interrupted() {}
}
