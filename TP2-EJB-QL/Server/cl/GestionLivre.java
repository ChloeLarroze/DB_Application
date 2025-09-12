package cl;

import jakarta.ejb.Remote;
import java.util.List;

@Remote
public interface GestionLivre {
    //step 2
    void nouveauLivre(String isbn, String titre);
    
    //steps 4& 5
    void supprimerLivre(String isbn);
    Livre rechercherLivre(String isbn);
    List<Livre> listerLivres();
    
    //step 5 
    void emprunterLivre(String isbn);
    void rendreLivre(String isbn);
    List<Livre> listerLivresDisponibles();
}