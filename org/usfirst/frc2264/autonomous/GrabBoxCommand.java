package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.commands.CommandBase;
import org.usfirst.frc2264.subsystems.Subsystems;

import edu.wpi.first.wpilibj.Timer;

public class GrabBoxCommand extends CommandBase {
	public GrabBoxCommand() {
		super("Autonomous > Grab Box");
		this.requires(Subsystems.claw);
		this.requires(Subsystems.lift);
	}
	public void initialize() {
		Subsystems.lift.setLevel(0);
		Subsystems.claw.startClosing();
		Timer.delay(1.0);
		Subsystems.claw.stop();
		Subsystems.lift.setLevel(1);
	}
	public void execute() {}
	public boolean isFinished() { return true; }
	public void end() {}
	public void interrupted() {}
}