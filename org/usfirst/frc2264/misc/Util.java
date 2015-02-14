package org.usfirst.frc2264.misc;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Util {
	public static void log(String s) {
		SmartDashboard.putString("Debug stuff", s);
	}
	public static void waitFor(BooleanSupplier condition) {
		while(!condition.getAsBoolean()) Timer.delay(0.01);
	}
}
