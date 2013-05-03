package br.com.caelum.vraptor.console;

public interface CommandParser {

	void readAll() throws Exception;

	void read(String cmd) throws Exception;

}
