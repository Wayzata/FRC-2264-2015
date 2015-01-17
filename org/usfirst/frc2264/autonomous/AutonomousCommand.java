package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.commands.CommandBase;

/**
 * The command that runs when we start autonomous mode.
 */
public class AutonomousCommand extends CommandBase {
	AutonomousState state;
	
	public AutonomousCommand() {
		super("Autonomous");
		//this.requires(armSubsystem, clawSubsystem, driveSubsystem, switchSubsystem, visionSubsystem);
		//this.requires(debugSubsystem);
	}
	protected void initialize() {
		System.out.println("Autonomous mode started.");
		this.state = AutonomousState.FIND_AND_GRAB_BOX;
	}
	protected void execute() {
		//switchSubsystem.debugPollAllSwitches();
		// debugSubsystem.setMotor(1.0);
//		switch(this.state) {
//		case FIND_AND_GRAB_BOX:
//			// TODO
//			for(int i = 1; i <= 4; ++i)
//				driveSubsystem.manualSet(i, 1.0);
//			break;
//		case END:
//			System.out.println("This command is over. This text shouldn't print maybe?");
//		}
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
 