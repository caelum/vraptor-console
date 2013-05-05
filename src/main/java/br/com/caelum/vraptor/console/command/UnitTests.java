package br.com.caelum.vraptor.console.command;

import com.google.inject.Inject;

public class UnitTests extends MavenCommand {

	@Inject
	public UnitTests(Maven maven) {
		super(maven, "test", "surefire-report:report");
	}

}
