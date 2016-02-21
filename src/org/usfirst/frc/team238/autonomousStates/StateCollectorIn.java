package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.commands.CommandCollectorDeploy;
import org.usfirst.frc.team238.commands.CommandCollectorRetractTeleop;
import org.usfirst.frc.team238.commands.CommandCollectorIn;

public class StateCollectorIn {

	/*
		This code is meant to deploy the Intake/"loader".
		Start up the wheels and take in a ball.
		When done, it'll stop the wheels and Retract the "loader".
	*/
	CommandCollectorRetractTeleop collectorRetract;
	CommandCollectorDeploy collectorDeploy;
	CommandCollectorIn collectorIntake;
	
	//@Override
	public void process () {
		
		collectorDeploy.execute();
		collectorIntake.execute();
		System.out.println("Rotating In.....");
		
	}
	
	//@Override
	public boolean done() {
		
		if(collectorIntake.complete()){
			
			System.out.println("Rotating In: DONE//Ball received?");
			System.out.println("Retracting.....");
			collectorRetract.execute();
			
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
