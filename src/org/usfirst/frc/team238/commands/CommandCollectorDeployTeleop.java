package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.Intake;

public class CommandCollectorDeployTeleop implements Command {
	
	Intake myIntake;
	
	
	public CommandCollectorDeployTeleop(Intake theIntake)
	{
		this.myIntake = theIntake;
		
	}
	
	public void prepare(){
		
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		myIntake.deployCollector();
	}


	//@Override
	public void execute(double overRideValue) {
		// TODO Auto-generated method stub

	}

	 
	public boolean complete()
	{
		 return myIntake.complete();
	}

	@Override
	public void setParams() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}
}
