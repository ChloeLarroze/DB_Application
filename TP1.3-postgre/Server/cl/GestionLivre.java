package cl;

import jakarta.ejb.Remote;

@Remote
public interface GestionLivre {
    //step 2
    void nouveauLivre(String isbn, String titre);
    void supprimerLivre(String isbn);
    
    //step 5 
    Livre rechercherLivre(String isbn);
    boolean estDispo(); //check si le livre est dispo à l'emprunt
    void updateDispo(boolean dispo); //met à jour la dispo du livre
    void emprunterLivre(String isbn);
    void rendreLivre(String isbn);
}