package br.com.caelum.vraptor.console.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.vraptor.console.command.parser.CommandClassesScanner;
import br.com.caelum.vraptor.console.command.parser.ParsedCommand;
import br.com.caelum.vraptor.console.command.parser.ParsedCommandFactory;

public class CommandClassesScannerTest {
	
	private ParsedCommandFactory commandFactory = new ParsedCommandFactory();

	@Test
	public void should_find_command_for_name() {
		CommandClassesScanner scanner = new CommandClassesScanner();
		ParsedCommand command = parse("compile");
		Class<? extends Command> compileCommand = scanner.commandFor(command);
		assertEquals(Compile.class, compileCommand);
	}

	
	@Test
	public void should_find_correct_name_for_annotated_command() {
		CommandClassesScanner scanner = new CommandClassesScanner();
		Class<? extends Command> withExtraName = scanner.commandFor(parse(StartJetty.COMMAND_NAME));
		Class<? extends Command> withDefaultName = scanner.commandFor(parse("startJetty"));
		assertEquals(StartJetty.class, withDefaultName);
		assertEquals(StartJetty.class, withExtraName);
	}

	private ParsedCommand parse(String cmd) {
		return commandFactory.build(cmd);
	}
}
