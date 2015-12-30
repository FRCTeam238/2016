package org.usfirst.frc.team238.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.usfirst.frc.team238.robot.Robot;

public class AutonomousController implements AutonomousState {

	private AutonomousState currentState;
	private AutonomousState lastState;
	private int index = 0;
	Robot the238Robot;
	ArrayList<AutonomousState> steps;
	
//	This is a copy of what's in the file amode238.txt 
//		{"org.usfirst.frc.team238.autonomousStates.StateLoadBin",""},
//		{"org.usfirst.frc.team238.autonomousStates.StateDriveForward","24"},
//		{"org.usfirst.frc.team238.autonomousStates.StateFinished",""}

	
	public AutonomousController()
	{
		
	}
	
	public void init(CommandController theMCP)
	{
		
		steps = new ArrayList<AutonomousState>();

		File file = new File("/home/lvuser/amode238.txt");
		//read  the file to replace the sequece arrays
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    String params[];
		     int i = 0;
		     
		    while ((line = br.readLine()) != null) {
		    	System.out.println("Autonomous Read from file = "
						+ line);
		    	params = line.split(",");
		    	System.out.println("Autonomous Class = "
						+ params[0]);
		    	System.out.println("Autonomous Parameter = "
						+ params[1]);
		    	try {
					//use reflection to create state object
					AutonomousState xxx = (AutonomousState) Class.forName(params[0]).newInstance();
					
					if(!params[1].isEmpty())
					{
						xxx.init(Integer.parseInt(params[1]), theMCP);
					}
					else
					{
						xxx.init(0, theMCP);
					}
					//add it to the steps
					steps.add(xxx);
					
					//sets initial state
					if(i == 0)
					{
						setState(xxx);
						i++;
					}

				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException e) {
					
					e.printStackTrace();
				}
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void init(int value, CommandController theMcp)
	{	
		
	}
	
	public void setState(AutonomousState state)
	{
		this.currentState = state;
	}
	
	@Override
	public void process() {
		
		this.currentState.process();
		
		if(this.currentState.done() == true)
		{
			setState(getNextState());
		}

	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

	private AutonomousState getNextState()
	{
		AutonomousState nextState = steps.get(++index);
		
		return(nextState);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
