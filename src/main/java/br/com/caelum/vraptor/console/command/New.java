package br.com.caelum.vraptor.console.command;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.console.command.newproject.CreateDotFiles;
import br.com.caelum.vraptor.console.command.newproject.CopyHomeController;
import br.com.caelum.vraptor.console.command.newproject.ExecuteMvnEclipse;
import br.com.caelum.vraptor.console.command.newproject.NewProjectAction;
import br.com.caelum.vraptor.console.command.newproject.WritePom;
import br.com.caelum.vraptor.console.command.parser.ParsedCommand;
import br.com.caelum.vraptor.console.guice.WorkingDir;

import com.google.inject.Inject;

public class New implements Command {
	
	private String artifcatId = "myproducts";
	private File projectHome;
	private final WorkingDir wd;
	private final List<NewProjectAction> actions;
	private final Maven maven;
	
	@Inject
	public New(WorkingDir wd, Maven maven) {
		this.wd = wd;
		this.maven = maven;
		actions = buildActions();
	}

	private List<NewProjectAction> buildActions() {
		List<NewProjectAction> actions = new ArrayList<>();
		actions.add(new WritePom());
		actions.add(new ExecuteMvnEclipse(maven));
		actions.add(new CopyHomeController());
		actions.add(new CreateDotFiles());
		return actions;
	}

	@Override
	public void execute(ParsedCommand parsedCommand) throws Exception {
		if (parsedCommand.argsCount() >= 2) {
			artifcatId = parsedCommand.getArg(2);
		}
		projectHome = new File(wd.getDir(), artifcatId);
		checkArgs();
		for (NewProjectAction action : actions) {
			action.execute(parsedCommand, projectHome);
		}
	}

	private void checkArgs() {
		if (projectHome.exists()) {
			throw new IllegalArgumentException(projectHome.getAbsolutePath() + " already exists!");
		}
	}
	
}
