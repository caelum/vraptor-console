package br.com.caelum.vraptor.console.command;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CommandLine {

	private final List<String> commands;

	public CommandLine(String ... commands) {
		this.commands = Arrays.asList(commands);
	}

	static CommandLine command(String ... commands) {
		return new CommandLine(commands);
	}
	
	@Override
	public String toString() {
		return commands.toString();
	}

	public String[] prepend(String prefix) {
		LinkedList<String> cmds = new LinkedList<>(commands);
		cmds.addFirst(prefix);
		return cmds.toArray(new String[cmds.size()]);
	}

}
