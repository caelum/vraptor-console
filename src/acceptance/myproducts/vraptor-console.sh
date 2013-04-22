BINARY=vraptor-console-0.7.0-SNAPSHOT-distribution.jar
TARGET=target/release/$BINARY

# http://docs.oracle.com/javase/7/docs/technotes/guides/jpda/conninv.html
if [ ! -f $TARGET ];
then
	curl http://ibiblio.com/$BINARY > $TARGET
fi
java -Xmx2g -Xms1g -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=1044 -cp target/vraptor-console-extra -jar $TARGET "run target/myproducts-1.0.0-SNAPSHOT.war"
