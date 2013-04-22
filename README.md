VRaptor-Console
===============
A console to support develop of webapps with a jetty server embedded and commons tasks such as running unit tests.

Building from source
--------------------
    git clone git@github.com:caelum/vraptor-console.git
    cd vraptor-console/
    PROJECT_HOME=/path/to/your/project scripts/deploy-to-acceptance.sh


Running vraptor console
-----------------------

    ./vraptor-console.sh

How to create a new project
---------------------------

	Create a vraptor maven based project and add the one line of configuration from the Maven section.
	
How to run VRaptor-Console with your Maven project
--------------------------------------------------

Add to your pom.xml:

	<build>
		<outputDirectory>src/main/webapp/WEB-INF/classes/</outputDirectory>
	</build>


How to migrate another kind of project
--------------------------------------

	Create a pom.xml based https://github.com/caelum/vraptor-console/blob/master/src/acceptance/myproducts/pom.xml

How to run VRaptor-Console with your Ant project:

	???


SUPPORTED

	run ==> (re)starts the jetty server
	restart ==> (re)starts the jetty server
	stop ==> stops the jetty server
	unitTests => mvn test surefire-report:report
	compile => mvn compile
	war => mvn package
	custom class actions located in target/vraptor-console-extra will be loaded automatically 
	
Production

In a production environment, no extra contexts are set up.
To go live, do:

	mvn package
	VRAPTOR_ENVIRONMENT=production java -jar vraptor-console-1.0.0.jar 'run my-application.war'
	
Instead of setting the VRAPTOR_ENVIRONMENT, you can use a custom web.xml according to the vraptor-environment rules.


Development

	Support remote debug via port 1044, simply connect to it via Eclipse.
	
URIS

	/target ==> display the target directory
	/tests/unit ==> unitTests
	
	
pom.xml

	Any changes to your pom.xml will make WatchPom download the required libraries from the net and do a:
	
	mvn eclipse:eclipse

TODO BEFORE RELEASE 1
- tapa na pantera: documentacao aqui
- doc for the vraptor documentation on how to create, config, run
- release it
- blog post
- gnarus tutorial

TODO
- unit tests auto refersh (+template engine)
- if unit tests webpage is open, autorun tests every save?
- commit option after unit tests are green?
- allow to run jasmine JS tests
- doc on how to use ant
- support multiple jetty versions
- acceptance-test
- allow to run acceptance tests via web
- include vraptor-environment, vraptor-simplemail, vraptor-errorcontrol
- if you add a new jar while your server is running, you need to setExtraClasspath again...
- if you server starts with problems, it seems like it gets stuck on 8080
- when invoking Maven.execute(multipleCommands), it should invoke maven only once
