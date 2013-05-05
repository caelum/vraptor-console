package br.com.caelum.vraptor.console.command;

import com.google.inject.Inject;


public class Run extends DelegateCommand {
	
	@Inject
	public Run(Maven maven) {
		super(new Restart(maven));
	}

}
