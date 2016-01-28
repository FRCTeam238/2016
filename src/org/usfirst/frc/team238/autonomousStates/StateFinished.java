package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;

public class StateFinished implements AutonomousState {

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	//used when autonomous is interrupted
	@Override
	public void reset() {
		
	}
	
	@Override
	public void init(String params[], CommandController theMcp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void process() {
		System.out.println("AutonomousMode Done ");

	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
