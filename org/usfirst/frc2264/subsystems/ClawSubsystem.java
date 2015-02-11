package org.usfirst.frc2264.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawSubsystem extends Subsystem {
	private Jaguar motor;
	private double speed;
	
	public ClawSubsystem(int port) {
		this.motor = new Jaguar(port);
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	public void startOpening() { this.motor.set(this.speed); }
	public void startClosing() { this.motor.set(-this.speed); }
	public void stopMoving() { this.motor.set(0.0); }
}
