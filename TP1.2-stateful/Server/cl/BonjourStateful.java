// BonjourStateful.java - Corrected Stateful Bean
package cl;
import jakarta.ejb.Stateful;

@Stateful
public class BonjourStateful implements IBonjour {
    private String lastNom;
    
    @Override
    public String salut(String nom) {
        this.lastNom = nom;
        return "Bonjour " + nom;
    }
    
    @Override
    public String re_salut() {
        if (lastNom != null) {
            return "Bonjour à nouveau " + lastNom;
        } else {
            return "Pas de nom précédent";
        }
    }
}