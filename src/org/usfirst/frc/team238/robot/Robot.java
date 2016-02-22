package org.usfirst.frc.team238.robot;


import org.usfirst.frc.team238.core.AutonomousController;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.Navigation;
import org.usfirst.frc.team238.robot.Drivetrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

// @SuppressWarnings("deprecation")
public class Robot extends IterativeRobot {

	private static int count = 0;
	//private static boolean AUTO_STARTED = false;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	
	CANTalon leftFrontDrive; //id = 1
	CANTalon leftRearDrive; //id = 2
	CANTalon rightFrontDrive; //id = 3
	CANTalon rightRearDrive; //id = 4
	
	
	Preferences myPreferences;
	ControlBoard myControlBoard;
	CommandController theMCP;
	RobotDrive myRobotDrive;
	Intake myIntake;
	Navigation myNavigation;
	Drivetrain myDriveTrain;
	
	// Autonomous Mode Support
	String autoMode;
	AutonomousDrive autonomousDrive;
	private AutonomousController theMACP;
	SendableChooser autonomousChooser;
	Logger myLogger;
	
	
	public void disabledInit() {
		try {
			// only use checkForSmartDashboardChanges function in init methods
			// or you will smoke the roborio into a useless pile of silicon
			checkForSmartDashboardChanges(CrusaderCommon.PREFVALUE_OP_AUTO, CrusaderCommon.PREFVALUE_OP_AUTO_DEFAULT);
			
			Logger.logString("disabledInit:");
		
		} catch (Exception ex) {
			Logger.logString("disabledInit exception");
		}
	}

	public void disabledPeriodic() {
		boolean debug;
		try {
			if (count > 500) {

				count = 0;

				debug = SmartDashboard.getBoolean("Debug");
				Logger.logBoolean("disabledPeriodic:Debug=  " , debug);
				
				String automousModeFromDS = (String) autonomousChooser.getSelected();
				Logger.logString("The chosen One =  " + automousModeFromDS);
				
				
				if (automousModeFromDS != null) {
					
					if (automousModeFromDS.isEmpty()){
						automousModeFromDS =  CrusaderCommon.PREFVALUE_OP_AUTO_DEFAULT; 
					}
					
					theMACP.pickAMode(Integer.parseInt(automousModeFromDS));
					theMACP.dump();
				}
			}
			count++;
		} catch (Exception ex) {
			Logger.logString("disabledPriodic exception" );
			ex.printStackTrace();
		}

	}

	public void teleopInit() {
		try {
			Logger.logString("TeleopInit()");
		} catch (Exception ex) {
			Logger.logString("TeleopInit:Exception");
		}

	}

	public void autonomousInit() {
		try {

			Logger.logString("AutononousInit()");

			try {
			
				String automousModeFromDS = (String) autonomousChooser.getSelected();
				Logger.logString("The chosen One =  " + automousModeFromDS);
				theMACP.pickAMode(Integer.parseInt(automousModeFromDS));
				
			} catch (Exception ex) {
				Logger.logString("AutononousInit:Something BAD happened");
			}
		} catch (Exception ex) {
			Logger.logString("AutononousInit:Exception");
		}
	}

