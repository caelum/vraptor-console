package br.com.caelum.vraptor.console.executor;

import br.com.caelum.vraptor.console.command.Command;

public class SimpleCommandExecutor implements CommandExecutor {

	@Override
	public void parse(String line) throws Exception {
		String commandName = Character.toUpperCase(line.charAt(0)) + line.substring(1, line.length());
		try {
			Object instance = Class.forName(
					"br.com.caelum.vraptor.console.command." + commandName)
					.newInstance();
			Command cmd = (Command) instance;
			cmd.execute();
		} catch (ClassNotFoundException e) {
			System.err.println("Command " + commandName + " not found");
		}
	}

}
