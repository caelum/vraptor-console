VRaptor-Console
===============
A console to support develop of webapps with a jetty server embedded and commons tasks such as running unit tests.


Installing
----------
Download vraptor-console tar.gz distribution avaiable here: http://code.google.com/p/vraptor-console/downloads/list

Then, unpack the tar it and add the extracted directory to your PATH. For example:

	tar xzf vraptor-console-0.7.0-SNAPSHOT.tar.gz -C ~/programs
	export PATH=$PATH:~/programs/vraptor-console/

Running
-------
If your path is configured correctly, open a terminal and simply run: 

    vraptor-console.sh

Building from source
--------------------
    git clone git@github.com:caelum/vraptor-console.git
    cd vraptor-console/
    mvn package
    
This should generate `target/vraptor-console-0.7.0-SNAPSHOT.tar.gz` file. Unpack this file to some dir 
and make sure to add `vraptor-console.sh` to your PATH. 

How to create a new project
---------------------------
	vraptor-console.sh new com.mycompany.package appname

This will create a new folder `appname` with a default pom.xml required.	

How to run VRaptor-Console with your Maven project
--------------------------------------------------

Add to your pom.xml:

	<build>
		<outputDirectory>src/main/webapp/WEB-INF/classes/</outputDirectory>
	</build>


How to migrate another kind of project
--------------------------------------

Create a pom.xml based https://github.com/caelum/vraptor-console/blob/master/src/acceptance/myproducts/pom.xml

Development
-----------

To debug yout application, simply connect to port 1044 via Eclipse creating new remote debug configuration.

Supported
---------

	new <groupId> <artifactId> => creates a new project with a basic pom.xml needed
	run ==> (re)starts the jetty server
	restart ==> (re)starts the jetty server
	stop ==> stops the jetty server
	unitTests => mvn test surefire-report:report
	compile => mvn compile
	war => mvn package
	startJetty => starts jetty without recompiling classes
	custom class actions located in target/vraptor-console-extra will be loaded automatically 
	
URIS (except in production environment)
---------------------------------------
	/vraptor/target ==> display the target directory
	/vraptor/tests/unit ==> unitTests
	
Deploy to heroku
----------------

To deploy to heroku you need to use the custom vraptor-buildpack. So, starting from scratch:

	vraptor-console.sh new br.com.caelum example
	cd example/
	git init .
	git commit -am "initial commit"
	heroku create example
    heroku plugins:install https://github.com/heroku/heroku-buildpacks
	heroku buildpacks:set csokol/vraptor-buildpack -a example
	git push heroku master
	
Customizing jetty version
-------------------------
In the first run of vraptor-console.sh, jetty-8.1.10 will be downloaded and used. To use other versions, 
simply create a `vraptor-console.properties` file and define JETTY_URL variable with the url of jetty-dist to download.
For example, to use jetty 9:

	JETTY_URL="http://eclipse.c3sl.ufpr.br/jetty/stable-9/dist/jetty-distribution-9.0.2.v20130417.tar.gz"  
	
Other variables may be overriden in `vraptor-console.properties` file. See `vraptor-console.sh`to find out which variables
may be customized. 
	
Production
----------

In a production environment, no extra contexts are set up.
To go live, do:

	mvn package
	echo "VRAPTOR_ENVIRONMENT=production" >> vraptor-console.properties
	vraptor-console.sh 'run my-application.war'
	
Instead of setting the VRAPTOR_ENVIRONMENT, you can use a custom web.xml according to the vraptor-environment rules.

pom.xml
-------
Any changes to your pom.xml will make WatchPom download the required libraries from the net and do a:
	
mvn eclipse:eclipse
