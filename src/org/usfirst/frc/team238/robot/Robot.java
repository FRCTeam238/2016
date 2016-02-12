package org.usfirst.frc.team238.robot;


import org.usfirst.frc.team238.core.AutonomousController;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.robot.Navigation;
import org.usfirst.frc.team238.robot.Drivetrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon;

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

	public void disabledInit() {
		try {
			// only use checkForSmartDashboardChanges function in init methods
			// or you will smoke the roborio into a useless pile of silicon
			checkForSmartDashboardChanges(CrusaderCommon.PREFVALUE_OP_AUTO, CrusaderCommon.PREFVALUE_OP_AUTO_DEFAULT);
			
			System.out.println("disabledInit:");
		
		} catch (Exception ex) {
			System.out.println("disabledInit exception");
		}
	}

	public void disabledPeriodic() {

		try {
			if (count > 500) {

				count = 0;

				String automousModeFromDS = SmartDashboard.getString(CrusaderCommon.PREFVALUE_OP_AUTO);
				System.out.println("disabledPeriodic:AmodeFromDS =  " + automousModeFromDS);
				
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
			System.out.println("disabledPriodic exception" );
			ex.printStackTrace();
		}

	}

	public void teleopInit() {
		try {
			System.out.println("TeleopInit()");
			// only use checkForSmartDashboardChanges function in init methods
			// or you will
			// smoke the roborio into a useless pile of silicon
			//checkForSmartDashboardChanges("mode", CrusaderCommon.PREFVALUE_OP_AUTO_DEFAULT);
			System.out.println("TeleopInit:");
		} catch (Exception ex) {
			System.out.println("TeleopInit:Exception");
		}

	}

	public void autonomousInit() {
		try {

			System.out.println("AutononousInit()");

			// only use checkForSmartDashboardChanges function in init methods
			// or you will
			// smoke the roborio into a useless pile of silicon
			try {
				checkForSmartDashboardChanges(CrusaderCommon.PREFVALUE_OP_AUTO, CrusaderCommon.PREFVALUE_OP_AUTO_DEFAULT);
				System.out.println("AutononousInit:");
			} catch (Exception ex) {
				System.out.println("AutononousInit:CMDB Exception");
			}

			// Note: Command objects for autonomous are initialized in
			// RobotInit
			try {
			
				autoMode = SmartDashboard.getString(CrusaderCommon.PREFVALUE_OP_AUTO); 
						//myPreferences.getString(CrusaderCommon.PREFVALUE_OP_AUTO, "3");
				theMACP.pickAMode(Integer.parseInt(autoMode));
				System.out.println("AutononousInit:Amode =  " + autoMode);
				
			} catch (Exception ex) {
				System.out.println("AutononousInit:Timer");
			}
		} catch (Exception ex) {
			System.out.println("AutononousInit:Exception");
		}
	}

	public void robotInit() {

		try {
			System.out.println("RobotInit()");
			SmartDashboard.putString(CrusaderCommon.PREFVALUE_OP_AUTO, "");

			//object that is the code representation for the physical control board
			myControlBoard = new ControlBoard();
			myControlBoard.controlBoardInit();

			//Create robot core objects 
			
			
			leftFrontDrive = new CANTalon(1);  //id = 1; 5
			leftRearDrive = new CANTalon(2);   //id = 2;6
			rightFrontDrive = new CANTalon(3); //id = 3;7
			rightRearDrive = new CANTalon(4);  //id = 4;8
			
			myRobotDrive = new RobotDrive(leftFrontDrive,leftRearDrive,rightFrontDrive,rightRearDrive);
			myRobotDrive.setSafetyEnabled(false);
			
			autonomousDrive = new AutonomousDrive(myRobotDrive);
			autonomousDrive.init();
			
			myIntake = new Intake();
			myIntake.Init();
			
			myNavigation = new Navigation();
			myNavigation.init();
			
			myDriveTrain = new Drivetrain(myRobotDrive);
			myDriveTrain.init();
			//Controller object for telop
			theMCP = new CommandController();
			theMCP.init(myRobotDrive, autonomousDrive, myIntake, myDriveTrain);

			//Controller Object for autonomous
			theMACP = new AutonomousController(); 
			theMACP.init(theMCP);
		
			System.out.println("Fully Initialized");

		} catch (Exception ex) {

			System.out.println(ex.getMessage());
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

		try {
			
			theMACP.process();
			
			
		} catch (Exception ex) {
			System.out.println("Autonomous exception");
			ex.printStackTrace();
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {

		int commandValue[];
		SmartDashboard.putString("Is this working","Yep");
		try {
			System.out.println("PAST TRY");
			//get the buttons (commands) that were pressed on the control board
			commandValue = myControlBoard.getCommands();
			//pass the array with the commands coming form the control to the Controller object 
			theMCP.buttonPressed(commandValue);
			System.out.println("BEFORE NAVIGATION");
			myNavigation.navxValues();
			System.out.println("AFTER NAVIGATION");
			

		} catch (Exception e) {
			System.out.println("telopperiodic: ");
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
			System.out.println("CheckSDChanges:valueFromPrefs : " + key + " = " + valueFromPrefs);
			String valueFromDS = null;
			
			try {
				valueFromDS = SmartDashboard.getString(key);
			} catch (Exception ex) {
				ex.printStackTrace();
				SmartDashboard.putString(key, valueFromPrefs);
			}

			System.out.println("CheckSDChanges.ValFromDS : " + key + " = " + valueFromDS);

			// check for null and also if it's empty don't overwrite what's in
			// the preferences table
			if ((valueFromDS != null)  && (!valueFromDS.isEmpty())) {
								// if they are not the same then update the preferences
				if (!valueFromPrefs.equalsIgnoreCase(valueFromDS)) {
					
					System.out.println("CheckSDChanges.UpdatePrefs" + key + " = " + valueFromDS);
					myPreferences.putString(key, valueFromDS);

					// NEVER NEVER use this save() in a periodic function or you
					// will destroy your RoboRio
					// making it an expensive chunk of useless plastic and
					// silicon
					myPreferences.save();
				}
			}
			
			if(( valueFromDS != null) && (valueFromDS.isEmpty()) && (!valueFromPrefs.isEmpty())) {
			
				SmartDashboard.putString(key, valueFromPrefs);
			
			}
		}
	}
}
