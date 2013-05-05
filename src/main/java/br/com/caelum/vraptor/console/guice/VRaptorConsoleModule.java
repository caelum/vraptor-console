package br.com.caelum.vraptor.console.guice;

import java.io.File;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class VRaptorConsoleModule extends AbstractModule {
	
	@Override
	protected void configure() {
		bind(File.class)
	        .annotatedWith(Names.named("MavenOutput"))
	        .toInstance(new File("/tmp/output.txt"));
	}

}
