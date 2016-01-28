package org.usfirst.frc.team238.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Talon;


public class Lift 
{	
	private DigitalInput autoLoadedSwitch;
	
	DigitalInput stopGoingDownSwitch;

	//Declarations of the pistons for the claw
	Solenoid rightFrontPiston;
	Solenoid rightBackPiston;



	//Compressor compress;  May not be needed

	//Switches that tell the elevator(lift?) when to stop
	Encoder liftEncoderLeft;
	Encoder liftEncoderRight;
	
	int currentLiftLevel;  

	//motors to control the lift
	
	Talon toteLiftMotorLeft;
	Talon toteLiftMotorRight;

	double potensOffsetValue = 0.0; //can't Zero a pot so need something to hold what the offset is between the two
	
	double liftEncoderLeftMin; 
	double liftEncoderLeftMax; 
	double liftEncoderRightMin;
	double liftEncoderRightMax;
	
	
	
	
	public void liftInit()
	{
		//initializations of everything
		try{
			autoLoadedSwitch = new DigitalInput(1);
			//stopGoingDownSwitch = new DigitalInput(0);
			

			// The pneumatics are used to clamp onto the new game piece
			rightFrontPiston = new Solenoid(4); 
			rightBackPiston = new Solenoid(1);  


			currentLiftLevel = CrusaderCommon.GROUND_LEVEL; // Maybe?  under revise
			SmartDashboard.putNumber("CurrentLevel: ", currentLiftLevel);

			
			liftEncoderLeft = new Encoder(4,5);
			liftEncoderRight = new Encoder(6,7);
			
			toteLiftMotorLeft = new Talon(6);
			toteLiftMotorRight = new Talon(7);
			
			letItGo();
			
			setMinAndMax(currentLiftLevel);

		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());  
		}
	}
	
	public boolean isBinLoaded()
	{
		boolean retval =  autoLoadedSwitch.get();
		System.out.println("loaded switch=" + retval);
		SmartDashboard.putString("isBinLoaded", retval ? "pressed" : "not pressed");
		return retval;
	}
	
	/*public boolean stopGoingDownBoolean()
	{
		boolean stopGoingDown = stopGoingDownSwitch.get();
		return stopGoingDown;
	}*/
	
	public int getLevel()
	{
		return currentLiftLevel;
	}
	
	public void manualControlOfLifter(double overRideValue)  
	{
		toteLiftMotorLeft.set(overRideValue);
		toteLiftMotorRight.set(overRideValue);
		//liftMotorLeft.set(overRideValue);
		SmartDashboard.putNumber("ManualMode", overRideValue);
		
		if (ControlBoard.resetEncoderValue() == true)
		{
			liftEncoderLeft.reset();
			liftEncoderRight.reset();
		}
		
	}
	//sets min and max values for the encoders NEEDS TO BE UPDATED FOR ENCODERS!!!!!!!!!!
	public void setMinAndMax(int level){
		
		switch(level){
			case CrusaderCommon.GROUND_LEVEL:
				liftEncoderLeftMin = CrusaderCommon.POT_GROUND - CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderLeftMax = CrusaderCommon.POT_GROUND + CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderRightMin = CrusaderCommon.POT_GROUND - CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderRightMax = CrusaderCommon.POT_GROUND + CrusaderCommon.POT_NEUTRAL_ZONE;

				break;
			case CrusaderCommon.TRAVEL_LEVEL:
				liftEncoderLeftMin = CrusaderCommon.POT_TRAVEL - CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderLeftMax = CrusaderCommon.POT_TRAVEL + CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderRightMin = CrusaderCommon.POT_TRAVEL - CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderRightMax = CrusaderCommon.POT_TRAVEL + CrusaderCommon.POT_NEUTRAL_ZONE;

				break;
			case CrusaderCommon.COOP_LEVEL:
				liftEncoderLeftMin = CrusaderCommon.POT_COOP - CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderLeftMax = CrusaderCommon.POT_COOP + CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderRightMin = CrusaderCommon.POT_COOP - CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderRightMax = CrusaderCommon.POT_COOP + CrusaderCommon.POT_NEUTRAL_ZONE;

				break;
			case CrusaderCommon.LOADING_LEVEL:
				liftEncoderLeftMin = CrusaderCommon.POT_LOADING - CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderLeftMax = CrusaderCommon.POT_LOADING + CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderRightMin = CrusaderCommon.POT_LOADING - CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderRightMax = CrusaderCommon.POT_LOADING + CrusaderCommon.POT_NEUTRAL_ZONE;
				break;
			case CrusaderCommon.DELIVER_LEVEL:
				liftEncoderLeftMin = CrusaderCommon.POT_DELIVER - CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderLeftMax = CrusaderCommon.POT_DELIVER + CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderRightMin = CrusaderCommon.POT_DELIVER - CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderRightMax = CrusaderCommon.POT_DELIVER + CrusaderCommon.POT_NEUTRAL_ZONE;
				break;
			default:
				liftEncoderLeftMin = CrusaderCommon.POT_GROUND - CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderLeftMax = CrusaderCommon.POT_GROUND + CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderRightMin = CrusaderCommon.POT_GROUND - CrusaderCommon.POT_NEUTRAL_ZONE;
				liftEncoderRightMax = CrusaderCommon.POT_GROUND + CrusaderCommon.POT_NEUTRAL_ZONE;
				break;
				
		}
		
	}
	
	/* we use the left side pot as the reference, determine the difference between right and left as the value 
	 * to be used as the "offset". Add that value (pos or neg) to the right side every time a get() is done
	 * to essentially "level" them. Then the checks can be done to see if they are getting out of synch  
	 */

	
	/*
	private boolean isAtLevel(double level, double leftPot, double rightPot){
		boolean atLevel = false;
		double leftSide = leftPot - level;
		double rightSide = rightPot- level;
		
		if((Math.abs(leftSide) < CrusaderCommon.POT_DEAD_BAND) || (Math.abs(rightSide) < CrusaderCommon.POT_DEAD_BAND))
		{
			atLevel = true;
		}
		
		return atLevel;
	}
	*/
	
	/*
	 * The jaguars will start going forward and the lift will go up.
	 * 
	 */
	public void liftGoesUp(double differential)  
	{	
		//if the potentiometers readings are more than "X" different we need to adjust motor speed
		if( Math.abs(differential) > CrusaderCommon.POT_DIFF_MAX)
		{
			if(differential < 0)
			{
			
				//left side is above right side so slow it (left?) down
				toteLiftMotorLeft.set(CrusaderCommon.LIFT_GOES_UP_NORMAL);
				toteLiftMotorRight.set(CrusaderCommon.LIFT_GOES_UP_NORMAL);
			}
			else
			{
				//left side is below right side so slow right down
				
				toteLiftMotorLeft.set(CrusaderCommon.LIFT_GOES_UP_NORMAL);
				toteLiftMotorRight.set(CrusaderCommon.LIFT_GOES_UP_NORMAL);
			}
		}
		else
		{
		
			toteLiftMotorLeft.set(CrusaderCommon.LIFT_GOES_UP_NORMAL);
			toteLiftMotorRight.set(CrusaderCommon.LIFT_GOES_UP_NORMAL);
		}
		
		
		SmartDashboard.putNumber("Lift Motor Left", toteLiftMotorLeft.get());
	}
	
	

	
	/*
	 * The jaguars will start going backwards and the lift will go down.
	 */
	public void liftGoesDown(double differential)  
	{	
		//if the potentiometers readings are more than "X" different we need to adjust motor speed
		if( Math.abs(differential) > CrusaderCommon.POT_DIFF_MAX)
		{
			if(differential < 0)
			{
				//left side is above right side so slow right down
				
				toteLiftMotorLeft.set(CrusaderCommon.LIFT_GOES_DOWN_NORMAL);
				toteLiftMotorRight.set(CrusaderCommon.LIFT_GOES_DOWN_NORMAL);
			}
			else
			{
				//left side is below right side so slow left down
			
				toteLiftMotorLeft.set(CrusaderCommon.LIFT_GOES_DOWN_NORMAL);
				toteLiftMotorRight.set(CrusaderCommon.LIFT_GOES_DOWN_NORMAL);
			}
		}
		else
		{
			
			toteLiftMotorLeft.set(CrusaderCommon.LIFT_GOES_DOWN_NORMAL);
			toteLiftMotorRight.set(CrusaderCommon.LIFT_GOES_DOWN_NORMAL);
		}	
		
		
		SmartDashboard.putNumber("Lift Motor Left", toteLiftMotorLeft.get());
		SmartDashboard.putNumber("Lift Motor Right", toteLiftMotorRight.get());
	}

	/*
	 * The jaguars will stop.  
	 * The output will be 0, for the jaguar will be set to zero. 
	 */
	public void stop()
	{
		
		toteLiftMotorLeft.set(CrusaderCommon.LIFT_STOPS);
		toteLiftMotorRight.set(CrusaderCommon.LIFT_STOPS);
	}
	/*This is where the lift goes up and down*/

	private double update(Talon motor, double encoderValue,  double min, double max ) {
		double direction = CrusaderCommon.LIFT_STOPS;
		SmartDashboard.putString("Is THIS working", "Yes It IS");
		
		if( (encoderValue >= min) && (encoderValue <= max))
		{
			motor.set(CrusaderCommon.LIFT_STOPS);
		}
		else if( encoderValue > max)
		{
			//going up
			motor.set(CrusaderCommon.LIFT_GOES_UP_NORMAL);
			direction = CrusaderCommon.LIFT_GOES_UP_NORMAL;
		}
		else 
		{
			//going down
			motor.set(CrusaderCommon.LIFT_GOES_DOWN_NORMAL);
			direction = CrusaderCommon.LIFT_GOES_DOWN_NORMAL;
		}
		//Stops from going down too far
		/*if (stopGoingDownBoolean() == true && (direction == CrusaderCommon.LIFT_GOES_UP_NORMAL))
		{
			motor.set(CrusaderCommon.LIFT_STOPS);
		}*/
		
		return direction;
	}
	
	private void goToSpecifiedLevel(int level){
		

		double loadEncoderValueLeft = liftEncoderLeft.get();
		double loadEncoderValueRight = liftEncoderRight.get() * -1;
		//double differential = loadPotValueLeft -  loadPotValueRight;
		
		setMinAndMax(level);
		
		double leftDirection = update(toteLiftMotorLeft, loadEncoderValueLeft,  liftEncoderLeftMin, liftEncoderLeftMax );
		double rightDirection = update(toteLiftMotorRight, loadEncoderValueRight,  liftEncoderRightMin, liftEncoderRightMax );
	
		
		/* if both update calls come back with stop then set to Loading Level
		 * if both come back other than stop make the call to see if they need balancing
		 * if one is in the stopped state just leave it and the other will catch up
		 */
		if( (leftDirection == CrusaderCommon.LIFT_STOPS) && (rightDirection == CrusaderCommon.LIFT_STOPS)) 
		{
			currentLiftLevel = level;
//			if(currentLiftLevel == CrusaderCommon.GROUND_LEVEL)
//			{
//				liftEncoderLeft.reset();
//				liftEncoderRight.reset();
//			}
		}
		/*else if ((leftDirection != CrusaderCommon.LIFT_STOPS) && (rightDirection != CrusaderCommon.LIFT_STOPS))
		{
			if((leftDirection == CrusaderCommon.LIFT_GOES_UP_NORMAL) || (rightDirection == CrusaderCommon.LIFT_GOES_UP_NORMAL))
			{
				//liftGoesUp(differential);
			}
			else
			{
				//liftGoesDown(differential);
			}
		} //otherwise leave well enough alone.
		*/

		SmartDashboard.putNumber("Level MIN", liftEncoderLeftMin);
		SmartDashboard.putNumber("Level MAX", liftEncoderLeftMax);
		SmartDashboard.putNumber("Left Dir", leftDirection);
		SmartDashboard.putNumber("TargetLevel: ", level);
		SmartDashboard.putNumber("liftEncoderLeft", loadEncoderValueLeft);
		SmartDashboard.putNumber("liftEncoderRight", loadEncoderValueRight);
	}
	/*This is where levels are set*/
	public void setToGround()  
	{
		goToSpecifiedLevel(CrusaderCommon.GROUND_LEVEL);
	}
	
	public void liftToLoadLevel()  
	{
		goToSpecifiedLevel(CrusaderCommon.LOADING_LEVEL);
	}
	
	/*
	 * This method will bring the lift to level 1 from any height
	 * Lift will stop when the travelSwitch is hit
	 * start making a Static final variables
	 */
	public void travelingMode() 
	{
		goToSpecifiedLevel(CrusaderCommon.TRAVEL_LEVEL);
	}
	
	public void setToCoop()
	{
		goToSpecifiedLevel(CrusaderCommon.COOP_LEVEL);
	}
	
	
	public void setToCatch()
	{
		goToSpecifiedLevel(CrusaderCommon.DELIVER_LEVEL);
	}

	public void clampOn()    
	{						 		

		rightFrontPiston.set(true);
		//rightBackPiston.set(true);
		
		//leftFrontPiston.set(true);
		//leftBackPiston.set(true);

	}

	/*
	 *  This signals the right pistons to turn off.
	 *	This read in a button value to see if it is true or not.
	 *  If the button is not pressed, than the solenoids on the right side will turn off.
	 */	
	public void letItGo()   
	{		

		rightFrontPiston.set(false);
		//rightBackPiston.set(false);
		
		//leftFrontPiston.set(false);
		//leftBackPiston.set(false);
	}

	public void UpdateDashboard()
	{
		SmartDashboard.putNumber("CurLevel: ", currentLiftLevel);
		double loadEncoderValueLeft = liftEncoderLeft.get();
		double loadEncoderValueRight = liftEncoderRight.get();

		SmartDashboard.putNumber("Encoder value LEFT", loadEncoderValueLeft);
		SmartDashboard.putNumber("Encoder value RIGHT", loadEncoderValueRight);
	}
}