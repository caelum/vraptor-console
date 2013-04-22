package br.com.caelum.vraptor.console.command;

import java.io.File;

public abstract class MavenCommand implements Command {

	private final String[] cmd;

	MavenCommand(String... cmd) {
		this.cmd = cmd;
	}

	@Override
	public void execute(String[] args, File output) throws Exception {
		new Maven().execute(output, new CommandLine(cmd));
	}

}
