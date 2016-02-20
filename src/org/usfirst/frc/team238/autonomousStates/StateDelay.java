package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.commands.CommandDelay;
import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;

public class StateDelay implements AutonomousState
{
	
	CommandDelay delay;
	String parameters[];
	
	public void init(String params[], CommandController theMcp)
	{
		delay = (CommandDelay) theMcp.getAutoCmd("CommandDelay");
		parameters = params;
	}
	
	int count;
	int targetValue;

	public void process() 
	{
		delay.execute();
		System.out.println("delaying");
	}
	public boolean done()  
	{
		if(delay.done() == true)
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		delay.setParams(Integer.parseInt(parameters[0]));
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}


}

