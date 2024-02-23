package PackageClass;

import com.google.gson.annotations.SerializedName;

public class Joueur {

    @SerializedName("idJoueur")
   int idJoueur;
    @SerializedName("idEquipe")
   int idEquipe;
    @SerializedName("nom")
    String nom;
    @SerializedName("prenom")
    String prenom;

    public Joueur(int idJoueur, int idEquipe, String nom, String prenom) {
        this.idJoueur = idJoueur;
        this.idEquipe = idEquipe;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


}
