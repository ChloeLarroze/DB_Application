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
    
    //constructeur par défaut (requis par JPA)
    public Livre() {
    }
    
    // Constructeur à 2 arguments
    public Livre(String isbn, String titre) {
        this.isbn = isbn;
        this.titre = titre;
    }
    
    @Override
    public String toString() {
        return "Livre{" +
                "isbn='" + isbn + '\'' +
                ", titre='" + titre + '\'' +
                '}';
    }
}