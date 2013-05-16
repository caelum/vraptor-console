package br.com.caelum.vraptor.console.command.jetty.context;

import java.io.File;
import java.util.concurrent.Callable;

import org.eclipse.jetty.server.Handler;

import br.com.caelum.vraptor.console.command.Execute;
import br.com.caelum.vraptor.console.command.Maven;
import br.com.caelum.vraptor.console.command.UnitTests;
import br.com.caelum.vraptor.console.command.parser.ParsedCommandFactory;

public class UnitTestsContext extends ExceptProductionContextFactory {

	@Override
	public Handler getContext() {
		Callable<String> runUnit = new Callable<String>() {
			public String call() throws Exception {
				File output = new File("target/" + System.currentTimeMillis() + "-vraptor-console-output-unit-tests.txt");
				output.deleteOnExit();
				Maven maven = new Maven(output);
				UnitTests unitTests = new UnitTests(maven);
				Execute.inParallel(unitTests, ParsedCommandFactory.commandFor("unitTests"));
				String iFrameOutput = iframe("/vraptor/" + output.getPath());
				String iFrameReport = iframe("/vraptor/target/site/surefire-report.html");
				return "<html>(<a href='#' class='refresh'>refresh</a>)" + iFrameOutput + iFrameReport + "</html>";
			}

			private String iframe(String src) {
				return "<iframe style='width: 100%; height: 50%' src='" + src + "'></iframe><br/>";
			}
		};
		return new SimpleContext("/vraptor/tests/unit", runUnit).build();
	}
}
