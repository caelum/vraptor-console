package br.com.caelum.vraptor.console.command;

import java.io.IOException;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public class Execute {

	public static void inParallel(final Command cmd, final ParsedCommand parsedCommand) throws IOException {
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
	}

}
