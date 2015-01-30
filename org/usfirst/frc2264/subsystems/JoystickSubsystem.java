package org.usfirst.frc2264.subsystems;

import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc2264.misc.ClawDirection;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickSubsystem extends Subsystem {
	private Joystick driveJoystick, clawAndLiftJoystick;
	private static final class AXIS_SCALING {
		public static final double X = -1.0;
		public static final double Y = -1.0;
		public static final double Z = 1.0;
		public static final double TWIST = 0.25;
	}
	
	public JoystickSubsystem(int driveID, int clawAndLiftID) {
		this.driveJoystick = new Joystick(driveID);
		this.clawAndLiftJoystick = new Joystick(clawAndLiftID);
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	
	public double getX() {
		return driveJoystick.getX() * AXIS_SCALING.X;
	}
	
	public double getY() {
		return driveJoystick.getY() * AXIS_SCALING.Y;
	}
	
	public double getZ() {
		return driveJoystick.getRawAxis(2) * AXIS_SCALING.Z;
	}
	
	public double getTwist() {
		return driveJoystick.getThrottle() * AXIS_SCALING.TWIST;
	}
	
	// TODO do something about the isButtonPressed() methods
	public boolean isButtonPressed() {
		return this.driveJoystick.getRawButton(1);
	}
	
	public boolean isButtonPressed(int portNum) {
		return driveJoystick.getRawButton(portNum);
	}
	
	public List<Integer> getButtonsPressed() {
		LinkedList<Integer> buttons = new LinkedList<Integer>();
		
		for (int i = 0; i < driveJoystick.getButtonCount(); i++) {
			if (driveJoystick.getRawButton(i)) {
				buttons.add(i);
			}
		}
		return buttons;
	}
	public int getPOV() {
		return this.clawAndLiftJoystick.getPOV(0);
	}
}
