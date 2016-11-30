package org.usfirst.frc.team238.core;

import java.util.HashMap;

import org.usfirst.frc.team238.commands.CommandCollectorIn;
import org.usfirst.frc.team238.commands.CommandCollectorOut;
import org.usfirst.frc.team238.commands.CommandStopCollector;
import org.usfirst.frc.team238.commands.CommandDeployWedge;
import org.usfirst.frc.team238.commands.CommandRetractWedge;
import org.usfirst.frc.team238.commands.CommandCollectorRetract;
import org.usfirst.frc.team238.commands.CommandCollectorDeploy;
import org.usfirst.frc.team238.commands.CommandCollectorDeploy;
import org.usfirst.frc.team238.robot.Intake;
import org.usfirst.frc.team238.robot.Hanger;
import org.usfirst.frc.team238.commands.CommandReleaseTheHounds;
import org.usfirst.frc.team238.commands.CommandResetPcm;
import org.usfirst.frc.team238.commands.CommandBeamMeUp;
import org.usfirst.frc.team238.commands.CommandRetractTheHounds;



public class OperatorCmdFactory {

	CommandCollectorIn operatorCollectorIn;
	
	CommandCollectorOut operatorCollectorOut;
	
	CommandStopCollector operatorStopCollector;
	
	CommandDeployWedge commandDeployWedge;
	
	CommandRetractWedge commandRetractWedge;
	
	CommandCollectorRetract operatorRetractCollectorTeleop;
	
	CommandCollectorDeploy operatorDeployCollector;
	
	CommandCollectorDeploy operatorDeployCollectorTeleop;
	
	CommandReleaseTheHounds commandReleaseTheHounds;
	
	CommandBeamMeUp commandBeamMeUp;
	
	CommandRetractTheHounds commandRetractTheHounds;
	
	CommandResetPcm commandResetPcm;
	
	HashMap <Integer, Command> operatorCommands;

	
	
	public void init(){
	
	operatorCommands = new HashMap<Integer, Command>(16);
	
	}
	
	public HashMap<Integer, Command> createOperatorCommands(Intake theIntake, Hanger theHanger){
	
		operatorCollectorIn = new CommandCollectorIn(theIntake);
		operatorCommands.put(2, operatorCollectorIn);
		
		operatorCollectorOut = new CommandCollectorOut(theIntake);
		operatorCommands.put(3, operatorCollectorOut);
		
		operatorStopCollector = new CommandStopCollector(theIntake, theHanger);
		operatorCommands.put(0, operatorStopCollector);
		
		operatorDeployCollectorTeleop = new CommandCollectorDeploy(theIntake);
		operatorCommands.put(6, operatorDeployCollectorTeleop);
		
		operatorRetractCollectorTeleop = new CommandCollectorRetract(theIntake);
		operatorCommands.put(7, operatorRetractCollectorTeleop);
		
		commandDeployWedge = new CommandDeployWedge(theIntake);
		operatorCommands.put(10, commandDeployWedge);
		
		 commandRetractWedge = new CommandRetractWedge(theIntake);
		operatorCommands.put(11, commandRetractWedge);
		
		commandReleaseTheHounds = new CommandReleaseTheHounds(theHanger);
		operatorCommands.put(1, commandReleaseTheHounds);
		
		commandBeamMeUp = new CommandBeamMeUp(theHanger);
		operatorCommands.put(5, commandBeamMeUp);
		
		commandRetractTheHounds = new CommandRetractTheHounds(theHanger);
		operatorCommands.put(9, commandRetractTheHounds);
		
		commandResetPcm = new CommandResetPcm(theIntake);
		operatorCommands.put(4, commandResetPcm);
		
		return operatorCommands;
	
	
	}
	
}
