package org.usfirst.frc2264.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The command that runs when we start autonomous mode.
 */
public class AutonomousCommandGroup extends CommandGroup {
	public AutonomousCommandGroup() {
		// TODO implement Reding's autonomous plan later cause not useful Brandon is lazy
		this.addSequential(new MoveToBoxCommand());
		this.addSequential(new GrabBoxCommand());
		this.addSequential(new MoveToAutoZoneCommand());
	}
}
 