package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.commands.CommandBase;

/**
 * The command that runs when we start autonomous mode.
 */
public class AutonomousCommand extends CommandBase {
	AutonomousState state;
	
	public AutonomousCommand() {
		super("Autonomous");
		this.requires(armSubsystem, clawSubsystem, driveSubsystem, visionSubsystem);
	}
	protected void initialize() {
		System.out.println("Autonomous mode started.");
		this.state = AutonomousState.FIND_AND_GRAB_BOX;
	}
	protected void execute() {
		// TODO
	}
	protected void end() {
		this.state = AutonomousState.END;
		System.out.println("Ended autonomous mode.");
	}
	
	protected boolean isFinished() {
		return this.state == AutonomousState.END;
	}
	
	protected void interrupted() {
		System.out.println("Interrupting autonomous mode (did teleop mode start?).");
		this.end();
	}
}
