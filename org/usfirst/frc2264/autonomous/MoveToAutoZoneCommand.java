package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.commands.CommandBase;
import org.usfirst.frc2264.subsystems.Subsystems;

public class MoveToAutoZoneCommand extends CommandBase {
	
	public MoveToAutoZoneCommand() {
		super("Autonmous > Move to Auto Zone");
		this.requires(Subsystems.drive);
	}

	protected void initialize() {
		this.setTimeout(5.0);
		Subsystems.drive.move(0.0, -0.2, 0.0);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
				
	}

	protected void interrupted() {
		
	}
	
}
