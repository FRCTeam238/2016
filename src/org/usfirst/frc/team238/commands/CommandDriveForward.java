
package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.Drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandDriveForward implements Command {

	Drivetrain myRobotDrive;

	double motorValue;
	int targetValue;
	double debug;
	
	public CommandDriveForward(Drivetrain robotDrive) {
		this.myRobotDrive = robotDrive;
		this.debug = SmartDashboard.getNumber("Debug");
	}
	
	public void execute() {
		myRobotDrive.driveForward(motorValue, motorValue);
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

	}
	 
	public boolean done()
	{
		boolean isDone = false;
		int amountOfTicks;

		debug = SmartDashboard.getNumber("Debug");
		
		if(debug == 1)
		{
			amountOfTicks = myRobotDrive.getEncoderCount(1);
		}
		else 
		{
			amountOfTicks = myRobotDrive.getEncoderTicks();
		}
		
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
