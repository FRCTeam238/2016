/**
 * 
 */
package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.commands.CommandCollectorOut;
import org.usfirst.frc.team238.commands.CommandDeployWedge;

/**
 * @author Crusader
 *
 */
public class StateDeployWedge implements AutonomousState {
	
	
	CommandDeployWedge deployWedge;
	String parameters[];
	/**
	 * 
	 */
	public void init(String params[], CommandController theMcp)
	{
		
		//get specific  objects needed for creating the command object 
		deployWedge = (CommandDeployWedge) theMcp.getOperatorCmd(10);
		parameters = params;
	}
	
	//@Override
		public void process () {

			deployWedge.execute();
			
		}
		
		//@Override
		public boolean done() {
			
			if(deployWedge.complete()){
				
				Logger.logString("The Wedge Is Deployed");
				
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

		@Override
		public void showParams() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateParams() {
			// TODO Auto-generated method stub
			
		}

}