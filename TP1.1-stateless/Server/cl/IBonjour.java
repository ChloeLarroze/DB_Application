//IBonjour.java
package cl; 
import jakarta.ejb.Remote;

@Remote
public interface IBonjour {
    String salut(String nom);
}


