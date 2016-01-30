//CAT Changing this comment - please remove on next commit
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
			myRobotDrive.startTick();
			AUTO_STARTED = true;
		}
	}

	public void execute(double overRideValue) {
	

	}
	
	public void setParams(String params[])
	{
		int value = Integer.parseInt(params[0]);
		
		myRobotDrive.setParam1(value);
	}
	 
	public boolean complete()
	{
		 return myRobotDrive.isActionComplete();
	}
	
	public void reset() {
		
		AUTO_STARTED = false;
		myRobotDrive.killTimer();

	}

}
