package br.com.caelum.vraptor.console.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class New implements Command {
	
	private String groupId = "br.com.caelum.vraptor.console";
	private String artifcatId = "myproducts";

	@Override
	public void execute(String[] args, File output) throws Exception {
		parseArgs(args);
		String pomContent = readBasePom();
		writePom(pomContent);
	}

	private String readBasePom() throws IOException {
		InputStream is = getClass().getResourceAsStream("/pom.xml.example");
		String pomContent = new Scanner(is).useDelimiter("$$").next();
		is.close();
		pomContent = pomContent.replace("PROJECT_GROUPID", groupId);
		pomContent = pomContent.replace("PROJECT_ARTIFACTID", artifcatId);
		return pomContent;
	}

	private void writePom(String pomContent) throws FileNotFoundException {
		File projectHome = new File(artifcatId);
		if (projectHome.exists()) {
			throw new RuntimeException(projectHome + " already exists!");
		}
		projectHome.mkdir();
		File pomFile = new File(projectHome, "pom.xml");
		PrintWriter printWriter = new PrintWriter(pomFile);
		printWriter.print(pomContent);
		printWriter.close();
	}

	private void parseArgs(String[] args) {
		if (args.length >= 2) {
			groupId = args[1];
			artifcatId = args[2];
		}
	}

}
