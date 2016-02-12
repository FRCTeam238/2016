package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.commands.CommandCollectorOut;

public class StateCollectorOut {
	
	CommandCollectorOut collectorOut;
	
	//@Override
		public void process () {

			collectorOut.execute();
			System.out.println("Firing..");
			
		}
		
		//@Override
		public boolean done() {
			
			if(collectorOut.complete()){
				
				System.out.println("The Ball's away!");
				
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
