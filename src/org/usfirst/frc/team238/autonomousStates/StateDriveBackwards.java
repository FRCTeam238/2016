package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team238.commands.CommandDriveBackwards;

public class StateDriveBackwards implements AutonomousState {

	CommandDriveBackwards driveBackwards;
	int count = 0;
	/*PURGE*/
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
		
		driveBackwards = (CommandDriveBackwards) theMcp.getAutoCmd("CommandDriveBackwards");
		parameters = params;
		
	}
	
	@Override
	public void process() {
		Logger.logInt("StateDriveBackwards.Process()  ", count);
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
		
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showParams() {

		for (int i = 0; i < parameters.length; i++)
		{		
			String params = "Parameter" + String.valueOf(i);
			SmartDashboard.putString(params, parameters[i]);
		}
		
	}

	@Override
	public void updateParams() {
		
		for (int i = 0; i < parameters.length; i++)
		{		
			String params = "Parameter" + String.valueOf(i);
			parameters[i] = SmartDashboard.getString(params);
		}
	}

}
