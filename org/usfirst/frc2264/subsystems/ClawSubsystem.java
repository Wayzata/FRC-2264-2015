package org.usfirst.frc2264.subsystems;

import com.ni.vision.NIVision.ObjectType;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawSubsystem extends Subsystem {
	private CANTalon motor;
	private DigitalInput tick, stop;
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
	}
	// High-level control
	public void open() {
		this.startOpening();
		while(this.stop.get()) Timer.delay(0.01);
		this.stop();
	}
	public void close(ObjectType type) {
		//
	}
	// Low-level control
	private void startOpening() { this.motor.set(this.speed); }
	private void startClosing() { this.motor.set(-this.speed); }
	public void stop() { this.motor.set(0.0); }
}
