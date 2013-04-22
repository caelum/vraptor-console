package br.com.caelum.vraptor.console.command.jetty.context;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.webapp.WebAppContext;

public class TargetContext extends ExceptProductionContextFactory {

	public Handler getContext() {
		WebAppContext handler = new WebAppContext();
		handler.setResourceBase("target");
		handler.setContextPath("/target");
		return handler;
	}

}
