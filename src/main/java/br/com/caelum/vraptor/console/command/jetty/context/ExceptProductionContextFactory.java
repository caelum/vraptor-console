package br.com.caelum.vraptor.console.command.jetty.context;

import org.eclipse.jetty.webapp.WebAppContext;

import br.com.caelum.vraptor.console.command.jetty.ContextFactory;

public abstract class ExceptProductionContextFactory implements ContextFactory {

	@Override
	public boolean shouldCreate(WebAppContext context) {
		return !isProduction(context);
	}

	private String env(WebAppContext context) {
		String contextInit = context
				.getInitParameter("br.com.caelum.vraptor.environment");
		if (contextInit != null) {
			return contextInit;
		}
		
		String contextEnv = System.getenv("VRAPTOR_ENVIRONMENT");
		if (contextEnv == null) {
			return "development";
		}
		return contextEnv;
	}

	private boolean isProduction(WebAppContext context) {
		return env(context).equalsIgnoreCase("production");
	}
}
