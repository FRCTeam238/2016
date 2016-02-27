package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.commands.CommandTurnRight;
import org.usfirst.frc.team238.robot.Navigation;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StateTurnRight implements AutonomousState {

	CommandTurnRight turnRightCommand;
	CommandController theMCP;
	String parameters[];
	
	int count;
	
	
	@Override
	public void prepare()
	{
		turnRightCommand.setParams(parameters);
		turnRightCommand.prepare();
		
	}
	
	@Override
	public void init(String params[], CommandController theMcp) {
		

		turnRightCommand = (CommandTurnRight) theMcp.getAutoCmd("CommandTurnRight");
		parameters = params;
		
	}
	
	@Override
	public void process() {
		Logger.logInt("StateTurnRight.Process()" , count);
		count++;
		turnRightCommand.execute();
	}
	
	@Override
	public boolean done() {
		
		if(turnRightCommand.done())
		{
			count = 0;
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

	@Override
	public void showParams() {
		// TODO Auto-generated method stub
		SmartDashboard.putString("Param 1 - targetValue", parameters[0]);
		SmartDashboard.putString("Param 2 - motorSpeed", parameters[1]);
		SmartDashboard.putString("Param 3 - newTargetYaw", parameters[2]);
	}

	@Override
	public void updateParams() {
		// TODO Auto-generated method stub
		
	}

}