package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.ControlBoard;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;

public class CommandTurnRight implements Command  {

	Drivetrain myRobotDrive;
	Navigation myNavigation;
	
	double motorValue;
	int targetValue;
	double newTargetYaw;
	
	public CommandTurnRight (Drivetrain robotDrive, Navigation myNavigationForTarget)
	{
		
		this.myRobotDrive = robotDrive;
		this.myNavigation = myNavigationForTarget;
		myNavigation.setTargetValues(newTargetYaw);
		
	}


	public void execute()   {
		//Using -motorValues to spin the right motors backwards
		//If that's how it works lol Maybe change this
		myRobotDrive.turnRight(motorValue, -motorValue);
	}
	
	public void setParams(String params[])
	{
		
		if ((params[0] != null) || (!params[0].isEmpty())){
			targetValue = Integer.parseInt(params[0]);
		}
		else {
			targetValue = 0;
		}

		if ((params[1] != null) || (!params[1].isEmpty())){
			motorValue = Integer.parseInt(params[1]);
		}
		else {
			motorValue = 1;
		}
		
		if((params[2] != null) || (!params[2].isEmpty())){
			newTargetYaw = Integer.parseInt(params[2]);
			
		} else {
			newTargetYaw = 0; //Don't turn if there's no input
			
		}
		
	}

	public boolean done()
	{
		if (myNavigation.areWeThereYet() == true){
			return true;
		}

		else{
			return false;
		}

	}
}
