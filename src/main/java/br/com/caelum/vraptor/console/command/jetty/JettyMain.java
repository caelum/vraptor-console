package br.com.caelum.vraptor.console.command.jetty;

public class JettyMain {

	public static Jetty8VRaptorServer startServer(String webappLocation) throws Exception {
		System.out.println("Reading webappdir from " + webappLocation);

		String webXmlLocation = getWebXmlLocation(webappLocation);

		return new Jetty8VRaptorServer(webappLocation, webXmlLocation);
	}

	private static String getWebXmlLocation(String webappDirLocation) {
		String webxml = System.getenv("VRAPTOR_WEBXML");
		if (webxml == null) {
			return webappDirLocation + "/WEB-INF/web.xml";
		}
		return webxml;
	}

}
