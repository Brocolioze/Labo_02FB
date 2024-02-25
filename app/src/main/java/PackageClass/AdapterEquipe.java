package PackageClass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labo_02fb.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterEquipe extends RecyclerView.Adapter<AdapterEquipe.ViewHolderEquipe> {

    // Interface pour gérer les clics sur les éléments de RecyclerView
    public interface InterfaceEquipe {
        void gestionClick(int position, Equipe equipe);
    }

    private InterfaceEquipe interfaceEquipe;
    private List<Equipe> liste;

    // Constructeur prenant la liste d'équipes et l'interface comme paramètres
    public AdapterEquipe(List<Equipe> liste, InterfaceEquipe interfaceEquipe) {
        this.liste = liste;
        this.interfaceEquipe = interfaceEquipe;
    }

    // Méthode appelée lorsqu'un ViewHolder doit être créé
    @NonNull
    @Override
    public ViewHolderEquipe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_equipe, parent, false);
        return new ViewHolderEquipe(view);
    }

    // Méthode appelée pour afficher les données à une position spécifique
    @Override
    public void onBindViewHolder(@NonNull ViewHolderEquipe holder, int position) {
        Equipe equipe = liste.get(position);
        holder.idEquipe.setText(String.valueOf(equipe.getIdEquipe()));
        holder.nomEquipe.setText(equipe.getNomEquipe());
        Picasso.get().load(equipe.getLogo()).into(holder.ivImg);
    }

    // Méthode renvoyant le nombre d'éléments dans la liste
    @Override
    public int getItemCount() {
        return liste.size();
    }

    // Classe ViewHolder pour les éléments de l'équipe
    public class ViewHolderEquipe extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView ivImg;
        public TextView idEquipe;
        public TextView nomEquipe;

        // Constructeur ViewHolderEquipe
        public ViewHolderEquipe(@NonNull View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.logoEquipe);
            idEquipe = itemView.findViewById(R.id.idEquipe);
            nomEquipe = itemView.findViewById(R.id.nomEquipe);

            // Définition du listener de clic sur l'élément de vue
            itemView.setOnClickListener(this);
        }

        // Méthode appelée lorsqu'un élément de la liste est cliqué
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Equipe equipe = liste.get(position);
                // Vérification que l'interface n'est pas nulle avant d'appeler sa méthode
                if (interfaceEquipe != null) {
                    interfaceEquipe.gestionClick(position, equipe);
                }
            }
        }
    }
}
