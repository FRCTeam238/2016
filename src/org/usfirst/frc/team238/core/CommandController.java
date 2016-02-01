package org.usfirst.frc.team238.core;


import java.util.HashMap;
import org.usfirst.frc.team238.robot.AutonomousDrive;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Intake;
import edu.wpi.first.wpilibj.RobotDrive;


public class CommandController {
	AutonomousCmdFactory theRouge;
	DriverCommandFactory  theDriverCommandFactory;
	OperatorCmdFactory theOperatorCmdFactory;
	
	HashMap<String, Command> autoCmdList;
	HashMap<Integer, Command> driverCmdList; 
	HashMap<Integer, Command> driverLeftCmdList;
	HashMap<Integer, Command> driverRightCmdList;
	HashMap<Integer, Command> operatorCmdList;
	
	public void  init(RobotDrive myRobotDrive, AutonomousDrive autonomousDrive, Intake intakeMechanism)
	{
		// populate the command lists
		setupOperatorCommands(intakeMechanism);
		setupDriverCommands(myRobotDrive);
		setupAutonomousCommands(autonomousDrive);
	}
	
	//gets an AutoCommand by key name
	public Command getAutoCmd(String cmdName)
	{
		return autoCmdList.get(cmdName);
		
	}
	
	//loads all the autonomous commands from the auto factory
	private void setupAutonomousCommands(AutonomousDrive autonomousDrive)
	{
		theRouge = new AutonomousCmdFactory();
		theRouge.init();
		autoCmdList = theRouge.createAutonomousCommands(autonomousDrive);
		
	}
	
	//gets a Driver Command by key name
	public Command getDriverCmd(String cmdName)
	{
		return driverCmdList.get(cmdName);
	}
	
	private void setupDriverCommands( RobotDrive myRobotDrive)
	{
		theDriverCommandFactory = new DriverCommandFactory();
		theDriverCommandFactory.init();
		
		driverLeftCmdList = theDriverCommandFactory.createDriverLeftCommands();
		driverRightCmdList = theDriverCommandFactory.createDriverRightCommands();
		driverCmdList = theDriverCommandFactory.createDriverCommands(myRobotDrive);
	}
	
	//gets Operator Commands by name
	public Command getOperatorCmd(String cmdName)
	{
		return operatorCmdList.get(cmdName);
	}
	
	private void setupOperatorCommands(Intake intakeMechanism)
	{
		theOperatorCmdFactory = new OperatorCmdFactory();
		theOperatorCmdFactory.init();
		
		operatorCmdList = theOperatorCmdFactory.createOperatorCommands(intakeMechanism);
	}

	/*
	 * Gets the buttons that are pressed or switches that are set from the controls (joysticks or custom)  
	 * which the values  ( button1 = 1 etc) are the key into a Map of commands that have been pre-loaded 
	 * in the setup methods,  then "get"s the command associated with the key in the hashmap and calls the execute function on that command.
	 */
	public void buttonPressed(int slot[]){
		
		Command commandForTheButtonPressed;
		Integer buttonPressed = slot[CrusaderCommon.INPUT_DRIVER_LEFT_JS];
		
		commandForTheButtonPressed = driverLeftCmdList.get(buttonPressed);
		if(commandForTheButtonPressed != null){
			commandForTheButtonPressed.execute();
		}
		
		buttonPressed = slot[CrusaderCommon.INPUT_DRIVER_RIGHT_JS];
		commandForTheButtonPressed = driverRightCmdList.get(buttonPressed);
		if(commandForTheButtonPressed != null){
			commandForTheButtonPressed.execute();
		}
		
		buttonPressed = slot[CrusaderCommon.DT_CMD_LIST];		
		commandForTheButtonPressed = driverCmdList.get(buttonPressed); 
		if(commandForTheButtonPressed != null){
			commandForTheButtonPressed.execute();
		}
		
		buttonPressed = slot[CrusaderCommon.OPR_CMD_LIST];		
		commandForTheButtonPressed = operatorCmdList.get(buttonPressed); 
		if(commandForTheButtonPressed != null){
			commandForTheButtonPressed.execute();
		}
	}
	
}
