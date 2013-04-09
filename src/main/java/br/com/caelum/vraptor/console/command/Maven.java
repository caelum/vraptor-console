package br.com.caelum.vraptor.console.command;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Maven {

	private static final Logger LOGGER = LoggerFactory.getLogger(Maven.class);

	public void execute(CommandLine... commands) {
		for (CommandLine c : commands)
			execute(c);
	}

	private void execute(CommandLine command) {
		try {
			String[] commands = command.prepend("mvn");
			System.out.println(Arrays.asList(commands));
			Process process = new ProcessBuilder(commands).inheritIO().start();
			int value = process.waitFor();
			if (value != 0) {
				LOGGER.error("Unable to run " + command + ", returning "
						+ value);
			}
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
