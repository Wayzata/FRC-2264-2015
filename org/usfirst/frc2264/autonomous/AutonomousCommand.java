package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.Debug;
import org.usfirst.frc2264.commands.CommandBase;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The command that runs when we start autonomous mode.
 */
public class AutonomousCommand extends CommandBase {
	AutonomousState state;
	
	public AutonomousCommand() {
		super("Autonomous");
		this.requires(driveSubsystem);
	}
	protected void initialize() {
		System.out.println("Autonomous mode started.");
		this.state = AutonomousState.DEBUG;
	}
	protected void execute() {
		switch(this.state) {
		case FIND_BOX:
			// TODO
			break;
		case MOVE_TO_BOX:
			// TODO
			break;
		case GRAB_BOX:
			// TODO
			break;
		case END:
			System.out.println("This command is over. This text shouldn't print maybe?");
			break;
		case DEBUG:
//			for(int i = 1; i <= 4; ++i)
//				driveSubsystem.manualSet(i, 0.5);
			driveSubsystem.move(0.0, 0.1);
			break;
		}
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
 