package br.com.caelum.vraptor.console.command;

import br.com.caelum.vraptor.console.command.jetty.JettyMain;
import br.com.caelum.vraptor.console.command.jetty.Jetty8VRaptorServer;

public class RunningServer {

	private static Jetty8VRaptorServer server;

	public synchronized static void restart(String context) throws Exception {
		if(server==null) start(context);
		else server.restartContexts();
	}

	private static void start(String context) throws Exception {
		RunningServer.server = JettyMain.startServer(context);
	}

	public synchronized static void stop() throws Exception {
		if(server!=null) {
			server.stop();
			server = null;
		}
	}

}
