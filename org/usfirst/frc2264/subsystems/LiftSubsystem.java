package org.usfirst.frc2264.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem {
	private SpeedController motor;
	private DigitalInput level, bottom;
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	
	// TODO Levels
	
	public LiftSubsystem(int motor, int levelSwitch, int bottomSwitch) {
		this.motor = new CANJaguar(motor);
		this.level = new DigitalInput(levelSwitch);
		this.bottom = new DigitalInput(bottomSwitch);
	}
	
	public void setSpeed(double speed) {
		this.motor.set(speed);
	}
}
