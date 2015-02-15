package org.usfirst.frc2264.teleoperated;

import org.usfirst.frc2264.misc.HorizontalDirection;
import org.usfirst.frc2264.misc.VerticalDirection;
import org.usfirst.frc2264.subsystems.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class TeleoperatedCommand extends Command {
	
	public TeleoperatedCommand() {
		this.requires(Subsystems.claw);
		this.requires(Subsystems.drive);
		this.requires(Subsystems.joystick);
		this.requires(Subsystems.lift);
		this.requires(Subsystems.vision);
	}

	protected void initialize() {
	}
	protected void execute() {
		Subsystems.drive.move(Subsystems.joystick.getX() * Subsystems.joystick.getZ(),
				Subsystems.joystick.getY() * Subsystems.joystick.getZ(),
				Subsystems.joystick.getTwist());
		// Lift
		if(Subsystems.joystick.getVertical() == VerticalDirection.UP)
			Subsystems.lift.manualSet(1.0);
		else if(Subsystems.joystick.getVertical() == VerticalDirection.DOWN)
			Subsystems.lift.manualSet(-1.0);
		else
			Subsystems.lift.manualSet(0.0);
		// Claw
		if(Subsystems.joystick.getHorizontal() == HorizontalDirection.LEFT)
			Subsystems.claw.startClosing();
		else if(Subsystems.joystick.getHorizontal() == HorizontalDirection.RIGHT)
			Subsystems.claw.startOpening();
		else
			Subsystems.claw.stop();
		Subsystems.vision.sendImage();
	}
	protected boolean isFinished() {
		return Subsystems.joystick.isButtonPressed();
	}
	protected void end() {
		Subsystems.claw.stop();
		Subsystems.drive.move(0.0, 0.0);
		Subsystems.lift.stop();
	}
	protected void interrupted() {}
}
