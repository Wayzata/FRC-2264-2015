package org.usfirst.frc2264.teleoperated;

import org.usfirst.frc2264.RobotParts;
import org.usfirst.frc2264.subsystems.Subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;


public class TeleoperatedCommand extends Command {
	// TODO make the shitty way of doing this not shitty
	SpeedController lift_motor = new CANJaguar(RobotParts.LIFT);
	
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
    	
    	// TODO also make this not shitty
    	this.lift_motor.set(.4 * Subsystems.joystick.getZ());
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
