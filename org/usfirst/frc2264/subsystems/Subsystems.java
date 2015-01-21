package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.RobotParts;

public class Subsystems {
	public static ClawSubsystem claw = new ClawSubsystem(RobotParts.CLAW);
	public static SwitchSubsystem clawTactileSwitch = new SwitchSubsystem(0 /* TODO */);
	public static DriveSubsystemCAN drive = new DriveSubsystemCAN();
	public static JoystickSubsystem joystick = new JoystickSubsystem();
	public static LiftSubsystem lift = new LiftSubsystem();
	public static VisionSubsystem vision = new VisionSubsystem();
}
