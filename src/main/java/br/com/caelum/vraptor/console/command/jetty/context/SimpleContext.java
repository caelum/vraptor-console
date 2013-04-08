package br.com.caelum.vraptor.console.command.jetty.context;

import java.io.IOException;
import java.util.concurrent.Callable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;

public class SimpleContext {

	private final String uri;
	private final Callable<String> action;

	public SimpleContext(String uri, final Runnable runnable) {
		this(uri, new Callable<String>() {
			@Override
			public String call() throws Exception {
				runnable.run();
				return "<html>Done!</html>";
			}
		});
	}

	public SimpleContext(String uri, Callable<String> action) {
		this.uri = uri;
		this.action = action;
	}

	public ContextHandler build() {
		AbstractHandler system = new AbstractHandler() {
			@Override
			public void handle(String target, Request baseRequest,
					HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
				try {
					String content = action.call();
					response.setContentType("text/html;charset=utf-8");
					response.setStatus(HttpServletResponse.SC_OK);
					baseRequest.setHandled(true);
					response.getWriter().println(content);
				} catch (Exception e) {
					throw new ServletException(e);
				}
			}
		};
		ContextHandler context = new ContextHandler();
		context.setContextPath(uri);
		context.setResourceBase(".");
		context.setClassLoader(Thread.currentThread().getContextClassLoader());
		context.setHandler(system);
		return context;
	}

}
