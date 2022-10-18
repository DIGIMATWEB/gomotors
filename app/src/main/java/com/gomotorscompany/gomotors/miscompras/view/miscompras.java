package com.gomotorscompany.gomotors.miscompras.view;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.Ordenar.view.ordenarViewImpl;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.agregarCompra.model.Complementoold;
import com.gomotorscompany.gomotors.mainContent.view.mainContentViewImpl;
import com.gomotorscompany.gomotors.miscompras.adapter.adapterPedidos;
import com.gomotorscompany.gomotors.miscompras.adapter.adapterRequestPedido;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;
import com.gomotorscompany.gomotors.miscompras.model.set.Complemento_startOrder;
import com.gomotorscompany.gomotors.miscompras.model.set.Paquete_startOrder;
import com.gomotorscompany.gomotors.miscompras.model.set.Producto_startOrder;
import com.gomotorscompany.gomotors.miscompras.model.set.requeststartOrder;
import com.gomotorscompany.gomotors.miscompras.presenter.presenterStartOrderImpl;
import com.gomotorscompany.gomotors.miscompras.presenter.presenterStartOrderInterface;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class miscompras extends Fragment implements View.OnClickListener,miscomprasView, OnMapReadyCallback {
    public static final String TAG = miscompras.class.getSimpleName();

    private ProgressDialog progressDialog;
    private GoogleMap mMap;
    private MapView mView;
    private Marker mainIconMarker;
    private ordenarViewImpl orders;
    private mainContentViewImpl mainActivity;
    private List<Complementoold> listacomplementos;
    private ConstraintLayout carritolayout;
    private RecyclerView rv;
    private adapterRequestPedido adapter;
    private adapterPedidos adapter2;
    private Switch swapcardcash;
    private ImageView swapcardscash;
    private Button pagarbutton;

    private Boolean iscard=false;
    private String direccionentrega,categoria,clave,nombresucursal,idsucursal;
    private Double latitude,longitude;

    private presenterStartOrderInterface presenter;

    private requeststartOrder startOrder;

    public List<requeststartOrder> orderslist;

    public String costofinal,descripcion,nombreP;
    private CardView enrestaurante,carrito;
    private ConstraintLayout ordenesCliente;
    private TextView domiciliotxt,ordenes;

    private List<datagetOrders> dataorders;
    private PieChart pieChart2;
    private RecyclerView rv2;
    private ImageView chart;
    private ConstraintLayout constrainpiechr;
    private int anterior,posterior;
    private Boolean iraReparto=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.compras, container, false);
        mainActivity= (mainContentViewImpl) this.getActivity();
        initView(view);
        mView.onCreate(savedInstanceState);
        if (mView != null) {
            mView.getMapAsync(this);
        }
        return view;
    }

    private void initView(View view) {//descripcionCarrito ,porductos ,listacomplementos
        rv2=view.findViewById(R.id.rvOrdenesl);
        chart=view.findViewById(R.id.chartordenes);
        chart.setOnClickListener(this);
        pieChart2=view.findViewById(R.id.pieChart2);
        constrainpiechr= view.findViewById(R.id. constrainpiechrordenes);
        constrainpiechr.setVisibility(View.GONE);

        mView = view.findViewById(R.id.mapspagar);
        carritolayout=view.findViewById(R.id.carritolayout);
        enrestaurante=view.findViewById(R.id.enrestaurante);
        carrito=view.findViewById(R.id.carrito);
        domiciliotxt=view.findViewById(R.id.domiciliotxt);
        ordenes=view.findViewById(R.id.ordenes);
        enrestaurante.setOnClickListener(this);
        carrito.setOnClickListener(this);
        ordenesCliente=view.findViewById(R.id.ordenesCliente);
        if(orderslist==null)
        {
            orderslist=new ArrayList<>();
        }
        swapcardcash=view.findViewById(R.id.switch1);
        swapcardscash=view.findViewById(R.id.swapcardscash);
        pagarbutton=view.findViewById(R.id.pagarbutton);
        pagarbutton.setOnClickListener(this);
        rv=view.findViewById(R.id.rvCarrito);
        datafrombundle();

        swapcardcash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
              //  Toast.makeText(getContext(), ""+b, Toast.LENGTH_SHORT).show();
                iscard=b;
                if(b==false)
                {
                    swapcardscash.setImageResource(R.drawable.cahsicon);
                }else{
                    swapcardscash.setImageResource(R.drawable.cardsicon);
                }
            }
        });
        progressDialog=new ProgressDialog(getActivity());
        presenter=new presenterStartOrderImpl(this,getContext());
        presenter.requestlasOrders();
    }
    private  void datafrombundle()
    {
        Bundle bndlcompra;
        bndlcompra = getActivity().getIntent().getExtras();
        if(bndlcompra!=null) {
            carritolayout.setVisibility(View.VISIBLE);
            idsucursal=bndlcompra.getString("idsucursal");
            nombresucursal=bndlcompra.getString("nombresucursal");
            costofinal=bndlcompra.getString("costofinal");
            descripcion=bndlcompra.getString("descripcion");
            nombreP=bndlcompra.getString("nombreP");
            direccionentrega=bndlcompra.getString("carritoDireccionentrega");
            latitude= bndlcompra.getDouble("carritolatitude");
            longitude= bndlcompra.getDouble("carritolongitud");
            categoria=bndlcompra.getString("carritocategoria");
            clave=bndlcompra.getString("porductos" );
            listacomplementos = (List<Complementoold>) bndlcompra.getSerializable("datalistacomplementos");

            //bndlcompra.getString("listacomplementos")
//            Log.e("listacomplementos4","carrito "+listacomplementos.size());
            buildOrder();
        }else
        {
            carritolayout.setVisibility(View.GONE);
            ordenesCliente.setVisibility(View.GONE);

        }
    }

    private  void buildOrder()
    {
        /**pago con efectovo*/
       // Toast.makeText(getContext(), "Pago en efectivo", Toast.LENGTH_SHORT).show();
        List<Paquete_startOrder> paquetes = new ArrayList<>();
        List<Producto_startOrder> productos = new ArrayList<>();
        List<Complemento_startOrder> listcomp=new ArrayList<>();

        listcomp.clear();
        if(categoria.equals("producto"))
        {
            Producto_startOrder produto=new Producto_startOrder(clave,1,true);
            productos.add(produto);
        }else if(categoria.equals("paquete"))
        {
            Paquete_startOrder paquete=new Paquete_startOrder(Integer.valueOf(clave),1,true);
            paquetes.add(paquete);
        }
        if(!listacomplementos.isEmpty()&&listacomplementos!=null)
        {

            for (int i=0;i<listacomplementos.size();i++)
            {
                Log.e("countitems13","sku "+listacomplementos.get(i).getSkuComplemento()+"   cantidad"+listacomplementos.get(i).getCantidad());
                Complemento_startOrder complemento=new Complemento_startOrder( listacomplementos.get(i).getSkuComplemento(),listacomplementos.get(i).getCantidad(),true);
                listcomp.add(complemento);
            }
        }


        Log.e("countitems13","carrito "+"dir: "+direccionentrega+" lat: "+latitude
                +" longr: "+longitude+" nomSuc: "+nombresucursal+" paq: "+paquetes.size()+" products: "+productos.size()+" compl: "+listcomp.size());
       startOrder=new requeststartOrder(0,"",direccionentrega,latitude,longitude,idsucursal,paquetes,productos,listcomp);
        orderslist.add(startOrder);
        fillAdapter(orderslist);

    }
    public void fillAdapter(List<requeststartOrder> startOrder)
    {
        adapter=new adapterRequestPedido(this,startOrder,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

 private void filldata(List<datagetOrders> dataorders) {
        adapter2=new adapterPedidos(dataorders,getContext());
         LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,true);
                rv2.setLayoutManager(layoutManager);
                rv2.setAdapter(adapter2);
                if(dataorders.size()!=0||dataorders!=null) {
                    rv2.scrollToPosition(dataorders.size() - 1);
                }


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
    public void setOreders(List<datagetOrders> data) {

       this.dataorders=data;
        if(dataorders!=null)
        {
            posterior=dataorders.size();
            if(anterior!=0) {
//                Log.e("sizeOrdenes", "numero de ordenes: " + dataorders.size());
//                Log.e("sizeOrdenes", "anterior: " + anterior + "  posterior: " + posterior);
                if(anterior!=posterior)
                {
                    //Log.e("sizeOrdenes", "orden del pedido " + dataorders.get(posterior-1).getOrdenNum());

                    //mandar a cocina
                    if(iraReparto==false)
                    {
                        presenter.mandarACocina(dataorders.get(posterior-1).getOrdenNum());
                        iraReparto=true;
                    }



                }
            }
            filldata(dataorders);
            fillChart(dataorders);
        }
    }

    private void gotoPedidoFragment() {
        mainActivity.gotoPedido();
    }

    //
    @Override
    public void showprogresDialog() {
        progressDialog.setMessage("Solicitando orden");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgresDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
        mView.onStart();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
                uiSettingsMap(mMap);
                        try {
                            // Customise the styling of the base map using a JSON object defined
                            // in a raw resource file.
                            boolean success = mMap.setMapStyle(
                                    MapStyleOptions.loadRawResourceStyle(
                                            this.getContext(), R.raw.style_json));

                            if (!success) {
                                Log.e("MapsActivityRaw", "Style parsing failed.");
                            }
                        } catch (Resources.NotFoundException e) {
                            Log.e("MapsActivityRaw", "Can't find style.", e);
                        }
            if(latitude!=null&&longitude!=null) {
                location();
            }
    }

    private void location() {
        mMap.clear();
        LatLng location = new LatLng(latitude, longitude);
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bluedot);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 60, 60, false);
        mainIconMarker=  mMap.addMarker(new MarkerOptions().position(location).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        //mMap.addMarker(new MarkerOptions().position(torreon).title("Bienvenido a quesipizzas"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,13.5f));
    //    mMap.animateCamera(CameraUpdateFactory.scrollBy(0.0f, 1250));


        if(orders.listasucursales!=null&&!orders.listasucursales.isEmpty())
        {
            Log.e("minimapa",""+orders.listasucursales.get(0).getIcon());
            LatLng location1 = new LatLng(orders.listasucursales.get(0).getLatitude(), orders.listasucursales.get(0).getLongitud());

            if(orders.listasucursales.get(0).getIcon()!=null) {
                Glide.with(this)
                        .asBitmap()
                        .load(orders.listasucursales.get(0).getIcon())
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {

                                Bitmap smallMarker1 = Bitmap.createScaledBitmap(resource, 80, 80, false);
                                mMap.addMarker(new MarkerOptions().position(location1).icon(BitmapDescriptorFactory.fromBitmap(smallMarker1)));

                                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                                LatLng point = new LatLng(latitude, longitude);
                                LatLng point1 = new LatLng(orders.listasucursales.get(0).getLatitude(), orders.listasucursales.get(0).getLongitud());
                                builder.include(point);
                                builder.include(point1);
                                LatLngBounds bounds = builder.build();
                                if (bounds != null) {
                                    mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 80));
                                }

                            }
                        });
            }else
            {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,13.5f));
               // Toast.makeText(getContext(), "no se encontro sucursal", Toast.LENGTH_SHORT).show();
            }


        }


        else
        {
            Log.e("minimapa","vacio");
        }

    }

    private void uiSettingsMap(GoogleMap mMap) {
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setScrollGesturesEnabledDuringRotateOrZoom(false);
        uiSettings.setZoomControlsEnabled(false);
        uiSettings.setScrollGesturesEnabled(false);
        uiSettings.setScrollGesturesEnabledDuringRotateOrZoom(false);
        uiSettings.setZoomGesturesEnabled(false);
        uiSettings.setAllGesturesEnabled(false);
      //  mMap.setPadding (0, 1400, 0, 80);
    }
    public void eliminarCarrito()
    {

        getActivity().getIntent().removeExtra("idsucursal");
        getActivity().getIntent().removeExtra("nombresucursal");
        getActivity().getIntent().removeExtra("costofinal");
        getActivity().getIntent().removeExtra("descripcion");
        getActivity().getIntent().removeExtra("nombreP");
        getActivity().getIntent().removeExtra("carritoDireccionentrega");
        getActivity().getIntent().removeExtra("carritolatitude");
        getActivity().getIntent().removeExtra("carritolongitud");
        getActivity().getIntent().removeExtra("carritocategoria");
        getActivity().getIntent().removeExtra("porductos");
        getActivity().getIntent().removeExtra("datalistacomplementos");
        mainActivity.goTomiscompras();

    }

    @Override
    public void setSuccesOrderCreated(String s) {
        Log.e("sizeOrdenes",""+s);
        if(s.equals("105"))
        {
            eliminarCarrito();
            presenter.requestlasOrders();
            pagarbutton.setEnabled(true);
            presenter.hideprogresdialog();
//            Handler promohandler = new Handler();
//            promohandler.postDelayed(new Runnable() {
//                public void run() {
//
//
//                }
//            }, 2000);

        }
    }
    @Override
    public void didnotsucced() {
        pagarbutton.setEnabled(true);
       // Toast.makeText(getContext(), "no exitoso", Toast.LENGTH_SHORT).show();
        pagarbutton.performClick();
    }

    @Override
    public void succesGotoReparto() {
        Log.e("sizeOrdenes", "Ir a reparto exitoso");
        gotoPedidoFragment();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pagarbutton:
                if(iscard==false)
                {


                    if(dataorders!=null) {
                        anterior = dataorders.size();

                        Log.e("sizeOrdenes", "numero de ordenes: " + anterior);
                    }
                        presenter.requestStratOrder(startOrder);
                    pagarbutton.setEnabled(false);

                }else
                {
                    /**pago con Tarjeta*/
                    // Toast.makeText(getContext(), "Pago con tarjeta hash no diponible", Toast.LENGTH_SHORT).show();
                }

                break;
            case  R.id.carrito:
                ordenesCliente.setVisibility(View.GONE);
                carritolayout.setVisibility(View.VISIBLE);

                 domiciliotxt.setTextColor(getResources().getColor(R.color.white));
                 ordenes.setTextColor(getResources().getColor(R.color.black));
                enrestaurante.setCardBackgroundColor(getResources().getColor(R.color.alfa));
                enrestaurante.setCardElevation(0);
                carrito.setCardBackgroundColor(getResources().getColor(R.color.colorRed));
                Bundle bndlcompra;
                bndlcompra = getActivity().getIntent().getExtras();
                if(startOrder!=null) {
                    Log.e("minimapa1","bundle vacio"+startOrder);
                }else
                {
                    carritolayout.setVisibility(View.GONE);
                }
                break;
            case  R.id.enrestaurante:
//                Log.e("sizeOrdenes","numero de ordenes: "+dataorders.size());

                ordenesCliente.setVisibility(View.VISIBLE);
                carritolayout.setVisibility(View.GONE);

                domiciliotxt.setTextColor(getResources().getColor(R.color.black));
                ordenes.setTextColor(getResources().getColor(R.color.white));
                carrito.setCardBackgroundColor( getResources().getColor(R.color.alfa));
                //adomicilio.setCardElevation(0);
                enrestaurante.setCardBackgroundColor(getResources().getColor(R.color.colorRed));
                Bundle bndlcompra1;
                bndlcompra1 = getActivity().getIntent().getExtras();
                if(bndlcompra1!=null) {
                    Log.e("minimapa1","bundle vacio"+startOrder);
                }else
                {
                    Log.e("minimapa1","bundle con datos"+startOrder);
                }
                break;
            case R.id.chartordenes:
                if(constrainpiechr.getVisibility()==View.GONE)
                {
                    constrainpiechr.setVisibility(View.VISIBLE);
                }else
                {
                    constrainpiechr.setVisibility(View.GONE);
                }
                break;


        }
    }
}
/*
{
        "id_order": 0,
        "Token": "string",
        "direccion_entrega": "string",        x
        "latitud": 0,                         x
        "longitud": 0,                        x
        "sucursal": "string",                 x
        "paquetes": [
        {
        "id_paquetes": 0,
        "cantidad": 0,
        "status": true
        }
        ],
        "productos": [
        {
        "sku_producto": "string",
        "cantidad": 0,
        "status": true
        }
        ],
        "complementos": [
        {
        "sku_complemento": "string",
        "cantidad": 0,
        "status": true
        }
        ]
        }
        */