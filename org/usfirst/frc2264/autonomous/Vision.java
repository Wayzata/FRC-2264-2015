package org.usfirst.frc2264.autonomous;


public class Vision {
	public final double k; // In inches*pixels
	public Vision() {
		// Based on settings from our camera.
		this(640, 51.50219228499263);
	}
	public Vision(int imageWidth_px, double cameraAngle_deg) {
		this(imageWidth_px, cameraAngle_deg, 7.0);
	}
	public Vision(int imageWidth_px, double cameraAngle_deg, double tapeLength_in) {
		this.k = (tapeLength_in * imageWidth_px) / (2 * Math.tan(cameraAngle_deg * Math.PI / 360));
	}
	public double calculateDistance(int tapeWidth_px) {
		return this.k / tapeWidth_px;
	}
}
