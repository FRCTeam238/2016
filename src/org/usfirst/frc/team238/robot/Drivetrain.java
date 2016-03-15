package org.usfirst.frc.team238.robot;


import org.usfirst.frc.team238.core.Logger;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Drivetrain {

	Solenoid shifterSolenoid;
	RobotDrive robotMotors;
	
	CANTalon leftFrontDrive;
	CANTalon rightFrontDrive;

	int encoderLeft;
	int encoderRight;
	
	int counter;
	
	// need turn left turn right
	
	public Drivetrain(RobotDrive theRobotDrive)
	{
		this.robotMotors = theRobotDrive;
	}
	
	public void init(CANTalon leftFrontDriveTalon, CANTalon rightFrontDriveTalon)
	{
		//Have to find the values for Encoders- HS
		
		leftFrontDrive = leftFrontDriveTalon;
		rightFrontDrive = rightFrontDriveTalon;
		shifterSolenoid = new Solenoid (0);
		
		
		counter = 0;
		leftFrontDrive.configEncoderCodesPerRev(256);
		rightFrontDrive.configEncoderCodesPerRev(256);


	}
	public int getEncoderTicks()
	{
		boolean debug;
		
		debug = SmartDashboard.getBoolean("Debug");
		
		if(debug == false)
		{
			encoderLeft = leftFrontDrive.getEncPosition();
			encoderRight = rightFrontDrive.getEncPosition();
			return encoderRight;
		}
		else 
		{
			
			counter++;
			encoderRight = counter * 1000;
			return encoderRight;
			
		}
		
		
	}
	
	public void resetEncoders(){
		counter=0;
		
		boolean debug;
		
			debug = SmartDashboard.getBoolean("Debug");
		
			if(debug == false)
			{
				leftFrontDrive.setPosition(0);
				rightFrontDrive.setPosition(0);
				
				encoderLeft = leftFrontDrive.getEncPosition();
				encoderRight = rightFrontDrive.getEncPosition();
			}
			else 
			{
				encoderLeft = 0;
				encoderRight = 0;
			}
		

		
		Logger.logTwoInt("ENCODER LEFT : " , encoderLeft , "ENCODER RIGHT : " , encoderRight);
	}
	
	public int getEncoderCount(int count)
	{
		counter++;
		return counter;
		
	}
	
	public void shiftHigh()
	{
		shifterSolenoid.set(CrusaderCommon.SHIFTER_HIGH_GEAR);
		Logger.logString("!!!!!!!!!!DEBUGHIGH!!!!!!!!!!");
	}
	
	public void shiftLow()
	{
		shifterSolenoid.set(CrusaderCommon.SHIFTER_LOW_GEAR);
		Logger.logString("!!!!!!!!!!DEBUGLOW!!!!!!!!!!");
	}
	
	public void driveForward(double leftMotorValue, double rightMotorValue)  {
		
		robotMotors.tankDrive(leftMotorValue, rightMotorValue);	
	}
	
	public void driveBackwards(double leftMotorValue, double rightMotorValue)  {
		
		robotMotors.tankDrive(leftMotorValue * -1, rightMotorValue * -1);
	}
	
	public void turnLeft (double leftJsValue, double rightJsValue){
		
		robotMotors.tankDrive(leftJsValue, rightJsValue * -1);
	}
	
	public void turnRight(double leftJsValue, double rightJsValue){
		
		robotMotors.tankDrive(leftJsValue * -1, rightJsValue);
	}
	
	public boolean complete() {
		// TODO Auto-generated method stub
		return false;
	}

}


















