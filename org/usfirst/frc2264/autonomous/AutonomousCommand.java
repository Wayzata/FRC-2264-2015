package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.commands.CommandBase;
import org.usfirst.frc2264.autonomous.Vision;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The command that runs when we start autonomous mode.
 */
public class AutonomousCommand extends CommandBase {
	AutonomousState state;
	
	public AutonomousCommand() {
		super("Autonomous");
		// this.requires(Subsystems.drive);
	}
	protected void initialize() {
		System.out.println("Autonomous mode started.");
		this.state = AutonomousState.FIND_BOX;
	}
	Joystick j = new Joystick(0);
	protected void execute() {
		switch(this.state) {
		case FIND_BOX:
			// TODO
			for(Integer i = 0; i < 4; ++i)
				SmartDashboard.putNumber(i.toString() + "a", j.getRawAxis(i));
			for(Integer i = 1; i <= 7; ++i)
				SmartDashboard.putBoolean(i.toString() + "b", j.getRawButton(i));
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
 