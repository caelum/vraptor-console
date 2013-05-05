package br.com.caelum.vraptor.console.command;

import java.io.File;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public interface Command {

	void execute(ParsedCommand parsedCommand, File output) throws Exception;

}
