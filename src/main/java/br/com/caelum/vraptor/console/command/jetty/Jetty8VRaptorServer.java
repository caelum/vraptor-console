package br.com.caelum.vraptor.console.command.jetty;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import br.com.caelum.vraptor.console.command.WatchPom;
import br.com.caelum.vraptor.console.command.jetty.context.ExceptProductionContextFactory;
import br.com.caelum.vraptor.console.command.jetty.context.SystemRestartContext;
import br.com.caelum.vraptor.console.command.jetty.context.TargetContext;
import br.com.caelum.vraptor.console.command.jetty.context.UnitTestsContext;

public class Jetty8VRaptorServer {

	private final Server server;
	private final ContextHandlerCollection contexts;

	public Jetty8VRaptorServer() {
		this.server = createServer();
		this.contexts = new ContextHandlerCollection();
	}

	void loadContextsFromWebappDir(String webappDirLocation, String webXmlLocation) {
		WebAppContext context = loadContext(webappDirLocation, webXmlLocation);
		contexts.setHandlers(getHandlers(context));
	}
	
	public void runWar(String warPath) throws Exception {
		WebAppContext context = contextWithPath();
		context.setWar(warPath);
		server.setHandler(context);
		server.start();
	}

	private WebAppContext contextWithPath() {
		WebAppContext context = new WebAppContext();
		context.setContextPath(getContext());
		return context;
	}

	private Handler[] getHandlers(WebAppContext context) {
		List<ExceptProductionContextFactory> factories = Arrays.asList(
				new SystemRestartContext(this), new UnitTestsContext(),
				new TargetContext());

		List<Handler> handlers = new ArrayList<>();
		handlers.add(context);
		for (ContextFactory factory : factories) {
			if (factory.shouldCreate(context)) {
				handlers.add(factory.getContext());
			}
		}
		return handlers.toArray(new Handler[0]);
	}

	public void start() throws Exception {
		server.setHandler(contexts);
		server.start();
	}

	private WebAppContext loadContext(String webappDirLocation,
			String webXmlLocation) {
		WebAppContext context = contextWithPath();
		String allJars = getJars();
		context.setResourceBase(webappDirLocation);
		context.setExtraClasspath(allJars);
		context.setDescriptor(webXmlLocation);
		context.setParentLoaderPriority(true);
		return context;
	}

	private String getJars() {
		File libDir = new File(WatchPom.LIB_DIRECTORY);
		StringBuilder path = new StringBuilder();
		if (!libDir.exists()) {
			return "";
		}
		for (File lib : libDir.listFiles()) {
			String name = lib.getAbsolutePath();
			if (name.endsWith(".jar")) {
				path.append(name).append(';');
			}
		}
		if (path.toString().isEmpty())
			return "";
		return path.toString().substring(0, path.length() - 1);
	}

	private static String getContext() {
		return System.getProperty("vraptor.context", "/");
	}

	public void restartContexts() {
		try {
			contexts.stop();
			contexts.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Server createServer() {
		String webPort = getPort();
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}
		Server server = new Server(Integer.valueOf(webPort));
		return server;
	}

	private String getPort() {
		return System.getenv("PORT");
	}

	public void stop() throws Exception {
		server.stop();
	}

}
