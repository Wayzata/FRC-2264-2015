package org.usfirst.frc2264.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawSubsystem extends Subsystem {
	private CANTalon motor;
	private final double speed = 1.0;
	
	public ClawSubsystem(int port) {
		this.motor = new CANTalon(port);
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	public void startOpening() { this.motor.set(this.speed); }
	public void startClosing() { this.motor.set(-this.speed); }
	public void stop() { this.motor.set(0.0); }
}
