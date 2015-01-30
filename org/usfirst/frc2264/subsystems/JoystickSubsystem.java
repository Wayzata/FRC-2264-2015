package org.usfirst.frc2264.subsystems;

import java.util.LinkedList;
import java.util.List;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class JoystickSubsystem extends Subsystem {
	private Joystick joystick;
	private static final class AXIS_SCALING {
		public static final double X = -1.0;
		public static final double Y = -1.0;
		public static final double Z = 1.0;
		public static final double TWIST = 0.25;
	}
	
	public JoystickSubsystem(int joystick) {
		this.joystick = new Joystick(joystick);
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	
	public double getX() {
		return joystick.getX() * AXIS_SCALING.X;
	}
	
	public double getY() {
		return joystick.getY() * AXIS_SCALING.Y;
	}
	
	public double getZ() {
		return joystick.getRawAxis(2) * AXIS_SCALING.Z;
	}
	
	public double getTwist() {
		return joystick.getThrottle() * AXIS_SCALING.TWIST;
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
