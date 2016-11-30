package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.Intake;

public class CommandCollectorOut extends AbstractCommand {

	Intake myIntake;
	int counter;
	
	public CommandCollectorOut (Intake theIntake)
	{
		this.myIntake = theIntake;
	}
	
	public void prepare(){
		
		
		
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		myIntake.rotateCollectorOut();
		counter++;
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
		if(counter == 10)
		{
			myIntake.stopCollector();
			counter = 0;
			return true;
		}
		else
		{
			return false;
		}
		
	}
}
