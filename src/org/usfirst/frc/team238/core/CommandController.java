package org.usfirst.frc.team238.core;


import java.util.HashMap;
import org.usfirst.frc.team238.robot.AutonomousDrive;
import org.usfirst.frc.team238.robot.ControlBoard;
import org.usfirst.frc.team238.robot.CrusaderCommon;


import edu.wpi.first.wpilibj.RobotDrive;


public class CommandController {
	
	Command operatorCommandList[];
	
	Command manualOperatorCommandList[];
	Command autonomousCommandList[];
	
	AutonomousCmdFactory theRouge;
	DriverCommandFactory  theDriverCommandFactory;
	
	HashMap<String, Command> autoCmdList;
	HashMap<Integer, Command> driverCmdList; 
	HashMap<Integer, Command> driverLeftCmdList;
	HashMap<Integer, Command> driverRightCmdList;
	
	
	public void  init(RobotDrive myRobotDrive, AutonomousDrive autonomousDrive)
	{
		int numCommands = 10; 
		System.out.println("ControlBoard Init:NUMCMDS = " + numCommands);
	
		operatorCommandList = new Command[numCommands];
		manualOperatorCommandList = new Command[numCommands];
		autonomousCommandList = new Command[numCommands];
		
		
		//now populate the commmand list
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
		theDriverCommandFactory = new DriverCommandFactory();
		theDriverCommandFactory.init();
		
		driverLeftCmdList = theDriverCommandFactory.createDriverLeftCommands();
		driverRightCmdList = theDriverCommandFactory.createDriverRightCommands();
		driverCmdList = theDriverCommandFactory.createDriverCommands(myRobotDrive);
		
		
	}
	
	private void setupOperatorCommands()
	{

		/*
		theOPeratorCommandFactory = new DriverCommandFactory();
		theOPeratorCommandFactory.init();
		
		operatorCmdList = theOPeratorCommandFactory.createCommands();
		 */
		
	}

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
		
		Integer buttonPressed = slot[CrusaderCommon.INPUT_DRIVER_LEFT_JS];
		driverLeftCmdList.get(buttonPressed).execute();
		
		buttonPressed = slot[CrusaderCommon.INPUT_DRIVER_RIGHT_JS];
		driverRightCmdList.get(buttonPressed).execute();
		
		buttonPressed = slot[CrusaderCommon.DT_CMD_LIST];
		System.out.println("!!!!!!!!!!!DEBUG!!!!!!!!!!! :  " + buttonPressed);
		Command test = driverCmdList.get(buttonPressed); //
		test.execute();
	}
	
}
