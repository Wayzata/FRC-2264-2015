package org.usfirst.frc2264.teleoperated;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleoperatedCommand extends Command {
	
    public TeleoperatedCommand() {
//    	this.requires(Subsystems.claw);
//    	this.requires(Subsystems.drive);
//    	this.requires(Subsystems.joystick);
//    	this.requires(Subsystems.lift);
    }

    protected void initialize() {
    	SmartDashboard.putNumber("power", 0.1);
    }
    protected void execute() {
//    	Subsystems.drive.move(Subsystems.joystick.getX(), Subsystems.joystick.getY(),
//    			Subsystems.joystick.getTwist());
//    	Subsystems.lift.setLevel(3); // TODO Control from joypad
    }
    protected boolean isFinished() {
    	return false; //Subsystems.joystick.isButtonPressed();
    }
    protected void end() {
//    	Subsystems.claw.stop();
//    	Subsystems.drive.move(0.0, 0.0);
//    	Subsystems.lift.stop();
    }
    protected void interrupted() {}
}
