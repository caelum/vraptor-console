package br.com.caelum.vraptor.console.command.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.reflections.Reflections;

import br.com.caelum.vraptor.console.command.Command;
import br.com.caelum.vraptor.console.command.CommandConfig;

public class CommandClassesScanner {

	private Map<String, Class<? extends Command>> commandsByName;

	public CommandClassesScanner() {
		commandsByName = new HashMap<>();
		addCommands();
	}
	
	public Class<? extends Command> commandFor(ParsedCommand parsedCommand) {
		Class<? extends Command> clazz = commandsByName.get(parsedCommand.getCommandName());
		if (clazz == null) {
			throw new NoSuchElementException("could not find command " + clazz);
		}
		return clazz;
	}

	private void addCommands() {
		Reflections reflections = new Reflections("br.com.caelum.vraptor.console.command");
		Set<Class<? extends Command>> commands = reflections.getSubTypesOf(Command.class);
		for (Class<? extends Command> commandClass : commands) {
			addCommand(commandClass);
		}
	}

	private void addCommand(Class<? extends Command> commandClass) {
		CommandConfig commandConfig = commandClass.getAnnotation(CommandConfig.class);
		String className = commandClass.getSimpleName();
		String commandName = extractName(className);
		if (commandConfig != null) {
			commandsByName.put(commandConfig.extraName(), commandClass);
		}
		commandsByName.put(commandName, commandClass);
	}

	private String extractName(String className) {
		return Character.toLowerCase(className.charAt(0)) + className.substring(1, className.length());
	}

}
