package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.commands.CommandBase;
import org.usfirst.frc2264.subsystems.Subsystems;

/**
 * The command that runs when we start autonomous mode.
 */
public class AutonomousCommand extends CommandBase {
	public AutonomousCommand() {
		super("Autonomous");
//		this.requires(Subsystems.claw);
		this.requires(Subsystems.drive);
		this.requires(Subsystems.lift);
//		this.requires(Subsystems.vision);
	}
	protected void initialize() {
	}
	protected void execute() {
	}
	protected void end() {
	}
	
	protected boolean isFinished() {
		return this.isTimedOut();
	}
	
	protected void interrupted() {
		this.end();
	}
}
 
