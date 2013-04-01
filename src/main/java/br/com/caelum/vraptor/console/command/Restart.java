package br.com.caelum.vraptor.console.command;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Restart implements Command {

	@Override
	public void execute() throws Exception {
		new Maven().execute(new CommandLine("compile"));
		if (new File("jetty").exists()) {
			customNotImplementedJetty();
		} else {
			RunningServer.restart();
		}
	}
	
	private void customNotImplementedJetty() throws MalformedURLException,
			ClassNotFoundException, NoSuchMethodException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		// use esse diretorio
		URLClassLoader loader = new URLClassLoader(new URL[] { new File(
				"jetty").toURL() }, this.getClass().getClassLoader());
		Class<?> type = loader.loadClass("br.....Main");
		Method method = type.getMethod("main",
				new Class[] { String[].class });
		Object instance = type.newInstance();
		method.invoke(instance, new String[] {});
	}

}
