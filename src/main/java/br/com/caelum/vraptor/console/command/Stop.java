package br.com.caelum.vraptor.console.command;

import java.io.File;


public class Stop implements Command {

	@Override
	public void execute(String[] args, File output) throws Exception {
		RunningServer.stop();
	}

}
