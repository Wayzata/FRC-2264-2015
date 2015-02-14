package org.usfirst.frc2264.misc;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Timer;

public class Util {
	public static void waitFor(BooleanSupplier condition) {
		while(!condition.getAsBoolean()) Timer.delay(0.01);
	}
}
