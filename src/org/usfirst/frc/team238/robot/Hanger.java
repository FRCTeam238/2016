package org.usfirst.frc.team238.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;

public class Hanger {
	
	Solenoid deploySolenoid;
	CANTalon hangTalonOne;
	CANTalon hangTalonTwo;
	DriverStation theDriverstation;
	
	boolean matchTimeTest;

	public Hanger() {
		theDriverstation = DriverStation.getInstance();
		
		
	}
	
	public void init()
	{
		hangTalonOne = new CANTalon(3);
		hangTalonTwo = new CANTalon(4);
		deploySolenoid = new Solenoid(3);
	}
	
	public void releaseTheHounds()
	{
		
		
		matchTimeTest = SmartDashboard.getBoolean("Match Time Flag");
		
		if(matchTimeTest == false)
		{
			if(theDriverstation.getMatchTime() < 30)
			{
				deploySolenoid.set(true);
			}
		}
		else
		{
			deploySolenoid.set(true);
		}
		
		
	
	}
	
	public void beamMeUpScotty()
	{
		
		matchTimeTest = SmartDashboard.getBoolean("Match Time Flag");
		
		if(matchTimeTest == false)
		{
			if(theDriverstation.getMatchTime() < 30)
			{
				hangTalonOne.set(CrusaderCommon.INTAKE_MOTOR_ROTATE_IN);
				hangTalonTwo.set(CrusaderCommon.INTAKE_MOTOR_ROTATE_IN);
			}
		}
		else
		{
			hangTalonOne.set(CrusaderCommon.INTAKE_MOTOR_ROTATE_IN);
			hangTalonTwo.set(CrusaderCommon.INTAKE_MOTOR_ROTATE_OUT);
		}
		
		
		
	}
	
	public void pauseTheBeamingScotty()
	{
		
		matchTimeTest = SmartDashboard.getBoolean("Match Time Flag");
		
		if(matchTimeTest == false)
		{
			if(theDriverstation.getMatchTime() < 30)
			{
				hangTalonOne.set(CrusaderCommon.INTAKE_MOTOR_OFF);
				hangTalonTwo.set(CrusaderCommon.INTAKE_MOTOR_OFF);
			}
		}
		else
		{
			hangTalonOne.set(CrusaderCommon.INTAKE_MOTOR_OFF);
			hangTalonTwo.set(CrusaderCommon.INTAKE_MOTOR_OFF);
		}
		
	}

}
