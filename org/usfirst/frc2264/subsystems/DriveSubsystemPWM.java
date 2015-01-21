package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.RobotParts;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

@Deprecated
public class DriveSubsystemPWM extends Subsystem {
	private Jaguar front_left, rear_left, front_right, rear_right;
	private RobotDrive drive;
	
	public DriveSubsystemPWM() {
		this.front_left = new Jaguar(RobotParts.PWM_MOTORS.FRONT_LEFT);
		this.rear_left = new Jaguar(RobotParts.PWM_MOTORS.REAR_LEFT);
		this.front_right = new Jaguar(RobotParts.PWM_MOTORS.FRONT_RIGHT);
		this.rear_right = new Jaguar(RobotParts.PWM_MOTORS.REAR_RIGHT);
		this.drive = new RobotDrive(this.front_left, this.rear_left,
				this.front_right, this.rear_right);
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
