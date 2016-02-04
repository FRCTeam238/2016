package org.usfirst.frc.team238.robot;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;


public class Drivetrain {

	Encoder encoderLeft;
	Encoder encoderRight;
	Solenoid shifterSolenoid;
	RobotDrive robotMotors;

	// need turn left turn right
	
	public Drivetrain(RobotDrive theRobotDrive)
	{
		this.robotMotors = theRobotDrive;
	}
	
	public void init()
	{
		//Have to find the values for Encoders- HS
		encoderLeft = new Encoder(1,0);
		encoderRight = new Encoder (3,0);
		shifterSolenoid = new Solenoid (0);
		//did we change the encoder number???



	}
	public int getEncoderTicks(){
		return encoderLeft.get();
	}
	
	public void shiftHigh()
	{
		shifterSolenoid.set(CrusaderCommon.SHIFTER_HIGH_GEAR);
	}
	
	public void shiftLow()
	{
		shifterSolenoid.set(CrusaderCommon.SHIFTER_LOW_GEAR);
	}
	
	public void driveForward(double leftMotorValue, double rightMotorValue)  {
		
		robotMotors.tankDrive(leftMotorValue, rightMotorValue);	
	}
	
	public void driveBackwards(double leftMotorValue, double rightMotorValue)  {
		
		robotMotors.tankDrive(leftMotorValue, rightMotorValue);
	}
	
	public void turnLeft (double leftJsValue, double rightJsValue){
		
		robotMotors.tankDrive(leftJsValue, rightJsValue);
	}
	
	public void turnRight(double leftJsValue, double rightJsValue){
		
		robotMotors.tankDrive(leftJsValue, rightJsValue);
	}
	
	public boolean complete() {
		// TODO Auto-generated method stub
		return false;
	}

}


















