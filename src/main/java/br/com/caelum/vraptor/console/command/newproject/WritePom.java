package br.com.caelum.vraptor.console.command.newproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public class WritePom implements NewProjectAction {
	
	private static final String PROJECT_ARTIFACTID = "PROJECT_ARTIFACTID";
	private static final String PROJECT_GROUPID = "PROJECT_GROUPID";
	private String groupId = "br.com.caelum.vraptor.console";
	private String artifcatId = "myproducts";

	@Override
	public void execute(ParsedCommand parsedCommand, File projectHome) {
		parse(parsedCommand);
		try {
			String pomContent = readBasePom();
			writePom(projectHome, pomContent);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private String readBasePom() throws IOException {
		InputStream is = getClass().getResourceAsStream("/pom.xml.example");
		String pomContent = new Scanner(is).useDelimiter("$$").next();
		is.close();
		pomContent = pomContent.replace(PROJECT_GROUPID, groupId);
		pomContent = pomContent.replace(PROJECT_ARTIFACTID, artifcatId);
		return pomContent;
	}

	private void writePom(File projectHome, String pomContent) throws FileNotFoundException {
		projectHome.mkdir();
		File pomFile = new File(projectHome, "pom.xml");
		PrintWriter printWriter = new PrintWriter(pomFile);
		printWriter.print(pomContent);
		printWriter.close();
	}
	
	private void parse(ParsedCommand parsedCommand) {
		if (parsedCommand.argsCount() >= 2) {
			groupId = parsedCommand.getArg(0);
			artifcatId = parsedCommand.getArg(1);
		}
	}



}
