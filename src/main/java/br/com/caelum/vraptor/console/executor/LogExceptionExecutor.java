package br.com.caelum.vraptor.console.executor;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public class LogExceptionExecutor implements CommandExecutor {

	private final CommandExecutor executor;

	public LogExceptionExecutor(CommandExecutor executor) {
		this.executor = executor;
	}

	@Override
	public void parse(ParsedCommand parsedCommand) {
		try {
			executor.parse(parsedCommand);
		} catch (Exception ex) {
			System.out.println("Executing " + parsedCommand + " threw an exception");
			ex.printStackTrace();
		}
	}

}
