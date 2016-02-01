package org.usfirst.frc.team238.core;

import java.util.HashMap;

import org.usfirst.frc.team238.commands.CommandCollectorIn;
import org.usfirst.frc.team238.commands.CommandCollectorOut;
import org.usfirst.frc.team238.commands.CommandStopCollector;
import org.usfirst.frc.team238.robot.Intake;



public class OperatorCmdFactory {

	CommandCollectorIn operatorCollectorIn;
	
	CommandCollectorOut operatorCollectorOut;
	
	CommandStopCollector operatorStopCollector;
	
	HashMap <String, Command> operatorCommands;
	
	public void Init(){
	
	operatorCommands = new HashMap<String, Command>(10);
	
	}
	
	public HashMap<String, Command> createOperatorCommands(Intake theIntake){
	
		operatorCollectorIn = new CommandCollectorIn(theIntake);
		operatorCommands.put("CommandCollectorIn", operatorCollectorIn);
		operatorCollectorOut = new CommandCollectorOut(theIntake);
		operatorCommands.put("CommandCollectorOut", operatorCollectorOut);
		operatorStopCollector = new CommandStopCollector(theIntake);
		operatorCommands.put("CommandStopCollector", operatorStopCollector);
		
		return operatorCommands;
	
	
	}
	
}
