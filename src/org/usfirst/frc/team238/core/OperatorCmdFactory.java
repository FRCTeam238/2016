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
	
	HashMap <Integer, Command> operatorCommands;
	
	public void init(){
	
	operatorCommands = new HashMap<Integer, Command>(10);
	
	}
	
	public HashMap<Integer, Command> createOperatorCommands(Intake theIntake){
	
		operatorCollectorIn = new CommandCollectorIn(theIntake);
		operatorCommands.put(2, operatorCollectorIn);
		operatorCollectorOut = new CommandCollectorOut(theIntake);
		operatorCommands.put(3, operatorCollectorOut);
		operatorStopCollector = new CommandStopCollector(theIntake);
		operatorCommands.put(0, operatorStopCollector);
		
		return operatorCommands;
	
	
	}
	
}
