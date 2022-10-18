package com.gomotorscompany.gomotors.Zonas.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.Zonas.model.Location;
import com.gomotorscompany.gomotors.Zonas.view.zonasViewImpl;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class dotsAdapter extends RecyclerView.Adapter<dotsAdapter.ViewHolder>{

    private zonasViewImpl myView;
    private List<Location> location;
    private Context context;

    public dotsAdapter(zonasViewImpl myView,List<Location> location, Context context) {
        this.myView=myView;
        this.location=location;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_location, parent, false);
        return new  dotsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.latitud.setText(location.get(position).getLatitud());
        holder.longitud.setText(location.get(position).getLongitud());
        if(location.size()>3){
            holder.trash.setVisibility(View.VISIBLE);
            holder.trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    location.remove(position);
                    notifyDataSetChanged();
                    List<LatLng> latLngList=new ArrayList<>();
                    latLngList.clear();
                    for(int i=0;i<location.size();i++)
                    {
                        LatLng latLngi=new LatLng(Double.parseDouble(location.get(i).getLatitud()),Double.parseDouble(location.get(i).getLongitud()));
                        latLngList.add(latLngi);
                    }
                    myView.mapClearaftertrash();
                    myView.DrawnewPolygon(latLngList,"#6A03A9F4");
                }
            });

          }else

        {
            holder.trash.setVisibility(View.GONE);
        }
        holder.dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.zoomtoDot(location.get(position).getLatitud(),location.get(position).getLongitud());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return location.size();
    }


    public class ViewHolder  extends RecyclerView.ViewHolder {
        private ConstraintLayout trash,dot;
        private TextView latitud,longitud;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            trash= itemView.findViewById(R.id.constraintLayout6);
            latitud= itemView.findViewById(R.id.latitud);
            longitud= itemView.findViewById(R.id.longitud);
            dot=itemView.findViewById(R.id.constraintLayout5);
        }
    }
}
