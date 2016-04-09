package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.Hanger;

public class CommandRetractTheHounds implements Command {
	
	Hanger myHanger;

	public CommandRetractTheHounds(Hanger myHanger) {
		// TODO Auto-generated constructor stub
		this.myHanger = myHanger;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		myHanger.retractTheHounds();
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub

	}

}
