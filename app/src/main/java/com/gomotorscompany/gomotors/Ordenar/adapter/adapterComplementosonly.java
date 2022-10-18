package com.gomotorscompany.gomotors.Ordenar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.Ordenar.model.ingredientesAlone.dataIngredients;
import com.gomotorscompany.gomotors.Ordenar.view.ordenarViewImpl;
import com.gomotorscompany.gomotors.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class adapterComplementosonly extends RecyclerView.Adapter<adapterComplementosonly.ViewHolder>{
    private Context context;
    private List<dataIngredients> aloneIngredients;
    private  ordenarViewImpl myView;

    public adapterComplementosonly(List<dataIngredients> aloneIngredients, Context context, ordenarViewImpl ordenarView) {
        this.context=context;
        this.aloneIngredients=aloneIngredients;
        this.myView=ordenarView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_carousel_complementos, parent, false);
        return new adapterComplementosonly.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterComplementosonly.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if( aloneIngredients.get(position).getChecked()==true)
        {
            holder.choosecomplement.setVisibility(View.VISIBLE);
        }else
        {
            holder.choosecomplement.setVisibility(View.GONE);
        }

        holder.constraintComplementmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.choosecomplement.getVisibility()== View.GONE)
                {
                    holder.choosecomplement.setVisibility(View.VISIBLE);
                    aloneIngredients.get(position).setChecked(true);
                    myView.retrieveAloneIngredients(aloneIngredients);
                }else
                {
                    holder.choosecomplement.setVisibility(View.GONE);
                    aloneIngredients.get(position).setChecked(false);
                    myView.retrieveAloneIngredients(aloneIngredients);
                }
            }
        });
        holder.textalonecomplement.setText(aloneIngredients.get(position).getNameIngrediente());
        if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("adobada"))
        {
            Glide.with(context).load(R.drawable.adobada).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("cebolla")) {
            Glide.with(context).load(R.drawable.extracebolla).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("champiñon")) {
            Glide.with(context).load(R.drawable.champinon).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("chorizo")) {
            Glide.with(context).load(R.drawable.chorizo).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("extra aceituna")) {
            Glide.with(context).load(R.drawable.aceitunasextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("extra adobada")) {
            Glide.with(context).load(R.drawable.extradobada).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("extra aceituna")) {
            Glide.with(context).load(R.drawable.aceitunasextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("extra cebolla")) {
            Glide.with(context).load(R.drawable.extracebolla).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("extra champiñon")) {
            Glide.with(context).load(R.drawable.champinon).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("extra chorizo")) {
            Glide.with(context).load(R.drawable.chorizo).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("extra frijoles")) {
            Glide.with(context).load(R.drawable.frijolesextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("extra jalapeño")) {
            Glide.with(context).load(R.drawable.jalapenoextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("extra jamon")) {
            Glide.with(context).load(R.drawable.jamonextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("extra morron")) {
            Glide.with(context).load(R.drawable.pimientosextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("extra piña")) {
            Glide.with(context).load(R.drawable.pinaextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("extra queso crema")) {
            Glide.with(context).load(R.drawable.quesocremaextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("extra salchicha")) {
            Glide.with(context).load(R.drawable.salchichaextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("extra tocino")) {
            Glide.with(context).load(R.drawable.tocinoextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("jalapeño")) {
            Glide.with(context).load(R.drawable.jalapenoextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("jamon")) {
            Glide.with(context).load(R.drawable.jamonextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("peperoni extra tamaño chico")) {
            Glide.with(context).load(R.drawable.peperoniextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("pepperoni")) {
            Glide.with(context).load(R.drawable.peperoniextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("pimiento")) {
            Glide.with(context).load(R.drawable.pimientosextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("piña")) {
            Glide.with(context).load(R.drawable.pinaextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("porcion de queso extra")) {
            Glide.with(context).load(R.drawable.quesoextra).into(holder.imageIngredient);
        }
        else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("q crema")) {
            Glide.with(context).load(R.drawable.quesocremaextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("queso")) {
            Glide.with(context).load(R.drawable.quesoextra).into(holder.imageIngredient);
        }else if(aloneIngredients.get(position).getNameIngrediente().toLowerCase().equals("tocino")) {
            Glide.with(context).load(R.drawable.jamonextra).into(holder.imageIngredient);
        }else {

            Glide.with(context).load(R.drawable.peperoni).into(holder.imageIngredient);
        }
    }

    @Override
    public int getItemCount() {
        return aloneIngredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout choosecomplement,constraintComplementmain;
        ImageView imageIngredient;
        TextView textalonecomplement;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            choosecomplement=itemView.findViewById(R.id.choosecomplement);
            constraintComplementmain=itemView.findViewById(R.id.constraintComplementmain);
            imageIngredient=itemView.findViewById(R.id.imageIngredient);
            textalonecomplement=itemView.findViewById(R.id.textalonecomplement);
        }
    }
}
