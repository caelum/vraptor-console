package br.com.caelum.vraptor.console.guice;

import java.io.File;

public class WorkingDir {
	
	private final File dir;

	public WorkingDir(File dir) {
		this.dir = dir;
		dir.mkdirs();
	}

	public File getDir() {
		return dir;
	}

}
