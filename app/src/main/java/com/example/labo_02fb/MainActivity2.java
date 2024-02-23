package com.example.labo_02fb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import PackageClass.AdapterEquipe;
import PackageClass.AdapterJoueur;
import PackageClass.Equipe;
import PackageClass.Joueur;
import PackageClass.InterfaceUtilisateur;
import PackageClass.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity  {

    RecyclerView rvListeJoueur;
    AdapterJoueur adapterJoueur;
    ImageView LogoEquipe;
    TextView EquipeNom;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        LogoEquipe = findViewById(R.id.LogoEquipe);
        EquipeNom = findViewById(R.id.EquipeNom);

        rvListeJoueur = findViewById(R.id.rvListeJoueur);
        rvListeJoueur.setHasFixedSize(true);
        rvListeJoueur.setLayoutManager(new LinearLayoutManager(this));




        Intent intent = getIntent();


        if (intent != null && intent.hasExtra("equipe_id") && intent.hasExtra("equipe_logo") && intent.hasExtra("equipe_nom")) {
            int equipeId = intent.getIntExtra("equipe_id", -1);

            String logo = intent.getStringExtra("equipe_logo");

            String nomEquipe = intent.getStringExtra("equipe_nom");


                Picasso.get().load(logo).into(LogoEquipe);

                EquipeNom.setText(nomEquipe);

                getListeJoueurs(equipeId);


        }
    }

    private void getListeJoueurs(int equipeId) {

        InterfaceUtilisateur serveur = RetrofitInstance.getInstance().create(InterfaceUtilisateur.class);

        Call<List<Joueur>> call = serveur.getIdEquipe(equipeId);

        call.enqueue(new Callback<List<Joueur>>() {
            @Override
            public void onResponse(Call<List<Joueur>> call, Response<List<Joueur>> response) {



                List<Joueur> liste = response.body();


                adapterJoueur = new AdapterJoueur(liste);

                rvListeJoueur.setAdapter(adapterJoueur);

            }

            @Override
            public void onFailure(Call<List<Joueur>> call, Throwable t) {

                Toast.makeText(MainActivity2.this,"Une erreur",Toast.LENGTH_LONG).show();


            }
        });






        /*
        InterfaceUtilisateur serveur = RetrofitInstance.getInstance().create(InterfaceUtilisateur.class);
        Call<List<Joueur>> call = serveur.getIdEquipe(equipeId);
        call.enqueue(new Callback<List<Joueur>>() {
            @Override
            public void onResponse(Call<List<Joueur>> call, Response<List<Joueur>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Joueur> joueurList = response.body();
                    listeJoueurs.clear();
                    listeJoueurs.addAll(joueurList);
                    adapterJoueur.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity2.this, "Erreur lors de la récupération des joueurs", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Joueur>> call, Throwable t) {
                Toast.makeText(MainActivity2.this, "Une erreur est survenue : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
