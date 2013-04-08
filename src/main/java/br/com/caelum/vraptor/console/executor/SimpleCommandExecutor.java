package br.com.caelum.vraptor.console.executor;

import br.com.caelum.vraptor.console.command.Command;

public class SimpleCommandExecutor implements CommandExecutor {

	@Override
	public void parse(String line) throws Exception {
		String[] args = line.split("\\s+");
		
		String commandName = Character.toUpperCase(args[0].charAt(0)) + args[0].substring(1, line.length());
		try {
			Object instance = Class.forName(
					"br.com.caelum.vraptor.console.command." + commandName)
					.newInstance();
			Command cmd = (Command) instance;
			cmd.execute(args);
		} catch (ClassNotFoundException e) {
			System.err.println("Command " + commandName + " not found");
		}
	}

}
