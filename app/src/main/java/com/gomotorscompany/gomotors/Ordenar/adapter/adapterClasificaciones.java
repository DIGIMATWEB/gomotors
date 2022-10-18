package com.gomotorscompany.gomotors.Ordenar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.Ordenar.model.newmenu.clasificaciones;
import com.gomotorscompany.gomotors.Ordenar.view.ordenarViewImpl;
import com.gomotorscompany.gomotors.R;

import java.util.List;

public class adapterClasificaciones extends RecyclerView.Adapter<adapterClasificaciones.ViewHolder>{
    private Context context;
    private ordenarViewImpl mainview;
    private List<clasificaciones> data;
    public adapterClasificaciones(ordenarViewImpl mainview, List<clasificaciones> data, Context context) {
        this.mainview=mainview;
        this.data=data;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menuclasificaciones, parent, false);
        return new adapterClasificaciones.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterClasificaciones.ViewHolder holder, int position) {
    holder.clasificacion.setText(data.get(position).getClasificacion());
   /**productos  recyclerview de los productos*/
        holder. adapterMenu =new adapterProductos(mainview,data.get(position).getProducto(),context);
                LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        holder.rvitems.setLayoutManager(layoutManager);
        holder. rvitems.setAdapter( holder. adapterMenu);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        TextView clasificacion;
        RecyclerView rvitems;
        adapterProductos adapterMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clasificacion=itemView.findViewById(R.id.textCategoria);
            rvitems=itemView.findViewById(R.id.categoriaName);




        }
    }
}
