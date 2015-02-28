package org.usfirst.frc2264.subsystems;

import org.usfirst.frc2264.misc.Util;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.USBCamera;

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
	public boolean tick() {
		try {
			camera.getImage(this.frame);
			CameraServer.getInstance().setImage(this.frame);
			return true;
		} catch(Exception ex) {
			Util.reportError(ex);
			return false;
		}
	}
}
