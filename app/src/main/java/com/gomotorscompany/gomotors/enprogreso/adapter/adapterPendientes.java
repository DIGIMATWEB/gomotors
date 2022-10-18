package com.gomotorscompany.gomotors.enprogreso.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.enprogreso.model.chekpending.dataorderspending;
import com.gomotorscompany.gomotors.enprogreso.view.enprogreso;

import java.util.List;

public class adapterPendientes extends RecyclerView.Adapter<adapterPendientes.ViewHolder>{
    private Context context;
    private List<dataorderspending> data;
    private enprogreso myview;
    public adapterPendientes(enprogreso myview, List<dataorderspending> data, Context context) {
        this.data=data;
        this.context=context;
        this.myview=myview;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_orderspending, parent, false);
        return new adapterPendientes.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterPendientes.ViewHolder holder, int position) {

        holder.name.setText(data.get(position).getSuc());
        holder.aceptar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true)
                {
                    Log.e("switchAsignar","asignar"+data.get(position).getOrden());
                    myview.asignarOrden(data.get(position).getOrden());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        Switch aceptar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.sucursalname);
            aceptar=itemView.findViewById(R.id.switchAceptar);
        }
    }
}
