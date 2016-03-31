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
	boolean houndsOnTheLoose;
	public Hanger() {

		theDriverstation = DriverStation.getInstance();
	}
	
	public void init()
	{
		hangTalonOne = new CANTalon(3);
		hangTalonTwo = new CANTalon(4);
		deploySolenoid = new Solenoid(3);
		houndsOnTheLoose = false;
		matchTimeTest = false;
	}
	
	public void releaseTheHounds()
	{
		matchTimeTest = SmartDashboard.getBoolean("Match Time Flag");
		
		if(matchTimeTest == false)
		{
			if((theDriverstation.getMatchTime() < 30) && (ControlBoard.canWeReleaseTheHounds())) 
			{
				deploySolenoid.set(true);
				houndsOnTheLoose = true;
			}
		}
		else
		{
			if(ControlBoard.canWeReleaseTheHounds())
			{
				deploySolenoid.set(true);
				houndsOnTheLoose = true;
			}
		}
	
	}
	
	public void beamMeUpScotty()
	{
		
		matchTimeTest = SmartDashboard.getBoolean("Match Time Flag");
		
		if(houndsOnTheLoose == true)
		{
			if(matchTimeTest == false)
			{
				//mjf since there is a check in release the hounds for getMatchTime and we don't execute unless the hounds are loose so we don't need one here
				
				//Code for no JS
				//hangTalonOne.set(CrusaderCommon.INTAKE_MOTOR_ROTATE_IN);
				//hangTalonTwo.set(CrusaderCommon.INTAKE_MOTOR_ROTATE_OUT);
				
				//code for two JS
				hangTalonOne.set(ControlBoard.getOperatorLeftJs().getY());
				hangTalonTwo.set(ControlBoard.getOperatorRightJs().getY() * -1);
				
				//code for one JS
				//hangTalonOne.set(ControlBoard.getOperatorRightJs().getY());
				//hangTalonTwo.set(ControlBoard.getOperatorRightJs().getX());
				
				//alternative code for one JS
				//hangTalonOne.set(ControlBoard.getHangerLeftSide());
				//hangTalonTwo.set(ControlBoard.getHangerRightide());
				
			}
			else
			{
				//Code for no JS
				//hangTalonOne.set(CrusaderCommon.INTAKE_MOTOR_ROTATE_IN);
				//hangTalonTwo.set(CrusaderCommon.INTAKE_MOTOR_ROTATE_OUT);
			
				//code for two JS
				hangTalonOne.set(ControlBoard.getOperatorLeftJs().getY());
				hangTalonTwo.set(ControlBoard.getOperatorRightJs().getY() * -1);
				
				//code for one JS
				//hangTalonOne.set(ControlBoard.getOperatorRightJs().getY());
				//hangTalonTwo.set(ControlBoard.getOperatorRightJs().getX());
				
				//alternative code for one JS
				//hangTalonOne.set(ControlBoard.getHangerLeftSide());
				//hangTalonTwo.set(ControlBoard.getHangerRightide());
			}
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
