package br.com.caelum.vraptor.console;

import java.io.InputStream;
import java.net.URL;

import br.com.caelum.vraptor.console.executor.CommandExecutor;
import br.com.caelum.vraptor.console.executor.LogAndDelegate;
import br.com.caelum.vraptor.console.executor.LogExceptionExecutor;
import br.com.caelum.vraptor.console.executor.SimpleCommandExecutor;

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
		parser.read("watchPom");
		parser.readAll();
	}

	private static CommandParser getParser(String[] args, CommandExecutor executor)
			throws Exception {
		if (args.length != 0) {
			String[] commands = args;
			return new SingleCommand(commands, executor);
		}
		InputStream input = System.in;
		return new InteractiveCommandParser(input, executor);
	}

	private static CommandExecutor grabExecutor() {
		boolean DEBUG = true;
		CommandExecutor executor = new SimpleCommandExecutor();
		if (DEBUG) {
			executor = new LogAndDelegate(executor);
		}
		executor = new LogExceptionExecutor(executor);
		return executor;
	}

}
