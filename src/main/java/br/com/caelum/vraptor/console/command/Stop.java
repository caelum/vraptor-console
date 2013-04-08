package br.com.caelum.vraptor.console.command;


public class Stop implements Command {

	@Override
	public void execute(String[] args) throws Exception {
		RunningServer.stop();
	}

}
