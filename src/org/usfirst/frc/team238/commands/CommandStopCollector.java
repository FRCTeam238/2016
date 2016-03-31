package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.Intake;
import org.usfirst.frc.team238.robot.Hanger;

public class CommandStopCollector implements Command {
	
	Intake myIntake;
	Hanger myHanger;
	
	public CommandStopCollector(Intake theIntake, Hanger theHanger)
	{
		this.myIntake = theIntake;
		this.myHanger = theHanger;
	}

	public void prepare(){
		
		
		
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		myIntake.stopCollector();
		myHanger.pauseTheBeamingScotty();
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
