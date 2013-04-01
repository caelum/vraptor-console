# http://docs.oracle.com/javase/7/docs/technotes/guides/jpda/conninv.html
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=1044 -jar vraptor-console-*.jar