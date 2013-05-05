package br.com.caelum.vraptor.console.command;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public abstract class MavenCommand implements Command {

	private final String[] cmd;
	private Maven maven;

	MavenCommand(Maven maven, String... cmd) {
		this.maven = maven;
		this.cmd = cmd;
	}

	@Override
	public void execute(ParsedCommand parsedCommand) throws Exception {
		maven.execute(new CommandLine(cmd));
	}

}
