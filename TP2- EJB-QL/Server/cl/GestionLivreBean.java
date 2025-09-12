package cl;

//FIX 
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
// import javax.persistence.EntityManager; //deprecated
// import javax.persistence.PersistenceContext; //same
// import javax.persistence.TypedQuery; //same

@Stateless
public class GestionLivreBean implements GestionLivre {
    
    @PersistenceContext(unitName = "biblioPU")
    protected EntityManager em; //entity manager pour gérer les entités
    
    @Override
    public void nouveauLivre(String isbn, String titre) {
        Livre livre = new Livre(isbn, titre);
        em.persist(livre); //persist = ajoute l'entité à la base
    }
    @Override
    public void supprimerLivre(String isbn) {
        Livre livre = em.find(Livre.class, isbn); //on cherche le livre par son ISBN
        if (livre != null) {
            em.remove(livre); //si on le trouve, on le supprime
        } 
    }
    @Override    
    public Livre rechercherLivre(String isbn) {  
        return em.find(Livre.class, isbn); //on retourne le livre trouvé 
    }

    @Override
    public void emprunterLivre(String isbn) {
        Livre livre = rechercherLivre(isbn);
        if (livre.estDispo()) {
            livre.updateDispo(false);
        }
        em.merge(livre); //merge = met à jour l'entité dans la base
    }

    @Override
    public void rendreLivre(String isbn) {
        Livre livre = rechercherLivre(isbn);
        if (!livre.estDispo()) {
            livre.updateDispo(true);
        }
        em.merge(livre); //met à jour l'entité dans la base
    }
}