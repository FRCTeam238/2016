package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.commands.CommandCollectorDeploy;
import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StateCollectorDeploy implements AutonomousState {

	CommandCollectorDeploy deployIntake;
	String parameters[];
	/**
	 * 
	 */
	public void init(String params[], CommandController theMcp)
	{
		
		//get specific  objects needed for creating the command object 
		deployIntake = (CommandCollectorDeploy) theMcp.getOperatorCmd(8);
		parameters = params;
		
	}
	
	//@Override
		public void process () {
	
			deployIntake.execute();
		}
		
		//@Override
		public boolean done() {
			
			if(deployIntake.complete()){
				
				Logger.logString("The Intake Is Deployed");
				
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
			deployIntake.setParams(Integer.parseInt(parameters[0]));
		}

		@Override
		public void reset() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void showParams() {
			// TODO Auto-generated method stub
			SmartDashboard.putString("Param 1 - deployUp/Down", parameters[0]);
		}

		@Override
		public void updateParams() {
			// TODO Auto-generated method stub
			
		}

}