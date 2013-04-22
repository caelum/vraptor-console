package br.com.caelum.vraptor.console.command;

import java.io.File;

public class DelegateCommand implements Command {

	private final Command delegate;

	public DelegateCommand(Command delegate) {
		this.delegate = delegate;
	}

	public void execute(String[] args, File output) throws Exception {
		delegate.execute(args, output);
	}

	
}
