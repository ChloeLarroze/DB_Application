package cl;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

//  propose de créer une nouvelle session d'emprunts/
// retours en fournissant le numéro de l’emprunteur, puis des méthodes emprunter et rendre précisant
// l’ISBN du livre concerné. A la création de la session, on transmettra le numéro de l'emprunteur et
// on conservera dans le bean la référence sur l’emprunteur concerné

@Stateless
public class GestionEmprunt {
    private Emprunteur emprunteur;

    public GestionEmprunt(Emprunteur emprunteur) {
        this.emprunteur = emprunteur;
    }

    public void emprunter(String isbn) {
        // Logique pour emprunter un livre
    }

    public void rendre(String isbn) {
        // Logique pour rendre un livre
    }
}
