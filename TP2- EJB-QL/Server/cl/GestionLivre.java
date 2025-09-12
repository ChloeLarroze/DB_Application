package cl;

import jakarta.ejb.Remote;

@Remote
public interface GestionLivre {
    //step 2
    void nouveauLivre(String isbn, String titre);
    void supprimerLivre(String isbn);
    
    //step 5 
    Livre rechercherLivre(String isbn);
    void emprunterLivre(String isbn);
    void rendreLivre(String isbn);
}