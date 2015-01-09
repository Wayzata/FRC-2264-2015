
package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.commands.CommandBase;

/**
 * The command that runs when we start autonomous mode.
 */
public class AutonomousCommand extends CommandBase {
	public AutonomousCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}
	protected void initialize() {
		System.out.println("Autonomous mode started.");
	}
	protected void execute() {
	}
	protected void end() {
	}
	
	protected boolean isFinished() {
		// TODO Add an end condition.
		return false;
	}
	
	protected void interrupted() {
	}
}
