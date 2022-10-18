package com.gomotorscompany.gomotors.Zonas.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.Zonas.model.dataRapartidores;
import com.gomotorscompany.gomotors.Zonas.view.zonasViewImpl;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class repartidoresAdapter  extends RecyclerView.Adapter<repartidoresAdapter.ViewHolder>{
    private zonasViewImpl zonasView;
    private List<dataRapartidores> listRepartidores;
    private Context context;

    public repartidoresAdapter(zonasViewImpl zonasView, List<dataRapartidores> listRepartidores, Context context) {
        this.zonasView=zonasView;
        this.listRepartidores=listRepartidores;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_repartidores, parent, false);
        return new repartidoresAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull repartidoresAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.nameRepartidor.setText(listRepartidores.get(position).getNombre());
            if(listRepartidores.get(position).getStatus()==4){
                holder.repartidorlastconection.setText("Desconectado");
                holder.repartidorP.setBorderColor(context.getResources().getColor(R.color.green));
            }else if(listRepartidores.get(position).getStatus()==3){
                holder.repartidorlastconection.setText("Detenido");
                holder.repartidorP.setBorderColor(context.getResources().getColor(R.color.redmenus));
            }
            else if(listRepartidores.get(position).getStatus()==2){
                holder.repartidorlastconection.setText("Transito");
                holder.repartidorP.setBorderColor(context.getResources().getColor(R.color.quantum_yellow));
            }
            else if(listRepartidores.get(position).getStatus()==1){
                holder.repartidorlastconection.setText("Disponible");
                holder.repartidorP.setBorderColor(context.getResources().getColor(R.color.green));
            }
            else if(listRepartidores.get(position).getStatus()==0){
                holder.repartidorlastconection.setText("Entregando pedido");
                holder.repartidorP.setBorderColor(context.getResources().getColor(R.color.redmenus));
            }
            holder.repartidorP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    zonasView.zoomRepartidor(listRepartidores.get(position).getLatitud(),listRepartidores.get(position).getLongitud());

                }
            });

    }

    @Override
    public int getItemCount() {
        return listRepartidores.size();
    }
      public void setFilter(List<dataRapartidores> dataRapartidores){
            this.listRepartidores = new ArrayList<>();
            this.listRepartidores.addAll(dataRapartidores);
            notifyDataSetChanged();
        }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameRepartidor,repartidorlastconection;
        private CircleImageView repartidorP;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameRepartidor=itemView.findViewById(R.id.nameRepartidor);
            repartidorlastconection=itemView.findViewById(R.id.repartidorlastconection);
            repartidorP=itemView.findViewById(R.id. repartidorP);
        }
    }
}
