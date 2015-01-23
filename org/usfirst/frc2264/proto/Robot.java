package org.usfirst.frc2264.proto;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2264.misc.RobotDrive_2264;

/**
 * This is test code that controls the motors with throttle control based on the joystick
 */
public class Robot extends SampleRobot {
	
    RobotDrive_2264 robotDrive;
    Joystick stick;
    private Jaguar PWMthing;

    // Channels for the wheels
    final int frontLeftChannel	= 6;
    final int rearLeftChannel	= 4;
    final int frontRightChannel	= 2;
    final int rearRightChannel	= 1;
    
    // The channel on the driver station that the joystick is connected to
    final int joystickChannel	= 0;

    public Robot() {
    	PWMthing = new Jaguar(0);
        robotDrive = new RobotDrive_2264(new CANJaguar(frontLeftChannel), new CANJaguar(rearLeftChannel), new CANJaguar(frontRightChannel), new CANJaguar(rearRightChannel));
        robotDrive.setExpiration(0.1);
    	robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);	// invert the left side motors
    	robotDrive.setInvertedMotor(MotorType.kRearLeft, true);		// you may need to change or remove this to match your robot

        stick = new Joystick(joystickChannel);
    }
        

    /**
     * Runs the motors with Mecanum drive.
     */
    public void operatorControl() {
        robotDrive.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
            
            SmartDashboard.putNumber("X pos", stick.getX());
            SmartDashboard.putNumber("Y pos", stick.getY());
            SmartDashboard.putNumber("Z pos", stick.getZ());
            SmartDashboard.putNumber("Z rot", stick.getThrottle());
            
            robotDrive.mecanumDrive_Polar(getThrottle(), stick.getDirectionRadians(), 0);
            
            Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
        }
    }
    
    public double getThrottle() {
    	return 1 - (.5 * (stick.getZ() + 1));
    }
    
}
