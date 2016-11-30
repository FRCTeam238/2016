package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.commands.CommandRealign;

public class StateRealign implements AutonomousState {
	
	CommandRealign RealignCommand;
	int count = 0;
	String parameters[];

	public StateRealign() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void init(String params[], CommandController theMcp) {
		// TODO Auto-generated method stub
		RealignCommand = (CommandRealign) theMcp.getAutoCmd("CommandRealign");
		parameters = params;

	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		
		RealignCommand.setParams(parameters);
		RealignCommand.prepare();
	}


	@Override
	public void process() {
		// TODO Auto-generated method stub
		Logger.logInt("StateRealign.Process()  ", count);
		RealignCommand.execute();
		count++;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		if(RealignCommand.done())
		{
			count = 0;
			return true;
		}
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showParams() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateParams() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getParam(int value) {
			String output = "";
			if(parameters == null||parameters.length-1 < value){
			output="";
		}else{
			output=parameters[value];
		}
		return output;
	}

}
