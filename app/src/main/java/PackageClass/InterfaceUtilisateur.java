package PackageClass;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceUtilisateur {


    @POST("/H2024/420636RI/GR02/b_faleyras/addJoueur.php")
    @FormUrlEncoded
    Call<Boolean>addJoueur(@Field("nom") String nom,
                                 @Field("prenom") String prenom,
                           @Field("idEquipe") int id);


    @GET("/H2024/420636RI/GR02/b_faleyras/getJoueur.php")
    Call<List<Joueur>> getIdEquipe(@Query("idEquipe") int id);



    @GET("/H2024/420636RI/GR02/b_faleyras/getEquipe.php")
    Call<List<Equipe>> getEquipe();




}
