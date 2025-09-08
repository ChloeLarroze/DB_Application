package cl;
import jakarta.ejb.Stateless;

@Stateless
public class Bonjour implements IBonjour {
    public String salut(String nom) {
        return "Bonjour " + nom;
    }
}
