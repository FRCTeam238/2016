package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.commands.CommandDriveForward;
import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StateDriveForward implements AutonomousState {

	CommandDriveForward DriveForwardCommand;
	int count = 0;
	String parameters[];
	
	@Override
	public void prepare()
	{
		DriveForwardCommand.setParams(parameters);
		DriveForwardCommand.prepare();
		//Logger.logString(parameters);
		
	}
	
	@Override
	public void init(String params[], CommandController theMcp)
	{
		//get specific  objects needed for creating the command object 
		DriveForwardCommand = (CommandDriveForward) theMcp.getAutoCmd("CommandDriveForward");
		parameters = params;
	}
	
	@Override
	public void process() {
		Logger.logInt("StateDriveForward.Process()  ",
				count);
		count++;
		DriveForwardCommand.execute();
	}
	
	/*public void dumpToDashboard()
	{
		SmartDashboard.putString("DriveFwdParam1", parameters[0]);
		SmartDashboard.putString("DriveFwdParam2", parameters[1]);
		SmartDashboard.putString("DriveFwdParam3", parameters[2]);
	}*/

	@Override
	public boolean done() {
		if(DriveForwardCommand.done())
		{
			count=0;
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
