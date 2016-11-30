package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.commands.CommandCollectorDeploy;
import org.usfirst.frc.team238.commands.CommandCollectorRetract;
import org.usfirst.frc.team238.core.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team238.commands.CommandCollectorIn;

public class StateCollectorIn {

	CommandCollectorRetract collectorRetract;
	CommandCollectorDeploy collectorDeploy;
	CommandCollectorIn collectorIntake;
	String parameters[];
	
	//@Override
	public void process () {
		
		collectorIntake.execute();
		Logger.logString("Rotating In.....");
		
	}
	
	//@Override
	public boolean done() {
		
		if(collectorIntake.complete()){
			
			Logger.logString("Rotating In: DONE");
			
			return true;
			
		}else{
			
			return false;
			
		}
		
	}
	
	public void showParams() {
		
		SmartDashboard.putString("Param 1 - targetValue","0");
		SmartDashboard.putString("Param 2 - motorSpeed", "0");
		SmartDashboard.putString("Param 3 - rollValue", "0");
		SmartDashboard.putString("Param 4 - ultrasonicTarget", "0");
	}
	public void updateParams() {
		// TODO Auto-generated method stub
		
	}

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
