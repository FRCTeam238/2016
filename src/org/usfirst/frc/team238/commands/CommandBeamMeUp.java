package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Command;

import org.usfirst.frc.team238.robot.Hanger;

public class CommandBeamMeUp extends AbstractCommand {
	
	Hanger myHanger;

	public CommandBeamMeUp(Hanger myHanger) {
		// TODO Auto-generated constructor stub
		this.myHanger = myHanger;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		myHanger.beamMeUpScotty();
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub

	}

}
