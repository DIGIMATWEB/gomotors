package com.gomotorscompany.gomotors.Ordenar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.Ordenar.model.tamañosPizza;
import com.gomotorscompany.gomotors.Ordenar.view.ordenarViewImpl;
import com.gomotorscompany.gomotors.R;

import java.util.List;

public class adapterTamañosPizza extends RecyclerView.Adapter<adapterTamañosPizza.ViewHolder>{
    private Context context;
    private List<tamañosPizza> categoria;
    private ordenarViewImpl myView;
    private  boolean first=false;
    private int tpos;

    public adapterTamañosPizza(Context context, List<tamañosPizza> categoria, ordenarViewImpl myView) {
        this.context = context;
        this.categoria = categoria;
        this.myView = myView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(context).inflate(R.layout.item_sizes, parent, false);
                return new adapterTamañosPizza.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterTamañosPizza.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(categoria.get(position).getIschecke())
        {
            holder.buttonSizePizza.setTextColor(context.getColor(R.color.white));
            holder.buttonSizePizza.setBackgroundResource(R.drawable.shape_circle_gray);

        }else
        {
            holder.buttonSizePizza.setTextColor(context.getColor(R.color.black));
            holder.buttonSizePizza.setBackgroundResource(R.drawable.shape_circle_size_pizza);
        }

        holder.buttonSizePizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tpos=position;
                myView.settamaños( categoria.get(position).getTamaño(),position);
              //  notifyDataSetChanged();
            }
        });
    /**  holder.buttonSizePizza.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
              Log.e("switchtamaños",""+ b);

                  if (b == false) {
                      holder.buttonSizePizza.setTextColor(context.getColor(R.color.black));
                      holder.buttonSizePizza.setBackgroundResource(R.drawable.shape_circle_size_pizza);
                  } else {
                      if(tpos!=position) {
                          holder.buttonSizePizza.setTextColor(context.getColor(R.color.white));
                          holder.buttonSizePizza.setBackgroundResource(R.drawable.shape_circle_gray);
                      }
                      else
                      {
                          holder.buttonSizePizza.setTextColor(context.getColor(R.color.black));
                          holder.buttonSizePizza.setBackgroundResource(R.drawable.shape_circle_size_pizza);
                      }
                  }
              notifyDataSetChanged();
          }
      });*/
                holder.buttonSizePizza.setText(categoria.get(position).getTamaño().substring(0, 1));

    }

    @Override
    public int getItemCount() {
        return categoria.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        Button buttonSizePizza;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonSizePizza=itemView.findViewById(R.id.buttonSizePizza);

        }
    }
}
