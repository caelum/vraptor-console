package br.com.caelum.vraptor.console.command;

import java.io.File;

public class Distribution implements Command {

	@Override
	public void execute() throws Exception {
		new War().execute();
		File war = new File("target/myproducts-1.0.0-SNAPSHOT.war");
	}

}
