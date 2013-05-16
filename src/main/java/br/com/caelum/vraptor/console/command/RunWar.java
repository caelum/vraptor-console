package br.com.caelum.vraptor.console.command;

import br.com.caelum.vraptor.console.command.jetty.Jetty8VRaptorServer;
import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public class RunWar implements Command {

	@Override
	public void execute(ParsedCommand parsedCommand) throws Exception {
		if (parsedCommand.argsCount() < 1) {
			throw new IllegalArgumentException("you should provide the war path as argument!");
		}
		String warPath = parsedCommand.getArg(0);
		Jetty8VRaptorServer jettyVraptor = new Jetty8VRaptorServer();
		jettyVraptor.runWar(warPath);
	}

}
