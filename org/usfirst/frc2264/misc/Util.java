package org.usfirst.frc2264.misc;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Util {
	public static final double POLL_TIME = 0.01;
	public static void log(String s) {
		SmartDashboard.putString("Debug Message", s);
	}
	public static void doFor(BooleanSupplier task, double time) {
		double end = Timer.getFPGATimestamp() + time;
		while(Timer.getFPGATimestamp() < end && task.getAsBoolean())
			Timer.delay(POLL_TIME);
	}
	public static void waitUntil(BooleanSupplier condition) {
		while(!condition.getAsBoolean())
			Timer.delay(POLL_TIME);
	}
	public static void waitUntilNot(BooleanSupplier condition) {
		waitUntil(() -> !condition.getAsBoolean());
	}
}
