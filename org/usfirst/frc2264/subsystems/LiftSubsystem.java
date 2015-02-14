package org.usfirst.frc2264.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem {
	private CANTalon motor;
	private DigitalInput levelSwitch;
	private int level;
	private final Double speed = -1.0; // The motor is "backwards"
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	
	public LiftSubsystem(int motor, int levelSwitch) {
		this.motor = new CANTalon(motor);
		this.levelSwitch = new DigitalInput(levelSwitch);
		this.calibrate();
	}
	
	public void calibrate() {
		this.level = 0;
		this.motor.enableBrakeMode(true);
		this.stop();
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
			while(this.levelSwitch.get()) Timer.delay(0.01); // Wait until the switch *stops* being triggered.
			while(!this.levelSwitch.get()) Timer.delay(0.01); // Wait until the switch gets triggered.
			this.level += direction;
		}
		this.motor.set(0.0);
	}
	public int getLevel() { return this.level; }
	public void incrementLevel() { this.setLevel(this.getLevel() + 1); }
	public void decrementLevel() { this.setLevel(this.getLevel() - 1); }
}
