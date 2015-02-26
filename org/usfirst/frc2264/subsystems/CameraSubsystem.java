package org.usfirst.frc2264.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 * @author tikiking1
 */
public class CameraSubsystem extends Subsystem {
	USBCamera camera;
	Image frame;
	public CameraSubsystem() {
		this.frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		this.camera = new USBCamera("cam0");
		this.camera.openCamera();
		this.camera.startCapture();
		this.camera.setWhiteBalanceAuto();
		this.camera.setExposureAuto();
	}
	protected void initDefaultCommand() { this.setDefaultCommand(null); }
	public void tick() {
		camera.getImage(this.frame);
		CameraServer.getInstance().setImage(this.frame);
		Timer.delay(1/20); // Allegedly, this is required. I don't see the reason, but...
	}
}
