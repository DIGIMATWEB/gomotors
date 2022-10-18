package com.gomotorscompany.gomotors.miscompras.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.miscompras.model.set.requeststartOrder;
import com.gomotorscompany.gomotors.miscompras.view.miscompras;

import java.util.List;

public class adapterRequestPedido extends RecyclerView.Adapter<adapterRequestPedido.ViewHolder>{
    private Context context;
    private List<requeststartOrder> startOrder;
    private miscompras myview;
    public adapterRequestPedido(miscompras myview,List<requeststartOrder> startOrder, Context context)
    {
        this.myview=myview;
        this.startOrder=startOrder;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_carrito, parent, false);
        return new adapterRequestPedido.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterRequestPedido.ViewHolder holder, int position) {
        holder.nombre.setText(myview.nombreP);
        holder.descripcion.setText(myview.descripcion);
        holder.direccion.setText(startOrder.get(position).getDireccionEntrega());
        holder.preciofinal.setText("$ "+myview.costofinal);

        adapterComplementos adapter=new adapterComplementos(startOrder.get(position).getComplementos(),context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);

        holder.rv.setLayoutManager(layoutManager);
        holder.rv.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return startOrder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre,descripcion,direccion,preciofinal;
        RecyclerView rv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nameAlimento);
            descripcion=itemView.findViewById(R.id.descripcionAlimento);
            direccion=itemView.findViewById(R.id.direccioncliente);
            preciofinal=itemView.findViewById(R.id.precio);
            rv=itemView.findViewById(R.id.recyclerViewC);






        }
    }
}
