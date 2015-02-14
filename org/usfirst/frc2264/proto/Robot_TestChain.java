package org.usfirst.frc2264.proto;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;

public class Robot_TestChain extends SampleRobot {
	private Joystick stick;
	private CANTalon motor;
	
	public Robot_TestChain() {
		this.motor = new CANTalon(6);
		this.stick = new Joystick(0);
	}
	
	public void operatorControl() {
		while(this.isOperatorControl() && this.isEnabled()) {
			this.motor.set(this.stick.getZ());
		}
	}
}
