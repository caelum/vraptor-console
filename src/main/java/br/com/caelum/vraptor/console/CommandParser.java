package br.com.caelum.vraptor.console;

import java.io.InputStream;
import java.util.Scanner;

import br.com.caelum.vraptor.console.executor.CommandExecutor;

public class CommandParser {

	private final Scanner input;
	private final CommandExecutor executor;
	
	public CommandParser(InputStream input, CommandExecutor executor) {
		this.executor = executor;
		this.input = new Scanner(input);
	}

	public CommandParser(String[] commands, CommandExecutor executor) {
		StringBuilder all = new StringBuilder();
		for (String cmd : commands) {
			all.append(cmd);
			all.append('\n');
		}
		this.input = new Scanner(all.toString());
		this.executor = executor;
	}

	public void readAll() throws Exception {
		while(input.hasNextLine()) {
			String line = input.nextLine();
			read(line);
		}
	}

	public void read(String cmd) throws Exception {
		executor.parse(cmd);
	}

}
