package br.com.caelum.vraptor.console.command;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

@CommandConfig(extraName=StartJetty.COMMAND_NAME)
public class StartJetty implements Command {
	
	static final String COMMAND_NAME= "start-jetty";

	@Override
	public void execute(ParsedCommand parsedCommand) throws Exception {
		RunningServer.restart(getWebAppDir(parsedCommand));
	}
	
	private String getWebAppDir(ParsedCommand parsedCommand) {
		return parsedCommand.argsCount() > 1 ? parsedCommand.getArg(0) : getPropertyWebAppDir();
	}
	
	private static String getPropertyWebAppDir() {
		return System.getProperty("vraptor.webappdir", "src/main/webapp/");
	}


}
