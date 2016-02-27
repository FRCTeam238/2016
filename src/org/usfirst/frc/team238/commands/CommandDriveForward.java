
package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandDriveForward implements Command {

	Drivetrain myRobotDrive;
	Navigation myNavigation;

	double motorValue;
	double targetValue;
	boolean debug;
	double rollValue;
	double yawValue;
	double ultrasonicTarget;
	
	public CommandDriveForward(Drivetrain robotDrive, Navigation myNav) {
		this.myRobotDrive = robotDrive;
		this.myNavigation = myNav;
		this.debug = SmartDashboard.getBoolean("Debug");
	}
	
	public void prepare(){
		boolean debug;
		debug = SmartDashboard.getBoolean("Debug");
		
		if(debug == false)
		{
			myRobotDrive.resetEncoders();
		}
		
		Logger.logString("CommandDriveForward.prepare");
		yawValue = myNavigation.getYaw();
		
	}
	
	public void execute() {

		/*double currentYaw;
		double differential = 0;
		double newMotorValue = 0;
		double amountOfTicks;
		
		amountOfTicks = myRobotDrive.getEncoderTicks();
		
		currentYaw = myNavigation.getYaw();
		if(amountOfTicks > 5000)
		{
			differential = currentYaw - yawValue;
			newMotorValue = motorValue + Math.abs(differential);
		}*/
		
		myRobotDrive.driveForward(motorValue, motorValue);
		
		/*Logger.logTwoDouble("Current Yaw : ", currentYaw, "Differential : ", differential);
		Logger.logDouble("New Motor Value : ", newMotorValue);*/
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
		if ((params[3] != null) || (!params[3].isEmpty())){
			ultrasonicTarget = Double.parseDouble(params[3]) * 12;
		}
		else {
			ultrasonicTarget = 0;
		}

	}
	
	
	 
	public boolean done()
	{
		boolean isDone = false;
		double amountOfTicks;
		double currentUltrasonicDistance;

		debug = SmartDashboard.getBoolean("Debug");
		
		/*if(debug == 1)
		{
			amountOfTicks = myRobotDrive.getEncoderCount(1);
		}
		else 
		{
			
		}*/
		double currnetRollValue = myNavigation.getRoll();
		amountOfTicks = myRobotDrive.getEncoderTicks();
		currentUltrasonicDistance = myNavigation.ultrasonicSensor();
		Logger.logTwoDouble("Target Value = " , targetValue , " Amount Of Ticks = " , amountOfTicks);
		Logger.logTwoDouble("RollValue : " , rollValue , "CurrentRollValue : " , currnetRollValue);
		Logger.logTwoDouble("Ultrasonic Target Distance", ultrasonicTarget, "Ultrasonic Current Distance", currentUltrasonicDistance);
		
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
		else if (ultrasonicTarget > 0)
		{
			if (currentUltrasonicDistance < ultrasonicTarget)
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
