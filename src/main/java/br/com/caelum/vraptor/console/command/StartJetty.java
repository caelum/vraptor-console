package br.com.caelum.vraptor.console.command;

import java.io.File;

@CommandConfig(name=StartJetty.COMMAND_NAME)
public class StartJetty implements Command {
	
	static final String COMMAND_NAME= "start-jetty";

	@Override
	public void execute(String[] args, File output) throws Exception {
		RunningServer.restart(getWebAppDir(args));
	}
	
	private String getWebAppDir(String[] args) {
		return args.length > 1 ? args[1] : getPropertyWebAppDir();
	}
	
	private static String getPropertyWebAppDir() {
		return System.getProperty("vraptor.webappdir", "src/main/webapp/");
	}


}
