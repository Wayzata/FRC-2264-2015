package org.usfirst.frc2264.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	private CANTalon front_left, rear_left, front_right, rear_right;
	private RobotDrive drive;
	
	public DriveSubsystem(int frontLeft, int frontRight, int rearLeft, int rearRight) {
		this.front_left = new CANTalon(frontLeft);
		this.front_right = new CANTalon(frontRight);
		this.rear_left = new CANTalon(rearLeft);
		this.rear_right = new CANTalon(rearRight);
		this.drive = new RobotDrive(this.front_left, this.rear_left,
				this.front_right, this.rear_right);
		this.drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		this.drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	
	public void move(double x, double y) {
		this.move(x, y, 0);
	}
	
	public void move(double x, double y, double theta) {
		this.drive.mecanumDrive_Cartesian(x, y, theta, 0);
	}
	
	public void turn(double theta) {
		this.move(0, 0, theta);
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
