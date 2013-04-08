package br.com.caelum.vraptor.console.command.jetty.context;

import org.eclipse.jetty.server.handler.ContextHandler;

import br.com.caelum.vraptor.console.command.jetty.Jetty8VRaptorServer;

public class SystemRestartContext extends ExceptProductionContextFactory {

	private final Jetty8VRaptorServer server;

	public SystemRestartContext(Jetty8VRaptorServer server) {
		this.server = server;
	}

	public ContextHandler getContext() {
		SimpleContext context = new SimpleContext("/vraptor/restart", new Runnable() {
			public void run() {
				server.restartContexts();
			}
		});
		return context.build();
	}

}
