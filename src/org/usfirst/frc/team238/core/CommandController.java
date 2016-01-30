package org.usfirst.frc.team238.core;


import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team238.commands.CommandDriveForward;
import org.usfirst.frc.team238.commands.CommandTankDrive;
import org.usfirst.frc.team238.commands.NoDriverCommand;
import org.usfirst.frc.team238.commands.NoOperatorCommand;
import org.usfirst.frc.team238.robot.AutonomousDrive;
import org.usfirst.frc.team238.robot.ControlBoard;
import org.usfirst.frc.team238.robot.CrusaderCommon;



import edu.wpi.first.wpilibj.RobotDrive;


public class CommandController {
	
	Command operatorCommandList[];
	//Command driverRightCommandList[];
	//Command driverLeftCommandList[];
	Command manualOperatorCommandList[];
	Command autonomousCommandList[];
	//Command driveTrainList[];
	AutonomousCmdFactory theRouge;
	DriverCommandFactory  theDriverCommands;
	
	HashMap<String, Command> autoCmdList;
	HashMap<Integer, Command> driverCmdList; 
	HashMap<Integer, Command> driverLeftCmdList;
	HashMap<Integer, Command> driverRightCmdList;
	
	NoOperatorCommand theDoNothingCmd;
	NoDriverCommand theDoNothingRightDriverCmd;
	NoDriverCommand theDoNothingLeftDriverCmd;
	
	CommandTankDrive cmdToDriveTheRobot;
	CommandDriveForward autoDriveForward;
	
	public void  init(RobotDrive myRobotDrive, AutonomousDrive autonomousDrive)
	{
		int numCommands = 10; 
		System.out.println("ControlBoard Init:NUMCMDS = " + numCommands);
		operatorCommandList = new Command[numCommands];
	//	driverLeftCommandList = new Command[numCommands];
	//	driverRightCommandList = new Command[numCommands];
		manualOperatorCommandList = new Command[numCommands];
		autonomousCommandList = new Command[numCommands];
		//driveTrainList = new Command[1];
		
		//now populate the commmsnd list
		setupOperatorCommands();
		setupDriverCommands(myRobotDrive);
		setupAutonomousCommands(autonomousDrive);
	}
	
	//gets AutoCommand by name
	public Command getAutoCmd(String cmdName)
	{
		return autoCmdList.get(cmdName);
		
	}
	
	//loads all the autonomous commands from the auto factory
	private void setupAutonomousCommands(AutonomousDrive autonomousDrive)
	{
		//autoDriveForward = new CommandDriveForward(autonomousDrive);
		theRouge = new AutonomousCmdFactory();
		theRouge.init();
		autoCmdList = theRouge.createAutonomousCommands(autonomousDrive);
		
	}
	
	private void setupDriverCommands( RobotDrive myRobotDrive)
	{
		theDriverCommands = new DriverCommandFactory();
		theDriverCommands.init();
		
		driverLeftCmdList = theDriverCommands.createDriverLeftCommands();
		driverRightCmdList = theDriverCommands.createDriverRightCommands();
		driverCmdList = theDriverCommands.createDriverCommands(myRobotDrive);
		
//		cmdToDriveTheRobot = new CommandTankDrive(myRobotDrive);
//		
//		theDoNothingRightDriverCmd = new NoDriverCommand();
//		theDoNothingLeftDriverCmd = new NoDriverCommand();
//		
//		setCommand(	CrusaderCommon.DT_CMD_LIST, 
//					CrusaderCommon.DRIVE_TRAIN_CMD_IDX,
//					cmdToDriveTheRobot);
//		
//		setCommand(	CrusaderCommon.LEFTDRIVER_CMD_LIST,
//					CrusaderCommon.LEFTDRIVER_CMD_IDX_DONOTHING,
//					theDoNothingLeftDriverCmd);
//		
//		setCommand(	CrusaderCommon.RIGHTDRIVER_CMD_LIST,
//					CrusaderCommon.RIGHTDRIVER_CMD_IDX_DONOTHING,
//					theDoNothingRightDriverCmd);
//		
//		setCommand(	CrusaderCommon.LEFTDRIVER_CMD_LIST,
//					CrusaderCommon.LEFTDRIVER_CMD_IDX_SPINRIGHT,
//					theDoNothingLeftDriverCmd);
//
//		setCommand(	CrusaderCommon.LEFTDRIVER_CMD_LIST,
//					CrusaderCommon.LEFTDRIVER_CMD_IDX_SPINLEFT,
//					theDoNothingLeftDriverCmd);
//
//		setCommand(	CrusaderCommon.LEFTDRIVER_CMD_LIST,
//					CrusaderCommon.LEFTDRIVER_CMD_IDX_UNUSED3,
//					theDoNothingLeftDriverCmd);
//
//		
//		setCommand(	CrusaderCommon.LEFTDRIVER_CMD_LIST,
//					CrusaderCommon.LEFTDRIVER_CMD_IDX_SHIFTLOW,
//					theDoNothingRightDriverCmd);
//
//		
//		setCommand(	CrusaderCommon.RIGHTDRIVER_CMD_LIST,
//					CrusaderCommon.RIGHTDRIVER_CMD_IDX_SPINRIGHT,
//					theDoNothingRightDriverCmd);
//		
//		setCommand(	CrusaderCommon.RIGHTDRIVER_CMD_LIST,
//					CrusaderCommon.RIGHTDRIVER_CMD_IDX_SPINLEFT,
//					theDoNothingRightDriverCmd);
//
//		
//		setCommand(	CrusaderCommon.RIGHTDRIVER_CMD_LIST,
//					CrusaderCommon.RIGHTDRIVER_CMD_IDX_SHIFTHIGH,
//					theDoNothingRightDriverCmd);
//		
//		
	}
	
