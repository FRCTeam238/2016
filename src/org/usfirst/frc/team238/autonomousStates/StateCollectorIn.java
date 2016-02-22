package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.commands.CommandCollectorDeploy;
import org.usfirst.frc.team238.commands.CommandCollectorRetractTeleop;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.commands.CommandCollectorIn;

public class StateCollectorIn {

	CommandCollectorRetractTeleop collectorRetract;
	CommandCollectorDeploy collectorDeploy;
	CommandCollectorIn collectorIntake;
	
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
	/*
	 @Override
	public void reset (){
		
		collectorintake.reset();
	}
	*/
}
