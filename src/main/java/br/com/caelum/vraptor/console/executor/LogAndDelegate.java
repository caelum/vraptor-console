package br.com.caelum.vraptor.console.executor;

public class LogAndDelegate implements CommandExecutor {

	private final CommandExecutor executor;

	public LogAndDelegate(CommandExecutor executor) {
		this.executor = executor;
	}

	@Override
	public void parse(String line) throws Exception {
		long time = System.currentTimeMillis();
		System.out.println("Executing " + line);
		executor.parse(line);
		long finishingTime = System.currentTimeMillis();
		System.out.println("Executed " + line + " in " + ((finishingTime - time)/1000) + " seconds");
	}

}
