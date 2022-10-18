package com.gomotorscompany.gomotors.Ordenar.view;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gomotorscompany.gomotors.Ordenar.adapter.PhotosAdapter;
import com.gomotorscompany.gomotors.Ordenar.adapter.adapterClasificaciones;
import com.gomotorscompany.gomotors.Ordenar.adapter.adapterPaquetes;
import com.gomotorscompany.gomotors.Ordenar.adapter.companiesAdapter;
import com.gomotorscompany.gomotors.Ordenar.adapter.sucursalesAdapter;
import com.gomotorscompany.gomotors.Ordenar.model.Clasificacione;
import com.gomotorscompany.gomotors.Ordenar.model.Complementos;
import com.gomotorscompany.gomotors.Ordenar.model.ingredientesAlone.dataIngredients;
import com.gomotorscompany.gomotors.Ordenar.model.menuData;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.Sucursale;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.menuDatan;
import com.gomotorscompany.gomotors.Ordenar.model.sucursales;
import com.gomotorscompany.gomotors.Ordenar.presenter.mainpresenterView;
import com.gomotorscompany.gomotors.Ordenar.presenter.presenterViewImpl;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.mainContent.view.mainContentViewImpl;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.google.android.gms.common.api.Status;
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
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class orderViewImpln extends Fragment implements OnMapReadyCallback, View.OnClickListener, LocationListener,orderView, GoogleMap.OnInfoWindowClickListener,GoogleMap.OnMarkerClickListener ,GoogleMap.InfoWindowAdapter{ //GoogleMap.OnInfoWindowClickListener,GoogleMap.OnMarkerClickListener ,GoogleMap.InfoWindowAdapter
    public static final String TAG = ordenarViewImpl.class.getSimpleName();
    private GoogleMap mMap;
    private MapView mView;
    private double mylat,mylong;
    private double vehicleLat;
    private double vehicleLng;

    private BottomSheetBehavior bottomSheetBehavior,bottomSheetBehavior2,bottomSheetBehavior3,bottomSheetBehavior4,bottomSheetBehavior5;
    private ConstraintLayout linearLayoutBSheet,linearLayoutBSheet2,linearLayoutBSheet3,linearLayoutBSheet4,linearLayoutBSheet5,constraintcollapse,constraibexpanden,constrainScrolled,constraindireccion,constrainsearchGoogle;
    private boolean isClickedDrawTrip,swapstate=false;

    private CardView adomicilio,ensucursal;
    private TextView adomiciliotxt,ensucursaltxt;
    private boolean issucursal=true;

    private LocationManager locationManager;
    private double latitude,longitude;
    private Marker mainIconMarker,entregaMarker,flagdelivery;
    private LatLng myloc;

    private TextView txtUbicacion,txtRestaurante,textViewLocation;

    private mainpresenterView presenter;
    private ProgressDialog progressDialog;
    private List<menuData> fulldata;
    private List<menuDatan> fulldatan;

    private RecyclerView rv,rvSuc,rvMenu,rvPaquetes;
    private companiesAdapter adapter;
    private sucursalesAdapter adapterSuc;
    private adapterClasificaciones adapterMenu;
    private adapterPaquetes adapterPaq;
    private  List<LatLng> latitudlongitudSuc=new ArrayList<>();
    private ImageView selecionadireccion,markermanuallocation,closesearchlocation,closelocalmenu,backtoCommerce;
    private  boolean isdireccionpush=false;
    private static final int AUTOCOMPLETE_REQUEST_CODE = 23487;

    private List<sucursales> mysucursales;
    private mainContentViewImpl mainActivity;
    private ViewPager pager;
    private PagerAdapter pAdapter;
    private int companyIndex;
    private LinearLayout dotslayout;
    private int customdots_position;
    private ImageView paquetes,armatupizza;
    private  List<Clasificacione> clasif;

    private Timer timer;

    private int idsuc;
    private String sucursalnam;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_ordenes, container, false);
        mainActivity= (mainContentViewImpl) this.getActivity();
        locationManager= (LocationManager) getContext().getSystemService(LOCATION_SERVICE);


        // LocationListener locationListenerGPS = null;
        if (ActivityCompat.checkSelfPermission(this.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((this.getActivity()), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,200,5,this);


        initView(view);
        mView.onCreate(savedInstanceState);
        if (mView != null) {
            mView.getMapAsync(this);
        }

        return view;
    }

    private void initView(View view) {
        mView = view.findViewById(R.id.map_Ordenes);
        linearLayoutBSheet = view.findViewById(R.id.bottomSheet);
        linearLayoutBSheet2= view.findViewById(R.id.bottomSheetmenu);
        linearLayoutBSheet3= view.findViewById(R.id.bottomsheet_productss);
        linearLayoutBSheet4= view.findViewById(R.id.bottomsheet_promociones);
        linearLayoutBSheet5= view.findViewById(R.id.bottomsheet_armatupizza);

        paquetes=view.findViewById(R.id.paquetes);
        armatupizza=view.findViewById(R.id. armatupizza);
        paquetes.setOnClickListener(this);
        armatupizza.setOnClickListener(this);

        adomicilio= view.findViewById(R.id.adomicilio);
        adomicilio.setOnClickListener(this);
        ensucursal= view.findViewById(R.id.enrestaurante);
        ensucursal.setOnClickListener(this);
        txtUbicacion=view.findViewById(R.id.domiciliotxt);
        txtRestaurante=view.findViewById(R.id.enrestaurantetxt);
        textViewLocation=view.findViewById(R.id.textViewLocation);
        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String direccion     = preferences.getString(GeneralConstantsV2.DIRECCION_USER, null);
        if(direccion!=null)
        {textViewLocation.setText(direccion);}

        closesearchlocation=view.findViewById(R.id.closesearchlocation);
        closesearchlocation.setOnClickListener(this);

        closelocalmenu =view.findViewById(R.id.closelocalmenu);
        closelocalmenu.setOnClickListener(this);
        backtoCommerce=view.findViewById(R.id.backtoCommerce);
        backtoCommerce.setOnClickListener(this);
        rv=view.findViewById(R.id.rvComercios);
        rvSuc=view.findViewById(R.id.rvSucursales);
        rvMenu=view.findViewById(R.id.rvCarta);
        rvPaquetes=view.findViewById(R.id.rvpromociones);
        rvSuc.setVisibility(View.GONE);

        markermanuallocation=view.findViewById(R.id.markermanuallocation);
        markermanuallocation.setOnClickListener(this);

        //  rvD=view.findViewById(R.id.rvdireccionesGoogle);
        constraintcollapse=view.findViewById(R.id.constrainCollapse);
        constraibexpanden=view.findViewById(R.id.constrainEXPANDED);
        constrainScrolled=view.findViewById(R.id.constrainScrolled);
        constraindireccion=view.findViewById(R.id.constraindireccion);
        constraindireccion.setOnClickListener(this);
        constrainsearchGoogle=view.findViewById(R.id.constrainsearchGoogle);
        constrainsearchGoogle.setVisibility(View.GONE);
        initMainView();

        progressDialog = new ProgressDialog(getActivity());
        presenter= new presenterViewImpl(this,getContext());
        presenter.requestLocales();

        pager = (ViewPager) view.findViewById(R.id.viewP);



        dotslayout = view.findViewById(R.id.dots_layout);



//        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
//
//        Gson gson = new Gson();
//        String json = preferences.getString("MyObject", "");
//        MyObject obj = gson.fromJson(json, MyObject.class);

//        SharedPreferences sharedPreferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
//        if (sharedPreferences.contains("MyObject")) {
//            final Gson gson = new Gson();
//           gson.fromJson(sharedPreferences.getString("MyObject", ""), menuData.class );
//
//        }
    }

    private void initMainView() {
        bottomSheetSettings();
        bottomSheetSettings2();
        bottomSheetSettings3();
        bottomSheetSettings4();
        bottomSheetSettings5();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior3.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
    private void bottomSheetSettings5(){
        bottomSheetBehavior5= BottomSheetBehavior.from(linearLayoutBSheet5);
        bottomSheetBehavior5.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("bottonsheetmenu","STATE_DRAGGING");
                        linearLayoutBSheet5.setBackgroundResource(R.drawable.square_bottom_shet);
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("bottonsheetmenu","STATE_COLLAPSED");
                        linearLayoutBSheet5.setBackgroundResource(R.drawable.square_bottom_shet);
                        linearLayoutBSheet5.setVisibility(View.GONE);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("bottonsheetmenu","STATE_EXPANDED");
                        linearLayoutBSheet5.setBackgroundResource(R.drawable.round_bottom_shet);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
    private void bottomSheetSettings4(){
        bottomSheetBehavior4= BottomSheetBehavior.from(linearLayoutBSheet4);
        bottomSheetBehavior4.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("bottonsheetmenu","STATE_DRAGGING");
                        linearLayoutBSheet4.setBackgroundResource(R.drawable.square_bottom_shet);
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("bottonsheetmenu","STATE_COLLAPSED");
                        linearLayoutBSheet4.setBackgroundResource(R.drawable.square_bottom_shet);
                        linearLayoutBSheet4.setVisibility(View.GONE);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("bottonsheetmenu","STATE_EXPANDED");
                        linearLayoutBSheet4.setBackgroundResource(R.drawable.round_bottom_shet);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
    private void bottomSheetSettings3(){
        bottomSheetBehavior3= BottomSheetBehavior.from(linearLayoutBSheet3);
        bottomSheetBehavior3.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("bottonsheetmenu","STATE_DRAGGING");
                        linearLayoutBSheet3.setBackgroundResource(R.drawable.square_bottom_shet);
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("bottonsheetmenu","STATE_COLLAPSED");
                        linearLayoutBSheet3.setBackgroundResource(R.drawable.square_bottom_shet);
                        timer.cancel();
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("bottonsheetmenu","STATE_EXPANDED");
                        linearLayoutBSheet3.setBackgroundResource(R.drawable.round_bottom_shet);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
    private void bottomSheetSettings2(){
        bottomSheetBehavior2= BottomSheetBehavior.from(linearLayoutBSheet2);
        bottomSheetBehavior2.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        linearLayoutBSheet3.setVisibility(View.GONE);
//                        Log.e("bottonsheetmenu","STATE_DRAGGING");
//                        bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                        Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            public void run() {
//                                linearLayoutBSheet.setVisibility(View.VISIBLE);
//                                linearLayoutBSheet2.setVisibility(View.GONE);
//                            }
//                        }, 500);

                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("bottonsheetmenu","STATE_COLLAPSED");
                        linearLayoutBSheet3.setVisibility(View.GONE);
                        linearLayoutBSheet.setVisibility(View.VISIBLE);
                        linearLayoutBSheet2.setVisibility(View.GONE);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("bottonsheetmenu","STATE_EXPANDED");
                        if(linearLayoutBSheet3.getVisibility()==View.GONE) {
                            linearLayoutBSheet3.setVisibility(View.VISIBLE);
                        }
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
    private void bottomSheetSettings() {
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayoutBSheet);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        constrainScrolled.setVisibility(View.VISIBLE);
                        constraintcollapse.setVisibility(View.GONE);
                        constraibexpanden.setVisibility(View.GONE);
                        constrainsearchGoogle.setVisibility(View.GONE);
                        //   Toast.makeText(getContext(), "drag", Toast.LENGTH_SHORT).show();
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        rv.setVisibility(View.VISIBLE);
                        rvSuc.setVisibility(View.GONE);

                        constrainScrolled.setVisibility(View.GONE);
                        constraintcollapse.setVisibility(View.GONE);
                        constraibexpanden.setVisibility(View.VISIBLE);
                        constrainsearchGoogle.setVisibility(View.GONE);
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mainIconMarker.getPosition().latitude, mainIconMarker.getPosition().longitude), 16.5f));
                        //Scrollmap();
                        isdireccionpush=false;
                        //Toast.makeText(getContext(), "colapsada", Toast.LENGTH_SHORT).show();
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:

                        if(isdireccionpush==true) {
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                            constrainScrolled.setVisibility(View.GONE);
                            constraintcollapse.setVisibility(View.GONE);
                            constraibexpanden.setVisibility(View.GONE);
                            constrainsearchGoogle.setVisibility(View.VISIBLE);

                        }else {
                            constrainScrolled.setVisibility(View.GONE);
                            constraintcollapse.setVisibility(View.VISIBLE);
                            constraibexpanden.setVisibility(View.GONE);
                            constrainsearchGoogle.setVisibility(View.GONE);
                        }



                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                mMap.animateCamera(CameraUpdateFactory.scrollBy(0.0f, 1050));
                            }
                        }, 500);   //5 seconds
                        // Toast.makeText(getContext(), "expandida", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
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
        mMap.setInfoWindowAdapter(this);
        aDomicilio();
    }

    private void uiSettingsMap(GoogleMap mMap) {
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        mMap.setPadding (0, 1400, 0, 80);
    }

    @Override
    public void onStart() {
        super.onStart();
        mView.onStart();
    }



    private void aDomicilio() {
        mMap.clear();
        LatLng torreon = new LatLng(25.5383667, -103.446846);
        choselocalremoteLocation();
        // checkmylocation(torreon);

    }
    private void enSucursal() {
        mMap.clear();
        LatLng torreon = new LatLng(25.5383667, -103.446846);
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bluedot);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 60, 60, false);
        mainIconMarker=  mMap.addMarker(new MarkerOptions().position(torreon).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        //mMap.addMarker(new MarkerOptions().position(torreon).title("Bienvenido a quesipizzas"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(torreon,13.5f));

        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        mMap.animateCamera(CameraUpdateFactory.zoomTo(13), 2000, null);



    }
    private void Scrollmap()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                mMap.animateCamera(CameraUpdateFactory.scrollBy(0.0f, 1250));
            }
        }, 500);   //5 seconds

    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        this.latitude=location.getLatitude();
        this.longitude=location.getLongitude();
        if(mainIconMarker!=null) {
            mainIconMarker.setPosition(new LatLng(latitude, longitude));
            myloc = new LatLng(latitude, longitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myloc, 16.5f));
            Log.e("findlocation", " update for      Lat:" + latitude + "   Long: " + longitude);
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
    private void choselocalremoteLocation()
    {
        myloc= new LatLng(latitude, longitude);
        LatLng torreon = new LatLng(25.5383667, -103.446846);
        double sphericald= SphericalUtil.computeDistanceBetween(torreon,myloc);
        double realdistance=sphericald/1000;
        //      Toast.makeText(getContext(), "D :" +realdistance, Toast.LENGTH_SHORT).show();
        Log.e("findlocation", "  Lat:" + latitude + "   Long: " + longitude+"  rD "+realdistance);
        if(realdistance>900) {
            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bluedot);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, 60, 60, false);
            mainIconMarker= mMap.addMarker(new MarkerOptions().position(torreon).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
            //mMap.addMarker(new MarkerOptions().position(torreon).title("Bienvenido a quesipizzas"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(torreon,16.5f));

            mMap.animateCamera(CameraUpdateFactory.zoomIn());

// Zoom out to zoom level 10, animating with a duration of 2 seconds.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);
//                            CameraPosition cameraPosition = new CameraPosition.Builder()
//                                    .target(torreon )      // Sets the center of the map to Mountain View
//                                    .zoom(19)                   // Sets the zoom
//                                    .bearing(45)                // Sets the orientation of the camera to east
//                                    .tilt(70)                   // Sets the tilt of the camera to 30 degrees
//                                    .build();                   // Creates a CameraPosition from the builder
//                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


            //    Toast.makeText(getContext(), "Lo sentimos te encuentras fuera de nuestra zona de distribucion" +realdistance, Toast.LENGTH_SHORT).show();
        }else {

            // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16.5f));
            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bluedot);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, 60, 60, false);
            LatLng point = new LatLng(latitude, longitude);
            mainIconMarker=  mMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
            //startMaker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).snippet(String.valueOf(calles.get(0))));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16.5f));
        }
    }



    @Override
    public void showProgressDialog() {
        progressDialog.setMessage("Cargando datos ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void igredientesIndividuales(List<dataIngredients> data) {

    }


    @Override
    public void setCompaniastoView(List<menuDatan> data, List<Sucursale> sucursales) {
        this.fulldatan= data;
     /*   if(data!=null) {
            List<sucursales> sucursalespos=new ArrayList<>();

            latitudlongitudSuc.clear();
            for (int i = 0; i < data.size(); i++) {
                Log.e("compañias", "dataView" + fulldata.get(i).getIdEmpresa() + "  " + fulldata.get(i).getEmpresaNombre() + "  " + fulldata.get(i).getIdEmpresa()+"    size: "+ data.size()+"   size sucursales:  "+fulldata.get(i).getSucursales().size() );
                fulldata.get(i).getSucursales().size();
                sucursalespos.addAll(fulldata.get(i).getSucursales());
                if(fulldata.get(i).getSucursales().size()>0)
                {
                    for(int k=0;k<sucursalespos.size();k++)
                    {
                        if(!sucursalespos.get(k).getSucursalLat().isEmpty()&&!sucursalespos.get(k).getSucursalLong().isEmpty()&&!sucursalespos.get(k).getSucursalLat().equals("0.0")&&!sucursalespos.get(k).getSucursalLong().equals("0.0")&&!sucursalespos.get(k).getSucursalLat().equals("1.0")&&!sucursalespos.get(k).getSucursalLong().equals("1.0")) {
                            Double dx = Double.parseDouble(sucursalespos.get(k).getSucursalLat());
                            Double dy = Double.parseDouble(sucursalespos.get(k).getSucursalLong());
                            LatLng newmarker = new LatLng(dx, dy);

                            @SuppressLint("UseCompatLoadingForDrawables") BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_restaurante);
                            Bitmap b = bitmapdraw.getBitmap();
                            Bitmap smallMarker = Bitmap.createScaledBitmap(b, 80, 80, false);

                            mMap.addMarker(new MarkerOptions().position(newmarker).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                            //mMap.addMarker(new MarkerOptions().position(newmarker));
                            latitudlongitudSuc.add(newmarker);
                            Log.e("compañias","coordenadas "+newmarker);
                        }
                    }
                }
            }
            Log.e("compañias",""+latitudlongitudSuc.size());
            Scrollmap();

        }
        fillRv();
*/
    }

    @Override
    public void setCategoriasMenu(List<String> categoriasDisponibles) {

    }

    private void addComercios()
    {
        if(!latitudlongitudSuc.isEmpty())
        {
            for(int i=0; i<latitudlongitudSuc.size();i++)
            {
                @SuppressLint("UseCompatLoadingForDrawables") BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_restaurante);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 80, 80, false);

                mMap.addMarker(new MarkerOptions().position(latitudlongitudSuc.get(i)).snippet("adasdasdsadasdasd").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

                // mainIconMarker=  mMap.addMarker(new MarkerOptions().position(point)
            }
        }
    }
    private void fillRv() {
       // adapter=new companiesAdapter(this,fulldata,getContext());  /**  productos por sucursal */
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    private void fillRvSuc(List<sucursales> mysuc)
    {
     //   adapterSuc= new sucursalesAdapter(this,mysuc,getContext());  /**  productos por sucursal */
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rvSuc.setLayoutManager(layoutManager);
        rvSuc.setAdapter(adapterSuc);
    }


    private void fillClasificaciones(List<Clasificacione> clasificaciones)
    {
     //   adapterMenu =new adapterClasificaciones(this,clasificaciones,getContext());/**  productos por sucursal */
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rvMenu.setLayoutManager(layoutManager);
        rvMenu.setAdapter(adapterMenu);
    }
    private  void fillPaquetes(List<Clasificacione> clasificaciones)
    {
      //  adapterPaq=new adapterPaquetes(this,clasificaciones,getContext());/**productos por paquete*/
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rvPaquetes.setLayoutManager(layoutManager);
        rvPaquetes.setAdapter(adapterPaq);
    }
    private void fillpageradapter()
    {
        pAdapter = new PhotosAdapter(getChildFragmentManager(), getContext());//(getChildFragmentManager(), fulldata.get(companyIndex).getBanner(),getContext());
        pager.setAdapter(pAdapter);
        timer =new Timer();
        timer.schedule(new orderViewImpln.sliderTimer(),2000,4000);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("bannerPos",""+position);
                dotsposition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
    class   sliderTimer extends TimerTask {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(pager.getCurrentItem()<fulldata.get(companyIndex).getBanner().size()-1)
                    {
                        pager.setCurrentItem(pager.getCurrentItem()+1);
                    }else
                    {
                        pager.setCurrentItem(0);
                    }
                }
            });
        }
    }
    private void dotsposition(int currentposition)
    {
        if(dotslayout.getChildCount()>0)
        {
            dotslayout.removeAllViews();
        }
        ImageView dots[]=new ImageView[fulldata.get(companyIndex).getBanner().size()];
        for (int i=0; i<fulldata.get(companyIndex).getBanner().size();i++){
            dots[i]=new ImageView(getContext());
            if(i==currentposition)
            {
                dots[i].setImageDrawable(getContext().getDrawable(R.drawable.selected_dot));
            }else
            {
                dots[i].setImageDrawable(getContext().getDrawable(R.drawable.default_dot));
            }
            LinearLayout.LayoutParams linearLayout=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayout.setMargins(4,0,4,0);
            dotslayout.addView(dots[i],linearLayout);
        }
    }
    public void hideSheetlavel(String namesucursal,int idsucursal,List<Clasificacione> clasificaciones)
    {
        this.idsuc=idsucursal;
        this.sucursalnam=namesucursal;
        this.clasif=clasificaciones;
        linearLayoutBSheet.setVisibility(View.GONE);
        linearLayoutBSheet2.setVisibility(View.VISIBLE);
        linearLayoutBSheet3.setVisibility(View.GONE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetBehavior3.setState(BottomSheetBehavior.STATE_COLLAPSED);
                fillClasificaciones(clasificaciones);
                linearLayoutBSheet3.setVisibility(View.VISIBLE);
            }
        }, 500);



        // constrainScrolled.setVisibility(View.GONE);
        //                        constraintcollapse.setVisibility(View.GONE);
        //                        constraibexpanden.setVisibility(View.VISIBLE);
        //                        constrainsearchGoogle.setVisibility(View.GONE);
        //                        bottomSheetBehavior.setDraggable(true);
        //                        isdireccionpush=false;
        //bottomSheet //gone
        //bottom_sheet_menu //visible
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getInfoWindow(Marker marker) {
        return prepareInfoView( marker);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getInfoContents(Marker marker) {
        return prepareInfoView( marker);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onInfoWindowClick(Marker marker) {
        prepareInfoView( marker);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(getContext(), "markerclic", Toast.LENGTH_SHORT).show();
        return false;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private View prepareInfoView(Marker marker) {
        //prepare InfoView programmatically
        LatLng mypointlatlong = new LatLng(latitude, longitude);
        // LatLng torreon = new LatLng(25.5393929, -103.4532015);
        if(marker.getPosition().latitude!=latitude&&marker.getPosition().longitude!=longitude&&marker.getPosition().latitude!=25.5393929&&marker.getPosition().longitude!=-103.4532015&&marker.getPosition().latitude!=mainIconMarker.getPosition().latitude&&marker.getPosition().longitude!=mainIconMarker.getPosition().longitude) {
            Log.e("infoviewmylocation", "markerlat" + marker.getPosition().latitude + "  markerlong" + marker.getPosition().longitude + "  reallat: " + mylat + "  realong: " + mylong);
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.window_design, null);

            //region oldlayout
//            LinearLayout infoView = new LinearLayout(getContext());
//            LinearLayout.LayoutParams infoViewParams = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            infoViewParams.leftMargin = 10;
//            infoViewParams.rightMargin = 10;
//            infoView.setOrientation(LinearLayout.HORIZONTAL);
//            //infoView.setBackground(getResources().getDrawable(R.drawable.ic_ubicacionl));
//            infoView.setLayoutParams(infoViewParams);
//
//       /* ImageView infoImageView = new ImageView(getContext());
//        //Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
//        Drawable drawable = getResources().getDrawable(android.R.drawable.ic_dialog_map);
//        infoImageView.setImageDrawable(drawable);
//        infoView.addView(infoImageView);*/
//
//            LinearLayout subInfoView = new LinearLayout(getContext());
//            LinearLayout.LayoutParams subInfoViewParams = new LinearLayout.LayoutParams(
//
//                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            subInfoView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
//            subInfoView.setOrientation(LinearLayout.VERTICAL);
//            subInfoView.setGravity(Gravity.CENTER_VERTICAL);
//            subInfoView.setLayoutParams(subInfoViewParams);
//
//            TextView nameVehicle = new TextView(getContext());
//            nameVehicle.setText(" Unidad: ");
//            TextView subInfoLat = new TextView(getContext());
//            subInfoLat.setText(" Lat: " + marker.getPosition().latitude + "  " + " Long: " + marker.getPosition().longitude + " ");
//            TextView subInfoLnt = new TextView(getContext());
//            subInfoLnt.setText("                                    ");
//            TextView subInfodate = new TextView(getContext());
//            subInfodate.setText(" Localización: " + marker.getSnippet() + " ");//positionsdatetime.get(0) );
//            subInfoView.addView(nameVehicle);
//            subInfoView.addView(subInfoLat);
//            subInfoView.addView(subInfodate);
//            subInfoView.addView(subInfoLnt);
//
//            infoView.addView(subInfoView);
            //endregion

            return v;
        }else
        {
            return null;
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
            } else if (resultCode == RESULT_CANCELED) {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void dataReciclermenu(List<sucursales> sucursales, int position)
    {
        this.mysucursales=sucursales;
        this.companyIndex=position;
        if(!mysucursales.isEmpty()&&mysucursales.size()>0&&mysucursales!=null)
        {

            for(int i=0;i<mysucursales.size();i++)
            {

                Log.e("adapterpositions"," "+mysucursales.get(i).getSucursalNombre()
                        +" "+mysucursales.get(i).getSucursalLat()+" "+mysucursales.get(i).getSucursalLong()
                        +" "+mysucursales.get(i).getSucursalNombre()+" "+mysucursales.get(i).getSucursalApertura()
                        +" "+mysucursales.get(i).getSucursalCierre());
            }
            rv.setVisibility(View.GONE);
            rvSuc.setVisibility(View.VISIBLE);
            backtoCommerce.setVisibility(View.VISIBLE);
            fillRvSuc(mysucursales);
            fillpageradapter();

        }
    }
    public void interactActivity(String Category, String clave, String nombre, String precio, String descripcion, List<Complementos> complementos, String imagen) {
        timer.cancel();
        if(textViewLocation.getText().equals( "Direccion de entrega"))
        {
            Toast.makeText(getContext(), "necesitas elegir una direccion de entrega", Toast.LENGTH_SHORT).show();
        }else
        {
            Log.e("countitems1",""+textViewLocation.getText()+"    "+idsuc+"  "+sucursalnam+"  "+Category+" "+mainIconMarker.getPosition().latitude + "   Long: " + mainIconMarker.getPosition().longitude);
       //     mainActivity.activityMenu(String.valueOf( textViewLocation.getText()),idsuc,sucursalnam,mainIconMarker.getPosition().latitude,mainIconMarker.getPosition().longitude,Category,clave,nombre,precio,descripcion,complementos,imagen);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paquetes:
                linearLayoutBSheet4.setVisibility(View.VISIBLE);
                Handler promohandler = new Handler();
                promohandler.postDelayed(new Runnable() {
                    public void run() {
                        fillPaquetes(clasif);
                        bottomSheetBehavior4.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }, 500);
                break;
            case R.id.armatupizza:
                linearLayoutBSheet5.setVisibility(View.VISIBLE);
                Handler armatupizzahandler = new Handler();
                armatupizzahandler.postDelayed(new Runnable() {
                    public void run() {
                        bottomSheetBehavior5.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }, 500);

                break;
            case R.id.backtoCommerce:
                rv.setVisibility(View.VISIBLE);
                rvSuc.setVisibility(View.GONE);
                backtoCommerce.setVisibility(View.GONE);
                break;
            case R.id.adomicilio:
                txtUbicacion.setTextColor(getResources().getColor(R.color.white));
                txtRestaurante.setTextColor(getResources().getColor(R.color.black));
                ensucursal.setCardBackgroundColor(getResources().getColor(R.color.alfa));
                ensucursal.setCardElevation(0);
                adomicilio.setCardBackgroundColor(getResources().getColor(R.color.colorRed));
                aDomicilio();
                //Toast.makeText(getContext(),"a domicilio",Toast.LENGTH_LONG).show();
                //  Scrollmap();
                addComercios();
                break;
            case R.id.enrestaurante:
                txtUbicacion.setTextColor(getResources().getColor(R.color.black));
                txtRestaurante.setTextColor(getResources().getColor(R.color.white));
                adomicilio.setCardBackgroundColor( getResources().getColor(R.color.alfa));
                adomicilio.setCardElevation(0);
                ensucursal.setCardBackgroundColor(getResources().getColor(R.color.colorRed));
                mMap.clear();
                enSucursal();
                //Toast.makeText(getContext(),"en restaurante",Toast.LENGTH_LONG).show();
                Scrollmap();
                addComercios();
                break;
            case R.id.closesearchlocation:
                constrainScrolled.setVisibility(View.GONE);
                constraintcollapse.setVisibility(View.GONE);
                constraibexpanden.setVisibility(View.VISIBLE);
                constrainsearchGoogle.setVisibility(View.GONE);
                bottomSheetBehavior.setDraggable(true);
                isdireccionpush=false;

                break;
            case R.id.closelocalmenu:
                bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_COLLAPSED);
                linearLayoutBSheet3.setVisibility(View.GONE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        linearLayoutBSheet.setVisibility(View.VISIBLE);
                        linearLayoutBSheet2.setVisibility(View.GONE);
                    }
                }, 500);
                break;
            case  R.id.markermanuallocation:
                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.flag);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 110, 110, false);
                LatLng offset = new LatLng(mainIconMarker.getPosition().latitude+.0015000, mainIconMarker.getPosition().longitude-.0004500);//LatLng ofset = new LatLng(25.5393929, -103.4532015);
                entregaMarker= mMap.addMarker(new MarkerOptions()
                        .position(offset)
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                        .draggable(true));

                mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDragStart(Marker arg0) {
                        // TODO Auto-generated method stub
                        Log.d("System out", "onMarkerDragStart..."+arg0.getPosition().latitude+"..."+arg0.getPosition().longitude);
                    }

                    @SuppressWarnings("unchecked")
                    @Override
                    public void onMarkerDragEnd(Marker arg0) {
                        // TODO Auto-generated method stub
                        Log.d("System out", "onMarkerDragEnd..."+arg0.getPosition().latitude+"..."+arg0.getPosition().longitude);

                        mMap.animateCamera(CameraUpdateFactory.newLatLng(arg0.getPosition()));
                    }

                    @Override
                    public void onMarkerDrag(Marker arg0) {
                        // TODO Auto-generated method stub
                        Log.i("System out", "onMarkerDrag...");
                    }
                });
                break;
            case R.id.constraindireccion:
                isdireccionpush=true;
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                bottomSheetBehavior.setDraggable(false);
                constrainScrolled.setVisibility(View.GONE);
                constraintcollapse.setVisibility(View.GONE);
                constraibexpanden.setVisibility(View.GONE);
                constrainsearchGoogle.setVisibility(View.VISIBLE);
                AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
                String query = null;
                FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                        //.setLocationBias(bounds)
                        .setCountry("mx")
                        .setTypeFilter(TypeFilter.ADDRESS)
                        .setSessionToken(token)
                        .setQuery(query)
                        .build();

                if (!Places.isInitialized()) {
                    Places.initialize(this.getContext(), getString(R.string.api_key));
                }
                PlacesClient placesClient = Places.createClient(this.getContext());

                AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                        getChildFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

                autocompleteFragment.setTypeFilter (TypeFilter.ESTABLISHMENT);
                autocompleteFragment.setCountry("mx");
                autocompleteFragment.setLocationBias(RectangularBounds.newInstance (
                        new LatLng(25.504304, -103.539797),
                        new LatLng(25.839232, -103.219032)));

                autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS));

                autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                    @Override
                    public void onPlaceSelected(@NonNull Place place) {
                        place.getAddress();
                        place.getLatLng();
                        Log.e("autocompletedialog"," placeselected"+ place.getLatLng());
                        constrainScrolled.setVisibility(View.GONE);
                        constraintcollapse.setVisibility(View.GONE);
                        constraibexpanden.setVisibility(View.VISIBLE);
                        constrainsearchGoogle.setVisibility(View.GONE);
                        bottomSheetBehavior.setDraggable(true);
                        isdireccionpush=false;
                        //if(textViewLocation.getText().equals("Direccion de entrega")) {
                        textViewLocation.setText(place.getAddress());
                        SharedPreferences preferencias = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferencias.edit();
                        editor.putString(GeneralConstantsV2.DIRECCION_USER, place.getAddress());
                        editor.commit();



                        if(entregaMarker!=null)
                        {
                            entregaMarker.setPosition(place.getLatLng());
                        }else {
                            if(flagdelivery==null) {
                                @SuppressLint("UseCompatLoadingForDrawables")
                                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.flag);
                                Bitmap b = bitmapdraw.getBitmap();
                                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 80, 80, false);
                                MarkerOptions flag = new MarkerOptions().position(place.getLatLng()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                                flagdelivery = mMap.addMarker(flag);
                            }else
                            {
                                flagdelivery.setPosition(place.getLatLng());
                            }
                        }
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(),15.5f));
//                        if(entregaMarker!=null) {
//                        entregaMarker= mMap.addMarker(new MarkerOptions().position( place.getLatLng()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
//                        }else
//                        {
//                            entregaMarker.setPosition(place.getLatLng());
//                        }

//                        LatLngBounds.Builder builder = new LatLngBounds.Builder();
//                        LatLng dot = new LatLng(mainIconMarker.getPosition().latitude, mainIconMarker.getPosition().longitude);
//                        builder.include(dot);
//                        LatLng dot2=new LatLng(place.getLatLng().latitude,place.getLatLng().longitude);
//                        builder.include(dot2);
//
//                        LatLngBounds bounds = builder.build();
//                        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));

                    }


                    @Override
                    public void onError(@NonNull Status status) {
                        Log.e("autocompletedialog"," place error "+ status.getStatusMessage());
                        constrainScrolled.setVisibility(View.GONE);
                        constraintcollapse.setVisibility(View.GONE);
                        constraibexpanden.setVisibility(View.VISIBLE);
                        constrainsearchGoogle.setVisibility(View.GONE);
                        bottomSheetBehavior.setDraggable(true);
                        isdireccionpush=false;
                    }
                });
                break;
        }
    }







}
