package br.com.caelum.vraptor.console.command.parser;

import java.io.InputStream;
import java.util.Scanner;

import br.com.caelum.vraptor.console.executor.CommandExecutor;

public class InteractiveCommandParser implements CommandParser {

	private final Scanner input;
	private final CommandExecutor executor;
	private final ParsedCommandFactory parsedCommandFactory;
	
	public InteractiveCommandParser(InputStream input, CommandExecutor executor, ParsedCommandFactory parsedCommandFactory) {
		this.executor = executor;
		this.parsedCommandFactory = parsedCommandFactory;
		this.input = new Scanner(input);
	}

	@Override
	public void readAll() throws Exception {
		while(input.hasNextLine()) {
			String line = input.nextLine();
			read(line);
		}
	}

	@Override
	public void read(String cmd) throws Exception {
		executor.parse(parsedCommandFactory.build(cmd));
	}

}
