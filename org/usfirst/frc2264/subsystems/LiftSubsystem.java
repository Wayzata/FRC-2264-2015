package org.usfirst.frc2264.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem {
	private CANTalon motor;
	private DigitalInput levelSwitch, homeSwitch;
	private int level;
	private final double speed = -0.25; // The motor is "backwards"
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	
	public LiftSubsystem(int motor, int levelSwitch, int homeSwitch) {
		this.motor = new CANTalon(motor);
		this.levelSwitch = new DigitalInput(levelSwitch);
		this.homeSwitch = new DigitalInput(homeSwitch);
		// this.calibrate(); // TODO Re-add this once the carriage is on.
	}
	
	public void calibrate() {
		this.level = 0;
		if(this.homeSwitch.get()) return; // If we're already hitting the home
			// switch, we're done.
		// Move the motor up one level
		this.motor.set(this.speed);
		while(!this.levelSwitch.get()) Timer.delay(0.01);
		// Move the motor down
		this.motor.set(-1.0 * this.speed);
		while(!this.homeSwitch.get()) {
			Timer.delay(0.01);
		}
		this.motor.set(0.0);
	}
	
	public void stop() { this.motor.set(0.0); }
	
	public void setLevel(int goToLevel) {
		int direction;
		if(goToLevel == this.level) {
			return;
		} else if(goToLevel < this.level) {
			direction = -1;
		} else {
			direction = 1;
		}
		this.motor.set(direction * this.speed);
		while(goToLevel != this.level) {
			while(!this.levelSwitch.get()) Timer.delay(0.01); // Wait until the switch gets triggered.
			this.level += direction;
			// TODO We probably want some sort of set delay instead of the below.
			while(this.levelSwitch.get()) Timer.delay(0.01); // Wait until the switch *stops* being triggered.
		}
		this.motor.set(0.0);
	}
	public int getLevel() { return this.level; }
	public void incrementLevel() { this.setLevel(this.getLevel() + 1); }
	public void decrementLevel() { this.setLevel(this.getLevel() - 1); }
}
