/**
 * 
 */
package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;

/**
 * @author Crusader
 *
 */
public class CommandDelay implements Command {

	int count;
	int targetValue;
	
	/**
	 * 
	 */
	public CommandDelay() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team238.core.Command#execute()
	 */
	@Override
	public void execute() {
		count++;
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team238.core.Command#prepare()
	 */
	@Override
	public void prepare() {
		// TODO Auto-generated method stub

	}
	
	public void setParams(int params)
	{
		targetValue = params;
	}
	public boolean done()  
	{
		boolean isDone = false;
		
		if (count > targetValue){
			isDone = true;
		}

		return isDone;
	}

}
