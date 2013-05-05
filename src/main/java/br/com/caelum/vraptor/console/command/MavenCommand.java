package br.com.caelum.vraptor.console.command;

import java.io.File;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public abstract class MavenCommand implements Command {

	private final String[] cmd;

	MavenCommand(String... cmd) {
		this.cmd = cmd;
	}

	@Override
	public void execute(ParsedCommand parsedCommand, File output) throws Exception {
		new Maven().execute(output, new CommandLine(cmd));
	}

}
