package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.Intake;

public class CommandStopCollector implements Command {
	
	Intake myIntake; 
	
	public CommandStopCollector(Intake theIntake)
	{
		this.myIntake = theIntake;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		myIntake.stopCollector();
	}

	//@Override
	public void execute(double overRideValue) {
		// TODO Auto-generated method stub

	}
	public void setParams()
	{
		
	}
	 
	public boolean complete()
	{
		 return myIntake.complete();
	}
}
