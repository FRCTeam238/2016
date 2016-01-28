package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Lift;

public  class CommandGoToLift implements Command {
	
	Lift myLift;
	
	public CommandGoToLift(Lift theLift)
	{
		this.myLift = theLift;
		
	}
	public void execute()
	{
		myLift.clampOn();
		myLift.liftToLoadLevel();
		
	}
	
	public void execute(double overRideValue) 
	{
		myLift.clampOn();
		myLift.manualControlOfLifter(overRideValue);
		
	}
	
	public boolean complete()
	{
		if(myLift.getLevel() == CrusaderCommon.LOADING_LEVEL)
		{
			return true;
		}
		
		return false;
	}
}