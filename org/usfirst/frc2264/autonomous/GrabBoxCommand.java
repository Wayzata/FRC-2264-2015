package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.commands.CommandBase;
import org.usfirst.frc2264.subsystems.Subsystems;

public class GrabBoxCommand extends CommandBase {
	public GrabBoxCommand() {
		super("Autonomous > Grab Box");
		this.requires(Subsystems.claw);
		this.requires(Subsystems.lift);
	}
	public void initialize() {
		Subsystems.claw.open();
		Subsystems.claw.closeFor(1.0);
		Subsystems.lift.setLevel(1);
	}
	public void execute() {}
	public boolean isFinished() { return true; }
	public void end() {}
	public void interrupted() {}
}
