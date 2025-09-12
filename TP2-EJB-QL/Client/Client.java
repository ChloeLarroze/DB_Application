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
            
            // Test 1: Créer des livres
            System.out.println("\n1. Création de livres...");
            try {
                gestion.nouveauLivre("978-2-123456-78-9", "Introduction à Java EE");
                gestion.nouveauLivre("978-2-987654-32-1", "Bases de données avec PostgreSQL");
                gestion.nouveauLivre("978-2-456789-12-3", "Programmation Web avancée");
                System.out.println("✓ Livres créés avec succès");
            } catch (Exception e) {
                System.err.println("Erreur lors de la création: " + e.getMessage());
            }
            
            // Test 2: Lister tous les livres
            System.out.println("\n2. Liste de tous les livres:");
            try {
                List<Livre> livres = gestion.listerLivres();
                for (Livre livre : livres) {
                    System.out.println("- " + livre);
                }
            } catch (Exception e) {
                System.err.println("Erreur lors du listage: " + e.getMessage());
            }
            
            // Test 3: Rechercher un livre
            System.out.println("\n3. Recherche d'un livre spécifique:");
            try {
                Livre livre = gestion.rechercherLivre("978-2-123456-78-9");
                if (livre != null) {
                    System.out.println("Trouvé: " + livre);
                } else {
                    System.out.println("Livre non trouvé");
                }
            } catch (Exception e) {
                System.err.println("Erreur lors de la recherche: " + e.getMessage());
            }
            
            // Test 4: Emprunter un livre (étape 5)
            System.out.println("\n4. Test d'emprunt:");
            try {
                System.out.println("Emprunt du livre 978-2-123456-78-9...");
                gestion.emprunterLivre("978-2-123456-78-9");
                System.out.println("✓ Livre emprunté");
                
                // Vérifier l'état
                Livre livreEmprunte = gestion.rechercherLivre("978-2-123456-78-9");
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