package com.gomotorscompany.gomotors.pedido.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;
import com.bumptech.glide.Glide;

import java.util.List;

public class pagerAdapterPedido extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private int mposition;
    private List<datagetOrders> data;
    public pagerAdapterPedido(List<datagetOrders> data,Context context) {
        this.context= context;
        this.data=data;
        layoutInflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
        public Object instantiateItem(ViewGroup container, int position) {
        View itemView=layoutInflater.inflate(R.layout.item_pager_ordenes,container,false);
        this.mposition=position;
        /**colocar glide images*/
        CardView iv=itemView.findViewById(R.id.cardOrder);
        TextView folio=itemView.findViewById(R.id.txvOrderId1);//folio
        TextView repartidor=itemView.findViewById(R.id.txvClientName1);//repartidor
        TextView sucursal=itemView.findViewById(R.id.txvAddress1);//sucursal
        TextView detalles=itemView.findViewById(R.id.txvDescription1);//detalles
        TextView fecha=itemView.findViewById(R.id.txvDate1);//fecha
        TextView status=itemView.findViewById(R.id.txvStatusDescription1);//Status
        ImageView imageView31= itemView.findViewById(R.id.imageView31);//imagen

        folio.setText(""+data.get(position).getOrdenNum());
        repartidor.setText(data.get(position).getRepartidor());
        sucursal.setText(data.get(position).getNamesuc());
        detalles.setText("Paquetes: "+data.get(position).getPaquete().size()+"  Productos: "+data.get(position).getProductosU().size()+" Complementos: "+data.get(position).getComplemeto().size());
        fecha.setText(data.get(position).getFecha());
       /**el estatus se modificara a otro de los estatys 1 al 3*/
        // status.setText(data.get(position).getStatus());

        //
        if(data.get(position).getSemaforo()==1)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus6cancelado).into(imageView31) ;
            status.setText(data.get(position).getStatus());
            //status.setText("Asignando orden");
        }
        else if(data.get(position).getSemaforo()==2)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus1recolectar).into(imageView31) ;
            status.setText(data.get(position).getStatus());
            //status.setText("Asignando orden");
        }else if(data.get(position).getSemaforo()==3)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus2encola).into(imageView31) ;
            status.setText(data.get(position).getStatus());
            // status.setText("Orden en camino");
        }
        else if(data.get(position).getSemaforo()==4)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus3enprogreso).into(imageView31) ;
            status.setText(data.get(position).getStatus());
            //status.setText("Orden en camino");
        }
        else if(data.get(position).getSemaforo()==5)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus4terminado).into(imageView31) ;
            status.setText(data.get(position).getStatus());
        }
        else if(data.get(position).getSemaforo()==6)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus5noentregado).into(imageView31) ;
            status.setText(data.get(position).getStatus());
        }else if(data.get(position).getSemaforo()==7)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus6cancelado).into(imageView31) ;
            status.setText(data.get(position).getStatus());
        }
        else
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus6cancelado).into(imageView31) ;
            status.setText(data.get(position).getStatus());
        }


//        Glide.with(context)
//                .load(R.drawable.promo)
//
//                .into(iv);
        container.addView(itemView);


        return itemView;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager)container;
        View view = (View)object;
        vp.removeView(view);
    }

}
