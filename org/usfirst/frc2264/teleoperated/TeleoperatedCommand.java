package org.usfirst.frc2264.teleoperated;

import org.usfirst.frc2264.subsystems.Subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TeleoperatedCommand extends Command {

    public TeleoperatedCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.requires(Subsystems.lift);
    	this.requires(Subsystems.joystick);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Subsystems.lift.setSpeed(-.2 * Subsystems.joystick.getZ());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Subsystems.joystick.isButtonPressed();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
