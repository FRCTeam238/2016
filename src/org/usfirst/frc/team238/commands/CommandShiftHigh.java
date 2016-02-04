package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.Drivetrain;


public class CommandShiftHigh implements Command {

	Drivetrain myDrivetrain;
	public CommandShiftHigh(Drivetrain driveTrain)
	{
		this.myDrivetrain = driveTrain;
	}
	
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		myDrivetrain.shiftHigh();
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
		return myDrivetrain.complete();
	}

}
