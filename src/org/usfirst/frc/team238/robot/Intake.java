package org.usfirst.frc.team238.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake {

	Encoder intakeEncoder;
	CANTalon intakeMotor;
	Solenoid intakeSolenoid;
	
	
	public void Init()
	{
		intakeEncoder = new Encoder(4,5);
		intakeMotor = new CANTalon(5);
		intakeSolenoid = new Solenoid(0);
	}
	//Starts the intake pulling balls in.
	public void rotateCollectorIn()  
	{
		intakeMotor.set(CrusaderCommon.INTAKE_MOTOR_ROTATE_IN);
	}
	//Starts the intake pushing balls out.
	public void rotateCollectorOut() 
	{
		intakeMotor.set(CrusaderCommon.INTAKE_MOTOR_ROTATE_OUT);
	}
	
	//Stops the intake motor.
	public void stopCollector() 
	{
		intakeMotor.set(CrusaderCommon.INTAKE_MOTOR_OFF);
	}
	
	public void deployCollector() 
	{
		intakeSolenoid.set(true);
	}
	//Closes intake to prevent aquiring more than one ball. Use as soon as you aquire a ball.
	public void retractCollector() 
	{
		intakeSolenoid.set(false);
	}
	//Detects whether or not we have a ball in the holder.
	public void detectBall() 
	{
		
	}
	// For autonomous so it can set any 
	public void setParams()
	{
		
	}
	 // The autonomous query will check and see if the action is completed.
	public boolean complete()
	{
		 return false;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
