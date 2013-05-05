package br.com.caelum.vraptor.console.executor;

import br.com.caelum.vraptor.console.command.parser.CommandClassesScanner;
import br.com.caelum.vraptor.console.command.parser.ParsedCommand;
import br.com.caelum.vraptor.console.guice.VRaptorConsoleModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceBasedCommandExecutor implements CommandExecutor {

	private Injector injector;
	private CommandClassesScanner commands;

	public GuiceBasedCommandExecutor() {
		injector = Guice.createInjector(new VRaptorConsoleModule());
		commands = new CommandClassesScanner();
	}

	@Override
	public void parse(ParsedCommand parsedCommand) throws Exception {
		
	}

}
