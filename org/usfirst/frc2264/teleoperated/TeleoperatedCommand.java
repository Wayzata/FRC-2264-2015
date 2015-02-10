package org.usfirst.frc2264.teleoperated;

import org.usfirst.frc2264.misc.HorizontalDirection;
import org.usfirst.frc2264.subsystems.Subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleoperatedCommand extends Command {
	
    public TeleoperatedCommand() {
//    	this.requires(Subsystems.drive);
    	this.requires(Subsystems.joystick);
//    	this.requires(Subsystems.lift);
    }

    protected void initialize() {
    }
    protected void execute() {
		if(Subsystems.joystick.getHorizontal() == HorizontalDirection.LEFT) {
			Subsystems.claw.setSpeed(0.01);
		} else if(Subsystems.joystick.getHorizontal() == HorizontalDirection.RIGHT) {
			Subsystems.claw.setSpeed(-0.01);
		} else {
			Subsystems.claw.stop();
		}
//    	Subsystems.drive.move(Subsystems.joystick.getX(), Subsystems.joystick.getY(),
//    			Subsystems.joystick.getTwist());
    	//Subsystems.lift.setLevel(3); // TODO Control from joypad
//    	SmartDashboard.putNumber("POV value", Subsystems.joystick.getPOV());
    }
    protected boolean isFinished() {
    	return Subsystems.joystick.isButtonPressed();
    }
    protected void end() {
    	Subsystems.claw.stop();
//    	Subsystems.drive.move(0.0, 0.0);
//    	Subsystems.lift.stop();
    }
    protected void interrupted() {}
}
