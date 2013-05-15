package br.com.caelum.vraptor.console.command.newproject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public class CreateDotFiles implements NewProjectAction {

	@Override
	public void execute(ParsedCommand parsedCommand, File projectHome) {
		try {
			new File(projectHome, ".vraptor").createNewFile();
			File gitignore = new File(projectHome, ".gitignore");
			PrintWriter printWriter = new PrintWriter(gitignore);
			printWriter.println("bin/");
			printWriter.println("target/");
			printWriter.println("*.class");
			printWriter.println("*.jar");
			printWriter.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
