package com.gomotorscompany.gomotors.Ordenar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.Ordenar.model.newmenu.menuDatan;
import com.gomotorscompany.gomotors.Ordenar.view.ordenarViewImpl;
import com.gomotorscompany.gomotors.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class companiesAdapter  extends RecyclerView.Adapter<companiesAdapter.ViewHolder>{
private ordenarViewImpl mainview;
    private List<menuDatan> data;
    private Context context;

    public  companiesAdapter(ordenarViewImpl mainview, List<menuDatan> data, Context context)
    {
        this.mainview=mainview;
        this.data=data;
        this.context=context;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comercios, parent, false);
        return new companiesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull companiesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.nameComercio.setText(data.get(position).getNombreEmpresa());

      //  holder.category.setText(data.get(position).getCategoria());
        holder.itemcomerce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainview.dataReciclermenu(data.get(position).getLogo().get(0).getSucursales(),data.get(position).getLogo().get(0).getImg(),position);
                 //Toast.makeText(v.getContext(), ""+data.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        Glide.with(context).load(data.get(position).getLogo().get(0).getImg()).into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foto;
        CardView itemcomerce;
        TextView nameComercio,descripcionComerce,category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foto=itemView.findViewById(R.id.foto);
            itemcomerce=itemView.findViewById(R.id.itemcomerce);
            nameComercio=itemView.findViewById(R.id.nameEstablecimiento);
            category=itemView.findViewById(R.id.categoriaEstablecimiento);
        }
    }
}