	private void setupOperatorCommands()
	{

		/*
		 theDoNothingCmd = new NoOperatorCommand(theLift);
		 
		 setCommand(CrusaderCommon.OPR_CMD_LIST,
				    CrusaderCommon.OPR_CMD_IDX_DONOTHING, 
				    theDoNothingCmd);
		 */
		
	}
	
//	private void setCommand(int list, int slot, Command command){
//		
//		switch(list)
//		{
//		case CrusaderCommon.OPR_CMD_LIST:
//			operatorCommandList[slot] = command;
//			break;
//		case CrusaderCommon.LEFTDRIVER_CMD_LIST:
//			driverLeftCommandList[slot] = command;
//			break;
//		case CrusaderCommon.RIGHTDRIVER_CMD_LIST:
//			driverRightCommandList[slot] = command;
//			break;
//		case CrusaderCommon.AUTONOMOUS_CMD_LIST:
//			autonomousCommandList[slot] = command;
//			break;
//		case CrusaderCommon.DT_CMD_LIST:
//			driveTrainList[slot] = command;
//			break;
//		default:
//			manualOperatorCommandList[slot] = command;
//		}
//	}
//	
	/*
	 * Gets the buttons that are pressed or switches that are set from the controls (joysticks or custom)  
	 * which the values  ( button1 = 1 etc) are an index into the array of commands that have been pre-loaded 
	 * in robot.init, gets the command at that slot in the array and calls the execute function on that command.
	 */
	public void buttonPressed(int slot[]){
		
		//if the override switch is enabled the joystick in slot 0  will control motion of the lift, 
		//the upper and lower limit switched wired directly into the jags will protect over driving the motors in either direction 
		//but none of the other sensors will be used, this is for testing and extreme circumstances when 
		//sensors are compromised
		if(ControlBoard.overRide()){
			//the next 4 lines are what's done in the one line for operator and driverleft/right below
			//but has been expanded for clarity and teaching
			
			//get the value( button pressed) from the array
			int buttonpressed = slot[CrusaderCommon.INPUT_MANUAL_OVERRIDE];
			//use the index to get the command that was loaded during robot init
			Command manualCmd = operatorCommandList[buttonpressed];
			//ask the control Board for a value to feed to the execute command
			double valueForMotors = ControlBoard.getManualCommandValue();
			//execute the command
			manualCmd.execute(-valueForMotors);
			
			
		}
		else
		{
			//this one line does the same thing as the above 4 lines  except it's using the operatorCommandList
			//operatorCommandList[slot[CrusaderCommon.INPUT_OPR_CONTROL]].execute();
			//get the value( button pressed) from the array
			//int buttonpressed = slot[CrusaderCommon.INPUT_OPR_CONTROL];
			//use the index to get the command that was loaded during robot init
			//Command manualCmd = operatorCommandList[buttonpressed];
			//ask the control Board for a value to feed to the execute command
			//double valueForMotors = ControlBoard.getManualCommandValue();
			//execute the command
			//System.out.println("!!!!!!!!!!!DEBUG!!!!!!!!!!!" + buttonpressed);
			//manualCmd.execute();and
		}
		
		//driverLeftCommandList[slot[CrusaderCommon.INPUT_DRIVER_LEFT_JS]].execute();
		//driverRightCommandList[slot[CrusaderCommon.INPUT_DRIVER_RIGHT_JS]].execute();
		//driveTrainList[slot[CrusaderCommon.DT_CMD_LIST]].execute();
		
		Integer command = slot[CrusaderCommon.INPUT_DRIVER_LEFT_JS];
		driverLeftCmdList.get(command).execute();
		
		command = slot[CrusaderCommon.INPUT_DRIVER_RIGHT_JS];
		driverRightCmdList.get(command).execute();
		
		command = slot[CrusaderCommon.DT_CMD_LIST];
		System.out.println("!!!!!!!!!!!DEBUG!!!!!!!!!!! :  " + command);
		Command test = driverCmdList.get(command); //
		test.execute();
	}
	
}
