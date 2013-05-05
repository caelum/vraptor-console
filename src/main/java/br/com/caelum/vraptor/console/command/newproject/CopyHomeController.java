package br.com.caelum.vraptor.console.command.newproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public class CopyHomeController implements NewProjectAction {
	
	private static final String PROJECT_PACKAGE = "PROJECT_PACKAGE";
	private File mainSource;
	private String controllerPackagePath;

	@Override
	public void execute(ParsedCommand parsedCommand, File projectHome) {
		try {
			this.mainSource = new File(projectHome, "src/main/java");
			parse(parsedCommand);
			String homeController = readHomeController();
			writeController(homeController);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String readHomeController() throws IOException {
		InputStream is = getClass().getResourceAsStream("/HomeController.java.example");
		String controllerSource = new Scanner(is).useDelimiter("$$").next();
		is.close();
		controllerSource = controllerSource.replace(PROJECT_PACKAGE, controllerPackagePath);
		return controllerSource;
	}
	
	private void writeController(String controllerSource) throws FileNotFoundException {
		File controllerPackage = new File(mainSource, controllerPackagePath.replace(".", "/"));
		controllerPackage.mkdirs();
		File controller = new File(controllerPackage, "HomeController.java");
		PrintWriter printWriter = new PrintWriter(controller);
		printWriter.print(controllerSource);
		printWriter.close();
	}
	
	private void parse(ParsedCommand parsedCommand) {
		String groupId = "br.com.caelum.vraptor.console";
		String artifcatId = "myproducts";
		if (parsedCommand.argsCount() >= 2) {
			groupId = parsedCommand.getArg(1);
			artifcatId = parsedCommand.getArg(2);
		}
		this.controllerPackagePath = groupId + "." + artifcatId + ".controller";
	}


}
