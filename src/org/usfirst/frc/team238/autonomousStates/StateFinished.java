package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;

public class StateFinished implements AutonomousState {

	@Override
	public void prepare()
	{
		
	}
	
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
		Logger.logString("AutonomousMode Done ");

	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showParams() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateParams() {
		// TODO Auto-generated method stub
		
	}

}
