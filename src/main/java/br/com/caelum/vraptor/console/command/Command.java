package br.com.caelum.vraptor.console.command;

import java.io.File;

public interface Command {

	void execute(String[] args, File output) throws Exception;

}
