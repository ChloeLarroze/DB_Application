//IBonjour.java - fichier interface 
package cl; 
import jakarta.ejb.Remote;

@Remote
public interface IBonjour {
    String salut(String nom);
    String re_salut();
}