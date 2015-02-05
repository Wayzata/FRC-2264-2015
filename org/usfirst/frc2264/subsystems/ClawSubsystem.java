package org.usfirst.frc2264.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawSubsystem extends Subsystem {
	private CANTalon motor;
	
	public ClawSubsystem(int port) {
		this.motor = new CANTalon(port);
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	public void setSpeed(double speed) {
		this.motor.set(speed);
	}
	public void stop() { this.setSpeed(0.0); }
}
