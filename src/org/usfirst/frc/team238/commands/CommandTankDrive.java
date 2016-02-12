package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.ControlBoard;

import edu.wpi.first.wpilibj.RobotDrive;

public class CommandTankDrive implements Command {

	RobotDrive myRobotDrive; 
	public CommandTankDrive(RobotDrive robotDrive)
	{
		this.myRobotDrive = robotDrive;
	}
	
	@Override
	public void execute() {
		double leftJsValue = 0;
		double rightJsValue = 0;
		leftJsValue = ControlBoard.getDriverLeftJs().getY();
		rightJsValue = ControlBoard.getDriverRightJs().getY();

		myRobotDrive.tankDrive(leftJsValue, rightJsValue);
		
	}

	//@Override
	//public void execute(double overRideValue) {
		// TODO Auto-generated method stub

	}


