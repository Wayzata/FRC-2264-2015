package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.RobotParts;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystemManual extends Subsystem {
	private CANTalon motor;
	private static final double SPEED = -1.0;
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	
	public LiftSubsystemManual() {
		this(RobotParts.LIFT);
	}
	public LiftSubsystemManual(int motor) {
		this.motor = new CANTalon(motor);
		this.motor.enableBrakeMode(true);
	}
	public void set(double speed) {
		this.motor.set(speed * SPEED);
	}

	public void stop() { this.set(0.0); }
}
