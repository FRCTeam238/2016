package org.usfirst.frc.team238.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*
 * added talon, pid, encoder, joystick to code and intalalized them and added arm to robot.java
 */
public class Arm {
	Talon armTalon;
	PIDController armPID;
	Encoder armEncoder;
	Joystick armJoySlider;
	
	
	double sliderValue, encoderVal;
	
	public void armInit() 
	{
		armTalon = new Talon(4);
		//armEncoder = new Encoder(8,9);  //8 is Channel A, 9 is Channel B
		armJoySlider = ControlBoard.operatorJs; //new Joystick(1); //NO NO NO NO!!!!
		
	}
/* i added slider value to smartdash and inizalized it/
 *  we want to be able to use all the arms to the whole extent of the X axis 
 *  (even thought the slider looks like it is using the Y axis, look at Driver Station)
 *  
 *  the arm will be reading in the value of the slider and transfer that into the 
 *  amount of ticks the encoder needs to spin until it stops.
 *  Most likely there will be some if-else statements in both.
 *  Commenting done by SD and AT(@) */
	public void raiseArm()
	{
		//if the slidervalue equals encoder value, do nothing
		//else go up
		sliderValue = armJoySlider.getX() + 1;
		SmartDashboard.putNumber("sliderValue", sliderValue);
		encoderVal = 0;// armEncoder.getRaw(); //  .getDistance();
		//SmartDashboard.putNumber("armEncoder", encoderVal);
		double deadband = 50;
				
		sliderValue = (sliderValue  * 1750);
		
		if(sliderValue > (encoderVal + deadband) )
		{
			armTalon.set(CrusaderCommon.ARM_MOTOR_SPEED);
		}
		else if(sliderValue < (encoderVal - deadband))
		{
			armTalon.set(CrusaderCommon.ARM_MOTOR_SPEED_REVERSE);
		}
		else
		{
			armTalon.set(CrusaderCommon.MOTOROFF);
		}

	/*	if ((armJoySlider.getX() > 0.2)) //&& (armEncoder.getDistance() < CrusaderCommon.ARM_ENCODER_MAX_VAL))
		{
			armTalon.set(CrusaderCommon.ARM_MOTOR_SPEED);
		}
		else if ((armJoySlider.getX() < -0.2)) //&& (armEncoder.getDistance() > CrusaderCommon.ARM_ENCODER_MIN_VAL))
		{
			armTalon.set(CrusaderCommon.ARM_MOTOR_SPEED_REVERSE);
		}
		else
		{
			armTalon.set(CrusaderCommon.MOTOROFF);
		}
		*/	
	}
	
	public void lowerArm()
	{
		//if the slidervalue equals encoder value, do nothing
		//else go down
		sliderValue = armJoySlider.getX();
		SmartDashboard.putNumber("sliderValue", sliderValue);
		armTalon.set(CrusaderCommon.ARM_MOTOR_SPEED_REVERSE);
	}
	
	
}
