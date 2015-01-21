package org.usfirst.frc2264.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SwitchSubsystem extends Subsystem {
	private DigitalInput trigger;
	
    public SwitchSubsystem(int port) {
    	this.trigger = new DigitalInput(port);
    }
    
    public void initDefaultCommand() {
    	this.setDefaultCommand(null);
    }
    public boolean getState() {
    	return this.trigger.get();
    }
    public void debugPollAllSwitches() {
    	if(trigger.get())
    		throw new RuntimeException("!");
    }
}
