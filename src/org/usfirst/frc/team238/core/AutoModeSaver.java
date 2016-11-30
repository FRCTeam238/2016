package org.usfirst.frc.team238.core;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.usfirst.frc.team238.autonomousStates.*;
/**
 * @author Mike
 * This script is meant to create a new JSON file (amode file)
 */
public abstract class AutoModeSaver implements AutonomousState{
	
	private static final String newline = "\n";
	private static int numModes = 0;
	
	//Copies all the names for the auto modes
//	private static ArrayList<String> AutoModesList = new ArrayList<String>();
	//Copies all the names for the commands
//	private static ArrayList<String> CommandsList = new ArrayList<String>();
	//Copies all the numbers for the params
//	private static ArrayList<String> ParamsList = new ArrayList<String>();
	
	//These arrays keep track of how long the Modes/Commands are
	//Index's are modes, elements hold what the amount of commands are
//	private static ArrayList<Integer> NumberOfCommandsInModes = new ArrayList<Integer>();	
	//Index's are commands, elements hold what the amount of params are
//	private static ArrayList<Integer> NumberOfParamsInCommands = new ArrayList<Integer>();	
	
	//Can probably have all of the above replaced with this.
	@SuppressWarnings("rawtypes")
	private static ArrayList[] autonomousModeList;
	//Or this
	//Iterator = All commands
	//ArrayList = All Modes
	private static ArrayList<Iterator> AutoModesArrayList;
	@SuppressWarnings({"unchecked"})
	public static void readJson(CommandController theMCP) {
		
		try {
			JSONParser parser = new JSONParser(); //Creates a parser for reading amode238.txt
			
			String classPath = "org.usfirst.frc.team238.autonomousStates.State"; //Sets a classpath 
													//  /home/lvuser/amode238.txt
			Object obj = parser.parse(new FileReader("C:/Users/Mike/Desktop/amode238.txt")); //Creates an Object that has a FileReader with an address to amode.txt

			JSONObject jsonObject = (JSONObject) obj; //Creates a JSONObject with the obj Object
			
			JSONArray autonomousModes = (JSONArray) jsonObject.get("AutonomousModes"); //Creates JSONArray, getting all "AutonomousModes":  
			
			Iterator<JSONObject> aModeIterator = autonomousModes.iterator(); //Creates an iterator 
			
			numModes = autonomousModes.size(); //Creates an int of the size of the AutonomousModes
			
			System.out.println("  There are " + (numModes-1) +" detected AutonomousModes");
			
			autonomousModeList = new ArrayList[numModes]; //Creates a list of commandsteps for each mode
			
			//for(int i=0;i<numModes;i++){ //For every autonomousMode, create an ArrayList of AutonmousStates
				//autonomousModeList [i]= new ArrayList<AutonomousState>();
			//}
			
			int aModeIndexCounter = 0,numCommands=0;
			while (aModeIterator.hasNext()) { //While iterator has more states
            	
            	JSONObject autoModeX = aModeIterator.next(); //Return the next element in aModeIterator, Used to get Commands and Names
            
            	String name = (String) autoModeX.get("Name"); //Get the name of the autonomousMode
            	
            	System.out.println(newline+"  Autonmous Mode #" + name);
            	
            	//Starts building the list of Amodes available that will get pushed to the dashboard
            	/*
            	if(aModeIndexCounter == 1){
            		aModeChooser.addDefault(name, String.valueOf(aModeIndexCounter));
            	}
            	else{
            		aModeChooser.addObject(name,String.valueOf(aModeIndexCounter));
            	}
            	*/
            	
//            	AutoModesList.add(name);
            	
            	JSONArray companyList = (JSONArray) autoModeX.get("Commands"); //Creates an array of commands from autoModeX
            	
            	numCommands = companyList.size();
            	
            	System.out.println("There are "+numCommands+" States Detected");
            	
            	Iterator<JSONObject> iterator = companyList.iterator(); //Creates an iterator for the list
            	
            	while (iterator.hasNext()) { //While the iterator has elements
            		
            		JSONObject aCommand = iterator.next(); //Creates a JSONObject with a command from the companyList
            		
            		String cmdName = (String) aCommand.get("Name"); //Gets the command name
            		
//            		CommandsList.add(cmdName);
            		
            		String cmdClass = classPath + cmdName; //Gets the classPath of the command
            		
            		System.out.println(newline+"      Command Name: " + cmdName + newline + "      Class Location = " + cmdClass);

            		JSONArray paramList = (JSONArray) aCommand.get("Parameters"); //Creates an arrayList of the params in the aCommand
            		
            		Iterator<String> paramIterator = paramList.iterator(); //Creates an iterator of the params
            		
            		String params[] = new String[paramList.size()]; //Creates a new string of params = to the paramList.size
            	
 /*           		if(paramList.size()==0){//Because There are some states that don't have any params
            			
            				ParamsList.add("0");
            				NumberOfParamsInCommands.add(1);
            				
            		}else{
            			
            				NumberOfParamsInCommands.add(params.length);
            				
            		}
 */         		
            		int paramIncrementor = 0;
            		
            		while (paramIterator.hasNext()) { //While params have elements
            			
            			params[paramIncrementor] = (String) paramIterator.next();//Copy the params from the paramIterator to the new paramList
            			
 //           			ParamsList.add(params[paramIncrementor]);

            			System.out.println("          Param: " + paramIncrementor + " = " +params[paramIncrementor]);
            			
            			paramIncrementor++;
            		
            		
            			System.out.println("      There are "+paramList.size()+" Param(s) in this Command");

            			//This instantiates a new AutonomousState and adds it to a list
            			try{
            			
            				AutonomousState xxx = (AutonomousState) Class.forName(cmdClass).newInstance();
            			
            				xxx.init(params,theMCP);
            				
            				//xxx.showParams();
            				
            				autonomousModeList[aModeIndexCounter].add(xxx);
            			
            			}catch(InstantiationException | IllegalAccessException | ClassNotFoundException e){
            			
            				e.printStackTrace();
            			}
            		}
            	}
            	
            	System.out.println(newline+"	There are "+companyList.size()+" Command(s) in AutoMode: "+aModeIndexCounter);
            	
//            	NumberOfCommandsInModes.add(aModeIndexCounter, numCommands);
            	
            	aModeIndexCounter++;//this is used to index into the array of autonomous mode arrayLists
            }
			
			//Push the list of Amodes to the dashboard
			//SmartDashboard.putData("Choose Auto", aModeChooser);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void Save(){

		String NewAmode = BuildNewJSON();

		try {
			//in = new Scanner(System.in);
			//System.out.println(newline+"What shall this new amode be called?"+newline);
			
			String fileName = "amode238"; // in.next();
			
			System.out.println(newline+NewAmode+newline);
									//Save here	    /home/lvuser/amode238.txt
			FileWriter file = new FileWriter("C:/Users/Mike/Desktop/"+fileName+".txt");
			file.write(NewAmode);
			file.flush();
			file.close();
			
			System.out.println("SAVED");
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Save Function / FileWriter Failed!");
		} 
	}
	
	public static String BuildNewJSON(){
		String modeString = null;
		
		try{
		
		
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("BuildNewJSON has failed!");
		}
		
		return modeString;
	}

}
