package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.commands.CommandDriveBackwards;

public class StateDriveBackwards implements AutonomousState {

	CommandDriveBackwards driveBackwards;
	int count = 0;
	double howfar = 1.0; //This is probably not right, lol
	String parameters[];
	
	@Override
	public void prepare()
	{
		driveBackwards.setParams(parameters);
		driveBackwards.prepare();
		
	}
	
	@Override
	public void init(String params[], CommandController theMcp)
	{
		System.out.println("StateDriveForward = "+ String.valueOf(howfar));
		//get specific  objects needed for creating the command object 
		driveBackwards = (CommandDriveBackwards) theMcp.getAutoCmd("CommandDriveBackwards");
		parameters = params;
	}
	
	@Override
	public void process() {
		System.out.println("StateDriveBackwards.Process()  "+ String.valueOf(count));
		count++;
		driveBackwards.execute();
	}

	@Override
	public boolean done() {
		if(driveBackwards.done())
		{
			count=0;
			return true;
		}
		
		return false;
	}

	//used when autonomous is interrupted
	@Override
	public void reset() {
		//driveBackwards.reset();
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
