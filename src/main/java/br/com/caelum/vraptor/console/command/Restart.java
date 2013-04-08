package br.com.caelum.vraptor.console.command;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Restart implements Command {

	@Override
	public void execute(String[] args) throws Exception {
		new Maven().execute(new CommandLine("compile"));
		if (new File("jetty").exists()) {
			customJetty();
		} else {
			RunningServer.restart(getWebAppDir(args));
		}
	}

	private String getWebAppDir(String[] args) {
		return args.length > 1 ? args[1] : getPropertyWebAppDir();
	}
	
	private static String getPropertyWebAppDir() {
		return System.getProperty("vraptor.webappdir", "src/main/webapp/");
	}



	private void customNotImplementedJetty() throws MalformedURLException,
			NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		// use esse diretorio
		URLClassLoader loader = new URLClassLoader(
				new URL[] { new File("jetty").toURL() }, this.getClass()
						.getClassLoader());
		Class<?> type;
		try {
			type = loader.loadClass(this.getClass().getPackage().getName()
					+ ".Main");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		Method method = type.getMethod("main", new Class[] { String[].class });
		Object instance = type.newInstance();
		method.invoke(instance, new String[] {});
	}

	private void customJetty() {
		throw new UnsupportedOperationException(
				"/jetty dir found, but we haven't implemented "
						+ "custom jetty servers support, contact the developers for more info");
	}

}
