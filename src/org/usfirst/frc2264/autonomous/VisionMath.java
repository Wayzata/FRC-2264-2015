package org.usfirst.frc2264.autonomous;

public class VisionMath {
	public static double getDistanceFromTapeWidth(double tapeWidth) {
		// The below constant was calculated from data on the wiki.
		// Check the page https://github.com/Wayzata/FRC-2264-2015/wiki/Vision-Code
		/* The constant assumes !!we don't change cameras!! and that the images
		 * are at 640x<something>.
		 */ 
		return 4643.773604703946 / tapeWidth;
	}
}
