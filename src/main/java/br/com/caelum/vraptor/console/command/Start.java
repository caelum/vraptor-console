package br.com.caelum.vraptor.console.command;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;
import br.com.caelum.vraptor.undertown.builder.ServerBuilder;

import com.google.inject.Inject;

public class Start implements Command {
	
	private final Maven maven;
	private String webContentPath;

	@Inject
	public Start(Maven maven) {
		this.maven = maven;
	}

	@Override
	public void execute(ParsedCommand parsedCommand) throws Exception {
		maven.execute(new CommandLine("clean"), new CommandLine("compile"), WatchPom.COPY_DEPENDENCIES[1]);
		webContentPath = "src/main/webapp";
		String webInf = webContentPath + "/WEB-INF";
		
		URL classesDir = new File(webInf, "classes").toURI().toURL();
		File libsDir = new File(webInf, "lib");
		File[] jars = libsDir.listFiles(jarsFilter());
		
		Set<URL> urlsToLoad = new HashSet<>();
		urlsToLoad.add(classesDir);
		for (File file : jars) {
			urlsToLoad.add(new URL("jar:file:" + file.getAbsolutePath() +"!/"));
		}
		URL[] urls = urlsToLoad.toArray(new URL[urlsToLoad.size()]);
		ClassLoader loader = URLClassLoader.newInstance(urls, this.getClass().getClassLoader()); 
		startContainer(loader);
	}


	private void startContainer(final ClassLoader loader) {
		ServerBuilder
			.context("/app")
			.webAppFolder(webContentPath)
			.warName("app")
			.port(8080)
			.address("localhost")
			.setClassLoader(loader)
			.start();
	}

	private FilenameFilter jarsFilter() {
		return new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".jar");
			}
		};
	}
	
}