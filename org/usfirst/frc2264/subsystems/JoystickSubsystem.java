package org.usfirst.frc2264.subsystems;

import java.util.LinkedList;
import java.util.List;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class JoystickSubsystem extends Subsystem {
	private Joystick joystick;
	
	public JoystickSubsystem(int joystick) {
		this.joystick = new Joystick(joystick);
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	
	public double getX() {
		return joystick.getX();
	}
	
	public double getY() {
		return joystick.getY();
	}
	
	public double getZ() {
		return joystick.getRawAxis(2);
	}
	
	public double getThrottle() {
		return joystick.getThrottle();
	}
	
	// TODO do something about the isButtonPressed() methods
	public boolean isButtonPressed() {
		return this.joystick.getRawButton(1);
	}
	
	public boolean isButtonPressed(int portNum) {
		return joystick.getRawButton(portNum);
	}
	
	public List<Integer> getButtonsPressed() {
		LinkedList<Integer> buttons = new LinkedList<Integer>();
		
		for (int i = 0; i < joystick.getButtonCount(); i++) {
			if (joystick.getRawButton(i)) {
				buttons.add(i);
			}
		}
		return buttons;
	}
}
