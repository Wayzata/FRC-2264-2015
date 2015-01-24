package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.RobotParts;
import org.usfirst.frc2264.misc.RobotDrive_2264;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystemCAN extends Subsystem {
	private CANJaguar front_left, rear_left, front_right, rear_right;
	private RobotDrive_2264 drive;
	
	public DriveSubsystemCAN() {
		this.front_left = new CANJaguar(RobotParts.DRIVE.FRONT_LEFT);
		this.rear_left = new CANJaguar(RobotParts.DRIVE.REAR_LEFT);
		this.front_right = new CANJaguar(RobotParts.DRIVE.FRONT_RIGHT);
		this.rear_right = new CANJaguar(RobotParts.DRIVE.REAR_RIGHT);
		this.drive = new RobotDrive_2264(this.front_left, this.rear_left,
				this.front_right, this.rear_right);
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	
	public void move(double x, double y) {
		this.drive.mecanumDrive_Cartesian(x, y, 0, 0);
	}
	
	public void move(double x, double y, double theta) {
		this.drive.mecanumDrive_Cartesian(x, y, theta, 0);
	}
	
	public void turn(double theta) {
		this.drive.mecanumDrive_Polar(0, 0, theta);
	}
	
	@Deprecated
	public void manualSet(int motor, double v) {
		switch(motor) {
		case 1:
			this.front_left.set(v);
			break;
		case 2:
			this.rear_left.set(v);
			break;
		case 3:
			this.front_right.set(v);
			break;
		case 4:
			this.rear_right.set(v);
			break;
		default:
			throw new IllegalArgumentException();
		}
	}
}
