package cl;

import jakarta.ejb.Remote;
import java.util.List;

@Remote
public interface GestionLivre {
    //step 2
    void nouveauLivre(String isbn, String titre);
    
    //steps 4
    void supprimerLivre(String isbn);
    
    //step 5 
    Livre rechercherLivre(String isbn);
    void emprunterLivre(String isbn);
    void rendreLivre(String isbn);
}