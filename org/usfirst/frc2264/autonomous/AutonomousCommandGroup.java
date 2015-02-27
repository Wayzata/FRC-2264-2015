package org.usfirst.frc2264.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The command that runs when we start autonomous mode.
 */
public class AutonomousCommandGroup extends CommandGroup {
	public AutonomousCommandGroup() {
//		this.addSequential(new GrabBoxCommand());
		this.addSequential(new MoveToAutoZoneCommand());
	}
}
 