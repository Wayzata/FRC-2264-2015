package org.usfirst.frc2264.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawSubsystem extends Subsystem {
	private Jaguar jaguar;
	
	public ClawSubsystem(int port) {
		this.jaguar = new Jaguar(port);
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	@Deprecated
	public void manual(double speed) {
		this.jaguar.set(-speed);
	}
}
