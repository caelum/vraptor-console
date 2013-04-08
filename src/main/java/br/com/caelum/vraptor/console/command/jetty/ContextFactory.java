package br.com.caelum.vraptor.console.command.jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.webapp.WebAppContext;

public interface ContextFactory {

	abstract Handler getContext();

	abstract boolean shouldCreate(WebAppContext context);

}
