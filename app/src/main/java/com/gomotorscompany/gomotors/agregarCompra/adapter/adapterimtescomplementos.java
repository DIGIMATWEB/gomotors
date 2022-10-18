package com.gomotorscompany.gomotors.agregarCompra.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.Ordenar.model.newmenu.productocomplemento;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.agregarCompra.model.Complementoold;
import com.gomotorscompany.gomotors.agregarCompra.view.agregarcompra;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class adapterimtescomplementos extends RecyclerView.Adapter<adapterimtescomplementos.ViewHolder>{

    private Context context;
    private List<productocomplemento> producto;
    private List<productocomplemento> newcomplemento;
    private agregarcompra myview;
    private List<Complementoold>  newcomplementoold=new ArrayList<>();
    private adaptercomplementos adaptercomplemento;
    private int positionrecycler;

    public adapterimtescomplementos(int pos, adaptercomplementos adaptercomplementos, agregarcompra myview, List<productocomplemento> producto, Context context)
    {
        this.adaptercomplemento=adaptercomplementos;
        this.myview=myview;
        this.producto=producto;
        this.context=context;
        this.positionrecycler=pos;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.item_rvcomplementos, parent, false);
                        return new adapterimtescomplementos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterimtescomplementos.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//
        holder.complementosdesc.setText(producto.get(position).getNombre());
        Glide.with(context).load(producto.get(position).getImagen()).into(    holder.imagencomplemento);
        holder.precio.setText(producto.get(position).getPrecio());
        holder.countitems.setText("0");

        newcomplementoold.clear();
        if(newcomplementoold.isEmpty()) {
            for(int i=0;i<producto.size();i++) {
                newcomplementoold.add(new Complementoold(producto.get(position).getSku(), Integer.valueOf(String.valueOf(holder.countitems.getText())), true,producto.get(position).getImagen(),""));
            }
        }

        holder.addbutton.setOnClickListener(new View.OnClickListener() {//revisar
            @Override
            public void onClick(View view) {
                String cantidadComplementos= String.valueOf( holder.countitems.getText());
                int numeroCompl=Integer.valueOf(cantidadComplementos);
                int numcomplementos= numeroCompl+1;
                holder.countitems.setText(String.valueOf(numcomplementos));
                String valorproducto=producto.get(position).getPrecio();
                String valorcorregido=valorproducto.substring(1);
                Double valorUnitario=Double.parseDouble(valorcorregido);
                Double valorAgregado=valorUnitario;





                myview.addtofinalValue(valorAgregado);

                Log.e("bundlemainmenu","complementos   "+"add  "+holder.countitems.getText());

            }
        });
        holder.lesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cantidadComplementos= String.valueOf( holder.countitems.getText());
                int numeroCompl=Integer.valueOf(cantidadComplementos);

                if(numeroCompl==0)
                {
                    Log.e("bundlemainmenu", "complementos   " +" no puedes agregar numeros negativos ");
                }else {
                    int numcomplementos= numeroCompl-1;
                    holder.countitems.setText(String.valueOf(numcomplementos));

                    //valor del item
                    String valorproducto=producto.get(position).getPrecio();
                    String valorcorregido=valorproducto.substring(1);
                    Double valorUnitario=Double.parseDouble(valorcorregido);

                    Double valorAgregado=valorUnitario;


                    if(!holder.countitems.getText().equals("0"))
                    {

                    }

                    myview.addtofinalValue(-valorAgregado);
                }

                Log.e("bundlemainmenu","complementos   "+"add  "+holder.countitems.getText());
            }
        });



        holder.countitems.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                Complementoold complementoupdate=new Complementoold(producto.get(position).getSku(),Integer.valueOf(String.valueOf( holder.countitems.getText())),true,producto.get(position).getImagen(),"");
               if(newcomplementoold.size()!=0) {
                   newcomplementoold.set(position, complementoupdate);

                   adaptercomplemento.getlastlist(newcomplementoold,positionrecycler);

                 //  myview.setalldataprices(newcomplementoold);   /** esta linea debe corregirse ya que debe escribir en el adapter anterior  */

                /*   Log.e("listacomplementos3", "complementos   " + "add  " + newcomplementoold.size());
                   for (int i = 0; i < newcomplementoold.size(); i++) {
                       Log.e("listacomplementos3", "" +
                               newcomplementoold.get(i).getSkuComplemento() + "  " +
                               newcomplementoold.get(i).getCantidad() + "  " +
                               newcomplementoold.get(i).getStatus());
                   }
                   */


               }
            }
        });




    }

    @Override
    public int getItemCount() {
        return producto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView precio,texmoneda,complementosdesc,countitems;
        ImageView lesbutton,addbutton,imagencomplemento;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            precio=itemView.findViewById(R.id.precio);
            texmoneda=itemView.findViewById(R.id.texmoneda);
            complementosdesc=itemView.findViewById(R.id.complementosdesc);
            countitems  =itemView.findViewById(R.id.countitems);
            imagencomplemento= itemView.findViewById(R.id.imagencomplemento);

            lesbutton=itemView.findViewById(R.id.lesbutton);
            addbutton=itemView.findViewById(R.id.addButton);
        }
    }
}
// TextView precio,texmoneda,complementosdesc,countitems;
//        ImageView lesbutton,addbutton;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            precio=itemView.findViewById(R.id.precio);
//            texmoneda=itemView.findViewById(R.id.texmoneda);
//            complementosdesc=itemView.findViewById(R.id.complementosdesc);
//            countitems  =itemView.findViewById(R.id.countitems);
//
//            lesbutton=itemView.findViewById(R.id.lesbutton);
//            addbutton=itemView.findViewById(R.id.addButton);