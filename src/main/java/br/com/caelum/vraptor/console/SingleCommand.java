package br.com.caelum.vraptor.console;

import br.com.caelum.vraptor.console.executor.CommandExecutor;

public class SingleCommand implements CommandParser {
	
	private final CommandExecutor executor;
	private final String command;

	public SingleCommand(String[] command, CommandExecutor executor) {
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
		executor.parse(command);
	}

	@Override
	public void read(String cmd) throws Exception {
		executor.parse(cmd);
	}

}
