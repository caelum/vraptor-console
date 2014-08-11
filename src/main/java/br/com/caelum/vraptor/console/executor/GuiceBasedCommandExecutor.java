package br.com.caelum.vraptor.console.executor;

import br.com.caelum.vraptor.console.command.Command;
import br.com.caelum.vraptor.console.command.parser.CommandClassesScanner;
import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public class GuiceBasedCommandExecutor implements CommandExecutor {

	private CommandClassesScanner commands;

	public GuiceBasedCommandExecutor() {
		commands = new CommandClassesScanner();
	}

	@Override
	public void parse(ParsedCommand parsedCommand) throws Exception {
		Class<? extends Command> commandClass = commands.commandFor(parsedCommand);
		Command command = commandClass.newInstance();
		command.execute(parsedCommand);
	}

}
