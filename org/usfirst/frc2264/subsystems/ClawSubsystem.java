package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.misc.ObjectSize;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawSubsystem extends Subsystem {
	private CANTalon motor;
	private DigitalInput tick, stop;
	private int level;
	private double speed;
	
	public ClawSubsystem(int port, int tick, int stop) {
		this.motor = new CANTalon(port);
		this.tick = new DigitalInput(tick);
		this.stop = new DigitalInput(stop);
		this.calibrate();
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	private void calibrate() {
		this.startOpening();
		while(!this.stop.get()) Timer.delay(0.01); // Wait until the switch gets triggered.
		this.stop();
		this.level = 0;
	}
	// High-level control
	public void open() {
		this.startOpening();
		while(this.stop.get()) Timer.delay(0.01);
		this.stop();
	}
	public void close(ObjectSize size) {
		this.setLevel(size.level);
	}
	// Medium-level control
	private void setLevel(int goToLevel) {
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
			while(!this.tick.get()) Timer.delay(0.01); // Wait until the switch gets triggered.
			this.level += direction;
			// TODO We probably want some sort of set delay instead of the below.
			while(this.tick.get()) Timer.delay(0.01); // Wait until the switch *stops* being triggered.
		}
		this.stop();
	}
	// Low-level control
	private void startOpening() { this.motor.set(this.speed); }
	private void startClosing() { this.motor.set(-this.speed); }
	public void stop() { this.motor.set(0.0); }
}
