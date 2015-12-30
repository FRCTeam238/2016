package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.AutonomousDrive;

public class CommandDriveForward implements Command {

	AutonomousDrive myRobotDrive;
	boolean AUTO_STARTED;
	
	public CommandDriveForward(AutonomousDrive theRobotDrive)
	{
		this.myRobotDrive = theRobotDrive;
		AUTO_STARTED = false;
	}
	
	public void execute() 
	{
		if(AUTO_STARTED)
		{
			myRobotDrive.forward();
		}
		else
		{
			
			AUTO_STARTED = true;
		}
	}

	public void execute(double overRideValue) {
	

	}
	
	public void setParams()
	{
		
	}
	 
	public boolean complete()
	{
		 return myRobotDrive.isActionComplete();
	}

}
