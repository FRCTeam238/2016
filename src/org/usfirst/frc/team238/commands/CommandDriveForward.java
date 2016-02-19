
package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.Drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandDriveForward implements Command {

	Drivetrain myRobotDrive;

	double motorValue;
	double targetValue;
	double debug;
	
	public CommandDriveForward(Drivetrain robotDrive) {
		this.myRobotDrive = robotDrive;
		this.debug = SmartDashboard.getNumber("Debug");
	}
	
	public void prepare(){
		
		myRobotDrive.resetEncoders();
		
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

	}
	 
	public boolean done()
	{
		boolean isDone = false;
		double amountOfTicks;

		debug = SmartDashboard.getNumber("Debug");
		
		if(debug == 1)
		{
			amountOfTicks = myRobotDrive.getEncoderCount(1);
		}
		else 
		{
			amountOfTicks = myRobotDrive.getEncoderTicks();
		}
		System.out.println("Target Value = " + targetValue + " Amount Of Ticks = " + amountOfTicks);
		if (amountOfTicks > targetValue)
		{
			isDone = true;
			myRobotDrive.driveForward(0, 0);

		}
		else
		{
			isDone = false;
		}
		
		return isDone;
	}

}
