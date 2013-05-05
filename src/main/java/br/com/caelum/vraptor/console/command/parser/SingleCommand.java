package br.com.caelum.vraptor.console.command.parser;

import br.com.caelum.vraptor.console.executor.CommandExecutor;

public class SingleCommand implements CommandParser {
	
	private final CommandExecutor executor;
	private final String command;
	private final ParsedCommandFactory parsedCommandFactory;

	public SingleCommand(String[] command, CommandExecutor executor, ParsedCommandFactory parsedCommandFactory) {
		this.parsedCommandFactory = parsedCommandFactory;
		StringBuilder all = new StringBuilder();
		for (String cmd : command) {
			all.append(cmd);
			all.append(' ');
		}
		all.append('\n');
		this.command = all.toString();
		this.executor = executor;
	}


	@Override
	public void readAll() throws Exception {
		executor.parse(parsedCommandFactory.build(command));
	}

	@Override
	public void read(String cmd) throws Exception {
		throw new UnsupportedOperationException(SingleCommand.class.getSimpleName() + " " +
				"should be used to parse a single command with readAll method");
	}

}
