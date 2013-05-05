package br.com.caelum.vraptor.console.executor;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public interface CommandExecutor {

	void parse(ParsedCommand parsedCommand) throws Exception;

}
