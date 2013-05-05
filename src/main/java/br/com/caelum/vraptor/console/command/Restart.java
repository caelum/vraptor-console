package br.com.caelum.vraptor.console.command;

import java.io.File;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public class Restart implements Command {

	@Override
	public void execute(ParsedCommand parsedCommand, File output) throws Exception {
		compileAndCopyDeps(output);
		if (new File("src/jetty").exists()) {
			customJetty();
		} else {
			new StartJetty().execute(parsedCommand, output);
		}
	}

	private void compileAndCopyDeps(File output) {
		new Maven().execute(output, new CommandLine("compile"), WatchPom.COPY_DEPENDENCIES);
	}

	private void customJetty() {
		throw new UnsupportedOperationException(
				"/jetty dir found, but we haven't implemented "
						+ "custom jetty servers support, contact the developers for more info");
	}

}
