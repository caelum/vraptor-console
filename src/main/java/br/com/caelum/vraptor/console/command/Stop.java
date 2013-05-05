package br.com.caelum.vraptor.console.command;

import java.io.File;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;


public class Stop implements Command {

	@Override
	public void execute(ParsedCommand parsedCommand, File output) throws Exception {
		RunningServer.stop();
	}

}
