package br.com.caelum.vraptor.console.command;

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
	}

}
