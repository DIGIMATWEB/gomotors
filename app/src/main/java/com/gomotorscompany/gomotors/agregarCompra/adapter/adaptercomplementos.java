package com.gomotorscompany.gomotors.agregarCompra.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.Ordenar.model.newmenu.Complemento;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.agregarCompra.model.Complementoold;
import com.gomotorscompany.gomotors.agregarCompra.view.agregarcompra;

import java.util.List;

public class adaptercomplementos extends RecyclerView.Adapter<adaptercomplementos.ViewHolder> {
    private List<Complemento> comp;
    private Context context;
    private agregarcompra myview;

   // private List<Complementoold>  complementosdata=new ArrayList<>();


    public adaptercomplementos(agregarcompra agregarcompra, List<Complemento> comp, Context context)
    {
        this.myview=agregarcompra;
        this.comp=comp;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_complementos, parent, false);
                return new adaptercomplementos.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull adaptercomplementos.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.complementosdesc.setText(comp.get(position).getNombreProductoClasificacion());

        holder. adapterMenu =new adapterimtescomplementos(position,this,myview,comp.get(position).getProducto(),context);
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        holder.rvitems.setLayoutManager(layoutManager);
        holder. rvitems.setAdapter( holder. adapterMenu);
        Log.e("listacomplementos2","complementos   "+"total  "+ comp.size());
        if(comp!=null)
        {
            if(comp.size()>0)
            {
                for(int i=0;i<comp.size();i++)
                {
                    Log.e("listacomplementos3","complementos   "+" categoria "+ comp.get(i).getNombreProductoClasificacion()+" T: "+ comp.get(i).getProducto().size());
                }
            }
        }
        Log.e("listacomplementos3","complementos"  );

    }

    public void getlastlist()
    {

    }
    @Override
    public int getItemCount() {
        return comp.size();
    }///

    public void getlastlist(List<Complementoold> newcomplementoold, int positionrecycler) {
        for(int i=0;i<comp.size();i++) {
            Log.e("listacomplementos", "complementos   " + " categoria " + comp.get(i).getNombreProductoClasificacion() + " T: " + comp.get(i).getProducto().size());
        }
        Log.e("listacomplementos", "complementos" +comp.get(positionrecycler).getNombreProductoClasificacion());
        for (int i = 0; i < newcomplementoold.size(); i++) {
            if(newcomplementoold.get(i).getCantidad()!=0) {
                Log.e("listacomplementos", "" +
                        newcomplementoold.get(i).getSkuComplemento() + "  " +
                        newcomplementoold.get(i).getCantidad() + "  " +
                        newcomplementoold.get(i).getStatus());

                newcomplementoold.get(i).setCategoria(comp.get(positionrecycler).getNombreProductoClasificacion());
            }
        }
        myview.setalldataprices(newcomplementoold);

    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        TextView complementosdesc;
        RecyclerView rvitems;
        adapterimtescomplementos adapterMenu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            complementosdesc=itemView.findViewById(R.id.textCategoria);
            rvitems=itemView.findViewById(R.id.rvcategoriaName);



        }
    }
}
