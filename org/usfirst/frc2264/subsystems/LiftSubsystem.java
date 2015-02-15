package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.RobotParts;
import org.usfirst.frc2264.misc.Util;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem {
	private CANTalon motor;
	private DigitalInput levelSwitch;
	private int level;
	private static final double SPEED = -1.0;
	private static final int MAX_LEVEL = 3;
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	
	public LiftSubsystem() {
		this(RobotParts.LIFT, RobotParts.SWITCH_LIFT);
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
	
	public void setLevel(int goToLevel) {
		int direction;
		if(goToLevel == this.level || goToLevel < 0 || goToLevel > MAX_LEVEL) {
			return;
		} else if(goToLevel < this.level) {
			direction = -1;
		} else {
			direction = 1;
		}
		this.motor.set(direction * SPEED);
		while(goToLevel != this.level) {
			Util.waitUntilNot(this.levelSwitch::get);
			Util.waitUntil(this.levelSwitch::get);
			this.level += direction;
		}
		this.stop();
	}
	public int getLevel() { return this.level; }
	public void incrementLevel() { this.setLevel(this.getLevel() + 1); }
	public void decrementLevel() { this.setLevel(this.getLevel() - 1); }
	@Deprecated
	public void manualSet(double speed) { this.motor.set(SPEED * speed); }
	public void stop() { this.motor.set(0.0); }
}
