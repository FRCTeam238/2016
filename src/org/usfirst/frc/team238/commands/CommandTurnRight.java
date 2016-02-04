package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.ControlBoard;
import org.usfirst.frc.team238.robot.CrusaderCommon;

import edu.wpi.first.wpilibj.RobotDrive;

public class CommandTurnRight implements Command  {

	RobotDrive myRobotDrive;
	public CommandTurnRight (RobotDrive robotDrive)
	{
		this.myRobotDrive = robotDrive;
	}


	public void execute()   {
		double leftJsValue = CrusaderCommon.LIFT_GOES_UP_NORMAL;

		double rightJsValue = CrusaderCommon.LIFT_GOES_DOWN_NORMAL;

		myRobotDrive.tankDrive(leftJsValue, rightJsValue);
	}
}
