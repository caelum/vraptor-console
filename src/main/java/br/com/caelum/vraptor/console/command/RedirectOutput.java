package br.com.caelum.vraptor.console.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class RedirectOutput implements Runnable {

	private Scanner sc;
	private File target;

	public RedirectOutput(Scanner sc) {
		this.sc = sc;
	}

	public RedirectOutput(File output, InputStream inputStream) {
		this(new Scanner(inputStream));
		target = output;
	}

	@Override
	public void run() {
		try {
			new File(target.getParent()).mkdirs();
			FileWriter output = new FileWriter(target);
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				output.write(line + "\n");
				output.flush();
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public File getTarget() {
		return target;
	}

}
