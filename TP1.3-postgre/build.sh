#!/bin/bash

# =========================
# Configuration
# =========================
SERVER_DIR=Server
CLIENT_DIR=Client
EJB_JAR=BiblioEJB.jar

# =========================
# Cleaning old files & stopping domain (if running)
# =========================
echo "=== Cleaning old class files ==="
rm -f $SERVER_DIR/cl/*.class
rm -f $CLIENT_DIR/*.class
rm -f $SERVER_DIR/$EJB_JAR
rm -f $CLIENT_DIR/$EJB_JAR

echo "=== Stopping domain (if running) ==="
asadmin stop-domain

# =========================
# Compile Server EJBs
# =========================
echo "=== Compiling Server EJBs ==="
javac -cp "$CLASSPATH" $SERVER_DIR/cl/*.java

if [ $? -ne 0 ]; then
    echo "ERROR: Compilation failed!"
    exit 1
fi

# =========================
# Package BiblioEJB.jar
# =========================
echo "=== Packaging $EJB_JAR ==="
cd $SERVER_DIR
jar cvf $EJB_JAR cl/*.class META-INF
cd ..

if [ $? -ne 0 ]; then
    echo "ERROR: JAR creation failed!"
    exit 1
fi

# ========================
# Launch GlassFish Server
# ========================
echo "=== Launching GlassFish Server ==="
asadmin start-domain

# ========================
# Undeploy existing application (if any)
# ========================
echo "=== Undeploying existing application (if any) ==="
asadmin undeploy BiblioEJB 2>/dev/null || echo "No existing deployment found"

# ========================
# Deploy EJB
# ========================
echo "=== Deploying $EJB_JAR ==="
asadmin deploy $SERVER_DIR/$EJB_JAR

if [ $? -ne 0 ]; then
    echo "ERROR: Deployment failed!"
    echo "Check the server logs for more details:"
    # see all log: 
    #tail -100 $PAYARA_HOME/glassfish/domains/domain1/logs/server.log
    #tail -20 $PAYARA_HOME/glassfish/domains/domain1/logs/server.log | grep -A 5 -B 5 "Exception\|Error"
    exit 1
fi

# =========================
# Copy JAR to Client folder
# =========================
echo "=== Copying $EJB_JAR to Client folder ==="
cp $SERVER_DIR/$EJB_JAR $CLIENT_DIR/

# =========================
# Compile Client
# =========================
echo "=== Compiling Client ==="
javac -cp "$PAYARA_HOME/glassfish/lib/gf-client.jar:$PAYARA_HOME/glassfish/lib/appserver-rt.jar:$CLIENT_DIR/$EJB_JAR:." $CLIENT_DIR/Client.java

if [ $? -ne 0 ]; then
    echo "ERROR: Client compilation failed!"
    exit 1
fi

# =========================
# Run Client
# =========================
echo "=== Running Client ==="
cd $CLIENT_DIR
java --add-opens=java.base/java.util=ALL-UNNAMED \
     --add-opens=java.base/java.io=ALL-UNNAMED \
     --add-opens=java.base/java.lang=ALL-UNNAMED \
     --add-opens=java.management/javax.management.openmbean=ALL-UNNAMED \
     --add-opens=java.management/javax.management=ALL-UNNAMED \
     -cp "$PAYARA_HOME/glassfish/lib/gf-client.jar:$PAYARA_HOME/glassfish/lib/appserver-rt.jar:$EJB_JAR:." \
     Client
cd ..