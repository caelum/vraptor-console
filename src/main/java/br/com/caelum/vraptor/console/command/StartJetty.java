package br.com.caelum.vraptor.console.command;

import java.io.File;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

@CommandConfig(extraName=StartJetty.COMMAND_NAME)
public class StartJetty implements Command {
	
	static final String COMMAND_NAME= "start-jetty";

	@Override
	public void execute(ParsedCommand parsedCommand, File output) throws Exception {
		RunningServer.restart(getWebAppDir(parsedCommand));
	}
	
	private String getWebAppDir(ParsedCommand parsedCommand) {
		return parsedCommand.argsCount() > 1 ? parsedCommand.getArg(1) : getPropertyWebAppDir();
	}
	
	private static String getPropertyWebAppDir() {
		return System.getProperty("vraptor.webappdir", "src/main/webapp/");
	}


}
