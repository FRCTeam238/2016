package org.usfirst.frc.team238.robot;


import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;



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
	
	public void init()
	{
		//Have to find the values for Encoders- HS
		
		shifterSolenoid = new Solenoid (0);
		//did we change the encoder number??
		counter = 0;



	}
	public int getEncoderTicks()
	{
		encoderLeft = leftFrontDrive.getEncPosition();
		encoderRight = rightFrontDrive.getEncPosition();
		return encoderLeft;
		
	}
	
	public int getEncoderCount(int count)
	{
		counter++;
		return counter;
		
	}
	
	public void shiftHigh()
	{
		shifterSolenoid.set(CrusaderCommon.SHIFTER_HIGH_GEAR);
		System.out.println("!!!!!!!!!!DEBUGHIGH!!!!!!!!!!");
	}
	
	public void shiftLow()
	{
		shifterSolenoid.set(CrusaderCommon.SHIFTER_LOW_GEAR);
		System.out.println("!!!!!!!!!!DEBUGLOW!!!!!!!!!!");
	}
	
	public void driveForward(double leftMotorValue, double rightMotorValue)  {
		
		robotMotors.tankDrive(leftMotorValue, rightMotorValue);	
	}
	
	public void driveBackwards(double leftMotorValue, double rightMotorValue)  {
		
		robotMotors.tankDrive(leftMotorValue * -1, rightMotorValue * -1);
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


















