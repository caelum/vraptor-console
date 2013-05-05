package br.com.caelum.vraptor.console.command;

import java.io.File;
import java.io.IOException;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public class Execute {

	public static File inParallel(final Command cmd, final ParsedCommand parsedCommand) throws IOException {
		final File output = new File("target/" + System.currentTimeMillis() + "-vraptor-console-output-" + cmd +".txt");
		output.deleteOnExit();
		Runnable target = new Runnable() {
			@Override
			public void run() {
				try {
					cmd.execute(parsedCommand);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Thread thread = new Thread(target);
		thread.start();
		return output;
	}

}
