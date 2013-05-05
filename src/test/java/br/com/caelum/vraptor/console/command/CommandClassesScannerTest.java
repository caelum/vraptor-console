package br.com.caelum.vraptor.console.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommandClassesScannerTest {

	@Test
	public void should_find_command_for_name() {
		CommandClassesScanner scanner = new CommandClassesScanner();
		Class<? extends Command> compileCommand = scanner.commandFor("compile");
		assertEquals(Compile.class, compileCommand);
	}
	
	@Test
	public void should_find_correct_name_for_annotated_command() {
		CommandClassesScanner scanner = new CommandClassesScanner();
		Class<? extends Command> startJetty = scanner.commandFor(StartJetty.COMMAND_NAME);
		assertEquals(StartJetty.class, startJetty);
	}

}
