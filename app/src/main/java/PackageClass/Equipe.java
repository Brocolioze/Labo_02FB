package PackageClass;


import com.google.gson.annotations.SerializedName;

public class Equipe {

    @SerializedName("idEquipe")
    int idEquipe;
    @SerializedName("nomEquipe")
    String nomEquipe;
    @SerializedName("logo")
    String logo;


    public Equipe(int idEquipe, String nomEquipe, String logo) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.logo = logo;

    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }





}
