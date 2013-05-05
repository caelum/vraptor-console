package br.com.caelum.vraptor.console.command.newproject;

import java.io.File;

import br.com.caelum.vraptor.console.command.parser.ParsedCommand;

public interface NewProjectAction {
	void execute(ParsedCommand parsedCommand, File projectHome);
}
