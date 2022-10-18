package com.gomotorscompany.gomotors.pedido.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gomotorscompany.gomotors.Dialogs.asignando.view.asignando;
import com.gomotorscompany.gomotors.Dialogs.pedido.view.sinpedidos;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.Zonas.model.dataRapartidores;
import com.gomotorscompany.gomotors.mainContent.view.mainContentViewImpl;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;
import com.gomotorscompany.gomotors.pedido.adapter.pagerAdapterPedido;
import com.gomotorscompany.gomotors.pedido.model.repartidores.dataRepartidores;
import com.gomotorscompany.gomotors.pedido.presenter.presenterPedidoImpl;
import com.gomotorscompany.gomotors.pedido.presenter.presenterpedido;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class pedido extends Fragment implements View.OnClickListener, OnMapReadyCallback,pedidoView {
    public static final String TAG = pedido.class.getSimpleName();
    private MapView mView;
    private GoogleMap mMap;
    private double latitude;
    private double longitude;
    private Marker mainIconMarker;
    private Context context ;

    private pagerAdapterPedido pAdapter;
    private ViewPager pager;
    private List<datagetOrders> fulldata;
    private presenterpedido presenter;

    private TextView numberPedidos;
    private List<dataRepartidores> repartidores;
    private List<dataRapartidores> Allrepartidores;
    private List<Marker>  mainIconMarkers=new ArrayList<>();
    private Handler handler = new Handler();
    private Runnable runnable;
    private Boolean isenable=true;
    private mainContentViewImpl mainActivity;
    private int idrepartidorElegino=0;
    private boolean beginin=false;
    private ProgressDialog progressDialog;
    // private FragmentManager fm = getActivity().getSupportFragmentManager();;

    private boolean dotoncegeofence=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pedido, container, false);
        mainActivity= (mainContentViewImpl) this.getActivity();
        initView(view);
        mView.onCreate(savedInstanceState);
        if (mView != null) {
            mView.getMapAsync(this);
        }
        return view;
    }

    private void initView(View view) {
        context=getContext();
        mView = view.findViewById(R.id.map_view_tracking);
        pager=view.findViewById(R.id.viewPager);
        numberPedidos=view.findViewById(R.id.numberpedidos);
       // progressDialog = new ProgressDialog(getActivity());
        presenter=new presenterPedidoImpl(this,context);
        presenter.requestOrder();


    }
    /**el pager se escribe al llenar las ordenes*/
    private void fillPagerAdapter(List<datagetOrders> data) {
        pAdapter = new pagerAdapterPedido( data,getContext());//(getChildFragmentManager(), fulldata.get(companyIndex).getBanner(),getContext());
        pager.setAdapter(pAdapter);

    }

//region Mapa
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
    mMap = googleMap;
        uiSettingsMap(mMap);
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            context, R.raw.style_json));

            if (!success) {
              //  Log.e("MapsActivityRaw", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
          //  Log.e("MapsActivityRaw", "Can't find style.", e);
        }
        mMap.clear();

     mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(25.5383667, -103.446846), 12.0f));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                mMap.animateCamera(CameraUpdateFactory.scrollBy(0.0f, 1350));
            }
        }, 500);

}


    private void uiSettingsMap(GoogleMap mMap) {
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        mMap.setPadding (0, 1400, 0, 80);
    }
    //endregion mapa

