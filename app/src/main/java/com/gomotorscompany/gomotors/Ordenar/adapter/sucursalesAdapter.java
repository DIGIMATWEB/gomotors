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

import com.gomotorscompany.gomotors.Ordenar.model.newmenu.Sucursale;
import com.gomotorscompany.gomotors.Ordenar.view.ordenarViewImpl;
import com.gomotorscompany.gomotors.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class sucursalesAdapter extends RecyclerView.Adapter<sucursalesAdapter.ViewHolder>{
    private ordenarViewImpl mainview;
         private List<Sucursale> msucursales;
         private Context context;
         private String Img;

         public  sucursalesAdapter(ordenarViewImpl mainview, List<Sucursale> msucursales, String img, Context context)
         {
             this.mainview=mainview;
            this.msucursales=msucursales;
            this.context=context;
            this.Img=img;

         }
         @NonNull
         @Override
         public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                     View view = LayoutInflater.from(context).inflate(R.layout.item_sucursales, parent, false);
                     return new sucursalesAdapter.ViewHolder(view);
         }

         @Override
         public void onBindViewHolder(@NonNull sucursalesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
                       holder.nameComercio.setText(msucursales.get(position).getNombreSuc());
                      holder.category.setText(msucursales.get(position).getDireccion());
                        holder.descripcionComerce.setText("Pizza");
                         holder.itemcomerce.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {

                             mainview.hideSheetlavel(msucursales.get(position).getNombreSuc(),msucursales.get(position).getCodigoSucursal(),msucursales.get(position).getProductos(),msucursales.get(position).getPaquetes());

                         }
                         });
                         Glide.with(context).load(Img).into(holder.sucursalImage);
         }

         @Override
         public int getItemCount() {
                 return msucursales.size();
         }

         public class ViewHolder extends RecyclerView.ViewHolder {
                             CardView itemcomerce;
                             TextView nameComercio,descripcionComerce,category;
                             ImageView sucursalImage;
         public ViewHolder(@NonNull View itemView) {
                             super(itemView);
                             sucursalImage= itemView.findViewById(R.id.sucursalImage1);
                             itemcomerce=itemView.findViewById(R.id.itemcomerce1a);
                             nameComercio=itemView.findViewById(R.id.nameEstablecimiento1a);
                             category=itemView.findViewById(R.id.descripcionEstablecimiento1a);
                                descripcionComerce=itemView.findViewById(R.id.categoriaEstablecimientoa);
            }
         }
}
