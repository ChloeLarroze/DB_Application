package cl;

//import javax.persistence.*;
import jakarta.persistence.*;
import java.io.Serializable; // FIX

@Entity
@Table(name = "livre")
public class Livre implements Serializable { //FIX 
// Erreur: java.rmi.MarshalException: CORBA MARSHAL 1330446343 No; nested exception is: 
//         org.omg.CORBA.MARSHAL: PRÉCIS: 00810007: Underflow in BufferManagerReadStream after last fragment in message  vmcid: OMG  minor code: 7  completed: No

    //fix otherwise "Entity class [class cl.Livre] has no primary key specified" and 
    // deployment error
     @Id //fix 
    private String isbn;
    @Column(nullable = false) //fix
    private String titre;
    @Column(nullable = false) //fix
    private boolean disponible;

    //constructeur par défaut (requis par JPA)
    public Livre() {
    }

    //constructeur à 2 arguments
    public Livre(String isbn, String titre) {
        this.isbn = isbn;
        this.titre = titre;
        this.disponible = true; //livre dispo création
    }

    //methodes utiles pour le truc 5 
    public boolean estDispo() {
        return this.disponible;
    }

    public void updateDispo(boolean dispo) {
        this.disponible = dispo;
    }
}