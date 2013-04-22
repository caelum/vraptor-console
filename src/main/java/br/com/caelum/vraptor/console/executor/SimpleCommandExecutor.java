package br.com.caelum.vraptor.console.executor;

import java.io.File;

import br.com.caelum.vraptor.console.command.Command;

public class SimpleCommandExecutor implements CommandExecutor {

	@Override
	public void parse(String line) throws Exception {
		String[] args = line.split("\\s+");
		
		String commandName = Character.toUpperCase(args[0].charAt(0)) + args[0].substring(1, args[0].length());
		try {
			Object instance = Class.forName(
					"br.com.caelum.vraptor.console.command." + commandName)
					.newInstance();
			Command cmd = (Command) instance;
			File tmp = getTempFile(commandName);
			cmd.execute(args, tmp);
		} catch (ClassNotFoundException e) {
			System.err.println("Command " + commandName + " not found");
		}
	}

	private File getTempFile(String commandName) {
		File tmp = new File("target/" + System.currentTimeMillis() + "-vraptor-console-" + commandName + ".txt");
		tmp.deleteOnExit();
		return tmp;
	}

}
