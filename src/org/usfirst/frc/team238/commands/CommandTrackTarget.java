package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;
import org.usfirst.frc.team238.robot.Vision;

public class CommandTrackTarget implements Command {

	Drivetrain myRobotDrive;
	Navigation myNavigation;
	Vision myVision;
	
	
	
	
	
	public CommandTrackTarget(Drivetrain robotDrive, Navigation myNavigationForTarget, Vision theVision) {
		// TODO Auto-generated constructor stub
		this.myNavigation = myNavigationForTarget;
		this.myRobotDrive = robotDrive;
		this.myVision = theVision;
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		myNavigation.resetNAVX();
	}

	@Override
	public void setParams() {
		// TODO Auto-generated method stub
		 double[] visionAngle;
		 
		 visionAngle = myVision.getTheData();
		 
		 myNavigation.setTargetValues(visionAngle[CrusaderCommon.VISION_ANGLE_SLOT]);
		 
		 
		 
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		
		if(myNavigation.areWeThereYet() == true)
		{
			myRobotDrive.driveForward(0, 0);                                                               
			Logger.logString("We Have Aligned!");
		}
		
		return false;
	}

}
