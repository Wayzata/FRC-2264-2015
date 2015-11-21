package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.RobotParts;
import org.usfirst.frc2264.misc.HorizontalDirection;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

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
	public boolean startOpening(boolean unsafeOverride) {
		if(this.blockDirection == HorizontalDirection.RIGHT && !unsafeOverride) {
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
	public boolean startClosing(boolean unsafeOverride) {
		if(this.blockDirection == HorizontalDirection.LEFT && !unsafeOverride) {
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
