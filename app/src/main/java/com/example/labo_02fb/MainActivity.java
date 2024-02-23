package com.example.labo_02fb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import PackageClass.AdapterEquipe;
import PackageClass.Equipe;
import PackageClass.InterfaceUtilisateur;
import PackageClass.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterEquipe.InterfaceEquipe{

    RecyclerView rvListeEquipe;
    AdapterEquipe adapterEquipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListeEquipe = findViewById(R.id.rvListeEquipe);
        rvListeEquipe.setHasFixedSize(true);
        rvListeEquipe.setLayoutManager(new LinearLayoutManager(this));

        getListEquipe();


       // adapterEquipe = new AdapterEquipe(liste, this);
        //rvListeEquipe.setAdapter(adapterEquipe);


    }

    public void getListEquipe() {

        InterfaceUtilisateur serveur = RetrofitInstance.getInstance().create(InterfaceUtilisateur.class);

        Call<List<Equipe>> call = serveur.getEquipe();

        call.enqueue(new Callback<List<Equipe>>() {
            @Override
            public void onResponse(Call<List<Equipe>> call, Response<List<Equipe>> response) {
                List<Equipe> liste = response.body();
                adapterEquipe = new AdapterEquipe(liste);
                rvListeEquipe.setAdapter(adapterEquipe);
            }

            @Override
            public void onFailure(Call<List<Equipe>> call, Throwable t) {

                Toast.makeText(MainActivity.this,"Une erreur",Toast.LENGTH_LONG).show();

            }
        });




        /*
        InterfaceUtilisateur serveur = RetrofitInstance.getInstance().create(InterfaceUtilisateur.class);

        Call<List<Equipe>> call = serveur.getEquipe();

        call.enqueue(new Callback<List<Equipe>>() {
            @Override
            public void onResponse(Call<List<Equipe>> call, Response<List<Equipe>> response) {

                    List<Equipe> equipeList = response.body();
                    liste.clear();
                    liste.addAll(equipeList);
                    adapterEquipe.notifyDataSetChanged();

                    Toast.makeText(MainActivity.this, "Une erreur est survenue lors de la récupération des équipes", Toast.LENGTH_LONG).show();
                }


            @Override
            public void onFailure(Call<List<Equipe>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Une erreur est survenue lors de la récupération des équipes", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    @Override
    public void gestionClick(int position, Equipe equipe) {

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("equipe_id", equipe.getIdEquipe());
        intent.putExtra("equipe_logo", equipe.getLogo());
        intent.putExtra("equipe_nom", equipe.getNomEquipe());
        startActivity(intent);
    }
}
