package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.commands.CommandCollectorOut;
import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;

public class StateCollectorOut implements AutonomousState {
	
	CommandCollectorOut collectorOut;
	String parameters[];
	
	public void init(String params[], CommandController theMcp)
	{
		
		//get specific  objects needed for creating the command object 
		collectorOut = (CommandCollectorOut) theMcp.getOperatorCmd("CommandCollectorOut");
		parameters = params;
	}
	
	//@Override
		public void process () {

			collectorOut.execute();
			
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
		

		@Override
		public void init() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void prepare() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void reset() {
			// TODO Auto-generated method stub
			
		}

}
