BINARY=vraptor-console-0.7.0-SNAPSHOT-distribution.jar
RELEASE_PATH=vraptor-console/release/
TARGET=$RELEASE_PATH/$BINARY
JETTY_DIST=$RELEASE_PATH/jetty-distribution/
JETTY_URL="http://eclipse.c3sl.ufpr.br/jetty/stable-8/dist/jetty-distribution-8.1.10.v20130312.zip"

if [ ! -f $TARGET ]; then
	curl http://ibiblio.com/$BINARY > $TARGET
fi
if [ ! -f $JETTY_DIST ]; then
	curl $JETTY_URL > $RELEASE_PATH/jetty-dist.zip
	unzip $RELEASE_PATH/jetty-dist.zip -d $RELEASE_PATH >/dev/null
	mv $RELEASE_PATH/jetty-distribution-8.*/ $RELEASE_PATH/jetty-distribution 
fi

# http://docs.oracle.com/javase/7/docs/technotes/guides/jpda/conninv.html
java -Xmx2g -Xms1g -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=1044\
    -cp "${JETTY_DIST}/lib/*:${JETTY_DIST}/lib/jsp/*:$TARGET" \
    br.com.caelum.vraptor.console.Main
