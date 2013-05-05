package br.com.caelum.vraptor.console.command.newproject;

import java.io.File;

import br.com.caelum.vraptor.console.command.CommandLine;
import br.com.caelum.vraptor.console.command.Maven;
import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

import com.google.inject.Inject;

public class ExecuteMvnEclipse implements NewProjectAction {
	
	private final Maven mvn;
	private File mainSource;

	@Inject
	public ExecuteMvnEclipse(Maven mvn) {
		this.mvn = mvn;
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
