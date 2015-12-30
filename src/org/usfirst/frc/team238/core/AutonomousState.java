package org.usfirst.frc.team238.core;


public interface AutonomousState {
	
	public void init();
	public void init(int value, CommandController theMcp);
	public void process();
	public boolean done();
}
