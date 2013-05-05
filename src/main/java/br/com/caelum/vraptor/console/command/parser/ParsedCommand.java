package br.com.caelum.vraptor.console.command.parser;

import java.util.Arrays;

public class ParsedCommand {

	private final String commandName;
	private final String[] args;

	public ParsedCommand(String commandName, String[] args) {
		this.commandName = commandName;
		this.args = args;
	}

	public String getCommand() {
		return commandName;
	}
	
	public String[] getArgs() {
		return args;
	}

	public int argsCount() {
		return args.length;
	}
	
	public String getArg(int i) {
		return args[i];
	}

	@Override
	public String toString() {
		return commandName + " (arguments = " + Arrays.toString(args) + ")";
	}
	
	
}
