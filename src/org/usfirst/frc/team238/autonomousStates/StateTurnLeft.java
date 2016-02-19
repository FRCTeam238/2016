package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.commands.CommandTurnLeft;
import org.usfirst.frc.team238.robot.Navigation;
import com.kauailabs.navx.frc.AHRS;

public class StateTurnLeft implements AutonomousState {

	CommandTurnLeft turnLeftCommand;
	CommandController theMCP;
	String parameters[];
	
	double currentYaw;
	int count;
	//double newTargetYaw = -45; //For turning Left however many degrees uses negatives
	
	@Override
	public void prepare()
	{
		turnLeftCommand.setParams(parameters);
		turnLeftCommand.prepare();
		
	}
	
	
	@Override
	public void init(String params[], CommandController theMcp) {
		
		turnLeftCommand = (CommandTurnLeft) theMcp.getAutoCmd("CommandTurnLeft");
		parameters = params;
	
	}
	
	@Override
	public void process() {
		
		System.out.println("StateTurnLeft.Process()" + String.valueOf(count));
		count++;
		turnLeftCommand.execute();
		
	}
	
	@Override
	public boolean done() {
		
		if(turnLeftCommand.done())
		{
			count=0;
			return true;
		
		}
		
		return false;
	
	}

	@Override
	public void reset() {
	
		//turnLeftCommand.reset();
	
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		
	}

}
