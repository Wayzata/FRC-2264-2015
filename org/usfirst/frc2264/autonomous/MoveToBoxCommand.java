package org.usfirst.frc2264.autonomous;

import java.util.Optional;

import org.usfirst.frc2264.commands.CommandBase;
import org.usfirst.frc2264.subsystems.Subsystems;
import org.usfirst.frc2264.subsystems.VisionSubsystem.BoxParams;

public class MoveToBoxCommand extends CommandBase {
	private Optional<BoxParams> params;
	private static final Double FORWARD_SPEED = 0.2;
	private static final Double TURN_SPEED = 0.2;
	
    public MoveToBoxCommand() {
    	super("Autonomous > Move To Box");
        this.requires(Subsystems.drive);
        this.requires(Subsystems.vision);
    }
    
    protected void initialize() {
    }
    protected void execute() {
    	this.params = Subsystems.vision.poll();
    	if(!this.params.isPresent()) Subsystems.drive.turn(0.25);
    	else if(this.params.get().relativeCentre > 5) Subsystems.drive.move(0.0, FORWARD_SPEED, TURN_SPEED);
		else if(this.params.get().relativeCentre < -5) Subsystems.drive.move(0.0, FORWARD_SPEED, -TURN_SPEED);
		else Subsystems.drive.move(0.0, FORWARD_SPEED, 0.0);
    }
    protected boolean isFinished() {
        return this.params.get().particleWidth >= 320;
    }
    protected void end() {
    	Subsystems.drive.stop();
    }
    protected void interrupted() {
    }
}
