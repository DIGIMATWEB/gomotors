package com.gomotorscompany.gomotors.enprogreso.adapter;

import android.annotation.SuppressLint;
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
    public void onBindViewHolder(@NonNull adapterPendientes.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText("SUC: "+data.get(position).getSuc());
        holder.company.setText("");//+data.get(position).getNegocio());
        holder.order.setText("ORD: # "+data.get(position).getOrden());
        holder.cte.setText("CTE: "+data.get(position).getDireccion());
        holder.apago.setText(""+data.get(position).getApaga());
        holder.date.setText(""+data.get(position).getEnvio());
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
        TextView name,cte,company,order,apago,date;
        Switch aceptar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            company=itemView.findViewById(R.id.negocio);
            order=itemView.findViewById(R.id.orden);
            cte=itemView.findViewById(R.id.cliente);
            apago=itemView.findViewById(R.id.apago);
            date=itemView.findViewById(R.id.date);
            name=itemView.findViewById(R.id.sucursal);
            aceptar=itemView.findViewById(R.id.switchAceptar);
        }
    }
}