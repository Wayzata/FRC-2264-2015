package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.RobotParts;
import org.usfirst.frc2264.misc.HorizontalDirection;
import org.usfirst.frc2264.misc.Util;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClawSubsystem extends Subsystem {
	private CANTalon motor;
	private DigitalInput limitSwitch;
	private HorizontalDirection blockDirection = HorizontalDirection.NONE;
	private final double speed = 1.0;
	
	public ClawSubsystem() {
		this(RobotParts.CLAW, RobotParts.SWITCH_CLAW);
	}
	public ClawSubsystem(int motor, int limitSwitch) {
		this.motor = new CANTalon(motor);
		this.limitSwitch = new DigitalInput(limitSwitch);
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	// High-level
	public void open() {
		Util.waitUntilNot(this::startOpening);
		this.stop();
	}
	/* !!WARNING!! THE CLOSE METHOD IS HIGHLY UNSAFE */
//	public void close() {
//		Util.waitUntilNot(this::startClosing);
//		this.stop();
//	}
	// Slightly higher-level?
	public void openFor(double seconds) {
		Util.doFor(this::startOpening, seconds);
		this.stop();
	}
	public void closeFor(double seconds) {
		Util.doFor(this::startClosing, seconds);
		this.stop();
	}
	// Low-level
	public boolean startOpening() {
		SmartDashboard.putBoolean("X", this.limitSwitch.get());
		if(this.blockDirection == HorizontalDirection.RIGHT) {
			this.stop();
		} else {
			this.motor.set(this.speed);
			if(this.limitSwitch.get() && this.blockDirection == HorizontalDirection.NONE)
				this.blockDirection = HorizontalDirection.RIGHT;
			else if(!this.limitSwitch.get())
				this.blockDirection = HorizontalDirection.NONE;
		}
		return this.blockDirection == HorizontalDirection.NONE;
	}
	public boolean startClosing() {
		SmartDashboard.putBoolean("X", this.limitSwitch.get());
		if(this.blockDirection == HorizontalDirection.LEFT) {
			this.stop();
		} else {
			this.motor.set(-this.speed);
			if(this.limitSwitch.get() && this.blockDirection == HorizontalDirection.NONE)
				this.blockDirection = HorizontalDirection.LEFT;
			else if(!this.limitSwitch.get())
				this.blockDirection = HorizontalDirection.NONE;
		}
		return this.blockDirection == HorizontalDirection.NONE;
	}
	public void stop() { this.motor.set(0.0); }
}
