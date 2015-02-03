package org.usfirst.frc2264.teleoperated;

import org.usfirst.frc2264.subsystems.Subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TeleoperatedCommand extends Command {
	
    public TeleoperatedCommand() {
        // TODO Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.requires(Subsystems.drive);
    	this.requires(Subsystems.joystick);
    	this.requires(Subsystems.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Timer.delay(1.0); // For Debug reasons.
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Subsystems.drive.move(Subsystems.joystick.getX(), Subsystems.joystick.getY(),
    			Subsystems.joystick.getTwist());
    	Subsystems.lift.setLevel(3); // TODO Control from joypad
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Subsystems.joystick.isButtonPressed();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Subsystems.drive.move(0.0, 0.0);
    	Subsystems.lift.stop();
    }
  
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
