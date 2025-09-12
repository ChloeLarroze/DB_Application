package cl;

//FIX 
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
// import javax.persistence.EntityManager; //deprecated
// import javax.persistence.PersistenceContext; //same
// import javax.persistence.TypedQuery; //same
import java.util.List;

@Stateless
public class GestionLivreBean implements GestionLivre {
    
    @PersistenceContext(unitName = "biblioPU")
    private EntityManager em; //entity manager pour gérer les entités
    
    
    public void nouveauLivre(String isbn, String titre) {
        Livre livre = new Livre(isbn, titre);
        em.persist(livre);
    }
    

    public void supprimerLivre(String isbn) {
        Livre livre = em.find(Livre.class, isbn); //on cherche le livre par son ISBN
        if (livre != null) {
            em.remove(livre); //si on le trouve, on le supprime
        } 
    }
    
    public Livre rechercherLivre(String isbn) {  
        return em.find(Livre.class, isbn); //on retourne le livre trouvé 
    }
    

    public void emprunterLivre(String isbn) {
        Livre livre = em.find(Livre.class, isbn); //on cherche le livre par son ISBN
        em.merge(livre); 
    }
     
    public void rendreLivre(String isbn) {
        Livre livre = em.find(Livre.class, isbn);
        em.merge(livre);
    }
}