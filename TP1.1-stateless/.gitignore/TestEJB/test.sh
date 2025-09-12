# Create a simple EJB without package structure
mkdir -p TestEJB/META-INF

cat > TestEJB/SimpleEJB.java << 'EOF'
import jakarta.ejb.Stateless;

@Stateless
public class SimpleEJB {
    public String hello() {
        return "Hello World";
    }
}
EOF

cat > TestEJB/META-INF/MANIFEST.MF << 'EOF'
Manifest-Version: 1.0
Created-By: Test

EOF

# Compile and package
cd TestEJB
javac -cp "$CLASSPATH" SimpleEJB.java
jar cvf SimpleEJB.jar SimpleEJB.class META-INF/
cd ..

# Try deploying the simple version
asadmin deploy --force TestEJB/SimpleEJB.jar