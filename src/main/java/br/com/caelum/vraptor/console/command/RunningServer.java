package br.com.caelum.vraptor.console.command;

import br.com.caelum.vraptor.console.command.jetty.Main;
import br.com.caelum.vraptor.console.command.jetty.Jetty8VRaptorServer;

public class RunningServer {

	private static Jetty8VRaptorServer server;

	public synchronized static void restart() throws Exception {
		if(server==null) start();
		else server.restartContexts();
	}

	private static void start() throws Exception {
		RunningServer.server = Main.startServer();
	}

	public synchronized static void stop() throws Exception {
		server.stop();
		server = null;
	}

}
