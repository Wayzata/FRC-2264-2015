package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.RobotParts;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	private RobotDrive drive;
	
	public DriveSubsystem() {
		this.drive = new RobotDrive(RobotParts.DRIVE_MOTORS.LEFT_FRONT,
				RobotParts.DRIVE_MOTORS.LEFT_BACK,
				RobotParts.DRIVE_MOTORS.RIGHT_FRONT,
				RobotParts.DRIVE_MOTORS.RIGHT_BACK);
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	public void move(double theta, double d) {
		this.drive.mecanumDrive_Polar(d, theta, 0);
	}
	public void turn(double theta) {
		this.drive.mecanumDrive_Polar(0, 0, theta);
	}
}
