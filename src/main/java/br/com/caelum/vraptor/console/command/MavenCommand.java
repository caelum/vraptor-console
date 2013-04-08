package br.com.caelum.vraptor.console.command;

public abstract class MavenCommand implements Command {

	private final String[] cmd;

	MavenCommand(String... cmd) {
		this.cmd = cmd;
	}

	@Override
	public void execute(String[] args) throws Exception {
		new Maven().execute(new CommandLine(cmd));
	}

}
