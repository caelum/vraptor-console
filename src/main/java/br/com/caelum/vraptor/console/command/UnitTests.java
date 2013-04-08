package br.com.caelum.vraptor.console.command;


public class UnitTests extends MavenCommand {

	UnitTests() {
		super("test", "surefire-report:report");
	}

}
