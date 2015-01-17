package org.usfirst.frc2264.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2264.OI;
import org.usfirst.frc2264.subsystems.LiftSubsystem;
import org.usfirst.frc2264.subsystems.ClawSubsystem;
import org.usfirst.frc2264.subsystems.DriveSubsystemCAN;
import org.usfirst.frc2264.subsystems.VisionSubsystem;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 */
public abstract class CommandBase extends Command {
	public static OI oi; // TODO Find out what this is/does.
	
	// Subsystem instantiation.
	public static LiftSubsystem armSubsystem = new LiftSubsystem();
	public static ClawSubsystem clawSubsystem = new ClawSubsystem();
	public static DriveSubsystemCAN driveSubsystem = new DriveSubsystemCAN();
	public static VisionSubsystem visionSubsystem = new VisionSubsystem();
	
	public static void init() {
		// This MUST be here. If the OI creates Commands (which it very likely
		// will), constructing it during the construction of CommandBase (from
		// which commands extend), subsystems are not guaranteed to be
		// yet. Thus, their requires() statements may grab null pointers. Bad
		// news. Don't move it.
		oi = new OI(); // TODO Find out what this is/does.

		// Show what command your subsystem is running on the SmartDashboard
		// SmartDashboard.putData(/* ... */);
	}

	public CommandBase(String name) {
		super(name);
	}

	protected synchronized void requires(Subsystem ... subsystems) {
		for(Subsystem subsystem : subsystems)
			super.requires(subsystem);
	}
}
