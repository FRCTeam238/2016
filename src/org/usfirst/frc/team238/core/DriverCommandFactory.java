package org.usfirst.frc.team238.core;

import java.util.HashMap;

import org.usfirst.frc.team238.commands.CommandTankDrive;
import org.usfirst.frc.team238.commands.NoDriverCommand;

import edu.wpi.first.wpilibj.RobotDrive;

public class DriverCommandFactory {

	NoDriverCommand NoDriveCommand;
	HashMap <Integer, Command> driverLeftCommands;
	HashMap <Integer, Command> driverRightCommands;
	HashMap <Integer, Command> driverCommands;
	
	public void init(){
		driverLeftCommands = new HashMap<Integer, Command>(10);
		driverRightCommands = new HashMap<Integer, Command>(10);
		driverCommands = new HashMap<Integer, Command>(10);
	}
	
	
	public HashMap<Integer, Command> createDriverLeftCommands(){
		
		NoDriveCommand = new NoDriverCommand();
		
		driverLeftCommands.put(0, NoDriveCommand);
		
		return driverLeftCommands;
		
	}

	public HashMap<Integer, Command> createDriverRightCommands(){
		
		NoDriveCommand = new NoDriverCommand();
		
		driverRightCommands.put(0, NoDriveCommand);
		
		return driverRightCommands;
		
	}
	
public HashMap<Integer, Command> createDriverCommands(RobotDrive myRobotDrive){
		
	 	CommandTankDrive cmdToDriveTheRobot = new CommandTankDrive(myRobotDrive);
		
		
		driverCommands.put(0, cmdToDriveTheRobot);
		
		return driverCommands;
		
	}
}
