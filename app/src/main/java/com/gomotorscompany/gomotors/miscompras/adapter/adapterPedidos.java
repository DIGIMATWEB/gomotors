package com.gomotorscompany.gomotors.miscompras.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;
import com.bumptech.glide.Glide;

import java.util.List;

public class adapterPedidos extends RecyclerView.Adapter<adapterPedidos.ViewHolder>{
    private Context context;
    private List<datagetOrders> data;
    public adapterPedidos(List<datagetOrders> data,Context context)
    {
        this.data=data;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);//item_carrito
        return  new adapterPedidos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterPedidos.ViewHolder holder, int position) {
        holder.orden.setText("Num. orden:"+String.valueOf( data.get(position).getOrdenNum()));
        holder.nombreRepartidor.setText(""+data.get(position).getRepartidor());
        holder.direccion.setText(data.get(position).getDireccion());
        holder.descripcionPaquete.setText(
                "Paquetes : "+data.get(position).getPaquete().size()+" "+
                        "Productos : "+data.get(position).getProductosU().size()+" "+
                        "Complementos : "+ data.get(position).getComplemeto().size());
        holder.fecha.setText(data.get(position).getFecha());
        holder.statusorden.setText(data.get(position).getStatus());
        if(data.get(position).getSemaforo()==1)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus6cancelado).into(holder.imageView31) ;
        }
        else if(data.get(position).getSemaforo()==2)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus1recolectar).into(holder.imageView31) ;
        }else if(data.get(position).getSemaforo()==3)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus2encola).into(holder.imageView31) ;
        }
        else if(data.get(position).getSemaforo()==4)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus3enprogreso).into(holder.imageView31) ;
        }
        else if(data.get(position).getSemaforo()==5)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus4terminado).into(holder.imageView31) ;
        }
        else if(data.get(position).getSemaforo()==6)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus5noentregado).into(holder.imageView31) ;
        }else if(data.get(position).getSemaforo()==7)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus6cancelado).into(holder.imageView31) ;
        }
        else
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus6cancelado).into(holder.imageView31) ;
        }

     /*   holder.cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle dataorder=new Bundle();

                dataorder.putString("direccionBundle1", ""+data.get(position).getOrdenNum());
                dataorder.putString("direccionBundle2", data.get(position).getFecha());
                dataorder.putString("direccionBundle3", data.get(position).getStatus());
                dataorder.putInt("direccionBundle4", data.get(position).getSemaforo());
                //dataorder.putString("direccionBundle5", ""+data.get(position).getPaquete().size());
                //dataorder.putString("direccionBundle6", ""+data.get(position).getProductosU().size());
                //dataorder.putString("direccionBundle7", ""+data.get(position).getComplemeto().size());

                dataorder.putDouble("direccionBundle8", data.get(position).getLatitud());
                dataorder.putDouble("direccionBundle9", data.get(position).getLonguitud());
                dataorder.putString("direccionBundle10", ""+data.get(position).getDireccion());
                dataorder.putString("direccionBundle11", ""+data.get(position).getSuc());

                Intent intent = new Intent(context.getApplicationContext(), progresodetail.class);
                intent.putExtras(dataorder);
                intent.putExtra("direccionBundle5", (Serializable) data.get(position).getPaquete());
                intent.putExtra("direccionBundle6", (Serializable) data.get(position).getProductosU());
                intent.putExtra("direccionBundle7", (Serializable) data.get(position).getComplemeto());
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        TextView orden,nombreRepartidor,direccion,descripcionPaquete,fecha,statusorden;
        CardView cardOrder;
        ImageView imageView31;
        public ViewHolder(@NonNull View view) {
            super(view);
            orden=itemView.findViewById(R.id.txvOrderId1);
            nombreRepartidor=itemView.findViewById(R.id.txvClientName1);
            direccion=itemView.findViewById(R.id.txvAddress1);
            descripcionPaquete=itemView.findViewById(R.id.txvDescription1);
            fecha=itemView.findViewById(R.id.txvDate1);
            statusorden=itemView.findViewById(R.id.txvStatusDescription1);
            cardOrder= itemView.findViewById(R.id. cardOrder);
            imageView31= itemView.findViewById(R.id. imageView31);
        }
    }
}
