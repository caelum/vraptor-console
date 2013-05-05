package br.com.caelum.vraptor.console.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

public class CommandClassesScanner {

	private Map<String, Class<? extends Command>> commandsByName;

	public CommandClassesScanner() {
		commandsByName = new HashMap<>();
		addCommands();
	}
	
	public Class<? extends Command> commandFor(String name) {
		return commandsByName.get(name);
	}

	private void addCommands() {
		Reflections reflections = new Reflections("br.com.caelum.vraptor.console.command");
		Set<Class<? extends Command>> commands = reflections.getSubTypesOf(Command.class);
		System.out.println(commands);
		for (Class<? extends Command> commandClass : commands) {
			addCommand(commandClass);
		}
	}

	private void addCommand(Class<? extends Command> commandClass) {
		CommandConfig commandConfig = commandClass.getAnnotation(CommandConfig.class);
		String className = commandClass.getSimpleName();
		String commandName = extractName(className);
		if (commandConfig != null) {
			commandName = commandConfig.name();
		}
		commandsByName.put(commandName, commandClass);
	}

	private String extractName(String className) {
		return Character.toLowerCase(className.charAt(0)) + className.substring(1, className.length());
	}

}
