package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.RobotParts;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystemManual extends Subsystem {
	private CANTalon motor;
	private DigitalInput bottomSwitch;
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	
	public LiftSubsystemManual() {
		this(RobotParts.LIFT, RobotParts.SWITCH_LIFT, RobotParts.SWITCH_LIFT_BOTTOM);
	}
	public LiftSubsystemManual(int motor, int levelSwitch, int bottomSwitch) {
		this.motor = new CANTalon(motor);
		this.motor.enableBrakeMode(true);
		this.bottomSwitch = new DigitalInput(bottomSwitch);
	}
	public void set(double speed) {
		this.motor.set(speed);
	}

	public void stop() { this.set(0.0); }
}
