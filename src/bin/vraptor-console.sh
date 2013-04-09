BINARY=vraptor-console-0.7.0-SNAPSHOT-distribution.jar
BINARY_DIR=vraptor-console/target/
TARGET=$BINARY_DIR/$BINARY
mkdir -p $BINARY_DIR 

# http://docs.oracle.com/javase/7/docs/technotes/guides/jpda/conninv.html
if [ ! -f $TARGET ];
then
	curl http://ibiblio.com/$BINARY > $TARGET
fi
java ${VRAPTOR_CONSOLE_OPTS} -Xmx2g -Xms1g -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=1044 -cp target/vraptor-console-extra -jar $TARGET $*
