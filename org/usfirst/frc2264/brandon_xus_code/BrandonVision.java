package org.usfirst.frc2264.brandon_xus_code;

import org.usfirst.frc2264.Robot_2264;
import org.usfirst.frc2264.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BrandonVision extends SampleRobot
{
	public void robotInit()
	{ 
		Robot_2264 vision = new Robot_2264();
//		vision.robotInit();
		double distance = 0;
		DriveSubsystem drive = new DriveSubsystem(1, 2, 3, 4);
		
		do{
			while (isAutonomous() && isEnabled())
			{
				vision.autonomousPeriodic();
				distance = SmartDashboard.getNumber("Distance");
				if(SmartDashboard.getBoolean("IsTote"))
				{
					drive.move(1, 1, 0);
				}
			}
		}while(distance > 1);
	}
}
