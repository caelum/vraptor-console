package br.com.caelum.vraptor.console.executor;

public class LogExceptionExecutor implements CommandExecutor {

	private final CommandExecutor executor;

	public LogExceptionExecutor(CommandExecutor executor) {
		this.executor = executor;
	}

	@Override
	public void parse(String line) {
		try {
			executor.parse(line);
		} catch (Exception ex) {
			System.out.println("Executing " + line + " threw an exception");
			ex.printStackTrace();
		}
	}

}
