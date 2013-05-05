package br.com.caelum.vraptor.console.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public class New implements Command {
	
	private static final String PROJECT_ARTIFACTID = "PROJECT_ARTIFACTID";
	private static final String PROJECT_GROUPID = "PROJECT_GROUPID";
	private static final String PROJECT_PACKAGE = "PROJECT_PACKAGE";
	private String groupId = "br.com.caelum.vraptor.console";
	private String artifcatId = "myproducts";
	private File projectHome;
	private String controllerPackagePath;
	private File controllerPackage;

	@Override
	public void execute(ParsedCommand parsedCommand) throws Exception {
		parse(parsedCommand);
		projectHome = new File(artifcatId);
		checkArgs();
		String pomContent = readBasePom();
		writePom(pomContent);
		buildDirStructure();
		String homeController = readHomeController();
		writeController(homeController);
	}

	private String readBasePom() throws IOException {
		InputStream is = getClass().getResourceAsStream("/pom.xml.example");
		String pomContent = new Scanner(is).useDelimiter("$$").next();
		is.close();
		pomContent = pomContent.replace(PROJECT_GROUPID, groupId);
		pomContent = pomContent.replace(PROJECT_ARTIFACTID, artifcatId);
		return pomContent;
	}

	private void writePom(String pomContent) throws FileNotFoundException {
		projectHome.mkdir();
		File pomFile = new File(projectHome, "pom.xml");
		PrintWriter printWriter = new PrintWriter(pomFile);
		printWriter.print(pomContent);
		printWriter.close();
	}
	
	private void buildDirStructure() {
		File mainSource = new File(projectHome, "src/main/java");
		mainSource.mkdirs();
		new File(projectHome, "src/main/resources").mkdirs();
		new File(projectHome, "src/test/java").mkdirs();
		new File(projectHome, "src/test/resources").mkdirs();
		controllerPackage = new File(mainSource, controllerPackagePath.replace(".", "/"));
		controllerPackage.mkdirs();
	}
	
	private String readHomeController() throws IOException {
		InputStream is = getClass().getResourceAsStream("/HomeController.java.example");
		String controllerSource = new Scanner(is).useDelimiter("$$").next();
		is.close();
		controllerSource = controllerSource.replace(PROJECT_PACKAGE, controllerPackagePath);
		return controllerSource;
	}
	
	private void writeController(String controllerSource) throws FileNotFoundException {
		File controller = new File(controllerPackage, "HomeController.java");
		PrintWriter printWriter = new PrintWriter(controller);
		printWriter.print(controllerSource);
		printWriter.close();
	}
	
	private void checkArgs() {
		if (projectHome.exists()) {
			throw new IllegalArgumentException(projectHome + " already exists!");
		}
	}

	private void parse(ParsedCommand parsedCommand) {
		if (parsedCommand.argsCount() >= 2) {
			groupId = parsedCommand.getArg(1);
			artifcatId = parsedCommand.getArg(2);
		}
		this.controllerPackagePath = groupId + "." + artifcatId + ".controller";
	}

}
