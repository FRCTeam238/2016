package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.commands.CommandDriveForward;
import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;

public class StateDriveForward implements AutonomousState {

	CommandDriveForward cmdDrFwd;
	int count = 0;
	@Override
	public void init(String params[], CommandController theMcp)
	{
		//System.out.println("StateDriveForward = "
		//		+ String.valueOf(howfar));
		//get specific  objects needed for creating the command object 
		cmdDrFwd = (CommandDriveForward) theMcp.getAutoCmd("CommandDriveForward");
		cmdDrFwd.setParams(params);
	}
	
	@Override
	public void process() {
		System.out.println("StateDriveForward.Process()  "
				+ String.valueOf(count));
		count++;
		cmdDrFwd.execute();
	}

	@Override
	public boolean done() {
		if(cmdDrFwd.complete())
		{
			return true;
		}
		
		return false;
	}

	//used when autonomous is interrupted
	@Override
	public void reset() {
		cmdDrFwd.reset();
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
