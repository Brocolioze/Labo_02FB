package PackageClass;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labo_02fb.MainActivity2;
import com.example.labo_02fb.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterJoueur extends RecyclerView.Adapter<AdapterJoueur.ViewHolderJoueur> {

    List<Joueur> liste;

    public AdapterJoueur(List<Joueur> liste){

        this.liste = liste;
    }



    @NonNull
    @Override
    public ViewHolderJoueur onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_joueur, parent, false);
        return new ViewHolderJoueur(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderJoueur holder, int position) {
        Joueur joueur = liste.get(position);

        holder.idEquipe.setText(String.valueOf(joueur.getIdEquipe()));
        holder.idJoueur.setText(String.valueOf(joueur.getIdJoueur()));
        holder.nom.setText(joueur.getNom());
        holder.prenom.setText(joueur.getPrenom());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity2.class);
                intent.putExtra("equipe_id", joueur.getIdEquipe());
                intent.putExtra("joueur_id", joueur.getIdJoueur());
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return liste.size();
    }

    public static class ViewHolderJoueur extends RecyclerView.ViewHolder {
        public TextView idEquipe;
        public TextView idJoueur;
        public TextView nom;
        public TextView prenom;

        public ViewHolderJoueur(@NonNull View itemView) {
            super(itemView);
            idEquipe = itemView.findViewById(R.id.idEquipe);
            idJoueur = itemView.findViewById(R.id.idJoueur);
            nom = itemView.findViewById(R.id.nom);
            prenom = itemView.findViewById(R.id.prenom);
        }
    }
}
