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
	private File projectHome;

	@Override
	public void execute(ParsedCommand parsedCommand, File projectHome) {
		this.projectHome = projectHome;
		try {
			this.mainSource = new File(projectHome, "src/main/java");
			parse(parsedCommand);
			String homeController = readHomeController();
			writeController(homeController);
			copyHomeJsp();
			copyWebXml();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void copyHomeJsp() throws IOException {
		String jspContent = read("/home.jsp.example");
		File jspDir = new File(projectHome, "src/main/webapp/WEB-INF/jsp/home/");
		jspDir.mkdirs();
		File jsp = new File(jspDir, "home.jsp");
		write(jspContent, jsp);
	}
	
	private void copyWebXml() throws IOException {
		String webxml = read("/web.xml.example");
		File webxmlDir = new File(projectHome, "src/main/webapp/WEB-INF/");
		webxmlDir.mkdirs();
		File jsp = new File(webxmlDir, "web.xml");
		write(webxml, jsp);
	}

	private String readHomeController() throws IOException {
		String controllerSource = read("/HomeController.java.example");
		controllerSource = controllerSource.replace(PROJECT_PACKAGE, controllerPackagePath);
		return controllerSource;
	}

	private String read(String path) throws IOException {
		InputStream is = getClass().getResourceAsStream(path);
		String controllerSource = new Scanner(is).useDelimiter("$$").next();
		is.close();
		return controllerSource;
	}
	
	private void writeController(String controllerSource) throws FileNotFoundException {
		File controllerPackage = new File(mainSource, controllerPackagePath.replace(".", "/"));
		controllerPackage.mkdirs();
		File controller = new File(controllerPackage, "HomeController.java");
		write(controllerSource, controller);
	}

	private void write(String text, File file)
			throws FileNotFoundException {
		PrintWriter printWriter = new PrintWriter(file);
		printWriter.print(text);
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
