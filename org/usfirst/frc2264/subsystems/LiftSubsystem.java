package org.usfirst.frc2264.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem {
	private Jaguar motor;
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	// TODO
	
	public LiftSubsystem(int port) {
		this.motor = new Jaguar(port);
	}
	
	public void setSpeed(double speed) {
		this.motor.set(speed);
	}
}
