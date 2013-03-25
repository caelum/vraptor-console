package br.com.caelum.vraptor.console.command.jetty;


public class Main {
	public static Jetty8VRaptorServer startServer() throws Exception {
		String webappDirLocation = getWebAppDir();
		System.out.println("Reading webappdir from " + webappDirLocation);
		
		String webXmlLocation = getWebXmlLocation(webappDirLocation);

		Jetty8VRaptorServer vraptor = new Jetty8VRaptorServer(webappDirLocation, webXmlLocation);
		vraptor.start();
		return vraptor;
	}

    private static String getWebXmlLocation(String webappDirLocation) {
        String webxml = System.getenv("VRAPTOR_WEBXML");
		webxml = webxml == null ? webappDirLocation + "/WEB-INF/web.xml" : webxml;
		return webxml;
    }

	private static String getWebAppDir() {
		return System.getProperty("vraptor.webappdir", "src/main/webapp/");
	}

}
