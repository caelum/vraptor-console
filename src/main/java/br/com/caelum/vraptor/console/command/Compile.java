package br.com.caelum.vraptor.console.command;

import java.io.IOException;

public class Compile implements Command {

	@Override
	public void execute() throws IOException, InterruptedException  {
		new Maven().execute(new CommandLine("compile"));
	}

}
