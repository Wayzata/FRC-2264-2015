package org.usfirst.frc2264.commands;

import org.usfirst.frc2264.misc.ClawDirection;
import org.usfirst.frc2264.subsystems.Subsystems;

public class ClawCommand extends CommandBase {
	public ClawDirection direction;
	public double speed;
	
    public ClawCommand(ClawDirection direction) {
    	this(direction, 0.5);
    }
    public ClawCommand(ClawDirection direction, double speed) {
    	super("Claw " + (direction == ClawDirection.OPEN ? "Open" : "Closed"));
    	this.requires(Subsystems.claw);
    	this.requires(Subsystems.clawTactileSwitch);
    	this.direction = direction;
    	if(speed <= 0.0) throw new IllegalArgumentException();
    	this.speed = speed;
    }

    protected void initialize() {
    	Subsystems.claw.setSpeed(this.speed * (this.direction == ClawDirection.OPEN ? 1 : -1));
    }
    protected void execute() {}

    protected boolean isFinished() {
    	if(this.direction == ClawDirection.OPEN)
    		return false; // TODO
    	else
    		return Subsystems.clawTactileSwitch.getState();
    }
    protected void end() {
    	Subsystems.claw.stop();
    }
    protected void interrupted() { this.end(); }
}
