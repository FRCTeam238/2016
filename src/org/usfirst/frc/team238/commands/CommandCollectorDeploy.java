package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.Intake;

public class CommandCollectorDeploy implements Command {
	
	Intake myIntake;
	int deploy;
	
	public CommandCollectorDeploy(Intake theIntake)
	{
		this.myIntake = theIntake;
		
	}
	
	public void prepare(){
		
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Logger.logInt("CommandCollecter.execute deploy = : " , deploy);
		if(deploy == 1)
		{
			myIntake.deployCollector();
		}
		else
		{
			myIntake.retractCollector();
		}
	}

	//@Override
	public void execute(double overRideValue) {
		// TODO Auto-generated method stub

	}
	public void setParams(int param)
	{
		deploy = param;
	}
	 
	public boolean complete()
	{
		 return myIntake.complete();
	}
}
