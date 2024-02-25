package com.example.labo_02fb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity2 extends AppCompatActivity {

    RecyclerView rvListeJoueur;
    AdapterJoueur adapterJoueur;
    ImageView LogoEquipe;
    TextView EquipeNom;

    //EditText nom_ajout_joueur,prenom_ajout_joueur;

    FloatingActionButton idBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        LogoEquipe = findViewById(R.id.LogoEquipe);
        EquipeNom = findViewById(R.id.EquipeNom);

        idBack = findViewById(R.id.idBack);

        rvListeJoueur = findViewById(R.id.rvListeJoueur);
        rvListeJoueur.setHasFixedSize(true);
        rvListeJoueur.setLayoutManager(new GridLayoutManager(this, 2));


        Intent intent = getIntent();


        idBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setResult(RESULT_CANCELED);
                finish();

            }


        });


        if (intent != null && intent.hasExtra("equipe_id") && intent.hasExtra("equipe_logo") && intent.hasExtra("equipe_nom")) {
            int equipeId = intent.getIntExtra("equipe_id", -1);

            String logo = intent.getStringExtra("equipe_logo");

            String nomEquipe = intent.getStringExtra("equipe_nom");


            Picasso.get().load(logo).into(LogoEquipe);

            EquipeNom.setText(nomEquipe);

            getListeJoueurs(equipeId);


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity2, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.item_ajouter){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            View view = getLayoutInflater().inflate(R.layout.layout_carte,null);
            builder.setView(view);
            builder.setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    gestionClik();


                }
            });

            AlertDialog alertDialog1 = builder.create();



            alertDialog1.show();




             Toast.makeText(this,"Joueur Ajouter",Toast.LENGTH_LONG).show();
            return true;

        }

        return false;

    }


    private void getListeJoueurs(int equipe_ident) {

        InterfaceUtilisateur serveur = RetrofitInstance.getInstance().create(InterfaceUtilisateur.class);

        Call<List<Joueur>> call = serveur.getIdEquipe(equipe_ident);

        call.enqueue(new Callback<List<Joueur>>() {
            @Override
            public void onResponse(Call<List<Joueur>> call, Response<List<Joueur>> response) {
                if (response.isSuccessful()) {

                    List<Joueur> liste = response.body();


                    adapterJoueur = new AdapterJoueur(liste);

                    rvListeJoueur.setAdapter(adapterJoueur);

                } else {
                    Toast.makeText(MainActivity2.this, "Une erreur", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<Joueur>> call, Throwable t) {

                Toast.makeText(MainActivity2.this, "Une erreur", Toast.LENGTH_LONG).show();


            }
        });


    }

    public void gestionClik(){
/*
        Intent intent = getIntent();

        if(nom_ajout_joueur.getText().toString().trim().isEmpty() || prenom_ajout_joueur.getText().toString().trim().isEmpty()){

            Toast.makeText(MainActivity2.this,"Veuillez entrer les champs",Toast.LENGTH_LONG).show();

        }else{

            InterfaceUtilisateur serveur = RetrofitInstance.getInstance().create(InterfaceUtilisateur.class);

            //Call<Boolean> call = serveur.addJoueur(nom_ajout_joueur.getText().toString(),prenom_ajout_joueur.getText().toString());

            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    boolean resultat = response.body();
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    Toast.makeText(MainActivity2.this,"Une erreur",Toast.LENGTH_LONG).show();
                }
            });
        }



*/
    }



}