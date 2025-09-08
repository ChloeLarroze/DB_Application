
import javax.naming.InitialContext;
import javax.naming.NamingException;
// import jakarta.naming.InitialContext;
// import jakarta.naming.NamingException;

import cl.IBonjour;


public class Client {
    public static void main(String[] args) {
        try {
            InitialContext ctx = new InitialContext();
            
            System.out.println("=== Test Stateless Bean ===");
            // Note: Using "Bonjour" (class name), not "BonjourStateless"
            IBonjour bonjourStateless = (IBonjour) ctx.lookup("java:global/BonjourEJB/Bonjour!cl.IBonjour");
            System.out.println(bonjourStateless.salut("Alice"));
            System.out.println(bonjourStateless.re_salut());
            
            System.out.println("\n=== Test Stateful Bean ===");
            // This one is correct - class name is "BonjourStateful"
            IBonjour bonjourStateful = (IBonjour) ctx.lookup("java:global/BonjourEJB/BonjourStateful!cl.IBonjour");
            System.out.println(bonjourStateful.salut("Bob"));
            System.out.println(bonjourStateful.re_salut()); // Should remember "Bob"
            System.out.println(bonjourStateful.re_salut()); // Should still remember "Bob"
            
            // Test with new name
            System.out.println(bonjourStateful.salut("Charlie"));
            System.out.println(bonjourStateful.re_salut()); // Should now remember "Charlie"
            
        } catch (NamingException e) {
            System.err.println("JNDI Lookup Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



            
