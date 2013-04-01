package br.com.caelum.vraptor.console.command.jetty;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import br.com.caelum.vraptor.console.command.UnitTests;

public class Jetty8VRaptorServer {

	private final Server server;
	private final ContextHandlerCollection contexts;

	public Jetty8VRaptorServer(String webappDirLocation, String webXmlLocation) {
		this.server = createServer();
		this.contexts = new ContextHandlerCollection();
		reloadContexts(webappDirLocation, webXmlLocation);
	}

	private void reloadContexts(String webappDirLocation, String webXmlLocation) {
		WebAppContext context = loadContext(webappDirLocation, webXmlLocation);
		contexts.setHandlers(new Handler[] { context, systemRestart(), unitTests(), targetContext() });
	}

	private Handler targetContext() {
		WebAppContext handler = new WebAppContext();
		handler.setResourceBase("target");
		handler.setContextPath("/target");
		return handler;
	}

	public void start() throws Exception {
		server.setHandler(contexts);
		server.start();
	}

	private static WebAppContext loadContext(String webappDirLocation, String webXmlLocation) {
		WebAppContext context = new WebAppContext();
		context.setContextPath(getContext());
		File webapp = new File(webappDirLocation);
		if (webapp.isDirectory()) {
			context.setResourceBase(webappDirLocation);
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

	private ContextHandler systemRestart() {
		AbstractHandler system = new AbstractHandler() {
			@Override
			public void handle(String target, Request baseRequest,
					HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
				restartContexts();
				response.setContentType("text/html;charset=utf-8");
				response.setStatus(HttpServletResponse.SC_OK);
				baseRequest.setHandled(true);
				response.getWriter().println("<h1>Done</h1>");
			}
		};
		ContextHandler context = new ContextHandler();
		context.setContextPath("/vraptor/restart");
		context.setResourceBase(".");
		context.setClassLoader(Thread.currentThread().getContextClassLoader());
		context.setHandler(system);
		return context;
	}

	private ContextHandler unitTests() {
		AbstractHandler system = new AbstractHandler() {
			@Override
			public void handle(String target, Request baseRequest,
					HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
				try {
					new UnitTests().execute();
					response.sendRedirect("/target/site/surefire-report.html");
				} catch (Exception e) {
					e.printStackTrace();
				}
				baseRequest.setHandled(true);
			}
		};
		ContextHandler context = new ContextHandler();
		context.setContextPath("/vraptor/tests/unit");
		context.setResourceBase(".");
		context.setClassLoader(Thread.currentThread().getContextClassLoader());
		context.setHandler(system);
		return context;
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
