import javax.naming.InitialContext;
import javax.naming.NamingException;
import cl.GestionLivre;
import cl.Livre;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        try {
            InitialContext ctx = new InitialContext();
            GestionLivre gestion = (GestionLivre) ctx.lookup("java:global/BiblioEJB/GestionLivreBean!cl.GestionLivre");
            
            System.out.println("=== test biblio ===");
            
            // créer livres
            System.out.println("création livres");
            try {
                gestion.nouveauLivre("111", "Le petit prince");
                gestion.nouveauLivre("222", "Le rouge et le noir");
                gestion.nouveauLivre("333", "Germinal");
                System.out.println("livres créés");
            } catch (Exception e) {
                System.err.println("Erreur: " + e.getMessage());
            }


            
            // Test: Emprunter un livre (étape 5)
            System.out.println("test d'emprunt:");
            try {
                System.out.println("Emprunt du livre 111...");
                gestion.emprunterLivre("111");
                System.out.println("livre emprunté");
            } catch (Exception e) {
                System.err.println("Erreur: " + e.getMessage());
            }
            

            // Test: Rendre un livre
            System.out.println("\n Test de retour:");
            try {
                System.out.println("Retour du livre 111...");
                gestion.rendreLivre("111");
                System.out.println("livre rendu");
                
                //check
                Livre livreRendu = gestion.rechercherLivre("111");
                System.out.println("après retour: " + livreRendu);
            } catch (Exception e) {
                System.err.println("Erreur: " + e.getMessage());
            }
            
            // Test: Suppression d'un livre
            System.out.println("\n Suppression d'un livre:");
            try {
                gestion.supprimerLivre("111");
                System.out.println("livre supprimé");
            } catch (Exception e) {
                System.err.println("Erreur: " + e.getMessage());
            }
             
        } catch (NamingException e) {
            System.err.println("Erreur JNDI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}