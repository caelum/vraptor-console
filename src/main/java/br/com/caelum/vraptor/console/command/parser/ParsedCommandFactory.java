package br.com.caelum.vraptor.console.command.parser;

import static java.util.Arrays.asList;

import java.util.LinkedList;

public class ParsedCommandFactory {

	public ParsedCommand build(String cmd) {
		String[] args = cmd.split("\\s+");
		LinkedList<String> argsList = new LinkedList<>(asList(args));
		if (argsList.size() < 1) {
		        throw new IllegalArgumentException("Command was not passed");
		}
		String commandName = argsList.removeFirst();
		return new ParsedCommand(commandName, argsList);
	}

	public static ParsedCommand commandFor(String toParse) {
		return new ParsedCommandFactory().build(toParse);
	}

}
