package br.com.caelum.vraptor.console.command;


public class Run implements Command {

	@Override
	public void execute(String[] args) throws Exception {
		new Restart().execute(args);
	}

}
