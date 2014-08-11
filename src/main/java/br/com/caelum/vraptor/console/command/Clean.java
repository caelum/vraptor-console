package br.com.caelum.vraptor.console.command;


public class Clean extends MavenCommand {

	public Clean() {
		super(new Maven(), "clean");
	}
}
