package org.usfirst.frc.team238.core;

import java.util.HashMap;

import org.usfirst.frc.team238.commands.CommandCollectorIn;
import org.usfirst.frc.team238.commands.CommandCollectorOut;
import org.usfirst.frc.team238.commands.CommandStopCollector;
import org.usfirst.frc.team238.commands.CommandDeployWedge;
import org.usfirst.frc.team238.commands.CommandRetractWedge;
import org.usfirst.frc.team238.commands.CommandCollectorRetractTeleop;
import org.usfirst.frc.team238.commands.CommandCollectorDeploy;
import org.usfirst.frc.team238.commands.CommandCollectorDeployTeleop;
import org.usfirst.frc.team238.robot.Intake;



public class OperatorCmdFactory {

	CommandCollectorIn operatorCollectorIn;
	
	CommandCollectorOut operatorCollectorOut;
	
	CommandStopCollector operatorStopCollector;
	
	CommandDeployWedge commandDeployWedge;
	
	CommandRetractWedge commandRetractWedge;
	
	CommandCollectorRetractTeleop operatorRetractCollectorTeleop;
	
	CommandCollectorDeploy operatorDeployCollector;
	
	CommandCollectorDeployTeleop operatorDeployCollectorTeleop;

	
	HashMap <Integer, Command> operatorCommands;
	
	public void init(){
	
	operatorCommands = new HashMap<Integer, Command>(11);
	
	}
	
	public HashMap<Integer, Command> createOperatorCommands(Intake theIntake){
	
		operatorCollectorIn = new CommandCollectorIn(theIntake);
		operatorCommands.put(2, operatorCollectorIn);
		operatorCollectorOut = new CommandCollectorOut(theIntake);
		operatorCommands.put(3, operatorCollectorOut);
		operatorStopCollector = new CommandStopCollector(theIntake);
		operatorCommands.put(0, operatorStopCollector);
		operatorDeployCollectorTeleop = new CommandCollectorDeployTeleop(theIntake);
		operatorCommands.put(6, operatorDeployCollectorTeleop);
		operatorRetractCollectorTeleop = new CommandCollectorRetractTeleop(theIntake);
		operatorCommands.put(7, operatorRetractCollectorTeleop);
		commandDeployWedge = new CommandDeployWedge(theIntake);
		operatorCommands.put(10, commandDeployWedge);
		commandRetractWedge = new CommandRetractWedge(theIntake);
		operatorCommands.put(11, commandRetractWedge);
		
		operatorDeployCollector = new CommandCollectorDeploy(theIntake);
		operatorCommands.put(8, operatorDeployCollector);
		
		
		
		
		return operatorCommands;
	
	
	}
	
}
