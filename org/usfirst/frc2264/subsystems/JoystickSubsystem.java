package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.RobotParts;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class JoystickSubsystem extends Subsystem {
	private Joystick joystick, slider;
	
	public JoystickSubsystem() {
		this.joystick = new Joystick(RobotParts.CONTROLLERS.JOYSTICK);
		this.slider = new Joystick(RobotParts.CONTROLLERS.SLIDER);
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
}
