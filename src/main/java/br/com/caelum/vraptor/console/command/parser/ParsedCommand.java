package br.com.caelum.vraptor.console.command.parser;

import java.util.List;

public class ParsedCommand {

	private final String commandName;
	private final List<String> args;

	public ParsedCommand(String commandName, List<String> args) {
		this.commandName = commandName;
		this.args = args;
	}

	public String getCommandName() {
		return commandName;
	}
	
	public List<String> getArgs() {
		return args;
	}

	public int argsCount() {
		return args.size();
	}
	
	/**
	 * @param i zero based index
	 * @return get the i-nth argument of this command
	 */
	public String getArg(int i) {
		return args.get(i);
	}

	@Override
	public String toString() {
		return commandName + " (arguments = " + args + ")";
	}
	
	
}
