package br.com.caelum.vraptor.console.guice;

import java.io.File;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

public class VRaptorConsoleModule extends AbstractModule {
	
	public static final String MAVEN_OUTPUT = "MavenOutput";

	@Override
	protected void configure() {
		bind(File.class)
	        .annotatedWith(Names.named(MAVEN_OUTPUT))
	        .toInstance(new File("/tmp/output.txt"));
	}
	
	@Provides WorkingDir provideWorkingDir() {
		return new WorkingDir(new File("."));
	}

}
