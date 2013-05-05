package br.com.caelum.vraptor.console.command;

import com.google.inject.Inject;

public class Clean extends MavenCommand {

	@Inject
	public Clean(Maven maven) {
		super(maven, "clean");
	}
}
