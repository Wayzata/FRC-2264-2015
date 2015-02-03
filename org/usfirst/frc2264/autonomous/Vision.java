package org.usfirst.frc.team2264.robot;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2264.robot.DriveSubsystem;
import edu.wpi.first.wpilibj.Timer;

public class Vision extends SampleRobot
{
	public void robotInit()
	{ 
		Robot vision = new Robot();
		vision.robotInit();
		double distance = 0;
		DriveSubsystem drive = new DriveSubsystem(1, 2, 3, 4);
		
		do{
			while (isAutonomous() && isEnabled())
			{
				vision.autonomous();
				distance = SmartDashboard.getNumber("Distance");
				if(SmartDashboard.getBoolean("IsTote"))
				{
					
					drive.move(1, 1, 0);
				}
			}
		}while(distance > 1);
	}
}
