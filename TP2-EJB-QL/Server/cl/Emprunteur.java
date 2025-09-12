package cl;

public class Emprunteur {
    private String nom;
    private String email;

    public Emprunteur(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
