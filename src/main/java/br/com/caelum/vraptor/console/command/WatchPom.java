package br.com.caelum.vraptor.console.command;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static br.com.caelum.vraptor.console.command.CommandLine.command;

public class WatchPom implements Command {

	private final static Logger LOGGER = LoggerFactory.getLogger(WatchPom.class);

	public final static String LIB_DIRECTORY = "src/main/webapp/WEB-INF/lib";
	public final static CommandLine[] COPY_DEPENDENCIES = new CommandLine[] {
		command("clean"),
		command("dependency:copy-dependencies",
			"-DoutputDirectory=" + LIB_DIRECTORY,
			"-DincludeScope=runtime", "-Dsilent=true",
			"-DprependGroupId=true") };

	public final static CommandLine[] POM_UPDATE_COMMANDS = new CommandLine[] {
			COPY_DEPENDENCIES[0],
			COPY_DEPENDENCIES[1],
			command("compile"),
			command("eclipse:eclipse", "-Dwtpversion=2.0") };

	private final Maven mvn;

	public WatchPom(Maven mvn) {
		this.mvn = new Maven();
	}

	@Override
	public void execute(ParsedCommand parsedCommand) throws Exception {
		WatchService service = FileSystems.getDefault().newWatchService();
		configureWatcher(new File("."), service, false);
		configureWatcher(new File("src/main/webapp/WEB-INF/classes"), service,
				true);
		watchForChanges(service);
	}

	private static void configureWatcher(File listeningTo,
			WatchService service, boolean recursive) throws IOException {
		if (!listeningTo.exists()) {
			return;
		}
		LOGGER.debug("Listening to " + listeningTo.getPath());
		Path path = listeningTo.toPath();
		path.register(service, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);
		if (recursive) {
			for (File f : listeningTo.listFiles()) {
				if (f.isDirectory() && f.exists()) {
					configureWatcher(f, service, recursive);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void watchForChanges(final WatchService watcher) {
		Runnable onChange = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						WatchKey key = watcher.take();
						for (WatchEvent<?> event : key.pollEvents()) {
							WatchEvent<Path> ev = (WatchEvent<Path>) event;
							Path filename = ev.context();
							String name = filename.toFile().getName();
							if (name.equals("pom.xml")) {
								LOGGER.debug("pom changed");
								runCommands();
							} else if (name.endsWith(".class")) {
								LOGGER.info("Needs to restart (class " + name
										+ " changed)");
							} else {
								LOGGER.info("Change to " + name
										+ " was ignored");
							}
						}
						key.reset();
					} catch (InterruptedException e) {
						LOGGER.warn("Unable to detect change");
					}
				}
			}

			private void runCommands() {
				for (CommandLine command : POM_UPDATE_COMMANDS) {
					runCommand(command);
				}
			}

			private void runCommand(CommandLine command) {
				try {
					mvn.execute(command);
				} catch (Exception e) {
					LOGGER.error("Unable to run " + command, e);
				}
			}
		};
		new Thread(onChange).start();
	}

}
