package com.gomotorscompany.gomotors.miscompras.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.miscompras.model.set.Complemento_startOrder;

import java.util.List;

public class adapterComplementos extends RecyclerView.Adapter<adapterComplementos.ViewHolder>{
    private Context context;
    private List<Complemento_startOrder> compl;
    public adapterComplementos(List<Complemento_startOrder> compl, Context context)
    {
        this.compl=compl;
        this.context=context;
    }
    @NonNull
    @Override
    public adapterComplementos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_complementolist, parent, false);
        return new adapterComplementos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterComplementos.ViewHolder holder, int position) {
      //  Glide.with(context).load(compl.get(position).getSkuComplemento()) holder.imagnproducto
    }

    @Override
    public int getItemCount() {
        return compl.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagnproducto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagnproducto=itemView.findViewById(R.id.imagnproducto);
        }
    }
}
