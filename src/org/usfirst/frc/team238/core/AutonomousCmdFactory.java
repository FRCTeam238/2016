package org.usfirst.frc.team238.core;

import java.util.HashMap;


import org.usfirst.frc.team238.commands.CommandDriveForward;
import org.usfirst.frc.team238.robot.AutonomousDrive;

public class AutonomousCmdFactory {
	
	CommandDriveForward autoDriveForward;
	HashMap <String, Command> autonomousCommands;
	
	public void init(){
		autonomousCommands = new HashMap<String, Command>(10);
	}
	
	
	public HashMap<String, Command> createAutonomousCommands(AutonomousDrive autonomousDrive){
		
		autoDriveForward = new CommandDriveForward(autonomousDrive);
		autonomousCommands.put("CommandDriveForward", autoDriveForward);
		
		return autonomousCommands;
		
	}

}
