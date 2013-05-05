package br.com.caelum.vraptor.console.command;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public class DelegateCommand implements Command {

	private final Command delegate;

	public DelegateCommand(Command delegate) {
		this.delegate = delegate;
	}

	public void execute(ParsedCommand parsedCommand) throws Exception {
		delegate.execute(parsedCommand);
	}

	
}
