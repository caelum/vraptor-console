package br.com.caelum.vraptor.console.command;

import java.io.IOException;
import java.util.Arrays;

public class Maven {

	public void execute(CommandLine command) throws IOException, InterruptedException {
		String[] commands = command.prepend("mvn");
		System.out.println(Arrays.asList(commands));
		Process process = new ProcessBuilder(commands).inheritIO().start();
		int value = process.waitFor();
		if (value != 0) {
			System.out.println("Unable to run " + command + ", returning "
					+ value);
		}
	}

}
