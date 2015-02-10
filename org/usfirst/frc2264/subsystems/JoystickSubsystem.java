package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.misc.HorizontalDirection;
import org.usfirst.frc2264.misc.VerticalDirection;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class JoystickSubsystem extends Subsystem {
	private Joystick driveJoystick, clawAndLiftJoystick;
	private static final class AXIS_SCALING {
		public static final double X = 1.0;
		public static final double Y = 1.0;
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
		return driveJoystick.getRawAxis(0) * AXIS_SCALING.X;
	}
	
	public double getY() {
		return driveJoystick.getRawAxis(1) * AXIS_SCALING.Y;
	}
	
	public double getZ() {
		return driveJoystick.getRawAxis(2) * AXIS_SCALING.Z;
	}
	
	public double getTwist() {
		return driveJoystick.getRawAxis(3) * AXIS_SCALING.TWIST;
	}
	
	// TODO do something about the isButtonPressed() methods
	public boolean isButtonPressed() {
		return this.driveJoystick.getRawButton(2);
	}
	
	public boolean isButtonPressed(int portNum) {
		return driveJoystick.getRawButton(portNum);
	}
	
	public int getPOV() {
		return this.clawAndLiftJoystick.getPOV(0);
	}
	
	public VerticalDirection getVertical() {
		int pov = this.getPOV();
		
		if (pov >= 315 || (pov >= 0 && pov >= 45)) {
			return VerticalDirection.UP;
		} else if (pov >= 135 && pov <= 225) {
			return VerticalDirection.DOWN;
		} else {
			return VerticalDirection.NONE;
		}
	}
	
	public HorizontalDirection getHorizontal() {
		int pov = this.getPOV();
		
		if (pov >= 45 && pov <= 135) {
			return HorizontalDirection.RIGHT;
		} else if (pov >= 225 && pov <= 315) {
			return HorizontalDirection.LEFT;
		} else {
			return HorizontalDirection.NONE;
		}
	}
}
