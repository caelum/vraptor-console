package br.com.caelum.vraptor.console.command;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import br.com.caelum.vraptor.console.command.jetty.Main;
import br.com.caelum.vraptor.console.command.jetty.Jetty8VRaptorServer;

public class Run implements Command {

	@Override
	public void execute() throws Exception {
		new Restart().execute();
	}

}
