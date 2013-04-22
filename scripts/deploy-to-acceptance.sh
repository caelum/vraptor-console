set -e

PROJECT_HOME=${PROJECT_HOME:-src/acceptance/myproducts/}
RELEASE_PATH=$PROJECT_HOME/vraptor-console/release/

mvn clean package

mkdir -p $PROJECT_HOME
mkdir -p $RELEASE_PATH

cp target/vraptor-console-0.7.0-SNAPSHOT-distribution.jar $RELEASE_PATH
cp src/bin/vraptor-console.sh $PROJECT_HOME/
