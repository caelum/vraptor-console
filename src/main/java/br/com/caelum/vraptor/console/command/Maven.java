package br.com.caelum.vraptor.console.command;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Maven {

	private static final Logger LOGGER = LoggerFactory.getLogger(Maven.class);

	public void execute(File output, CommandLine... commands) {
		for (CommandLine c : commands)
			execute(output, c);
	}

	private void execute(File output, CommandLine command) {
		try {
			String[] commands = command.prepend("mvn");
			System.out.println(Arrays.asList(commands));
			Process process = new ProcessBuilder(commands).redirectErrorStream(
					true).start();
			RedirectOutput outputStream = new RedirectOutput(output,
					process.getInputStream());
			new Thread(outputStream).start();
			waitTillFinish(command, process);
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void waitTillFinish(CommandLine command, Process process)
			throws InterruptedException {
		int value = process.waitFor();
		if (value != 0) {
			LOGGER.error("Unable to run " + command + ", returning " + value);
		}
	}

}
