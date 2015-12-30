package org.usfirst.frc.team238.robot;


import org.usfirst.frc.team238.commands.CommandTankDrive;
import org.usfirst.frc.team238.commands.NoDriverCommand;
import org.usfirst.frc.team238.commands.NoOperatorCommand;
import org.usfirst.frc.team238.core.AutonomousController;
import org.usfirst.frc.team238.core.CommandController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.RobotDrive;

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
	private static boolean AUTO_STARTED = false;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	
	
	Preferences myPreferences;
	ControlBoard myControlBoard;
	CommandController theMCP;
	RobotDrive myRobotDrive;
	// Autonomous Mode Support
	String autoMode;
	AutonomousDrive autonomousDrive;

	

	private AutonomousController theMACP;

	public void disabledInit() {
		try {
			// only use checkForSmartDashboardChanges function in init methods
			// or you will
			// smoke the roborio into a useless pile of silicon
			checkForSmartDashboardChanges("mode",
					CrusaderCommon.PREFVALUE_OP_MODE_NORMAL);
		} catch (Exception ex) {
			System.out.println("disabledInit exception");
		}
	}

	public void disabledPeriodic() {

		try {
			if (count > 500) {

				count = 0;

				String modeFromDS = SmartDashboard.getString("mode");
				if (modeFromDS != null) {
					System.out.println("DSModeFromPeriodicDisabled = "
							+ modeFromDS);
				}
			}
			count++;
		} catch (Exception ex) {
			System.out.println("disabledPriodic exception");
		}

	}

	public void teleopInit() {
		try {
			System.out.println("TeleopInit()");
			// only use checkForSmartDashboardChanges function in init methods
			// or you will
			// smoke the roborio into a useless pile of silicon
			checkForSmartDashboardChanges("mode",
					CrusaderCommon.PREFVALUE_OP_MODE_NORMAL);
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
				checkForSmartDashboardChanges("auto", "3");
			} catch (Exception ex) {
				System.out.println("AutononousInit:CMDB Exception");
			}

			// Note: Command objects for autonomous are initialized in
			// RobotInit
			try {
			
				autoMode = myPreferences.getString("auto", "3");
				AUTO_STARTED = false;
				autonomousDrive.killTimer();
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
			SmartDashboard.putString(CrusaderCommon.PREFERENCE_OP_MODE, "");

			//object that is the code representation for the physical control board
			myControlBoard = new ControlBoard();
			myControlBoard.controlBoardInit();

			//Create robot core objects 
			
			myRobotDrive = new RobotDrive(0, 1, 2, 3);
			
			autonomousDrive = new AutonomousDrive(myRobotDrive);
			autonomousDrive.init();
			
			//Controller object for telop
			theMCP = new CommandController();
			theMCP.init(myRobotDrive, autonomousDrive);

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

			//get the buttons (commands) that were pressed on the control board
			commandValue = myControlBoard.getCommands();
			
			//pass the array with the commands coming form the control to the Controller object 
			theMCP.buttonPressed(commandValue);
			

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
			System.out.println("PREFs:" + key + " = " + valueFromPrefs);
			String valueFromDS = null;
			
			try {
				valueFromDS = SmartDashboard.getString(key);
			} catch (Exception ex) {
				ex.printStackTrace();
				SmartDashboard.putString(key, valueFromPrefs);
			}

			System.out.println("PREFs_DS:" + key + " = " + valueFromDS);

			// check for null and also if it's empty don't overwrite what's in
			// the preferences table
			if ((valueFromDS != null) && (!valueFromDS.isEmpty())) {
				System.out.println("SB:" + key + " = " + valueFromDS);

				// if they are not the same then update the preferences
				if (!valueFromPrefs.equalsIgnoreCase(valueFromDS)) {

					myPreferences.putString(key, valueFromDS);

					// NEVER NEVER use this save() in a periodic function or you
					// will destroy your RoboRio
					// making it an expensive chunk of useless plastic and
					// silicon
					myPreferences.save();
				}
			}
		}
	}
}
