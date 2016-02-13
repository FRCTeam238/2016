package org.usfirst.frc.team238.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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

	
	@Override
	public void prepare()
	{
		
		
		
	}
	
	public AutonomousController()
	{
		
	}
	
	public void init(CommandController theMCP)
	{
		readJson(theMCP);
	}
	
	public void init(String params[], CommandController theMcp)
	{	
		
	}
	public void reset()
	{	
		
	}
	
	public void pickAMode(int mode){
		steps = autonomousModeList [mode];
		setState(steps.get(0));
		index = 0;
	}
	
	public void setState(AutonomousState state)
	{
		this.currentState = state;
		state.prepare();
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
		System.out.println("getNextState:index = " + index);
		AutonomousState nextState = steps.get(++index);
		
		return(nextState);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	ArrayList<AutonomousState>[] autonomousModeList;
	
	@SuppressWarnings("unchecked")
	public void readJson(CommandController theMCP) {

		try {
			JSONParser parser = new JSONParser();
			
			String classPath = "org.usfirst.frc.team238.autonomousStates.State";
			
			Object obj = parser.parse(new FileReader("/home/lvuser/amode238.txt"));

			JSONObject jsonObject = (JSONObject) obj;
			JSONArray autonomousModes = (JSONArray) jsonObject.get("AutonomousModes");

			Iterator<JSONObject> aModeIterator = autonomousModes.iterator();
			int numModes = autonomousModes.size();
			System.out.println("NumModes : " + numModes);
			
			//create a list of commandsteps for each mode
			autonomousModeList = new ArrayList[numModes];
			
			for(int i=0;i<numModes;i++){
				autonomousModeList [i]= new ArrayList<AutonomousState>();
			}
			
			int aModeIndexCounter = 0;
			while (aModeIterator.hasNext()) {
            	
            	JSONObject autoModeX = aModeIterator.next();
            
            	String name = (String) autoModeX.get("Name");
            	System.out.println("Name: " + name);

            	JSONArray companyList = (JSONArray) autoModeX.get("Commands");

            	Iterator<JSONObject> iterator = companyList.iterator();
            	while (iterator.hasNext()) {
            		JSONObject aCommand = iterator.next();
            		String cmdName = (String) aCommand.get("Name");
            		System.out.println(cmdName);
            		String cmdClass = classPath + cmdName; 
            		System.out.println(cmdClass);

            		JSONArray paramList = (JSONArray) aCommand.get("Parameters");
            		Iterator<String> paramIterator = paramList.iterator();
            		
            		String params[] = new String[paramList.size()];
            		int i = 0;
            		while (paramIterator.hasNext()) {
            			params[i++] = (String) paramIterator.next();
            			System.out.println(params[i -1]);
            		}
            		try {
    					//use reflection to create state object
    					AutonomousState xxx = (AutonomousState) Class.forName(cmdClass).newInstance();
    					
    					xxx.init(params, theMCP);
    					
    					//add it to the steps for this autonomous mode   					
    					autonomousModeList[aModeIndexCounter].add(xxx);
    					

    				} catch (InstantiationException | IllegalAccessException
    						| ClassNotFoundException e) {
    					
    					e.printStackTrace();
    				}           		

            	}
            	//this is used to index into the array of autonomous mode arrayLists
            	aModeIndexCounter++;
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dump(){
		
		Iterator<AutonomousState> aModeIterator = steps.iterator();
		
		while(aModeIterator.hasNext()){
			AutonomousState thisState = aModeIterator.next();
			System.out.println("AUTONOMOUS DUMP " + thisState.getClass().getName());
		}
	}

}
