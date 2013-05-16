package br.com.caelum.vraptor.console.command;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public class Exit implements Command {

	@Override
	public void execute(ParsedCommand parsedCommand) throws Exception {
		System.exit(0);
	}

}
