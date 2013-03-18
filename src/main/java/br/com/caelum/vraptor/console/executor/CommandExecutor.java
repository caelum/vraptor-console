package br.com.caelum.vraptor.console.executor;

public interface CommandExecutor {

	void parse(String line) throws Exception;

}
