package org.usfirst.frc2264;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Debug {
	public static void log(String s) {
		SmartDashboard.putString("Debug stuff", s);
	}
}
