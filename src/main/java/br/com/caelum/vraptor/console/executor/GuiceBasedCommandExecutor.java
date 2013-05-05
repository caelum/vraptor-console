package br.com.caelum.vraptor.console.executor;

import br.com.caelum.vraptor.console.guice.VRaptorConsoleModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceBasedCommandExecutor implements CommandExecutor {

	private Injector injector;

	public GuiceBasedCommandExecutor() {
		injector = Guice.createInjector(new VRaptorConsoleModule());
	}

	@Override
	public void parse(String line) throws Exception {
	}

}
