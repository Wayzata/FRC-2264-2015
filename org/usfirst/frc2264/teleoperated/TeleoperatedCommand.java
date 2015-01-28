package org.usfirst.frc2264.teleoperated;

import org.usfirst.frc2264.subsystems.Subsystems;

import edu.wpi.first.wpilibj.command.Command;


public class TeleoperatedCommand extends Command {
    public TeleoperatedCommand() {
        // TODO Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.requires(Subsystems.drive);
    	this.requires(Subsystems.joystick);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Subsystems.drive.move(Subsystems.joystick.getX(), Subsystems.joystick.getY(),
    			Subsystems.joystick.getThrottle());
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
