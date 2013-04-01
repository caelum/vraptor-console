package br.com.caelum.vraptor.console.command;

import java.io.File;

public class Restart implements Command {

	@Override
	public void execute() throws Exception {
		new Maven().execute(new CommandLine("compile"));
		if (new File("jetty").exists()) {
			customJetty();
		} else {
			RunningServer.restart();
		}
	}

	private void customJetty() {
		throw new UnsupportedOperationException("/jetty dir found, but we haven't implemented " +
				"custom jetty servers support, contact the developers for more info");
		
	}
	

}
