package org.usfirst.frc.team238.robot;

//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake {

	//Encoder intakeEncoder;
	CANTalon intakeMotor;
	Solenoid intakeDeploySolenoid;
	Solenoid intakeWedgeSolenoid;
	Compressor thePump;
	
	public boolean intakeIsDown;
	
	
	public void Init()
	{
		//intakeEncoder = new Encoder(4,5);
		intakeMotor = new CANTalon(9);
		intakeDeploySolenoid = new Solenoid(1);
		intakeWedgeSolenoid = new Solenoid(2);
		
		thePump = new Compressor();
		
	}
	//Starts the intake pulling balls in.
	
	
	//Stops the intake motor.
	public void stopCollector() 
	{
		intakeMotor.set(CrusaderCommon.INTAKE_MOTOR_OFF);
	}
	
	public void deployCollector() 
	{
		intakeDeploySolenoid.set(true);
		intakeIsDown = true;
	}
	//Closes intake to prevent aquiring more than one ball. Use as soon as you aquire a ball.
	public void retractCollector() 
	{
		intakeDeploySolenoid.set(false);
		intakeIsDown = false;
	}
	//points the top of the wedge outwards
	public void wedgeDeploy()
	{
		intakeWedgeSolenoid.set(true);
	}
	//points the top of the wedge inwards
	public void wedgeRetract()
	{
		intakeWedgeSolenoid.set(false);
	}
	public void rotateCollectorIn()  
	{
		if (intakeIsDown == true)
		{
			intakeMotor.set(CrusaderCommon.INTAKE_MOTOR_ROTATE_IN);
		}
	}
	//Starts the intake pushing balls out.
	public void rotateCollectorOut() 
	{
		if (intakeIsDown == true)
		{
			intakeMotor.set(CrusaderCommon.INTAKE_MOTOR_ROTATE_OUT);
		}
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
		 return true;
	}
	
	public void resetPCM()
	{
		thePump.clearAllPCMStickyFaults(); 
		return ;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
