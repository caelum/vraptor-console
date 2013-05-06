BINARY=vraptor-console-0.7.0-SNAPSHOT-distribution.jar

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

PERM_GEM_OPTS=${PERM_GEM_OPTS:-"-XX:MaxPermSize=512m -XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled"}
MEM_OPTS=${MEM_OPTS:-"-Xmx2g -Xms1g"}
DEBUG_OPTS=${DEBUG_OPTS:-"-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=1044"}

EXTRA_COMMANDS=target/vraptor-console-extra/
RELEASE_PATH=$SCRIPT_DIR/lib/
TARGET=$RELEASE_PATH/$BINARY
JETTY_DIST=$RELEASE_PATH/jetty-distribution/
JETTY_URL="http://eclipse.c3sl.ufpr.br/jetty/stable-8/dist/jetty-distribution-8.1.10.v20130312.tar.gz"

if [ -e vraptor-console.properties ]; then
	source vraptor-console.properties
fi

if [ ! -f $TARGET ]; then
	curl http://ibiblio.com/$BINARY > $TARGET
fi
if [ ! -d $JETTY_DIST ]; then
	curl $JETTY_URL > $RELEASE_PATH/jetty-dist.tar.gz
	if [ $? != 0 ]; then
		echo "could not download jetty"
		exit 1
	fi
	tar xzf $RELEASE_PATH/jetty-dist.tar.gz -C $RELEASE_PATH >/dev/null
	mv $RELEASE_PATH/jetty-distribution-*/ $RELEASE_PATH/jetty-distribution 
fi


# http://docs.oracle.com/javase/7/docs/technotes/guides/jpda/conninv.html
java ${PERM_GEM_OPTS} ${MEM_OPTS} ${DEBUG_OPTS} \
    -cp "${JETTY_DIST}/lib/*:${JETTY_DIST}/lib/jsp/*:$TARGET:$EXTRA_COMMANDS" \
    br.com.caelum.vraptor.console.Main $@
