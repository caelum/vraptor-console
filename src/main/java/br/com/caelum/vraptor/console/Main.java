package br.com.caelum.vraptor.console;

import java.io.InputStream;
import java.net.URL;

import br.com.caelum.vraptor.console.command.parser.CommandParser;
import br.com.caelum.vraptor.console.command.parser.InteractiveCommandParser;
import br.com.caelum.vraptor.console.command.parser.ParsedCommandFactory;
import br.com.caelum.vraptor.console.command.parser.SingleCommand;
import br.com.caelum.vraptor.console.executor.CommandExecutor;
import br.com.caelum.vraptor.console.executor.GuiceBasedCommandExecutor;
import br.com.caelum.vraptor.console.executor.LogAndDelegate;
import br.com.caelum.vraptor.console.executor.LogExceptionExecutor;

/**
 * @author Chico Sokol
 * @author Guilherme Silveira
 */
public class Main {

	public static void main(String[] args) throws Exception {
		URL policyUrl = Main.class.getResource("/vraptor.policy");
		System.setProperty("java.security.policy", policyUrl.toString());
		
		CommandExecutor executor = grabExecutor();

		System.out.println("Starting VRaptor Console");
		CommandParser parser = getParser(args, executor);
		parser.readAll();
	}

	private static CommandParser getParser(String[] args, CommandExecutor executor)
			throws Exception {
		if (args.length != 0) {
			String[] commands = args;
			return new SingleCommand(commands, executor, new ParsedCommandFactory());
		}
		InputStream input = System.in;
		InteractiveCommandParser parser = new InteractiveCommandParser(input, executor, new ParsedCommandFactory());
		parser.read("watchPom");
		return parser;
	}

	private static CommandExecutor grabExecutor() {
		boolean DEBUG = true;
		CommandExecutor executor = new GuiceBasedCommandExecutor();
		if (DEBUG) {
			executor = new LogAndDelegate(executor);
		}
		executor = new LogExceptionExecutor(executor);
		return executor;
	}

}
