package com.gomotorscompany.gomotors.Ordenar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.Ordenar.model.newmenu.Complemento;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.listaproductos;
import com.gomotorscompany.gomotors.Ordenar.view.ordenarViewImpl;
import com.gomotorscompany.gomotors.R;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class adapterProductos extends RecyclerView.Adapter<adapterProductos.ViewHolder>{
    private Context context;
    private ordenarViewImpl mainview;
    private List<listaproductos> data;
    public adapterProductos(ordenarViewImpl mainview, List<listaproductos> data, Context context) {
        this.mainview=mainview;
        this.data=data;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menuproduct, parent, false);
        return new adapterProductos.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull adapterProductos.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameAlimento.setText(data.get(position).getNombre());
        holder.descripcionAlimento.setText(data.get(position).getDescripcion());
        holder.precio.setText(data.get(position).getPrecio());
        Glide.with(context).load(data.get(position).getImagen()).into(  holder.imagnproducto);

        holder.agregarb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Complemento> complementos=new ArrayList<>();
                complementos.clear();
                if(data.get(position).getComplementos()!=null)
                {
                    for(int i=0;i<data.get(position).getComplementos().size();i++)
                    {

                        complementos.add(data.get(position).getComplementos().get(i));
                        // =new Complemento(data.get(position).getComplementos().get(i));
//                                ,data.get(position).getComplementos().get(i).getDescripcion()
//                                ,data.get(position).getComplementos().get(i).getPrecio()
//                                ,data.get(position).getComplementos().get(i).getImagen());

       //                 complementos.add(com);
                    }
                    Gson gson = new Gson();
                    gson.toJson(complementos);
                    Log.e("mainActivityInteraction","directo al activity 1 "+gson.toString());
                }
                /**oldmainview*/
//                mainview.interactActivity("producto",data.get(position).getClave(),data.get(position).getNombre(),data.get(position).getPrecio(),
//                        data.get(position).getDescripcion(),data.get(position).getComplementos(),data.get(position).getImagen());


                mainview.interactActivity2("producto",complementos,data.get(position).getSku(),data.get(position).getNombre(),data.get(position).getPrecio(),
                       data.get(position).getDescripcion(),data.get(position).getImagen());
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView precio ,descripcionAlimento,nameAlimento;
        Button agregarb;
        ImageView  imagnproducto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameAlimento=itemView.findViewById(R.id.nameAlimento);
            descripcionAlimento=itemView.findViewById(R.id.descripcionAlimento);
            precio=itemView.findViewById(R.id.precio);
            agregarb=itemView.findViewById(R.id.agregarb);
            imagnproducto=itemView.findViewById(R.id.imagnproducto);
        }
    }
}
