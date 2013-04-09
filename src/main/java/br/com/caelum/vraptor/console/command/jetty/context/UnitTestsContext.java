package br.com.caelum.vraptor.console.command.jetty.context;

import java.util.concurrent.Callable;

import org.eclipse.jetty.server.Handler;

import br.com.caelum.vraptor.console.command.UnitTests;

public class UnitTestsContext extends ExceptProductionContextFactory {

	@Override
	public Handler getContext() {
		return new SimpleContext("/vraptor/tests/unit", new Callable<String>() {
			public String call() throws Exception {
				new UnitTests().execute(new String[]{});
				return "<html><iframe style='width: 100%, height: 100%' src='/target/site/surefire-report.html'/></html>";
			}
		}).build();
	}
}
