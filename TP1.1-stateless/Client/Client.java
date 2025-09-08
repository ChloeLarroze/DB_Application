
import javax.naming.InitialContext;
import javax.naming.NamingException;
// import jakarta.naming.InitialContext;
// import jakarta.naming.NamingException;

import cl.IBonjour;

public class Client {
    public static void main(String[] args) {
        try {
            InitialContext ctx = new InitialContext();
            // Use the correct portable JNDI name for EJB 3.1+
            IBonjour bonjour = (IBonjour) ctx.lookup("java:global/BonjourEJB/Bonjour!cl.IBonjour");
            System.out.println(bonjour.salut("Chloe"));
        } catch (NamingException e) {
            System.err.println("JNDI Lookup Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

