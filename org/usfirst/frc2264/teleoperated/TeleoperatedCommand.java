package org.usfirst.frc2264.teleoperated;

import org.usfirst.frc2264.misc.HorizontalDirection;
import org.usfirst.frc2264.misc.Util;
import org.usfirst.frc2264.misc.VerticalDirection;
import org.usfirst.frc2264.subsystems.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class TeleoperatedCommand extends Command {
	private Thread cameraThread;
	public TeleoperatedCommand() {
		this.requires(Subsystems.camera);
		this.requires(Subsystems.claw);
		this.requires(Subsystems.drive);
		this.requires(Subsystems.joystick);
		this.requires(Subsystems.lift);
	}

	protected void initialize() {
		this.cameraThread = new Thread("Camera") {
			public void run() {
				Util.doEvery(1/15, Subsystems.camera::tick);
			}
		};
		this.cameraThread.start();
	}
	protected void execute() {
		Subsystems.drive.move(Subsystems.joystick.getX() * Subsystems.joystick.getZ(),
				Subsystems.joystick.getY() * Subsystems.joystick.getZ(),
				Subsystems.joystick.getTwist());
		// Lift
		if(Subsystems.joystick.getVertical() == VerticalDirection.UP)
			Subsystems.lift.set(1.0);
		else if(Subsystems.joystick.getVertical() == VerticalDirection.DOWN)
			Subsystems.lift.set(-1.0);
		else
			Subsystems.lift.stop();
		// Claw
		if(Subsystems.joystick.getHorizontal() == HorizontalDirection.LEFT)
			Subsystems.claw.startClosing();
		else if(Subsystems.joystick.getHorizontal() == HorizontalDirection.RIGHT)
			Subsystems.claw.startOpening();
		else
			Subsystems.claw.stop();
	}
	protected boolean isFinished() {
		return false;
	}
	protected void end() {
		Subsystems.claw.stop();
		Subsystems.drive.stop();
		Subsystems.lift.stop();
	}
	protected void interrupted() {}
}
