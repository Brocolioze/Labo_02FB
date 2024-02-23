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

    public interface InterfaceEquipe {
        public void gestionClick(int position, Equipe equipe);
    }
    InterfaceEquipe interfaceEquipe;

    List<Equipe>liste;


    public AdapterEquipe(List<Equipe> liste, InterfaceEquipe interfaceEquipe) {

        this.liste = liste;
        this.interfaceEquipe = interfaceEquipe;
    }

    @NonNull
    @Override
    public ViewHolderEquipe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_equipe, parent, false);
        return new ViewHolderEquipe(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEquipe holder, int position) {
        Equipe equipe = liste.get(position);
        holder.idEquipe.setText(String.valueOf(equipe.getIdEquipe()));
        holder.nomEquipe.setText(equipe.getNomEquipe());
        Picasso.get().load(equipe.getLogo()).into(holder.ivImg);

    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public class ViewHolderEquipe extends RecyclerView.ViewHolder {
        public ImageView ivImg;
        public TextView idEquipe;
        public TextView nomEquipe;

        public ViewHolderEquipe(@NonNull View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.logoEquipe);
            idEquipe = itemView.findViewById(R.id.idEquipe);
            nomEquipe = itemView.findViewById(R.id.nomEquipe);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interfaceEquipe.gestionClick(getLayoutPosition(), liste.get(getLayoutPosition()));
                }
            });
        }


    }
}
