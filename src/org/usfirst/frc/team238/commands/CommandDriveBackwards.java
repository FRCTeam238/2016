package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.Drivetrain;

public class CommandDriveBackwards implements Command{

	Drivetrain myRobotDrive;

	double motorValue;
	int targetValue;

	public CommandDriveBackwards(Drivetrain robotDrive) {
		this.myRobotDrive = robotDrive;

	}
	
	public void execute() {
		myRobotDrive.driveBackwards(motorValue, motorValue);

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

	public boolean done(){

		boolean isDone = false;
		int amountOfTicks = myRobotDrive.getEncoderTicks();
		if (amountOfTicks > targetValue){
			isDone = true;
		}

		else{
			isDone = false;
		}
		return isDone;
	}
	
	
}
