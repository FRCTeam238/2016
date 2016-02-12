package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.commands.CommandTurnRight;
import org.usfirst.frc.team238.robot.Navigation;

public class StateTurnRight implements AutonomousState {

	CommandTurnRight turnRightCommand;
	CommandController theMCP;
	Navigation myNavigation;
	
	int count;
	int turnRightDegree = 45; //For turning Right however many degrees uses positives
	
	@Override
	public void prepare()
	{
	
		//Get current nav coordinates
		//Give nav the turnleftdegrees
		
	}
	
	@Override
	public void init(String params[], CommandController theMcp) {
		
		myNavigation.setTargetValues(turnRightDegree);
		turnRightCommand = (CommandTurnRight) theMcp.getAutoCmd("CommandTurnRight");
		turnRightCommand.setParams(params);
		
	}
	
	@Override
	public void process() {
		System.out.println("StateTurnRight.Process()" + String.valueOf(count));
		count++;
		turnRightCommand.execute();
	}
	
	@Override
	public boolean done() {
		
		if(turnRightCommand.done())
		{
			
			return true;
			
		}
		
		return false;
		
	}

	@Override
	public void reset() {
		
	//	turnRightCommand.reset();
		
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
			
		
	}

}