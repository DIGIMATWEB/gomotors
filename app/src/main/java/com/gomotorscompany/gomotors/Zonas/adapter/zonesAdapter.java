package com.gomotorscompany.gomotors.Zonas.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.Zonas.model.datarequesZonas;
import com.gomotorscompany.gomotors.Zonas.view.zonasViewImpl;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class zonesAdapter extends RecyclerView.Adapter<zonesAdapter.ViewHolder>{
    private List<datarequesZonas> dataZones;
    private Context context;
    private zonasViewImpl myView;


    public zonesAdapter(zonasViewImpl myView,List<datarequesZonas> dataZones, Context context) {
        this.myView=myView;
        this.dataZones=dataZones;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_zones, parent, false);
        return new zonesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull zonesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(dataZones.get(position).getLocation().size()>1)
        {
            Glide.with(context).load(R.drawable.poligons).into(holder.kindZone);
            holder.tipozona.setText("Poligonal");
        }else
        {
            Glide.with(context).load(R.drawable.circles).into(holder.kindZone);
            holder.tipozona.setText("Circular");
        }
        holder.nombre.setText(dataZones.get(position).getNombre());
        holder.color.setText("Color: null");//+dataZones.get(position).getColor());

        holder.dots1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.dots1.setVisibility(View.GONE);
                holder.edit1.setVisibility(View.VISIBLE);
                holder.erase1.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        holder.dots1.setVisibility(View.VISIBLE);
                        holder.edit1.setVisibility(View.GONE);
                        holder.erase1.setVisibility(View.GONE);
                    }
                }, 3000);

            }
        });
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.zoomtoDot(dataZones.get(position).getLocation().get(0).getLatitud(),dataZones.get(position).getLocation().get(0).getLongitud());
            }
        });
        holder.edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.tipozona.getText().equals("Circular")) {
                    boolean poligonType=false;
                    myView.editCurrentZone(poligonType, position, dataZones.get(position).getLocation(),dataZones.get(position).getNombre());
                }else if(holder.tipozona.getText().equals("Poligonal"))
                {
                    boolean poligonType=true;
                    myView.editCurrentZone(poligonType,position, dataZones.get(position).getLocation(), dataZones.get(position).getNombre());
                }
            }
        });
        holder.erase1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.eraseZone( dataZones.get(position).getColoniaID());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataZones.size();
    }
    public void setFilter(List<datarequesZonas> dataZonesList){
        this.dataZones = new ArrayList<>();
        this.dataZones.addAll(dataZonesList);
        notifyDataSetChanged();
    }
    public class ViewHolder  extends RecyclerView.ViewHolder {
        CardView item;
        TextView nombre,color,tipozona;
        ImageView dots1,edit1,erase1,kindZone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item =itemView.findViewById(R.id.cardView5);
            nombre=itemView.findViewById(R.id.nameEstablecimiento11);
            color=itemView.findViewById(R.id.descripcionEstablecimiento12);
            dots1=itemView.findViewById(R.id.dots1);
            edit1=itemView.findViewById(R.id.edit1);
            erase1=itemView.findViewById(R.id.erase1);
            kindZone= itemView.findViewById(R.id.kindZone);
            tipozona= itemView.findViewById(R.id. categoriaEstablecimiento);
        }
    }
}
