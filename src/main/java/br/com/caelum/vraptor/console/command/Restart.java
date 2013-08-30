package br.com.caelum.vraptor.console.command;

import java.io.File;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

import com.google.inject.Inject;

public class Restart implements Command {

	private final Maven maven;

	@Inject
	public Restart(Maven maven) {
		this.maven = maven;
	}

	@Override
	public void execute(ParsedCommand parsedCommand) throws Exception {
		compileAndCopyDeps();
		if (new File("src/jetty").exists()) {
			customJetty();
		} else {
			new StartJetty().execute(parsedCommand);
		}
	}

	private void compileAndCopyDeps() {
		maven.execute(WatchPom.POM_UPDATE_COMMANDS);
	}

	private void customJetty() {
		throw new UnsupportedOperationException(
				"/jetty dir found, but we haven't implemented "
						+ "custom jetty servers support, contact the developers for more info");
	}

}
