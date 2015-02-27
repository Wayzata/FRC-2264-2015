package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.RobotParts;
import org.usfirst.frc2264.misc.Util;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftSubsystemFancy extends Subsystem {
	private static enum State {
		ON_LEVEL,
		ON_FLAT
	}
	private State state;
	private CANTalon motor;
	private DigitalInput levelSwitch, bottomSwitch;
	private int level, targetLevel;
	private double motorDirection;
	private boolean calibrated;
	private static final double SPEED = -1.0;
	private static final int MAX_LEVEL = 4;
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	
	public LiftSubsystemFancy() {
		this(RobotParts.LIFT, RobotParts.SWITCH_LIFT, RobotParts.SWITCH_LIFT_BOTTOM);
	}
	public LiftSubsystemFancy(int motor, int levelSwitch, int bottomSwitch) {
		this.motor = new CANTalon(motor);
		this.levelSwitch = new DigitalInput(levelSwitch);
		this.bottomSwitch = new DigitalInput(bottomSwitch);
		this.calibrated = false;
	}
	
	public void calibrate() {
		this.level = 0;
		this.targetLevel = 0;
		SmartDashboard.putNumber("Lift Level", 0);
		this.motor.enableBrakeMode(true);
		if(this.bottomSwitch.get()) return;
		this.setMotorDirection(-1.0);
		Util.waitUntil(this.bottomSwitch::get);
		this.state = State.ON_LEVEL;
		this.calibrated = true;
		this.stop();
	}
	
	public void setLevel(int targetLevel) {
		this.targetLevel = targetLevel;
	}
	public void tick() {
		if(this.calibrated) {
			int direction;
			if(this.targetLevel < 0) this.targetLevel = 0;
			if(this.targetLevel > MAX_LEVEL) this.targetLevel = MAX_LEVEL;
			if(this.targetLevel == this.getLevel()) {
				direction = 0;
			} else if(this.targetLevel < this.getLevel()) {
				direction = -1;
			} else {
				direction = 1;
			}
			this.setMotorDirection(direction);
			if(this.state == State.ON_FLAT && this.levelSwitch.get()) {
				this.state = State.ON_LEVEL;
			} else if(this.state == State.ON_LEVEL && !this.levelSwitch.get()) {
				this.state = State.ON_FLAT;
				this.level += direction;
			}
		} else {
			Util.log("Not calibrated!");
		}
	}
	public int getLevel() {
		SmartDashboard.putNumber("Lift Level", this.level);
		return this.level;
	}
	public void incrementLevel() { this.setLevel(this.getLevel() + 1); }
	public void decrementLevel() { this.setLevel(this.getLevel() - 1); }
	public void stop() { this.setMotorDirection(0.0); }
	
	private void setMotorDirection(double newDirection) {
		if(newDirection != this.motorDirection) {
			this.motorDirection = newDirection;
			this.motor.set(this.motorDirection * SPEED);
		}
	}
}
