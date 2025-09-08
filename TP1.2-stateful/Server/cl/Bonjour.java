// Bonjour.java - Stateless Bean (we keep is as it is)
package cl;
import jakarta.ejb.Stateless;

@Stateless
public class Bonjour implements IBonjour {
    public String salut(String nom) {
        return "Bonjour " + nom;
    }
    
    public String re_salut() {
        return "Bean stateless - pas de mémoire du nom précédent";
    }
}