	public void robotInit() {

		try {
			Logger.logString("RobotInit()");
			SmartDashboard.putString(CrusaderCommon.PREFVALUE_OP_AUTO, "");
			
			SmartDashboard.putBoolean("Debug", false);
			
			myLogger = new Logger();
			
			//object that is the code representation for the physical control board
			myControlBoard = new ControlBoard();
			myControlBoard.controlBoardInit();

			//Create robot core objects 
			
												// Test Robot | Actual Robot
			leftFrontDrive = new CANTalon(5);  //id =  1			 5
			leftRearDrive = new CANTalon(6);   //id =  2			 6
			rightFrontDrive = new CANTalon(7); //id =  3			 7
			rightRearDrive = new CANTalon(8);  //id =  4			 8
			
			myRobotDrive = new RobotDrive(leftFrontDrive,leftRearDrive,rightFrontDrive,rightRearDrive);
			myRobotDrive.setSafetyEnabled(false);
			autonomousDrive = new AutonomousDrive(myRobotDrive);
			autonomousDrive.init();
			
			myIntake = new Intake();
			myIntake.Init();
			
			myNavigation = new Navigation();
			myNavigation.init();
			
			myDriveTrain = new Drivetrain(myRobotDrive);
			myDriveTrain.init(leftFrontDrive, rightFrontDrive);
			
			leftFrontDrive.setFeedbackDevice(FeedbackDevice.QuadEncoder);
			rightFrontDrive.setFeedbackDevice(FeedbackDevice.QuadEncoder);
			
			leftFrontDrive.enableBrakeMode(true);
			rightFrontDrive.enableBrakeMode(true);
			leftRearDrive.enableBrakeMode(true);
			rightRearDrive.enableBrakeMode(true);
			
			//Controller object for telop
			theMCP = new CommandController();
			theMCP.init(myRobotDrive, autonomousDrive, myIntake, myDriveTrain, myNavigation);

			//Controller Object for autonomous
			theMACP = new AutonomousController(); 
			theMACP.init(theMCP);
			
			autonomousChooser = new SendableChooser();
			autonomousChooser.addDefault("0 Do nothing", "0");
			autonomousChooser.addObject("1 - Mostly Everything", "1");
			autonomousChooser.addObject("2 - Moat", "2");
			autonomousChooser.addObject("3 - Cheval de Friese", "3");
			autonomousChooser.addObject("4 - Low Bar", "4");
			autonomousChooser.addObject("5 - Turn Left Test", "5");
			autonomousChooser.addObject("6 - Turn Right Test", "6");
			//autonomousChooser.addDefault("3 - NAMEHERE", "3");
			SmartDashboard.putData("Choose Autonomous", autonomousChooser);
			
			Logger.logString("Fully Initialized");

		} catch (Exception ex) {

			Logger.logString(ex.getMessage());
			ex.printStackTrace();

		}
	}

	public CommandController getTheMCP()
	{
		return theMCP;
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		SmartDashboard.putNumber("Left Encoder", leftFrontDrive.getEncPosition());
		SmartDashboard.putNumber("Right Encoder", rightFrontDrive.getEncPosition());
		try {
			
			theMACP.process();
			myNavigation.navxValues();
			myNavigation.ultrasonicSensor();
			
		} catch (Exception ex) {
			Logger.logString("Autonomous exception");
			ex.printStackTrace();
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {

		int commandValue[];
		
		SmartDashboard.putString("Is this working","Yep");
		SmartDashboard.putNumber("Left Encoder", leftFrontDrive.getEncPosition());
		SmartDashboard.putNumber("Right Encoder", rightFrontDrive.getEncPosition());
		
		try {

			//get the buttons (commands) that were pressed on the control board
			commandValue = myControlBoard.getCommands();
			//pass the array with the commands coming form the control to the Controller object 
			theMCP.buttonPressed(commandValue);
			myNavigation.navxValues();
			myNavigation.ultrasonicSensor();

		} catch (Exception e) {
			
			Logger.logString("telopperiodic: ");
			e.printStackTrace();
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

	/**
	 * This should ONLY be called from an init function
	 */
	private void checkForSmartDashboardChanges(String key, String value) {
		myPreferences = Preferences.getInstance();

		String valueFromPrefs = myPreferences.getString(key, value);
		if (valueFromPrefs != null) {
			Logger.logFourString("CheckSDChanges:valueFromPrefs : " , key , " = " , valueFromPrefs);
			String valueFromDS = null;
			
			try {
				valueFromDS = SmartDashboard.getString(key);
			} catch (Exception ex) {
				ex.printStackTrace();
				SmartDashboard.putString(key, valueFromPrefs);
			}

			Logger.logFourString("CheckSDChanges.ValFromDS : " , key , " = " , valueFromDS);

			// check for null and also if it's empty don't overwrite what's in
			// the preferences table
			if ((valueFromDS != null)  && (!valueFromDS.isEmpty())) {
								// if they are not the same then update the preferences
				if (!valueFromPrefs.equalsIgnoreCase(valueFromDS)) {
					
					Logger.logFourString("CheckSDChanges.UpdatePrefs" , key , " = " , valueFromDS);
					myPreferences.putString(key, valueFromDS);

					// NEVER NEVER use this save() in a periodic function or you
					// will destroy your RoboRio
					// making it an expensive chunk of useless plastic and
					// silicon
					
					//Thanks for the heads up bro!
					
					myPreferences.save();
				}
			}
			
			if(( valueFromDS != null) && (valueFromDS.isEmpty()) && (!valueFromPrefs.isEmpty())) {
			
				SmartDashboard.putString(key, valueFromPrefs);
			
			}
		}
	}
}
