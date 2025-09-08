# EJB Project with GlassFish/Payara
This project demonstrates how to create, deploy, and test Enterprise JavaBeans (EJBs) using GlassFish or Payara application server. It includes a simple test case and a more complex example with both stateless and stateful session beans.

## Prerequisites
1. **Java Development Kit (JDK) 11 or higher**
2. **GlassFish or Payara Application Server**
   - Download from: https://www.payara.fish/downloads/
   - Set `PAYARA_HOME` environment variable to your installation directory

## Environment Setup
### 1. Set Essential Environment Variables

Add the following to your shell profile (`.bashrc`, `.zshrc`, etc.):

```bash
# Set Payara/GlassFish home directory
export PAYARA_HOME=/path/to/your/payara6

# Set Java classpath for compilation and client execution
export CLASSPATH=$PAYARA_HOME/glassfish/lib/jakartaee-api-10.0.0.jar:$PAYARA_HOME/glassfish/lib/gf-client.jar:$PAYARA_HOME/glassfish/lib/appserver-rt.jar:.

# Add asadmin to PATH
export PATH=$PATH:$PAYARA_HOME/glassfish/bin
```

### 2. Verify Installation
```bash
# Check Java
javac -version

# Check Payara/GlassFish
asadmin version
```

## How to use ? 
Either go to `TP1.1-stateless` or `TP1.2-stateful` and run `
``bash
chmod +x ./build.sh
./build.sh
```

> NB: All useful commands are in TP1.1-stateless/.gitignore/payara_starter.txt file 
