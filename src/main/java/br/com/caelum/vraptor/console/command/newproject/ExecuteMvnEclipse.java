package br.com.caelum.vraptor.console.command.newproject;

import br.com.caelum.vraptor.console.command.CommandLine;
import br.com.caelum.vraptor.console.command.Maven;
import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

import java.io.File;

public class ExecuteMvnEclipse implements NewProjectAction {
	
	private final Maven mvn;
	private File mainSource;

	public ExecuteMvnEclipse(Maven mvn) {
		this.mvn = new Maven();
	}

	@Override
	public void execute(ParsedCommand parsedCommand, File projectHome) {
		buildSourceFolders(projectHome);
		mvn.useWorkingDir(projectHome);
		mvn.execute(new CommandLine("eclipse:eclipse"));
	}

	private void buildSourceFolders(File projectHome) {
		this.mainSource = new File(projectHome, "src/main/java");
		mainSource.mkdirs();
		new File(projectHome, "src/main/resources").mkdirs();
		new File(projectHome, "src/test/java").mkdirs();
		new File(projectHome, "src/test/resources").mkdirs();
	}
}
