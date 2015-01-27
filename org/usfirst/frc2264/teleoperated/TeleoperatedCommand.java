package org.usfirst.frc2264.teleoperated;

import org.usfirst.frc2264.subsystems.DriveSubsystemCAN;
import org.usfirst.frc2264.subsystems.JoystickSubsystem;
import org.usfirst.frc2264.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;


public class TeleoperatedCommand extends Command {
	public LiftSubsystem localLift; // Nix these once we fix these
	public JoystickSubsystem localJoystick;
	public DriveSubsystemCAN localDrive;
    public TeleoperatedCommand() {
        // TODO Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	localLift = new LiftSubsystem(0);
    	localJoystick = new JoystickSubsystem();
    	localDrive = new DriveSubsystemCAN();
    	this.requires(localLift);
    	this.requires(localJoystick);
    	this.requires(localDrive);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	localLift.setSpeed(0.4 * localJoystick.getZ());
    	localDrive.move(localJoystick.getX(), localJoystick.getY(), localJoystick.getThrottle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return localJoystick.isButtonPressed();
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }
  
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
