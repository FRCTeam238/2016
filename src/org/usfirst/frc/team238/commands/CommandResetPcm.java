package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.Intake;

public class CommandResetPcm extends AbstractCommand {
	
	Intake myIntake;
	
	
	public CommandResetPcm(Intake theIntake)
	{
		this.myIntake = theIntake;
		
	}
	
	public void prepare(){
		
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		myIntake.resetPCM();
	}


	//@Override
	public void execute(double overRideValue) {
		// TODO Auto-generated method stub

	}

	 
	public boolean complete()
	{
		 return myIntake.complete();
	}
}
