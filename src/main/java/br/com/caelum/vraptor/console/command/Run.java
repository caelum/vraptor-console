package br.com.caelum.vraptor.console.command;

public class Run extends DelegateCommand {
	
	public Run() {
		super(new Restart());
	}

}
