package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.misc.Util;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawSubsystem extends Subsystem {
	private CANTalon motor;
	private DigitalInput limitSwitch;
	private int state;
	private final double speed = 1.0;
	
	public ClawSubsystem(int motor, int limitSwitch) {
		this.motor = new CANTalon(motor);
		this.limitSwitch = new DigitalInput(limitSwitch);
		this.state = 0;
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	// High-level
	public void open() {
		this.startOpening();
		Util.waitFor(() -> !this.limitSwitch.get());
		Util.waitFor(() -> this.limitSwitch.get());
		this.stop();
	}
	public void close() {
		this.startClosing();
		Util.waitFor(() -> !this.limitSwitch.get());
		Util.waitFor(() -> this.limitSwitch.get());
		this.stop();
	}
	// Slightly higher-level?
	public void openFor(double seconds) {
		this.startOpening();
		Timer.delay(seconds);
		this.stop();
	}
	public void closeFor(double seconds) {
		this.startClosing();
		Timer.delay(seconds);
		this.stop();
	}
	// Low-level
	public void startOpening() { this.motor.set(this.speed); }
	public void startClosing() { this.motor.set(-this.speed); }
	public void stop() { this.motor.set(0.0); }
}
