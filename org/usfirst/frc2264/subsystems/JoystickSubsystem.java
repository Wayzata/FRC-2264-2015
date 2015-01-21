package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.RobotParts;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class JoystickSubsystem extends Subsystem {
	private Joystick joystick;
	
	public JoystickSubsystem() {
		this.joystick = new Joystick(RobotParts.JOYSTICK);
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	
	public boolean isButtonPressed() {
		return this.joystick.getRawButton(1);
	}
	
	public double getX() {
		return joystick.getX();
	}
	
	public double getY() {
		return joystick.getY();
	}
	
	public double getZ() {
		return joystick.getZ();
	}
	
	public double getThrottle() {
		return joystick.getThrottle();
	}
}
