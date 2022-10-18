package com.gomotorscompany.gomotors.Ordenar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.Ordenar.model.newmenu.listaproductos;
import com.gomotorscompany.gomotors.Ordenar.view.ordenarViewImpl;
import com.gomotorscompany.gomotors.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class adapterArmatuPizza  extends RecyclerView.Adapter<adapterArmatuPizza.ViewHolder>{
    private Context context;
    private List<listaproductos> categoria;
    private int pos;
    private ordenarViewImpl myView;
    public adapterArmatuPizza(ordenarViewImpl ordenarView,List<listaproductos> categoria, Context context) {
        this.context=context;
        this.categoria=categoria;
        this.myView=ordenarView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_armatupizza, parent, false);
        return new adapterArmatuPizza.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterArmatuPizza.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
      this.pos=position;


        //region chosse image
        if(categoria.get(position).getNombre().toLowerCase().equals("jose ronni"))
        {
            Glide.with(context).load(R.drawable.pepperoni).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("hula hula")) {
            Glide.with(context).load(R.drawable.hulahula).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("jose roni sp t")) {
            Glide.with(context).load(R.drawable.pepperoni).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("hula hula sp t")) {
            Glide.with(context).load(R.drawable.hulahula).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("quesosa")) {
            Glide.with(context).load(R.drawable.quesosa).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("takipizza sp t")) {
            Glide.with(context).load(R.drawable.takipizza).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("santa sp t")) {
            Glide.with(context).load(R.drawable.santa).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("mex mex sp t")) {
            Glide.with(context).load(R.drawable.memex).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("fans meat sp t")) {
            Glide.with(context).load(R.drawable.fansmeat).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("green diosa sp t")) {
            Glide.with(context).load(R.drawable.greendiosa).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("fanatica sp t")) {
            Glide.with(context).load(R.drawable.fanatica).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("4k sp t")) {
            Glide.with(context).load(R.drawable.cuatroksp).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("jose ronni/hula sp t")) {
            Glide.with(context).load(R.drawable.jrhula).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("jose ronni/mex sp t")) {
            Glide.with(context).load(R.drawable.jrmex).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("jose ronni/santa sp t")) {
            Glide.with(context).load(R.drawable.jrsanta).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("jose roni sp s")) {
            Glide.with(context).load(R.drawable.pepperoni).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("hula hula sp s")) {
            Glide.with(context).load(R.drawable.hulahula).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("quesosa sp s")) {
            Glide.with(context).load(R.drawable.quesosa).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("takipizza sp s")) {
            Glide.with(context).load(R.drawable.takipizza).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("fans meat sp s")) {
            Glide.with(context).load(R.drawable.fansmeat).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("greendiosa sp s")) {
            Glide.with(context).load(R.drawable.greendiosa).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("jose roni / mex sp s")) {
            Glide.with(context).load(R.drawable.jrmex).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("jose roni / santa sp s")) {
            Glide.with(context).load(R.drawable.jrsanta).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("fanatica sp s")) {
            Glide.with(context).load(R.drawable.fanatica).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("4 k sp s")) {
            Glide.with(context).load(R.drawable.cuatroksp).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("santa sp s")) {
            Glide.with(context).load(R.drawable.santa).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("mex mex sp s")) {
            Glide.with(context).load(R.drawable.memex).into(holder.pizza);
        }else if(categoria.get(position).getNombre().toLowerCase().equals("jose roni / hula sp s")) {
            Glide.with(context).load(R.drawable.jrhula).into(holder.pizza);
        }
        else
        {
            Glide.with(context).load(R.drawable.emptypizza).into(holder.pizza);
        }
        //endregion
    }

    @Override
    public int getItemCount() {
        return categoria.size();
    }//
    public int getPosition(){
        return pos;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView pizza;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pizza=itemView.findViewById(R.id.pizza);
        }
    }
}
