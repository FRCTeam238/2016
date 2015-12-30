package org.usfirst.frc.team238.robot;

public class CrusaderCommon {

	public final static double CLAWMOTORSPEED = 1.0;
	public final static double MOTOROFF = 0;
	public final static double CLAWMOTORSPEEDREVERSE = -1.0;
	public final static double DOORMOTORSPEED = 0.5;
	public final static double DOORMOTORSPEEDREVERSE = -0.5;
	
	public final static double ARM_MOTOR_SPEED = -1.0;
	public final static double ARM_MOTOR_SPEED_REVERSE = 1.0;
	public final static double ARM_ENCODER_MAX_VAL = 1.0;  //WILL BE CHANGED TO FIT
	public final static double ARM_ENCODER_MIN_VAL = -1.0; //WILL BE CHANGED TO FIT
	
	public static final String PREFERENCE_OP_MODE = "mode";
	public static final String PREFVALUE_OP_MODE_NORMAL = "normal";
	public static final String PREFVALUE_OP_MODE_TEST = "test";
	public static final String PREFVALUE_OP_AUTO = "auto";
	public static final String PREFVALUE_OP_AUTO_DEFAULT = "1";
	
	//two types of command lists
	public static final int OPR_CMD_LIST = 1;
	public static final int LEFTDRIVER_CMD_LIST = 2;
	public static final int RIGHTDRIVER_CMD_LIST = 3;
	public static final int DT_CMD_LIST = 4;
	public static final int AUTONOMOUS_CMD_LIST = 5;
	
	//public static final double POT_GROUND_MIN = 0.690;
	public static final double POT_GROUND = 50; //0.452; 		//0.440;
	public static final double R_POT_GROUND = 50; //0.450;
	//public static final double POT_GROUND_MAX = 0.610;
	
	//public static final double POT_TRAVEL_MIN = 0.380;
	public static final double POT_TRAVEL = 480; //0.335; 		//0.346;
	public static final double R_POT_TRAVEL = 480; //0.332;
	//public static final double POT_TRAVEL_MAX = 0.280;
	
	//public static final double POT_COOP_MIN = 0.230;
	public static final double POT_COOP = 1150; //0.184; 		//0.140;
	public static final double R_POT_COOP = 1150; //0.179;
	
	public static final double POT_DELIVER = 140; //0.170; 		//0.140;
	public static final double R_POT_DELIVER = 140; //0.189;
	
	public static final double POT_LOADING = 1790; //0.000; 		//0.018;
	public static final double R_POT_LOADING = 1790; //0.000; 		
	//public static final double POT_LOADING_MAX = 0.010;
	
	public static final double POT_NEUTRAL_ZONE = 10; //.002;
	
	public static final double POT_DIFF_MAX = 0.010;
	public static final double POT_DEAD_BAND = 0.005;
	
	//Levels for the Lift 
	public static final int GROUND_LEVEL = 1;
	public static final int TRAVEL_LEVEL = 2;
	public static final int LOADING_LEVEL = 3;
	public static final int DELIVER_LEVEL = 4;
	public static final int COOP_LEVEL = 5;
	//Added RAISE_ARM for testing purposes, Jon
	public static final int RAISE_ARM = 6;
	
	//Speed for the Lift Jaguars
	public static final double LIFT_GOES_UP_NORMAL = 0.25; //-1;
	public static final double LIFT_GOES_DOWN_NORMAL = -0.25; //1;
	public static final int LIFT_STOPS = 0;
	public static final double LIFT_GOES_DOWN_SLOW = -0.1;
	public static final double LIFT_GOES_UP_SLOW = 0.1;
	
	//
	public static final int INPUT_MANUAL_OVERRIDE = 0;
	public static final int INPUT_OPR_CONTROL = 1;
	public static final int INPUT_DRIVER_LEFT_JS = 2;
	public static final int INPUT_DRIVER_RIGHT_JS = 3;
	public static final int INPUT_AUTO_DRIVE = 4;
		// this element in the commandValue array is only
		// used by the AutoMode1Impl class. CommandController
		// is unaware of it
	public static final int INPUT_AUTO_PREDICATE = 5;

	
	public static final boolean SHIFTER_HIGH_GEAR = true;
	public static final boolean SHIFTER_LOW_GEAR = false;
	
	public static final int SHIFT_STATE_HIGH_GEAR = 1;
	public static final int SHIFT_STATE_LOW_GEAR = 0;
	
	/*
	 * The AUTO_DRIVE_* constants are the joystick positions when moving in the
	 * expected direction. These values are passed to the RobotDrive.tankDrive
	 * method.
	 */
	public static final double AUTO_DRIVE_FORWARD = 0.75;
	public static final double AUTO_DRIVE_BACKWARD = -0.75;
	public static final double AUTO_DRIVE_IDLE = 0.0;

	public static final int MAN_CMD_IDX_DONOTHING = 0;
		
	public static final int OPR_CMD_IDX_DONOTHING = 0;
	public static final int OPR_CMD_IDX_SETTOGROUND = 1;
	public static final int OPR_CMD_IDX_SETTOTRAVEL = 2;
	public static final int OPR_CMD_IDX_SETTOLIFT = 3;
	public static final int OPR_CMD_IDX_SETTODELIVER = 4;
	public static final int OPR_CMD_IDX_COOPPOINTS = 5;
	//RAISE ARM added for testing purposes, Jon
	public static final int OPR_CMD_IDX_RAISEARM = 6;
	
	public static final int LEFTDRIVER_CMD_IDX_DONOTHING = 0;
	public static final int LEFTDRIVER_CMD_IDX_SPINRIGHT = 1;
	public static final int LEFTDRIVER_CMD_IDX_SPINLEFT = 2;
	public static final int LEFTDRIVER_CMD_IDX_UNUSED3 = 3;
	public static final int LEFTDRIVER_CMD_IDX_SHIFTLOW = 4;
	
	public static final int RIGHTDRIVER_CMD_IDX_DONOTHING = 0;
	public static final int RIGHTDRIVER_CMD_IDX_SPINRIGHT = 2;
	public static final int RIGHTDRIVER_CMD_IDX_SPINLEFT = 1;
	public static final int RIGHTDRIVER_CMD_IDX_SHIFTHIGH = 3;

	public static final int AUTONOMOUS_CMD_IDX_DONOTHING = 0;
	public static final int AUTONOMOUS_CMD_IDX_DRIVEFORWARD = 1;
	public static final int AUTONOMOUS_CMD_IDX_DRIVEBACKWARD = 2;
	public static final int AUTONOMOUS_CMD_IDX_TURNLEFT = 3;
	public static final int AUTONOMOUS_CMD_IDX_TURNRIGHT = 4;
	
	public static final int DRIVE_TRAIN_CMD_IDX = 0;
	
	/*AUTONOMOUS INTEGERS*/
	
	public static final int AUTO_DRIVE_LIMIT = 15000;
	
}
