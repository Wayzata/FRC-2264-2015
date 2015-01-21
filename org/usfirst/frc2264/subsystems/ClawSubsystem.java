package org.usfirst.frc2264.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawSubsystem extends Subsystem {
	private Jaguar jaguar;
	
	public ClawSubsystem(int port) {
		this.jaguar = new Jaguar(port);
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	public void setSpeed(double speed) {
		this.jaguar.set(speed);
	}
	public void stop() { this.setSpeed(0.0); }
}
