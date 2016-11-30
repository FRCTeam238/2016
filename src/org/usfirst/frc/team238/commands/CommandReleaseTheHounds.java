package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Command;

import org.usfirst.frc.team238.robot.Hanger;

public class CommandReleaseTheHounds extends AbstractCommand {
	
	Hanger myHanger;

	public CommandReleaseTheHounds(Hanger myHanger) {
		// TODO Auto-generated constructor stub
		this.myHanger = myHanger;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		myHanger.releaseTheHounds();

	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub

	}

}
