package br.com.caelum.vraptor.console.command.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParsedCommandFactoryTest extends ParsedCommandFactory {

	@Test
	public void should_parse_command_from_string() {
		ParsedCommandFactory factory = new ParsedCommandFactory();
		ParsedCommand cmd = factory.build("command arg1 arg2");
		assertEquals("arg1", cmd.getArg(0));
		assertEquals("arg2", cmd.getArg(1));
		assertEquals("command", cmd.getCommandName());
	}


        @Test(expected = IllegalArgumentException.class)
        public void should_throw_exception_from_empty_string() {
	        ParsedCommandFactory factory = new ParsedCommandFactory();
		ParsedCommand cmd = factory.build(" ");
         }
}
