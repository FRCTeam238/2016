package org.usfirst.frc.team238.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;

public class Hanger {
	
	Solenoid deploySolenoid;
	CANTalon hangTalonOne;
	CANTalon hangTalonTwo;

	public Hanger() {
		
		
		
	}
	
	public void init()
	{
		hangTalonOne = new CANTalon(3);
		hangTalonTwo = new CANTalon(4);
		deploySolenoid = new Solenoid(3);
	}
	
	public void releaseTheHounds()
	{
		deploySolenoid.set(true);
	}
	
	public void beamMeUpScotty()
	{
		hangTalonOne.set(CrusaderCommon.INTAKE_MOTOR_ROTATE_IN);
		hangTalonTwo.set(CrusaderCommon.INTAKE_MOTOR_ROTATE_IN);
	}

}
