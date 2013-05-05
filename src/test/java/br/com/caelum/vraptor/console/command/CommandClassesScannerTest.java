package br.com.caelum.vraptor.console.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.vraptor.console.command.parser.CommandClassesScanner;

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
		Class<? extends Command> withExtraName = scanner.commandFor(StartJetty.COMMAND_NAME);
		Class<? extends Command> withDefaultName = scanner.commandFor("startJetty");
		assertEquals(StartJetty.class, withDefaultName);
		assertEquals(StartJetty.class, withExtraName);
	}

}
