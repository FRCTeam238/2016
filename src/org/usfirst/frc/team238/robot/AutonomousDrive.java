package org.usfirst.frc.team238.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;

public class AutonomousDrive {
	RobotDrive autoRobotDrive;
	Timer autonomousTimer;
	Encoder autonomousDriveEncoder;
	
	double autonomousDriveValue; 
	
	public AutonomousDrive(RobotDrive theRobotDrive)
	{
		this.autoRobotDrive = theRobotDrive;
	}
	
	public void init()
	{
		autonomousTimer = new Timer();
		autonomousTimer.reset();
		//autonomousTimer.start();
		actionComplete = false;
		autonomousDriveEncoder = new Encoder(8,9);
		autonomousDriveValue = autonomousDriveEncoder.get();
	}

	/*CAT Need to plan for two types of actions:
	 *   1. drive forward for three seconds
	 *   2. turn to the right 90 degrees
	 *   
	 *   Currently, both are implemented with the timer that runs for 3 seconds
	 *   before reporting action complete
	 *   
	 */
	boolean actionComplete = false;
	public void resetAction()
	{
		actionComplete = false;
	}
	
	public boolean isActionComplete()
	{
		if (!actionComplete)
		{
			actionComplete = autonomousTimer.get() > 3;
		}
		return actionComplete;
	}
	
	public void killTimer()
	{
		autonomousTimer.stop();
		//autonomousTimer.reset();
	}
	//Reset sets the encoder to zero. Get gets the current count
	public void startTick()
	{
		autonomousDriveEncoder.reset();
		autonomousDriveEncoder.get();
		

	}
	
	public void forward()
	{
		//CAT consider making forward, backward, turn left, turn right dumber and let ...
		//CAT ... the caller decide whether to drive or not, no matter what the timer is ...
		//CAT ... saying
		
	
		if(autonomousDriveEncoder.get() < autonomousDriveValue + CrusaderCommon.AUTO_DRIVE_LIMIT )
		{
			SmartDashboard.putString("AutonomousDrive", "forward");
			autoRobotDrive.tankDrive(CrusaderCommon.AUTO_DRIVE_FORWARD, CrusaderCommon.AUTO_DRIVE_FORWARD);
		}
		else
		{
			idle();
		}
	}

	public void backward()
	{
		if(autonomousDriveEncoder.get() > autonomousDriveValue - CrusaderCommon.AUTO_DRIVE_LIMIT)
		{
			SmartDashboard.putString("AutonomousDrive", "backward");
			autoRobotDrive.tankDrive(CrusaderCommon.AUTO_DRIVE_BACKWARD, CrusaderCommon.AUTO_DRIVE_BACKWARD);
		}
		else
		{
			idle();
		}
	}
	
	public void turnRight()
	{
		if(autonomousDriveEncoder.get() < autonomousDriveValue + CrusaderCommon.AUTO_DRIVE_LIMIT )
		{
			SmartDashboard.putString("AutonomousDrive", "turn right");
			autoRobotDrive.tankDrive(CrusaderCommon.AUTO_DRIVE_FORWARD, CrusaderCommon.AUTO_DRIVE_BACKWARD);
		}
		else
		{
			idle();
		}
	}
	
	public void turnLeft()
	{
		if(autonomousDriveEncoder.get() > autonomousDriveValue - CrusaderCommon.AUTO_DRIVE_LIMIT)
		{
			SmartDashboard.putString("AutonomousDrive", "turn left");
			autoRobotDrive.tankDrive(CrusaderCommon.AUTO_DRIVE_BACKWARD, CrusaderCommon.AUTO_DRIVE_FORWARD);
		}
		else
		{
			idle();
		}
	}
	
	public void idle()
	{
		SmartDashboard.putString("AutonomousDrive", "idle");
		autoRobotDrive.tankDrive(CrusaderCommon.AUTO_DRIVE_IDLE, CrusaderCommon.AUTO_DRIVE_IDLE);
	}
}
