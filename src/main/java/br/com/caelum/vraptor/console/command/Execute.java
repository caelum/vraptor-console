package br.com.caelum.vraptor.console.command;

import java.io.File;
import java.io.IOException;

public class Execute {

	public static File inParallel(final Command cmd, final String[] args) throws IOException {
		final File output = new File("target/" + System.currentTimeMillis() + "-vraptor-console-output-" + cmd +".txt");
		output.deleteOnExit();
		Runnable target = new Runnable() {
			@Override
			public void run() {
				try {
					cmd.execute(args, output);
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
