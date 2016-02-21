
package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandDriveForward implements Command {

	Drivetrain myRobotDrive;
	Navigation myNavigation;

	double motorValue;
	double targetValue;
	double debug;
	double rollValue;
	
	public CommandDriveForward(Drivetrain robotDrive, Navigation myNav) {
		this.myRobotDrive = robotDrive;
		this.myNavigation = myNav;
		this.debug = SmartDashboard.getNumber("Debug");
	}
	
	public void prepare(){
		
		myRobotDrive.resetEncoders();
		System.out.println("CommandDriveForward.prepare");
		
	}
	
	public void execute() {
		myRobotDrive.driveForward(motorValue, motorValue);
	}
	
	public void setParams(String params[])
	{

		if ((params[0] != null) || (!params[0].isEmpty())){
			targetValue = Double.parseDouble(params[0])*4560;
		}
		else {
			targetValue = 0;
		}

		if ((params[1] != null) || (!params[1].isEmpty())){
			motorValue = Double.parseDouble(params[1]);
		}
		else {
			motorValue = 1;
		}
		
		if ((params[2] != null) || (!params[2].isEmpty())){
			rollValue = Double.parseDouble(params[2]);
		}
		else {
			rollValue = 0;
		}

	}
	 
	public boolean done()
	{
		boolean isDone = false;
		double amountOfTicks;

		debug = SmartDashboard.getNumber("Debug");
		
		/*if(debug == 1)
		{
			amountOfTicks = myRobotDrive.getEncoderCount(1);
		}
		else 
		{
			
		}*/
		double currnetRollValue = myNavigation.roll();
		amountOfTicks = myRobotDrive.getEncoderTicks();
		System.out.println("Target Value = " + targetValue + " Amount Of Ticks = " + amountOfTicks);
		System.out.println("RollValue : " + rollValue + "CurrentRollValue : " + currnetRollValue);
		
		if (rollValue > 0)
		{
			if ( (currnetRollValue >= rollValue) && (amountOfTicks > 9000))
			{
				isDone = true;
				myRobotDrive.driveForward(0, 0);
	
			}
			else
			{
				isDone = false;
			}
		}
		else
		{
			if (amountOfTicks > targetValue)
			{
				isDone = true;
				myRobotDrive.driveForward(0, 0);
	
			}
			else
			{
				isDone = false;
			}
		}
		return isDone;
	}

}
