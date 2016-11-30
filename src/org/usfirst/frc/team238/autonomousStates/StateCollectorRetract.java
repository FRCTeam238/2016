package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.core.AutonomousState;

import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.commands.CommandCollectorDeploy;
import org.usfirst.frc.team238.commands.CommandCollectorRetract;
import org.usfirst.frc.team238.core.AutonomousState;

import org.usfirst.frc.team238.core.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StateCollectorRetract implements AutonomousState {
	
	CommandCollectorRetract retractIntake;
	String[] parameters;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(String[] params, CommandController theMcp) {
		// TODO Auto-generated method stub
		retractIntake = (CommandCollectorRetract) theMcp.getOperatorCmd(7);
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		retractIntake.execute();
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		if(retractIntake.complete()){
			
			Logger.logString("The Intake Is Deployed");
			
			return true;
			
		}else{
			
			return false;
			
		}
		
	}
	

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showParams() {
		// TODO Auto-generated method stub
		SmartDashboard.putString("Param 1 - targetValue", "0");
		SmartDashboard.putString("Param 2 - motorSpeed", "0");
		SmartDashboard.putString("Param 3 - rollValue", "0");
		SmartDashboard.putString("Param 4 - ultrasonicTarget", "0");
	}

	@Override
	public void updateParams() {
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
