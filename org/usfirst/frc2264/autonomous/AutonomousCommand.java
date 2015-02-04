package org.usfirst.frc2264.autonomous;

import org.usfirst.frc2264.commands.CommandBase;
import org.usfirst.frc2264.subsystems.Subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The command that runs when we start autonomous mode.
 */
public class AutonomousCommand extends CommandBase {
	public AutonomousCommand() {
		super("Autonomous");
		this.requires(Subsystems.vision);
	}
	protected void initialize() {
		System.out.println("Autonomous mode started.");
	}
	protected void execute() {
		Subsystems.vision.repollCamera();
		boolean canSeeBox = Subsystems.vision.canSeeBox();
		SmartDashboard.putNumber("Last Frame Timestamp", Timer.getFPGATimestamp());
		SmartDashboard.putBoolean("Can See Box", canSeeBox);
		if(canSeeBox) {
			SmartDashboard.putNumber("Distance", Subsystems.vision.getBoxDistance());
			SmartDashboard.putString("Direction", "TODO");
		}
	}
	protected void end() {
		System.out.println("Ended autonomous mode.");
	}
	
	protected boolean isFinished() {
		return false; // TODO Add end condition
	}
	
	protected void interrupted() {
		this.end();
	}
}
 