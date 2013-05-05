package br.com.caelum.vraptor.console.executor;

import java.io.File;
import java.util.NoSuchElementException;

import br.com.caelum.vraptor.console.command.Command;
import br.com.caelum.vraptor.console.command.parser.CommandClassesScanner;
import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public class SimpleCommandExecutor implements CommandExecutor {
	
	private CommandClassesScanner commands;

	public SimpleCommandExecutor() {
		commands = new CommandClassesScanner();
	}

	@Override
	public void parse(ParsedCommand parsedCommand) throws Exception {
		String commandName = parsedCommand.getCommand();
		try {
			Class<? extends Command> commandClass = commands.commandFor(commandName);
			Command cmd = commandClass.newInstance();
			File tmp = getTempFile(commandName);
			cmd.execute(parsedCommand, tmp);
		} catch (NoSuchElementException e) {
			System.err.println("Command " + commandName + " not found");
		}
	}

	private File getTempFile(String commandName) {
		File tmp = new File("target/" + System.currentTimeMillis() + "-vraptor-console-" + commandName + ".txt");
		tmp.deleteOnExit();
		return tmp;
	}

}
