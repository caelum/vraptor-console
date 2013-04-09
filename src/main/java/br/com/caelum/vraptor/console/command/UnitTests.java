package br.com.caelum.vraptor.console.command;

public class UnitTests extends MavenCommand {

	public UnitTests() {
		super("test", "surefire-report:report");
	}

}
