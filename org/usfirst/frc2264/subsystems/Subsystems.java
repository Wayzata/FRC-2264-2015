package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.RobotParts;

public class Subsystems {
//	public static ClawSubsystem claw = new ClawSubsystem(RobotParts.CLAW);
	public static DriveSubsystem drive = new DriveSubsystem(RobotParts.DRIVE.FRONT_LEFT,
			RobotParts.DRIVE.FRONT_RIGHT, RobotParts.DRIVE.REAR_LEFT, RobotParts.DRIVE.REAR_RIGHT);
	public static JoystickSubsystem joystick = new JoystickSubsystem(RobotParts.JOYSTICK);
	public static LiftSubsystem lift = new LiftSubsystem(RobotParts.LIFT,
			RobotParts.SWITCH_LIFT_LEVEL, RobotParts.SWITCH_LIFT_BOTTOM);
}
