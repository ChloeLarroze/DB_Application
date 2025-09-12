package cl;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
// import javax.persistence.EntityManager;
// import javax.persistence.PersistenceContext;
// import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class GestionLivreBean implements GestionLivre {
    
    @PersistenceContext(unitName = "biblioPU")
    private EntityManager em; //entity manager pour gérer les entités
    
    @Override //override de la méthode de l'interface dans step 2
    public void nouveauLivre(String isbn, String titre) {
        Livre livre = new Livre(isbn, titre);
        em.persist(livre);
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
        return em.find(Livre.class, isbn); //on retourne le livre trouvé ou null s'il n'existe pas
    }
    
    @Override
    public List<Livre> listerLivres() {
        TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre l", Livre.class); //JPQL query pour sélectionner tous les livres
        return query.getResultList();
    }
    
    @Override
    public void emprunterLivre(String isbn) {
        Livre livre = em.find(Livre.class, isbn); //on cherche le livre par son ISBN
        livre.emprunter(); //on appelle la méthode emprunter de l'entité Livre
        em.merge(livre);
    }
    
    @Override
    public void rendreLivre(String isbn) {
        Livre livre = em.find(Livre.class, isbn);
        livre.rendre();
        em.merge(livre);
    }
    
    @Override
    public List<Livre> listerLivresDisponibles() {
        TypedQuery<Livre> query = em.createQuery(
            "SELECT l FROM Livre l WHERE l.disponible = true", Livre.class);
        return query.getResultList();
    }
}