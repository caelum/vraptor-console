package br.com.caelum.vraptor.console.command;

public class UnitTests extends MavenCommand {

	public UnitTests() {
		super(new Maven(), "test", "surefire-report:report");
	}

}
