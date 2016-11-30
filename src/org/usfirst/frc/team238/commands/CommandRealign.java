package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandRealign extends AbstractCommand {
	
	Drivetrain myDrivetrain;
	Navigation myNavigation;
	
	boolean debug;
	double currentYaw;

	double motorValue;
	
	public CommandRealign(Drivetrain theDrivetrain, Navigation theNavigation) {
		// TODO Auto-generated constructor stub
		this.myDrivetrain = theDrivetrain;
		this.myNavigation = theNavigation;
		this.debug = SmartDashboard.getBoolean("Debug");
	}
	
	public void prepare() {
		// TODO Auto-generated method stub

	}
	
	public void setParams(String params[])
	{

		if ((params[0] != null) || (!params[0].isEmpty())){
			motorValue = Double.parseDouble(params[0]);
		}
		else {
			motorValue = 1;
		}
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		currentYaw = myNavigation.getYaw();
		
		if(currentYaw > 1)
		{
			myDrivetrain.turnLeft(motorValue, motorValue);
		}
		else if(currentYaw < -1)
		{
			myDrivetrain.turnRight(motorValue, motorValue);
		}
		else
		{
			
		}
	}
	
	public boolean done()
	{
		boolean isDone;
		
		if(currentYaw <= 1 && currentYaw >= -1)
		{
			isDone = true;
		}
		
		else
		{
			isDone = false;
		}
		
		return isDone;
	}


}
