package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.RobotParts;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	private CANTalon front_left, rear_left, front_right, rear_right;
	private RobotDrive drive;
	
	public DriveSubsystem() {
		this(RobotParts.DRIVE.FRONT_LEFT, RobotParts.DRIVE.FRONT_RIGHT,
		     RobotParts.DRIVE.REAR_LEFT,  RobotParts.DRIVE.REAR_RIGHT);
	}
	public DriveSubsystem(int frontLeft, int frontRight, int rearLeft, int rearRight) {
		this.front_left = new CANTalon(frontLeft);
		this.front_right = new CANTalon(frontRight);
		this.rear_left = new CANTalon(rearLeft);
		this.rear_right = new CANTalon(rearRight);
		this.front_left.enableBrakeMode(false);
		this.front_right.enableBrakeMode(false);
		this.rear_left.enableBrakeMode(false);
		this.rear_right.enableBrakeMode(false);
		this.drive = new RobotDrive(this.front_left, this.rear_left,
				this.front_right, this.rear_right);
	}
	
	protected void initDefaultCommand() { this.setDefaultCommand(null); }
	
	public void move(double x, double y) { this.move(x, y, 0.0); }
	public void move(double x, double y, double theta) { this.drive.mecanumDrive_Cartesian(x, -theta, -y, 0.0); }
	public void turn(double theta) { this.move(0.0, 0.0, theta); }
	public void stop() { this.move(0.0, 0.0, 0.0); }
}
