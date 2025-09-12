package cl;

//import javax.persistence.*;
import jakarta.persistence.*;
import java.io.Serializable; // FIX

@Entity
@Table(name = "livre")
public class Livre implements Serializable { //FIX 
    //private static final long serialVersionUID = 1L; //not used

    @Id
    private String isbn;
    
    @Column(nullable = false)
    private String titre;
    
    @Column(nullable = false)
    private boolean disponible;
    
    //constructeur par défaut (requis par JPA)
    public Livre() {
    }
    
    // Constructeur à 2 arguments
    public Livre(String isbn, String titre) {
        this.isbn = isbn;
        this.titre = titre;
        this.disponible = true; //a la création, le livre est disponible
    }
    
    //methodes step 5 TODO 
    public void emprunter() {
        if (disponible) {
            this.disponible = false;
        } else {
            throw new IllegalStateException("Le livre n'est pas disponible");
        }
    }
    
    public void rendre() {
        if (!disponible) {
            this.disponible = true;
        } else {
            throw new IllegalStateException("Le livre n'était pas emprunté");
        }
    }
    
    @Override
    public String toString() {
        return "Livre{" +
                "isbn='" + isbn + '\'' +
                ", titre='" + titre + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}