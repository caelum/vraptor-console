package br.com.caelum.vraptor.console.executor;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public class LogAndDelegate implements CommandExecutor {

	private final CommandExecutor executor;

	public LogAndDelegate(CommandExecutor executor) {
		this.executor = executor;
	}

	@Override
	public void parse(ParsedCommand parsedCommand) throws Exception {
		long time = System.currentTimeMillis();
		System.out.println("Executing " + parsedCommand);
		executor.parse(parsedCommand);
		long finishingTime = System.currentTimeMillis();
		System.out.println("Executed " + parsedCommand + " in " + ((finishingTime - time)/1000) + " seconds");
	}

}