/** todas las ordenes*/
    @Override
    public void setDataToView(List<datagetOrders> data) {   /**todos los datos de las ordenes*/
    this.fulldata=data;
    if(fulldata!=null&&isenable) {
    if(!fulldata.isEmpty()) {
        Log.e("pedidos", "" + fulldata.size());
        numberPedidos.setText(String.valueOf(fulldata.size()));
        fillPagerAdapter(fulldata);
        if (idrepartidorElegino == 0 && fulldata.get(0).getIdRepartidor() == null) {
            presenter.repartidoresmasCercanos();
            presenter.requestFullRepartidores();
        } else {
            idrepartidorElegino = fulldata.get(0).getIdRepartidor();
            mMap.clear();
            presenter.requestFullRepartidores();
        }
        markerset(fulldata.get(0).getLatitud(), fulldata.get(0).getLonguitud());   /**marcador del cliente*/
        drawComercio(fulldata.get(0).getLatsuc(), fulldata.get(0).getLongsuc());/***/
    }else
    {
        setDialogsinOrdenes();
    }
    }

    }
    public void actionsfromdialog()
    {
        mainActivity.goTomiscompras();
    }
    @Override
    public void setDialogAsignando() {
        asignando asignandopedido=new asignando();
        asignandopedido.show(getActivity().getSupportFragmentManager(), asignando.TAG);
    }

    @Override
    public void setDialogsinOrdenes() {


        sinpedidos externalGPSDialog = new sinpedidos();
//        //externalGPSDialog.setLocationVehicle(lat, lng);
        externalGPSDialog.show(getActivity().getSupportFragmentManager(), sinpedidos.TAG);
/*
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.sin_pedidos, null);
//
//        TextView mTextView = (TextView) view.findViewById(R.id.textview);
//        mTextView.setText(mDataRecieved);
//        setCancelable(false);

        builder.setView(view);
        builder.setCancelable(false);
        builder.setPositiveButton("ir a menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mainActivity.goTommenu();
                        Log.e("dialogovacio", "ir a menu");
                    }
                });

                builder.create();
        builder.show();

*/
    }

    @Override
    public void hideprogresdialog() {
     //   progressDialog.dismiss();
    }

    @Override
    public void showprogresdialog() {
//        progressDialog.setMessage("Cargando datos ...");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
      //  Routing sad=
    }

    /** repartidores mas cercanos*/
    @Override
    public void setRepartidores(List<dataRepartidores> data) {/**repartidores mas cercanos*/
        this.repartidores=data;
        if(repartidores!=null)
        {   if(repartidores.get(0).getListaRepa()!=null){
                ArrayList<Double> list=new ArrayList<>();
                list.clear();
                for(int i=0;i<repartidores.get(0).getListaRepa().size();i++)
                {
                    list.add(repartidores.get(0).getListaRepa().get(i).getDistancia());
                }
                int index= list.indexOf( Collections.min(list));
                Log.e("motosCercanas",""+repartidores.size()+"  distancia Repartidor: i "+repartidores.get(0).getListaRepa().get(index).getIdRepartidor());
                /**TODO obtenermos el token del repartidor*/
               // String tokenrepartidor=repartidores.get(0).getListaRepa().get(index).getToken();
               // idrepartidorElegino=repartidores.get(0).getListaRepa().get(index).getIdRepartidor();
               // presenter.setOrdertorapartidor(tokenrepartidor,fulldata.get(0).getOrdenNum());


            }
        }
    }


    /** pinta a todos los repartidores*/
    @Override
    public void setFullRepartidores(List<dataRapartidores> repartidoresAll) {/**repartidores TODO-S*/
        this.Allrepartidores=repartidoresAll;
        if(Allrepartidores!=null&&isenable)//enavble si el fragmento existe
        {

            if(idrepartidorElegino==0)
            {
                repartidoresfill();
                Log.e("motosCercanas"," request todaslas motos");
                setDialogAsignando();
            }else
            {
                Log.e("motosCercanas"," requestmoto asignada");
                List<dataRapartidores> repartidorAsigned=new ArrayList<>();
                repartidorAsigned.clear();
                mainIconMarkers.clear();
                for(int i=0;i<Allrepartidores.size();i++)
                {
                    if(Allrepartidores.get(i).getId()==idrepartidorElegino)
                    {
                        repartidorAsigned.add(Allrepartidores.get(i));
                    }
                }
                Allrepartidores.clear();
                Allrepartidores=repartidorAsigned;
                repartidoresfill2();
                loopHandler();
            }

        }

    }

    @Override
    public void codeAsignacion(int code) {
        if(code==200)
        {
            presenter.requestOrder();
        }
        else{
            Toast.makeText(context, "No se asigno Repartidor", Toast.LENGTH_SHORT).show();
        }
    }



    /**ciclo de repeticion*/
    private void loopHandler() {
        handler.postDelayed(runnable= new Runnable() {
            public void run() {
                //iteration=iteration+1;
                presenter.requestOrder();
                if(dotoncegeofence==false)
                {
                    getmatrixdistance();
                    dotoncegeofence=true;
                }
                Log.e("motos","cargando ubicacion de motos");

            }
        }, 10000);
    }

    private void getmatrixdistance() {
        Log.e("matrix","cargando ubicacion de motos");
        presenter.matrix();
    }

    /**pinta la sucursal*/
    private void drawComercio(Double latitudelat,Double longi) {
        LatLng newlandapps = new LatLng(latitudelat, longi);
        //LatLng driver = new LatLng(Double.parseDouble( listRepartidores.get(i).getLatitud()), Double.parseDouble( listRepartidores.get(i).getLongitud()));
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.tienda);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 180, 180, false);
        mMap.addMarker(new MarkerOptions().position(newlandapps).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
    }
    /**pinta la ubicacion del usuario*/
    private void markerset(Double latitudelat,Double longi) {/**cambiar esta hubiacione por  la del pedido**/
        LatLng newlandapps = new LatLng(latitudelat, longi);
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bluedot);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 60, 60, false);
        mainIconMarker= mMap.addMarker(new MarkerOptions().position(newlandapps).icon(  BitmapDescriptorFactory.fromBitmap(smallMarker)));
        if(beginin=false) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newlandapps, 12.0f));
            beginin=true;
        }


    }
    /**pinta a un repartidor demo*/
    private void repartidor() {
        LatLng newlandapps = new LatLng(25.5393667, -103.444546);
        //LatLng driver = new LatLng(Double.parseDouble( listRepartidores.get(i).getLatitud()), Double.parseDouble( listRepartidores.get(i).getLongitud()));
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.do1);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 180, 180, false);
        mMap.addMarker(new MarkerOptions().position(newlandapps).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
    }
    private void repartidoresfill2()
    {
        if(Allrepartidores!=null)
        {

            for(int i=0;i<Allrepartidores.size();i++)
            {

                Log.e("repartidoresV", "" + Allrepartidores.get(i).getNombre());


                LatLng driver = new LatLng(Double.parseDouble(Allrepartidores.get(i).getLatitud()), Double.parseDouble(Allrepartidores.get(i).getLongitud()));
                BitmapDrawable bitmapdraw = (BitmapDrawable) getActivity().getResources().getDrawable(R.drawable.do1);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 180, 180, false);

                if (mMap != null) {
                    //mainIconMarker
                    if(mainIconMarkers.size()!=Allrepartidores.size()) {
                        // mainIconMarker = mMap.addMarker(new MarkerOptions().position(driver).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                        //  mainIconMarker.setTag(listRepartidores.get(i).getId());

                        if(mainIconMarkers.isEmpty())
                        {

                            mainIconMarkers.add(mMap.addMarker(new MarkerOptions().position(driver).icon(BitmapDescriptorFactory.fromBitmap(smallMarker))));
                            mainIconMarkers.get(0).setTag(Allrepartidores.get(i).getId());
                            //     historicdrivers.add(new historicData(iteration,Allrepartidores.get(i).getId(),Double.parseDouble(Allrepartidores.get(i).getLatitud()), Double.parseDouble(Allrepartidores.get(i).getLongitud())));
                        }else{
                            for(int k=0;k<mainIconMarkers.size();k++)
                            {
                                if (mainIconMarkers.get(k).getTag().equals(Allrepartidores.get(i).getId())) {

                                    mainIconMarker.setPosition(new LatLng(Double.parseDouble(Allrepartidores.get(i).getLatitud()), Double.parseDouble(Allrepartidores.get(i).getLongitud())));
                                    mainIconMarkers.set(k,mMap.addMarker(new MarkerOptions().position(driver).icon(BitmapDescriptorFactory.fromBitmap(smallMarker))));
                                    mMap.addMarker(new MarkerOptions().position(mainIconMarkers.get(k).getPosition()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                                } else {
                                    Marker newMarker=mMap.addMarker(new MarkerOptions().position(driver).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                                    newMarker.setTag(Allrepartidores.get(i).getId());
                                    mainIconMarkers.add(newMarker);
                                    //      historicdrivers.add(new historicData(iteration,Allrepartidores.get(i).getId(),Double.parseDouble(Allrepartidores.get(i).getLatitud()), Double.parseDouble(Allrepartidores.get(i).getLongitud())));
                                    Log.e("motoslista",  "   "+k+"  "+i+"   " + mainIconMarkers.size());
                                    break;
                                }
                            }
                        }
                    }else
                    {
                        for(int k=0;k<mainIconMarkers.size();k++) {
                            if (mainIconMarkers.get(k).getTag().equals(Allrepartidores.get(i).getId())) {
                                //  historicdrivers.add(new historicData(iteration,Allrepartidores.get(i).getId(),Double.parseDouble(Allrepartidores.get(i).getLatitud()), Double.parseDouble(Allrepartidores.get(i).getLongitud())));
                                /**manejar que se mueva lento la moto*/
                                mainIconMarkers.get(k).setPosition(new LatLng(Double.parseDouble(Allrepartidores.get(i).getLatitud()), Double.parseDouble(Allrepartidores.get(i).getLongitud())));


                            }
                        }
                        //     Log.e("motoslista", "" + mainIconMarkers.size());

                    }


                }
            }
            //   Log.e("motoslista1", "" + historicdrivers.size());
        }

    }
     /**llena todos los repartidores*/
    private void repartidoresfill()
    {
        if(Allrepartidores!=null)
        {

            for(int i=0;i<Allrepartidores.size();i++)
            {

                Log.e("repartidoresV", "" + Allrepartidores.get(i).getNombre());


                LatLng driver = new LatLng(Double.parseDouble(Allrepartidores.get(i).getLatitud()), Double.parseDouble(Allrepartidores.get(i).getLongitud()));
                BitmapDrawable bitmapdraw = (BitmapDrawable) getActivity().getResources().getDrawable(R.drawable.do1);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 180, 180, false);

                if (mMap != null) {
                    //mainIconMarker
                    if(mainIconMarkers.size()!=Allrepartidores.size()) {
                        // mainIconMarker = mMap.addMarker(new MarkerOptions().position(driver).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                        //  mainIconMarker.setTag(listRepartidores.get(i).getId());

                        if(mainIconMarkers.isEmpty())
                        {

                            mainIconMarkers.add(mMap.addMarker(new MarkerOptions().position(driver).icon(BitmapDescriptorFactory.fromBitmap(smallMarker))));
                            mainIconMarkers.get(0).setTag(Allrepartidores.get(i).getId());
                       //     historicdrivers.add(new historicData(iteration,Allrepartidores.get(i).getId(),Double.parseDouble(Allrepartidores.get(i).getLatitud()), Double.parseDouble(Allrepartidores.get(i).getLongitud())));
                        }else{
                            for(int k=0;k<mainIconMarkers.size();k++)
                            {
                                if (mainIconMarkers.get(k).getTag().equals(Allrepartidores.get(i).getId())) {

                                    mainIconMarker.setPosition(new LatLng(Double.parseDouble(Allrepartidores.get(i).getLatitud()), Double.parseDouble(Allrepartidores.get(i).getLongitud())));
                                    mainIconMarkers.set(k,mMap.addMarker(new MarkerOptions().position(driver).icon(BitmapDescriptorFactory.fromBitmap(smallMarker))));
                                    mMap.addMarker(new MarkerOptions().position(mainIconMarkers.get(k).getPosition()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                                } else {
                                    Marker newMarker=mMap.addMarker(new MarkerOptions().position(driver).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                                    newMarker.setTag(Allrepartidores.get(i).getId());
                                    mainIconMarkers.add(newMarker);
                              //      historicdrivers.add(new historicData(iteration,Allrepartidores.get(i).getId(),Double.parseDouble(Allrepartidores.get(i).getLatitud()), Double.parseDouble(Allrepartidores.get(i).getLongitud())));
                                    Log.e("motoslista",  "   "+k+"  "+i+"   " + mainIconMarkers.size());
                                    break;
                                }
                            }
                        }
                    }else
                    {
                        for(int k=0;k<mainIconMarkers.size();k++) {
                            if (mainIconMarkers.get(k).getTag().equals(Allrepartidores.get(i).getId())) {
                              //  historicdrivers.add(new historicData(iteration,Allrepartidores.get(i).getId(),Double.parseDouble(Allrepartidores.get(i).getLatitud()), Double.parseDouble(Allrepartidores.get(i).getLongitud())));
                                /**manejar que se mueva lento la moto*/
                                mainIconMarkers.get(k).setPosition(new LatLng(Double.parseDouble(Allrepartidores.get(i).getLatitud()), Double.parseDouble(Allrepartidores.get(i).getLongitud())));


                            }
                        }
                        //     Log.e("motoslista", "" + mainIconMarkers.size());

                    }


                }
            }
         //   Log.e("motoslista1", "" + historicdrivers.size());
        }

    }
    @Override
    public void onStart() {
        super.onStart();
        mView.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        mView.onResume();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isenable=false;
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
        mView.onPause();
        handler.removeCallbacks(runnable);


    }

    @Override
    public void onStop() {
        super.onStop();
        mView.onStop();
        handler.removeCallbacks(runnable);
        Log.e("onStop", "OK");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mView.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }


}