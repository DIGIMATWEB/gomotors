package com.gomotorscompany.gomotors.enprogreso.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.enprogreso.adapter.adapterOrdenes;
import com.gomotorscompany.gomotors.enprogreso.adapter.adapterPendientes;
import com.gomotorscompany.gomotors.enprogreso.model.chekpending.dataorderspending;
import com.gomotorscompany.gomotors.enprogreso.presenter.presenterrequestOrders;
import com.gomotorscompany.gomotors.enprogreso.presenter.presenterrequestOrdersImpl;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class enprogreso extends Fragment implements View.OnClickListener,enprogresoView, LocationListener {
    public static final String TAG = enprogreso.class.getSimpleName();
    private presenterrequestOrders presenter;
    private List<datagetOrders> dataorders;
    private adapterOrdenes adapter;
    private adapterPendientes adapterpendings;
    private RecyclerView rv,rvpendientes;
    private ImageView chart;
    private ConstraintLayout constrainpiechr,pedidosencola,dialogpendientes;
    private PieChart pieChart2;
    private double latitude,longitude;
    private LocationManager locationManager;
    private int gerarquiaint;
    private ProgressDialog progressDialog;
    private TextView textpendings;
    private int pendientesSize;
    private Handler handler = new Handler();
    private Runnable runnable;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.en_progreso, container, false);



        // LocationListener locationListenerGPS = null;


        initView(view);
        checkpermisionslevel();
        return view;
    }

    private void checkpermisionslevel() {
        SharedPreferences preferencias = getActivity().getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String gerarquia     = preferencias.getString(GeneralConstantsV2.LEVEL_PERMISIONS, null);

        if(gerarquia.equals("1")) {
            gerarquiaint=Integer.valueOf( gerarquia);
            Log.e("ordeneslista", "admministrador   " + gerarquia);
        }else if(gerarquia.equals("2"))
        {
            gerarquiaint=Integer.valueOf( gerarquia);
            Log.e("ordeneslista", "repartidores   " + gerarquia);


            locationManager= (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this.getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((this.getActivity()), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

            }
            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,200,5,this);
            } else {

            }

        }
        else if(gerarquia.equals("3"))
        {
            gerarquiaint=Integer.valueOf( gerarquia);
            Log.e("ordeneslista", " usuario cliente  " + gerarquia);
        }
    }

    private void initView(View view) {

        /** miscompras / model / get */
        rv=view.findViewById(R.id.rvOrdenesl);
        rvpendientes =view.findViewById(R.id.rvpendientes);
        chart=view.findViewById(R.id. chart);
        chart.setOnClickListener(this);
        pieChart2=view.findViewById(R.id.pieChart2);
        constrainpiechr= view.findViewById(R.id. constrainpiechr);
        constrainpiechr.setVisibility(View.GONE);
        pedidosencola=view.findViewById(R.id.pedidosencola);
        pedidosencola.setOnClickListener(this);
        textpendings=view.findViewById(R.id.numberpedidos);
        dialogpendientes =view.findViewById(R.id.dialogpendientes);
        dialogpendientes.setOnClickListener(this);
        progressDialog = new ProgressDialog(getActivity());
        presenter=new presenterrequestOrdersImpl(this,getContext());
        presenter.requestOrders();
        presenter.checkEncola();

    }
    @Override
    public void hideprogresdialog() {
      //  progressDialog.dismiss();
    }

    @Override
    public void showprogresdialog() {
//        progressDialog.setMessage("Cargando datos ...");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
    }

    @Override
    public void setOrdenesPendientes(List<dataorderspending> data) {

        if(data!=null)
        {
            this.pendientesSize=data.size();
            if(gerarquiaint==2) {
                if (!data.isEmpty()) {
                    if (pendientesSize > 0) {
                        pedidosencola.setVisibility(View.VISIBLE);
                    } else {
                        pedidosencola.setVisibility(View.GONE);
                    }
                } else {
                    pedidosencola.setVisibility(View.GONE);
                }
            }
            textpendings.setText(""+pendientesSize);
            fillrvpendientes(data);
        }
    }

    @Override
    public void succesAsignarRepartidor(int code) {
        if(code==200)
        {
          //  Toast.makeText(getContext(), "Repartidor Asignado", Toast.LENGTH_SHORT).show();
            getActivity().recreate();
        }
    }


    @Override
    public void setOreders(List<datagetOrders> data) {
        this.dataorders=data;
        loopHandler();
        if(dataorders!=null)
        {   if(!dataorders.isEmpty())
            {
                if(dataorders.get(0).getSemaforo()>5)
                {
                    presenter.liberarRepartidor();
                }

            }else
        {

        }
            filldata(dataorders);
            fillChart(dataorders);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);


    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    private void loopHandler() {
        handler.postDelayed(runnable= new Runnable() {
            public void run() {

                presenter.requestOrders();
                presenter.checkEncola();

            }
        }, 10000);
    }
    private void filldata(List<datagetOrders> dataorders) {
        adapter=new adapterOrdenes(dataorders,getContext());
         LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(adapter);

                
    }
    private void fillrvpendientes(List<dataorderspending> data) {
        adapterpendings=new adapterPendientes(this,data,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvpendientes.setLayoutManager(layoutManager);
        rvpendientes.setAdapter(adapterpendings);
    }
    public void asignarOrden(int idorder)
    {
        dialogpendientes.setVisibility(View.GONE);
        presenter.setOrdertorapartidor(idorder);
    }

    private void fillChart(List<datagetOrders> dataorders) {
        List<String> ordenes=new ArrayList<>();
        ordenes.clear();
        for (int i = 0; i<dataorders.size(); i++){
            ordenes.add(""+dataorders.get(i).getSemaforo());
        }
        int asignando = Collections.frequency(ordenes,"1");
        int recolectar = Collections.frequency(ordenes,"2");
        int encola = Collections.frequency(ordenes,"3");
        int progreso = Collections.frequency(ordenes,"4");
        int terminado = Collections.frequency(ordenes,"5");
        int noentregado = Collections.frequency(ordenes,"6");
        int cancelado = Collections.frequency(ordenes,"7");

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label = "type";

        Map<String, Integer> typeAmountMap = new HashMap<>();
        typeAmountMap.put("asignando",asignando);
        typeAmountMap.put("recolectar",recolectar);
        typeAmountMap.put("encola",encola);
        typeAmountMap.put("progreso",progreso);
        typeAmountMap.put("terminado",terminado);
        typeAmountMap.put("noentregado",noentregado);
        typeAmountMap.put("Cancelados",cancelado);

        //initializing colors for the entries
                ArrayList<Integer> colors = new ArrayList<>();
                colors.add(Color.parseColor("#585858"));
                colors.add(Color.parseColor("#FFC107"));
               // colors.add(Color.parseColor("#00C48C"));
                colors.add(Color.parseColor("#FF5722"));
                //colors.add(Color.parseColor("#FF0000"));
                //colors.add(Color.parseColor("#FF000000"));

        //input data and fit data into pie chart entry
        for(String type: typeAmountMap.keySet()){
            pieEntries.add(new PieEntry(typeAmountMap.get(type)));
        }

        //collecting the entries with label name
        PieDataSet pieDataSet = new PieDataSet(pieEntries,null);
        //setting text size of the value

        pieDataSet.setValueTextSize(12f);
        //pieDataSet.setValueTextColor(254);
//        pieChart2.getDescription().setEnabled(false);
//        pieChart2.setRotationEnabled(false);
       // pieChart2.setCenterText(progreso+" de "+String.valueOf(tickets.size()));
        //providing color list for coloring different entries
         pieDataSet.setColors(colors);
        //grouping the data set from entry to chart
        PieData pieData = new PieData(pieDataSet);

        pieChart2.setHoleRadius(70f);
        pieChart2.getLegend().setEnabled(false);
        pieChart2.setData(pieData);
        pieChart2.animateXY(500,500);
        pieChart2.invalidate();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chart:
                if(constrainpiechr.getVisibility()==View.GONE)
                {
                    constrainpiechr.setVisibility(View.VISIBLE);
                }else
                {
                    constrainpiechr.setVisibility(View.GONE);
                }
                break;
            case R.id.pedidosencola:
                presenter.checkEncola();
                if(dialogpendientes.getVisibility()==View.VISIBLE)
                {
                    dialogpendientes.setVisibility(View.GONE);
                }else
                {
                    if(pendientesSize>0) {
                        dialogpendientes.setVisibility(View.VISIBLE);
                    }else
                    {
                        Toast.makeText(getContext(), "No hay ordenes pendientes", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.dialogpendientes:
                break;
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if(gerarquiaint==2){//location.getLatitude()!=latitude&&
        this.latitude=location.getLatitude();
        this.longitude=location.getLongitude();
        Log.e("findlocation", " update for      Lat:" + latitude + "   Long: " + longitude);
         //   Toast.makeText(getContext(), "Lat: "+latitude+"   Long: "+longitude, Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                  presenter.setposition(latitude,longitude);
                }
            }, 10000);

        }

    }
}
//           for(int i=0;i<dataorders.size();i++) {
//                Log.e("ordeneslista", ""
//                        + dataorders.get(i).getOrdenNum()+" "
//                        + dataorders.get(i).getSemaforo()+" "
//                        + dataorders.get(i).getSemaforo()+" "
//                        + dataorders.get(i).getToken()+" "
//                        + dataorders.get(i).getSuc()+" "
//                        + dataorders.get(i).getFecha()+" "
//                        + dataorders.get(i).getDireccion()+" "
//                        + dataorders.get(i).getLatitud()+" "
//                        + dataorders.get(i).getLonguitud()+" "
//                        + dataorders.get(i).getPaquete().size()+" "
//                        + dataorders.get(i).getProductosU().size()+" "
//                        + dataorders.get(i).getComplemeto().size()+" ");
//            }