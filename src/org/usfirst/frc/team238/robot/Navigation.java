package org.usfirst.frc.team238.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

public class Navigation {
	
	AHRS ahrs;
	boolean areWeThereYet;
	
	
	public void init()
	{
		
		ahrs = new AHRS(SerialPort.Port.kMXP);
		areWeThereYet = true;
		
	}
	//NOTE: make a set target for yaw and roll
	double currentYaw = ahrs.getYaw();
	double currentRoll = ahrs.getRoll();
	double targetYaw;
	
	public void setTargetValues()
	{
		//targetYaw = CrusaderCommon.NAVX_TARGET_YAW;
	}
	
	//Values being read from the NavX board
	public void navxValues()
	{
		Timer.delay(0.020);
		
		SmartDashboard.putBoolean(  "IMU_Connected",        ahrs.isConnected());
        SmartDashboard.putBoolean(  "IMU_IsCalibrating",    ahrs.isCalibrating());
        
		SmartDashboard.putNumber("Gyro_X", ahrs.getRawGyroX());
		SmartDashboard.putNumber("Gyro_Y", ahrs.getRawGyroY());
		SmartDashboard.putNumber("Gyro_Z", ahrs.getRawGyroZ());
		
		SmartDashboard.putNumber("Accel_X", ahrs.getRawAccelX());
		SmartDashboard.putNumber("Accel_Y", ahrs.getRawAccelY());
		SmartDashboard.putNumber("Accel_Z", ahrs.getRawAccelZ());
		
        SmartDashboard.putNumber("IMU_Yaw", ahrs.getYaw());
        SmartDashboard.putNumber("IMU_Pitch", ahrs.getPitch());
        SmartDashboard.putNumber("IMU_Roll", ahrs.getRoll());
	}
	//Tells us if we are at our target yaw
	public void areWeThereYet()
	{
		if(currentYaw > targetYaw - 5 && currentYaw < targetYaw + 5)
		{
			areWeThereYet = true;
		}
		else
		{
			areWeThereYet = false;
		}
	}
	
}
