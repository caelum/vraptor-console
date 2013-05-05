package br.com.caelum.vraptor.console.command.parser;

public class ParsedCommandFactory {

	public ParsedCommand build(String cmd) {
		String[] args = cmd.split("\\s+");
		String commandName = args[0];
		return new ParsedCommand(commandName, args);
	}

}
