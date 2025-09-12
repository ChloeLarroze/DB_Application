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
            
            System.out.println("=== Test de Gestion de Bibliothèque ===");
            
            // créer livres
            System.out.println("création livres...");
            try {
                gestion.nouveauLivre("111", "Le petit prince");
                gestion.nouveauLivre("222", "Le rouge et le noir");
                gestion.nouveauLivre("333", "Germinal");
                System.out.println("✓ Livres créés avec succès");
            } catch (Exception e) {
                System.err.println("Erreur lors de la création: " + e.getMessage());
            }
            
            // liste des livres
            System.out.println("liste de tous les livres:");
            try {
                List<Livre> livres = gestion.listerLivres();
                for (Livre livre : livres) {
                    System.out.println("- " + livre);
                }
            } catch (Exception e) {
                System.err.println("Erreur lors du listage: " + e.getMessage());
            }
            
            // rehcerhce livre
            System.out.println("recherche d'un livre spécifique:");
            try {
                Livre livre = gestion.rechercherLivre("111");
                if (livre != null) {
                    System.out.println("Trouvé: " + livre);
                } else {
                    System.out.println("Livre non trouvé");
                }
            } catch (Exception e) {
                System.err.println("Erreur lors de la recherche: " + e.getMessage());
            }
            
            // Test 4: Emprunter un livre (étape 5)
            System.out.println("test d'emprunt:");
            try {
                System.out.println("Emprunt du livre 111...");
                gestion.emprunterLivre("111");
                System.out.println("✓ Livre emprunté");
                
                //vérifier l'état
                Livre livreEmprunte = gestion.rechercherLivre("111");
                System.out.println("État après emprunt: " + livreEmprunte);
            } catch (Exception e) {
                System.err.println("Erreur lors de l'emprunt: " + e.getMessage());
            }
            
            // Test 5: Lister les livres disponibles
            System.out.println("\n5. Livres disponibles:");
            try {
                List<Livre> disponibles = gestion.listerLivresDisponibles();
                for (Livre livre : disponibles) {
                    System.out.println("- " + livre);
                }
            } catch (Exception e) {
                System.err.println("Erreur lors du listage des disponibles: " + e.getMessage());
            }
            
            // Test 6: Rendre un livre
            System.out.println("\n6. Test de retour:");
            try {
                System.out.println("Retour du livre 978-2-123456-78-9...");
                gestion.rendreLivre("978-2-123456-78-9");
                System.out.println("✓ Livre rendu");
                
                // Vérifier l'état
                Livre livreRendu = gestion.rechercherLivre("978-2-123456-78-9");
                System.out.println("État après retour: " + livreRendu);
            } catch (Exception e) {
                System.err.println("Erreur lors du retour: " + e.getMessage());
            }
            
            // Test 7: Suppression d'un livre
            System.out.println("\n7. Suppression d'un livre:");
            try {
                gestion.supprimerLivre("978-2-456789-12-3");
                System.out.println("✓ Livre supprimé");
                
                // Vérifier la suppression
                System.out.println("Liste après suppression:");
                List<Livre> livresRestants = gestion.listerLivres();
                for (Livre livre : livresRestants) {
                    System.out.println("- " + livre);
                }
            } catch (Exception e) {
                System.err.println("Erreur lors de la suppression: " + e.getMessage());
            }
            
        } catch (NamingException e) {
            System.err.println("Erreur JNDI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}