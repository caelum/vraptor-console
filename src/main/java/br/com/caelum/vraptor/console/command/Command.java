package br.com.caelum.vraptor.console.command;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public interface Command {

	void execute(ParsedCommand parsedCommand) throws Exception;

}
