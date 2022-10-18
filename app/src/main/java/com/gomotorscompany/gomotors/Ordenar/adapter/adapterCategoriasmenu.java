package com.gomotorscompany.gomotors.Ordenar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.R;

import java.util.List;

public class adapterCategoriasmenu extends RecyclerView.Adapter<adapterCategoriasmenu.ViewHolder>{
    private Context context;
    private List<String> categorias;


    public adapterCategoriasmenu(List<String> categorias,Context context)
    {
        this.categorias=categorias;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_categoriasdelmenu, parent, false);
        return new adapterCategoriasmenu.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterCategoriasmenu.ViewHolder holder, int position) {
        holder.categoriasfolder.setText("Pizza");//categorias.get(position));
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoriasfolder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoriasfolder=itemView.findViewById(R.id.categoriasfolder);
        }
    }
}
