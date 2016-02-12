package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.commands.CommandDriveForward;
import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;

public class StateDriveForward implements AutonomousState {

	CommandDriveForward DriveForwardCommand;
	int count = 0;
	
	@Override
	public void prepare()
	{
		
		
		
	}
	
	@Override
	public void init(String params[], CommandController theMcp)
	{
		//System.out.println("StateDriveForward = "
		//		+ String.valueOf(howfar));
		//get specific  objects needed for creating the command object 
		DriveForwardCommand = (CommandDriveForward) theMcp.getAutoCmd("CommandDriveForward");
		DriveForwardCommand.setParams(params);
	}
	
	@Override
	public void process() {
		System.out.println("StateDriveForward.Process()  "
				+ String.valueOf(count));
		count++;
		DriveForwardCommand.execute();
	}

	@Override
	public boolean done() {
		if(DriveForwardCommand.done())
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	public void reset(){
		
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
