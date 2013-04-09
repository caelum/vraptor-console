package br.com.caelum.vraptor.console.command.jetty;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import br.com.caelum.vraptor.console.command.jetty.context.ExceptProductionContextFactory;
import br.com.caelum.vraptor.console.command.jetty.context.SystemRestartContext;
import br.com.caelum.vraptor.console.command.jetty.context.TargetContext;
import br.com.caelum.vraptor.console.command.jetty.context.UnitTestsContext;

public class Jetty8VRaptorServer {

	private final Server server;
	private final ContextHandlerCollection contexts;

	public Jetty8VRaptorServer(String webappDirLocation, String webXmlLocation) throws Exception {
		this.server = createServer();
		this.contexts = new ContextHandlerCollection();
		reloadContexts(webappDirLocation, webXmlLocation);
		start();
	}

	private void reloadContexts(String webappDirLocation, String webXmlLocation) {
		WebAppContext context = loadContext(webappDirLocation, webXmlLocation);
		contexts.setHandlers(getHandlers(context));
	}

	private Handler[] getHandlers(WebAppContext context) {
		List<ExceptProductionContextFactory> factories = Arrays.asList(new SystemRestartContext(this), new UnitTestsContext(), new TargetContext());
		
		List<Handler> handlers = new ArrayList<>();
		handlers.add(context);
		for (ContextFactory factory : factories) {
			if(factory.shouldCreate(context)) {
				handlers.add(factory.getContext());
			}
		}
		return handlers.toArray(new Handler[0]);
	}

	private void start() throws Exception {
		server.setHandler(contexts);
		server.start();
	}

	private static WebAppContext loadContext(String webappDirLocation, String webXmlLocation) {
		WebAppContext context = new WebAppContext();
		context.setContextPath(getContext());
		File webapp = new File(webappDirLocation);
		if (webapp.isDirectory()) {
			context.setResourceBase(webappDirLocation);
			context.setExtraClasspath("target/classes");
			context.setDescriptor(webXmlLocation);
		} else {
			context.setWar(webappDirLocation);
		}
		context.setParentLoaderPriority(true);
		return context;
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

	private static Server createServer() {
		String webPort = getPort();
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}
		Server server = new Server(Integer.valueOf(webPort));
		return server;
	}

	private static String getPort() {
		return System.getenv("PORT");
	}

	public void stop() throws Exception {
		server.stop();
	}

}
