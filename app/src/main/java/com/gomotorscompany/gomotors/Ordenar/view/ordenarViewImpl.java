package com.gomotorscompany.gomotors.Ordenar.view;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gomotorscompany.gomotors.Dialogs.distanciaLejana.view.maxDistance;
import com.gomotorscompany.gomotors.Dialogs.firslogin.view.menulogin;
import com.gomotorscompany.gomotors.Ordenar.adapter.CenterZoomLayoutManager;
import com.gomotorscompany.gomotors.Ordenar.adapter.PhotosAdapter;
import com.gomotorscompany.gomotors.Ordenar.adapter.VerticalSpaceItemDecoration;
import com.gomotorscompany.gomotors.Ordenar.adapter.adapterArmatuPizza;
import com.gomotorscompany.gomotors.Ordenar.adapter.adapterCategoriasmenu;
import com.gomotorscompany.gomotors.Ordenar.adapter.adapterClasificaciones;
import com.gomotorscompany.gomotors.Ordenar.adapter.adapterComplementosonly;
import com.gomotorscompany.gomotors.Ordenar.adapter.adapterPaquetes;
import com.gomotorscompany.gomotors.Ordenar.adapter.adapterTamañosPizza;
import com.gomotorscompany.gomotors.Ordenar.adapter.companiesAdapter;
import com.gomotorscompany.gomotors.Ordenar.adapter.sucursalesAdapter;
import com.gomotorscompany.gomotors.Ordenar.model.Clasificacione;
import com.gomotorscompany.gomotors.Ordenar.model.ingredientesAlone.dataIngredients;
import com.gomotorscompany.gomotors.Ordenar.model.menuData;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.Complemento;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.Logo;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.Paquete;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.Sucursale;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.clasificaciones;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.listaproductos;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.menuDatan;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.responseMenun;
import com.gomotorscompany.gomotors.Ordenar.model.sucursales;
import com.gomotorscompany.gomotors.Ordenar.model.tamañosPizza;
import com.gomotorscompany.gomotors.Ordenar.presenter.mainpresenterView;
import com.gomotorscompany.gomotors.Ordenar.presenter.presenterViewImpl;
import com.gomotorscompany.gomotors.mainContent.view.mainContentViewImpl;
import com.gomotorscompany.gomotors.miscompras.model.restaurants.restaurantsLocations;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
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
import com.google.gson.Gson;
import com.google.maps.android.SphericalUtil;
import com.gomotorscompany.gomotors.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ordenarViewImpl extends Fragment implements OnMapReadyCallback,View.OnClickListener,LocationListener ,orderView,GoogleMap.OnInfoWindowClickListener,GoogleMap.OnMarkerClickListener ,GoogleMap.InfoWindowAdapter,View.OnDragListener,View.OnLongClickListener{ //GoogleMap.OnInfoWindowClickListener,GoogleMap.OnMarkerClickListener ,GoogleMap.InfoWindowAdapter
    public static final String TAG = ordenarViewImpl.class.getSimpleName();
    private GoogleMap mMap;
    private MapView mView;
    private double mylat,mylong;
    private double vehicleLat;
    private double vehicleLng;

   public static List<restaurantsLocations> listasucursales=new ArrayList<>();/**revisar aqui la ubicacion del comercio*/

    private BottomSheetBehavior bottomSheetBehavior,bottomSheetBehavior2,bottomSheetBehavior3,bottomSheetBehavior4,bottomSheetBehavior5;
    private ConstraintLayout linearLayoutBSheet,linearLayoutBSheet2,linearLayoutBSheet3,linearLayoutBSheet4,linearLayoutBSheet5,constraintcollapse,constraibexpanden,constrainScrolled,constraindireccion,constrainsearchGoogle,constrainblue,constraingreen,constrainCover,addcomplements;
    private boolean isClickedDrawTrip,swapstate=false;

    private CardView adomicilio,ensucursal;
    private TextView adomiciliotxt,ensucursaltxt;
    private boolean issucursal=true;

    private LocationManager locationManager;
    private double latitude,longitude;
    private Marker mainIconMarker,entregaMarker,flagdelivery;
    private  LatLng myloc;

    private TextView txtUbicacion,txtRestaurante,textViewLocation,preciarmatupizza,armatupizzaCount,namePizzaarma,namePizzaarmades,draggableitem;

    private mainpresenterView presenter;
    private ProgressDialog progressDialog;
    private menulogin externalGPSDialog;
    private maxDistance farfromtag;
    private List<menuDatan> fulldatan;
    private List<menuData> fulldata;
    private RecyclerView rv,rvSuc,rvMenu,rvPaquetes,rvcat,rvArma ,rvtamañospizza,rvcomplementosarmatupizza;
    private companiesAdapter adapter;
    private adapterCategoriasmenu adaptercategorias;
    private sucursalesAdapter adapterSuc;
    private adapterClasificaciones adapterMenu;
    private adapterPaquetes adapterPaq;
    private adapterArmatuPizza adapterSinglePizza;
    private adapterTamañosPizza adaptertamañosPizza;
    private adapterComplementosonly adaptercomplementosonly;

    private  List<LatLng> latitudlongitudSuc=new ArrayList<>();
    private ImageView selecionadireccion,markermanuallocation,closesearchlocation,closelocalmenu,backtoCommerce;
    private  boolean isdireccionpush=false;
    private static final int AUTOCOMPLETE_REQUEST_CODE = 23487;

    private List<sucursales> mysucursales;
    private List<Sucursale> mysucursalesn;
    private mainContentViewImpl mainActivity;
    private ViewPager pager;
    private PagerAdapter pAdapter;
    private int companyIndex;
    private LinearLayout dotslayout;
    private int customdots_position;
    private ImageView paquetes,armatupizza,addarmatupizza,removearmatupizza;
    private  List<Clasificacione> clasif;
    private  List<clasificaciones> clasifn;
    private List<tamañosPizza> tamaños=new ArrayList<>();
    private   Timer timer;

    private String idsuc;
    private String sucursalnam;
    private List<Sucursale> sucursalesnew;
    private VerticalSpaceItemDecoration verticalSpaceItemDecoration;


    private int pizzaPosition,pizzaPositionF,realposition;

    private int numberarmatupizzavalue,currentintexArmatupizza;

    private static final String TEXTVIEW_TAG = "DRAG TEXT";

    private  int begininglayoutparamswith;
    private ImageView hidervcomplemnetos;

    private List<dataIngredients> aloneIngredients;
    private Button agregarTodo;
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
       // linearLayoutBSheet5.setOnDragListener(this);
        constraingreen =view.findViewById(R.id.constraingreen);
        constraingreen.setOnDragListener(this);
        constrainblue =view.findViewById(R.id.constrainblue);
        constrainblue.setOnDragListener(this);

        constrainCover=view.findViewById(R.id.constrainCover);
        addcomplements=view.findViewById(R.id.addcomplements);
        addcomplements.setOnClickListener(this);
        hidervcomplemnetos=view.findViewById(R.id.hidervcomplemnetos);
        hidervcomplemnetos.setOnClickListener(this);
        agregarTodo=view.findViewById(R.id.agregarTodo);
        agregarTodo.setOnClickListener(this);

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
        preciarmatupizza=view.findViewById(R.id.preciarmatupizza);
        armatupizzaCount=view.findViewById(R.id.armatupizzaCount);
        namePizzaarma=view.findViewById(R.id.namePizzaarma);
        namePizzaarmades=view.findViewById(R.id.namePizzaarmades);
        addarmatupizza=view.findViewById(R.id.addarmatupizza);
        removearmatupizza=view.findViewById(R.id.removearmatupizza);
        addarmatupizza.setOnClickListener(this);
        removearmatupizza.setOnClickListener(this);

        draggableitem=view.findViewById(R.id.draggableitem);
        draggableitem.setTag(TEXTVIEW_TAG);
        draggableitem.setOnLongClickListener(this);

        constraingreen.setVisibility(View.GONE);
        constrainblue.setVisibility(View.GONE);
        draggableitem.setVisibility(View.GONE);


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
        rvcat=view.findViewById(R.id.rvcat);
        rvArma=view.findViewById(R.id.rvArma);
        rvtamañospizza=view.findViewById(R.id.rvtamañospizza);
        rvcomplementosarmatupizza=view.findViewById(R.id.rvcomplementosarmatupizza);
        rvcomplementosarmatupizza.setVisibility(View.GONE);
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
        presenter.requestProductos();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                checkMenu();
            }
        }, 2000);



        pager = (ViewPager) view.findViewById(R.id.viewP);
        dotslayout = view.findViewById(R.id.dots_layout);

    }
    private void checkMenu()
    {
        SharedPreferences npreferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String menu     = npreferences.getString(GeneralConstantsV2.JSON_MENU, null);
        if(menu!=null)
        {
            Log.e("menujson",""+menu);

            Gson gson = new Gson();
            responseMenun resp = gson.fromJson(menu, responseMenun.class);
            Log.e("menujsonoutput",""+resp.getData().size());
            if(!resp.getData().isEmpty())
            {
                List<menuDatan> data=resp.getData();
                if(data!=null)
                {
                    List<Logo>  infoinside=new ArrayList<>();
                    List<Sucursale> sucursales=new ArrayList<>();
                    infoinside.clear();
                    sucursales.clear();
                    for(int i=0;i<data.size();i++)
                    {   infoinside.addAll(data.get(i).getLogo());

                    }
                    for(int k=0;k<infoinside.size();k++)
                    {
                        sucursales.addAll(infoinside.get(k).getSucursales());
                    }
                    presenter.setComercios(data,sucursales);
                    List<String> categoriasDisponibles=new ArrayList<>();
                    fillCategoryAdapter(categoriasDisponibles);
                }
            }



        }else
        {
            Log.e("menujson","null menu");
            presenter.requestLocales();
        }

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
    private void bottomSheetSettings5(){/**menu local*/
        bottomSheetBehavior5= BottomSheetBehavior.from(linearLayoutBSheet5);
        bottomSheetBehavior5.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("bottonsheetmenu","STATE_DRAGGING");
                        linearLayoutBSheet5.setBackgroundResource(R.drawable.bgcollage);//.square_bottom_shet);
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("bottonsheetmenu","STATE_COLLAPSED");
                        linearLayoutBSheet5.setBackgroundResource(R.drawable.bgcollage);//.square_bottom_shet);
                        linearLayoutBSheet5.setVisibility(View.GONE);
                        rvArma.setOnFlingListener(null);
                        rvArma.removeItemDecoration(verticalSpaceItemDecoration);
                        rvArma.post(null);


                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("bottonsheetmenu","STATE_EXPANDED");
                        linearLayoutBSheet5.setBackgroundResource(R.drawable.bgcollage);//.square_bottom_shet);
                        armaTuPizza();
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }



    private void bottomSheetSettings4(){/* promociones*/
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
                        if(timer!=null)
                        {timer.cancel();}
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
//                        constrainScrolled.setVisibility(View.VISIBLE);
//                        constraintcollapse.setVisibility(View.GONE);
//                        constraibexpanden.setVisibility(View.GONE);
//                        constrainsearchGoogle.setVisibility(View.GONE);
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


/*
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                mMap.animateCamera(CameraUpdateFactory.scrollBy(0.0f, 1050));
                            }
                        }, 500);
*/
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

drawsucursales();

    }

    private void drawsucursales() {
        if(sucursalesnew.size()>0)
        {
            for(int k=0;k<sucursalesnew.size();k++)
            {

                    LatLng newmarker = new LatLng(sucursalesnew.get(k).getLat(), sucursalesnew.get(k).getLong());

                    //@SuppressLint("UseCompatLoadingForDrawables") BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_restaurante);
                    BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.tienda);
                    Bitmap b = bitmapdraw.getBitmap();
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, 80, 80, false);

                    mMap.addMarker(new MarkerOptions().position(newmarker).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                    //mMap.addMarker(new MarkerOptions().position(newmarker));
                    latitudlongitudSuc.add(newmarker);
                    Log.e("compañias","coordenadas "+newmarker);

            }
        }
    }

    private void Scrollmap()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(mMap!=null)
                {  mMap.animateCamera(CameraUpdateFactory.scrollBy(0.0f, 1250));}
            }
        }, 2000);   //5 seconds

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
        }else
        {
            Log.e("findlocation","");
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
    private void choselocalremoteLocation()/**clase para encontrar tu ubicacion*/
    {
        myloc= new LatLng(latitude, longitude);
        LatLng torreon = new LatLng(25.5383667, -103.446846);
        double sphericald=SphericalUtil.computeDistanceBetween(torreon,myloc);
        double realdistance=sphericald/1000;

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
//        externalGPSDialog = new menulogin();
//        externalGPSDialog.show(getActivity().getSupportFragmentManager(), menulogin.TAG);


        progressDialog.setMessage("Cargando datos ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    @Override
    public void hideProgressDialog() {
        externalGPSDialog.dismiss();
       // progressDialog.dismiss();
    }

    @Override
    public void igredientesIndividuales(List<dataIngredients> data) {
        this.aloneIngredients=data;

        fillcomplementosAdicionales();
    }


    @Override
    public void setCompaniastoView(List<menuDatan> data, List<Sucursale> sucursales) {
    this.fulldatan= data;
    this.sucursalesnew=sucursales;

        if(fulldatan!=null) {
            List<sucursales> sucursalespos=new ArrayList<>();
            latitudlongitudSuc.clear();
            for (int i = 0; i < data.size(); i++) {
                Log.e("compañias", "dataView" + fulldatan.get(i).getNombreEmpresa());

            }
          /*  if(sucursales.size()>0)
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
            }*/
            Scrollmap();

        }
        fillRv();

   /* if(data!=null) {
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
        if(categoriasDisponibles!=null)
        {
            List<String> singleitem=new ArrayList<>();
            singleitem.clear();
            singleitem.add(categoriasDisponibles.get(0));
            fillCategoryAdapter(singleitem);
        }else
        {

        }
    }



    /** comercios en los mapas*/
    private void addComercios()
    {

        if(!latitudlongitudSuc.isEmpty())
        {
            for(int i=0; i<latitudlongitudSuc.size();i++)
            {
              //  @SuppressLint("UseCompatLoadingForDrawables") BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_restaurante);
                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.tienda);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 80, 80, false);

                mMap.addMarker(new MarkerOptions().position(latitudlongitudSuc.get(i)).snippet("adasdasdsadasdasd").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

               // mainIconMarker=  mMap.addMarker(new MarkerOptions().position(point)
            }
        }
    }
    private void armaTuPizza() {

        rvArma.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                    LinearLayoutManager layoutManager = ((LinearLayoutManager) rvArma.getLayoutManager());
                    int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                    pizzaPosition=firstVisiblePosition+1;
                    Log.e("recyclerArmatupizza5","   position "+ (firstVisiblePosition+1));
                    Log.e("refresh", "State - " + newState + " : firstVisiblePosition" + firstVisiblePosition);
                    Log.e("recyclerArmatupizza5","   scrolled "+pizzaPositionF);
                    finalRecyclerposition(pizzaPosition,pizzaPositionF);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });

    }
/** este metodo controla la posicion del recycler **/
    private void finalRecyclerposition(int pizzaPosition, int pizzaPositionF) {

       // Log.e("recyclerArmatupizza1","     "+pizzaPosition+"   "+pizzaPositionF);
        if(pizzaPositionF==0&&pizzaPosition==1)
        {
            this.realposition=pizzaPositionF;
            Log.e("recyclerArmatupizza7","real position: "+pizzaPositionF);
        }else
        {
            this.realposition=pizzaPosition;
            Log.e("recyclerArmatupizza7","real position: "+pizzaPosition);
        }
        editDataRecycler(realposition);
    }

    private void editDataRecycler(int realposition) {
        Log.e("recyclerArmatupizza8","real position: "+realposition+"   casifn pos: "+currentintexArmatupizza);
//        if(realposition<=clasifn.size())
//            {
                setfirstPrice(clasifn.get(currentintexArmatupizza).getProducto().get(realposition).getPrecio());
                namePizzaarma.setText(clasifn.get(currentintexArmatupizza).getProducto().get(realposition).getNombre());
                namePizzaarmades.setText(clasifn.get(currentintexArmatupizza).getProducto().get(realposition).getDescripcion());
//            }
//        else{
//            setfirstPrice(clasifn.get(currentintexArmatupizza).getProducto().get(0).getPrecio());
//        }/**TODO revisar logica de esto*/
    }

    public void detectFirstItem(int scrolled)
    {
        this.pizzaPositionF=scrolled;
        Log.e("recyclerArmatupizza6","   pizza "+scrolled);
        Log.e("menufulldata","   menufulldata "+clasifn.get(1).getProducto().size());


    }
    public void settamaños(String s, int position){
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//
//            }
//        }, 500);
        for(int k=0;k<tamaños.size();k++)
        {
            tamaños.get(k).setIschecke(false);
        }

        tamaños.get(position).setIschecke(true);
        adaptertamañosPizza.notifyDataSetChanged();

        for (int i=0;i<clasifn.size();i++)
        {
            if(clasifn.get(i).getClasificacion().equals(s))
            {
               // Toast.makeText(getContext(), "is contained "+ i, Toast.LENGTH_SHORT).show();
                rvArma.setOnFlingListener(null);
                rvArma.removeItemDecoration(verticalSpaceItemDecoration);
                rvArma.post(null);
                currentintexArmatupizza=i;


                fillarmaTuPizza(clasifn.get(i).getProducto());
                setfirstPrice(clasifn.get(i).getProducto().get(0).getPrecio());
                namePizzaarma.setText(clasifn.get(i).getProducto().get(0).getNombre());
                namePizzaarmades.setText(clasifn.get(i).getProducto().get(0).getDescripcion());
            }
        }
    }
    /**   campos fill para adaptadores */
    private void fillRv() {
        adapter=new companiesAdapter(this,fulldatan,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }
    private void fillmenucategories()
    {
        adaptertamañosPizza =new adapterTamañosPizza(getContext(),tamaños,this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rvtamañospizza.setLayoutManager(layoutManager);
        rvtamañospizza.setAdapter(adaptertamañosPizza);
        fillarmaTuPizza(clasifn.get(0).getProducto());
        setfirstPrice(clasifn.get(0).getProducto().get(0).getPrecio());
        namePizzaarma.setText(clasifn.get(0).getProducto().get(0).getNombre());
        namePizzaarmades.setText(clasifn.get(0).getProducto().get(0).getDescripcion());

    }
    private void fillcomplementosAdicionales(){
        adaptercomplementosonly=new adapterComplementosonly(aloneIngredients,getContext(),this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rvcomplementosarmatupizza.setLayoutManager(layoutManager);
        rvcomplementosarmatupizza.setAdapter(adaptercomplementosonly);
    }
    private void setfirstPrice(String precio) {
        preciarmatupizza.setText(precio);
    }
    private void setnumberarmatupizza(int value)
    {
        numberarmatupizzavalue= Integer.valueOf((String) armatupizzaCount.getText());
        if(numberarmatupizzavalue>=1)
        {
            numberarmatupizzavalue=numberarmatupizzavalue+value;
            if(numberarmatupizzavalue==0)
            {
                Toast.makeText(getContext(), "Tienes que agregar almenos un item", Toast.LENGTH_SHORT).show();
            }else {
                armatupizzaCount.setText(String.valueOf(numberarmatupizzavalue));
            }
        }else
        {
            //armatupizzaCount.setText(String.valueOf( numberarmatupizzavalue));
            Toast.makeText(getContext(), "Tienes que agregar almenos un item", Toast.LENGTH_SHORT).show();
        }
    }

    private void fillarmaTuPizza(List<listaproductos> producto){
        adapterSinglePizza  =new  adapterArmatuPizza(this,producto,getContext());
        LinearLayoutManager layoutManager=new CenterZoomLayoutManager(this,getContext(),LinearLayoutManager.HORIZONTAL,false);
        rvArma.setLayoutManager(layoutManager);

        rvArma.setAdapter(adapterSinglePizza);
        if(producto.size()==1)
        {
            verticalSpaceItemDecoration = new VerticalSpaceItemDecoration(this, 38);
            rvArma.addItemDecoration(verticalSpaceItemDecoration);
        }
        else{
            verticalSpaceItemDecoration = new VerticalSpaceItemDecoration(this, 240);
            rvArma.addItemDecoration(verticalSpaceItemDecoration);
            //layoutManager.scrollToPosition(6 / 2);//(storeList.size() / 2);
            rvArma.post(new Runnable() {
                @Override
                public void run() {
                    // Shift the view to snap  near the center of the screen.
                    // This does not have to be precise.

                    int dx = (rvArma.getWidth() - rvArma.getChildAt(0).getWidth()) / 2;

                    rvArma.scrollBy(-dx, 0);
                    // Assign the LinearSnapHelper that will initially snap the near-center view.
                    LinearSnapHelper snapHelper = new LinearSnapHelper();
                    snapHelper.attachToRecyclerView(rvArma);


                }
            });

        }

    }
    private void fillCategoryAdapter(List<String> categoriasDisponibles) {
        adaptercategorias=new adapterCategoriasmenu(categoriasDisponibles,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rvcat.setLayoutManager(layoutManager);
        rvcat.setAdapter(adaptercategorias);
        rvSuc.setNestedScrollingEnabled(true);
    }
    private void fillRvSuc(List<Sucursale> mysuc, String img)
    {
        adapterSuc= new sucursalesAdapter(this,mysuc,img,getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rvSuc.setLayoutManager(layoutManager);
        rvSuc.setAdapter(adapterSuc);
        rvSuc.setNestedScrollingEnabled(true);
    }


    private void fillClasificaciones(List<clasificaciones> clasificaciones)
    {
        adapterMenu =new adapterClasificaciones(this,clasificaciones,getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rvMenu.setLayoutManager(layoutManager);
        rvMenu.setAdapter(adapterMenu);
    }
    private  void fillPaquetes(List<Clasificacione> clasificaciones)
    {
        adapterPaq=new adapterPaquetes(this,clasificaciones,getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rvPaquetes.setLayoutManager(layoutManager);
        rvPaquetes.setAdapter(adapterPaq);
    }

    /**adapter de sucursales*/
    public void dataReciclermenu(List<Sucursale> sucursales, String img, int position)
    {
        this.mysucursalesn=sucursales;
        listasucursales.clear();
        bottomSheetBehavior.setDraggable(false);
        if(mysucursalesn!=null) {
            rv.setVisibility(View.GONE);
            rvSuc.setVisibility(View.VISIBLE);
            backtoCommerce.setVisibility(View.VISIBLE);
            fillRvSuc(mysucursalesn, img);
            fillpageradapter();
            if (!mysucursalesn.isEmpty())
                {
                    for (int i = 0; i < mysucursalesn.size(); i++) {

                    listasucursales.add(new restaurantsLocations(sucursales.get(i).getLat(), sucursales.get(i).getLong(), sucursales.get(i).getCodigoSucursal(), sucursales.get(i).getNombreSuc(), img));
                    }
                }
        }
      /*  this.companyIndex=position;
        if(!mysucursales.isEmpty()&&mysucursales.size()>0&&mysucursales!=null)
        {

            for(int i=0;i<mysucursales.size();i++)
            {

                Log.e("adapterpositions"," "+mysucursales.get(i).getSucursalNombre()
                        +" "+mysucursales.get(i).getSucursalLat()+" "+mysucursales.get(i).getSucursalLong()
                        +" "+mysucursales.get(i).getSucursalNombre()+" "+mysucursales.get(i).getSucursalApertura()
                        +" "+mysucursales.get(i).getSucursalCierre());
            }

            fillRvSuc(mysucursalesn,img);
          ///  fillpageradapter(); /anuncios/


        }*/
    }

    /**pager adapter de promociones*/
    private void fillpageradapter()
    {
        List<String> urlsbanners=new ArrayList<>();
        urlsbanners.clear();
        urlsbanners.add("");
        pAdapter = new PhotosAdapter(getChildFragmentManager(), getContext());//(getChildFragmentManager(), fulldata.get(companyIndex).getBanner(),getContext());
        pager.setAdapter(pAdapter);
        timer =new Timer();
        timer.schedule(new sliderTimer(),2000,4000);
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

    public void retrieveAloneIngredients(List<dataIngredients> aIngredients) {
        aloneIngredients=aIngredients;
        adaptercomplementosonly.notifyDataSetChanged();
    }


    /** clase timer pra motion de promociones*/
    class   sliderTimer extends TimerTask{

        @Override
        public void run() {
            if(getActivity()!=null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (pager.getCurrentItem() < 3)//(pager.getCurrentItem()<fulldata.get(companyIndex).getBanner().size()-1)
                        {
                            pager.setCurrentItem(pager.getCurrentItem() + 1);
                        } else {
                            pager.setCurrentItem(0);
                        }
                    }
                });
            }
        }
    }
    /**adaptador sheet de promociones*/
    private void dotsposition(int currentposition)/** puntos de los banners*/
    {
        if(dotslayout.getChildCount()>0)
        {
            dotslayout.removeAllViews();
        }
        ImageView dots[]=new ImageView[3];//[fulldata.get(companyIndex).getBanner().size()];
        for (int i=0; i<3;i++){//(int i=0; i<fulldata.get(companyIndex).getBanner().size();i++){
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
    /**boton sheet de sucursal*/
    public void hideSheetlavel(String namesucursal,String idsucursal,List<clasificaciones> clasificaciones,List<Paquete> paquetes)
    {
        this.idsuc=idsucursal;
        this.sucursalnam=namesucursal;
        this.clasifn=clasificaciones;
        tamaños.clear();

        if(clasifn!=null)
        {
            Log.e("tamañospizza",""+clasifn.size());
            for(int i=0;i<clasifn.size();i++)
            {
                if(clasifn.get(i).getClasificacion().toLowerCase().equals("chicas")||clasifn.get(i).getClasificacion().toLowerCase().equals("familiar")){
                    tamaños.add(new tamañosPizza( clasifn.get(i).getClasificacion(),false));
                }
            }
            tamaños.get(0).setIschecke(true);
        }

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
    @RequiresApi(api = Build.VERSION_CODES.M)/** infoview de mapá*/
    private View prepareInfoView(Marker marker) {
        //prepare InfoView programmatically
        LatLng mypointlatlong = new LatLng(latitude, longitude);
        // LatLng torreon = new LatLng(25.5393929, -103.4532015);
        if(marker.getPosition().latitude!=latitude&&marker.getPosition().longitude!=longitude&&marker.getPosition().latitude!=25.5393929&&marker.getPosition().longitude!=-103.4532015&&marker.getPosition().latitude!=mainIconMarker.getPosition().latitude&&marker.getPosition().longitude!=mainIconMarker.getPosition().longitude) {
            Log.e("infoviewmylocation", "markerlat" + marker.getPosition().latitude + "  markerlong" + marker.getPosition().longitude + "  reallat: " + mylat + "  realong: " + mylong);
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.window_design, null);
            return v;
        }else
        {
            return null;
        }

    }
    @Override    /**override de un onresult*/
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

    /**esta clase regresa del adaptador y va hacie el activity*/
   /* public void interactActivity(String Category, String clave, String nombre, String precio, String descripcion, List<Complementos> complementos, String imagen) {
        timer.cancel();//cancela el timer schedule
        if(textViewLocation.getText().equals( "Direccion de entrega"))
        {
            Toast.makeText(getContext(), "necesitas elegir una direccion de entrega", Toast.LENGTH_SHORT).show();
        }else
        {
            Log.e("countitems1",""+textViewLocation.getText()+"    "+idsuc+"  "+sucursalnam+"  "+Category+" "+mainIconMarker.getPosition().latitude + "   Long: " + mainIconMarker.getPosition().longitude);
            mainActivity.activityMenu(String.valueOf( textViewLocation.getText()),idsuc,sucursalnam,mainIconMarker.getPosition().latitude,mainIconMarker.getPosition().longitude,Category,clave,nombre,precio,descripcion,complementos,imagen);
        }

    }*/

    public void interactActivity2(String Category, List<Complemento> complementos, String sku, String nombre, String precio, String descripcion, String imagen){
        timer.cancel();//cancela el timer schedule
        if(textViewLocation.getText().equals( "Direccion de entrega"))
        {
            Toast.makeText(getContext(), "necesitas elegir una direccion de entrega", Toast.LENGTH_SHORT).show();
        }else
        {

           // Log.e("countitems1",""+textViewLocation.getText()+"    "+idsuc+"  "+sucursalnam+"  "+Category+" "+mainIconMarker.getPosition().latitude + "   Long: " + mainIconMarker.getPosition().longitude);
            mainActivity.activityMenu2( String.valueOf(textViewLocation.getText()),idsuc,sucursalnam,mainIconMarker.getPosition().latitude,mainIconMarker.getPosition().longitude,Category,complementos,sku,nombre,precio,descripcion,imagen);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(timer!=null) { timer.cancel();}
    }
    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {

        // Defines a variable to store the action type for the incoming event
        int action = dragEvent.getAction();

        // Handles each of the expected events
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:

                // Determines if this View can accept the dragged data
                if (dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // if you want to apply color when drag started to your view you can uncomment below lines
                    // to give any color tint to the View to indicate that it can accept
                    // data.
                    view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

                    // Invalidate the view to force a redraw in the new tint
                     view.invalidate();

                    // returns true to indicate that the View can accept the dragged data.
                    return true;
                }

                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:

                // Applies a MAGENTA or any color tint to the View,
                // Return true; the return value is ignored.

                    view.getBackground().setColorFilter(Color.MAGENTA, PorterDuff.Mode.SRC_IN);

                    // Invalidate the view to force a redraw in the new tint
                    view.invalidate();

                return true;

            case DragEvent.ACTION_DRAG_LOCATION:

                // Ignore the event
                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                // Re-sets the color tint to blue, if you had set the BLUE color or any color in ACTION_DRAG_STARTED. Returns true; the return value is ignored.

                //  view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

                //If u had not provided any color in ACTION_DRAG_STARTED then clear color filter.
                view.getBackground().clearColorFilter();

                // Invalidate the view to force a redraw in the new tint
                view.invalidate();

                return true;

            case DragEvent.ACTION_DROP:

                // Gets the item containing the dragged data
                ClipData.Item item = dragEvent.getClipData().getItemAt(0);

                // Gets the text data from the item.
                String dragData = item.getText().toString();

                // Displays a message containing the dragged data.
                Toast.makeText(getContext(), "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                // Turns off any color tints
                view.getBackground().clearColorFilter();

                // Invalidates the view to force a redraw
                view.invalidate();

                //get dragged view
                View v = (View) dragEvent.getLocalState();
                ViewGroup owner = (ViewGroup) v.getParent();
                owner.removeView(v); //remove the dragged view
                ConstraintLayout container = (ConstraintLayout) view; //caste the view into LinearLayout as our drag acceptable layout is LinearLayout
                container.addView(v); //Add the dragged view
                v.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE

                // Returns true. DragEvent.getResult() will return true.
                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                // Turns off any color tinting
                view.getBackground().clearColorFilter();

                // Invalidates the view to force a redraw
                /**TODO revisar si funciona*/
               // view.invalidate();

                // invoke getResult(), and displays what happened.
                if (dragEvent.getResult()){
                    Toast.makeText(getContext(), "The drop was handled.", Toast.LENGTH_SHORT).show();
                     constrainblue.removeView(view);
//                    view.setVisibility(View.GONE);

                    View v1 = (View) dragEvent.getLocalState();
                    ViewGroup owner2 = (ViewGroup) v1.getParent();
                    owner2.removeView(v1); //remove the dragged view
                    constraingreen= (ConstraintLayout) view; //caste the view into LinearLayout as our drag acceptable layout is LinearLayout
                    constraingreen.addView(v1); //Add the dragged view
                    v1.setVisibility(View.VISIBLE);
                         }
                else {
                    Toast.makeText(getContext(), "The drop didn't work.", Toast.LENGTH_SHORT).show();
                    //get dragged view
                    view = (View) dragEvent.getLocalState();
                    view.setVisibility(View.VISIBLE);
                }

                // returns true; the value is ignored.
                return true;

            // An unknown action type was received.
            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }


    @Override
    public boolean onLongClick(View view) {
        // Create a new ClipData.Item from the tag
        ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

        // Create a new ClipData using the tag as a label, the plain text MIME type, and
        // the already-created item. This will create a new ClipDescription object within the
        // ClipData, and set its MIME type entry to "text/plain"
        ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);

        // Instantiates the drag shadow builder.
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

        // Starts the drag
        view.startDrag(data //data to be dragged
                , shadowBuilder //drag shadow
                , view //local data about the drag and drop operation
                , 0 //flags (not currently used, set to 0)
        );

        //Set view visibility to INVISIBLE as we are going to drag the view
        view.setVisibility(View.INVISIBLE);

        return true;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paquetes:/**este on click espara llenar una lista con los paquetes*/
                linearLayoutBSheet4.setVisibility(View.VISIBLE);
                Handler promohandler = new Handler();
                promohandler.postDelayed(new Runnable() {
                    public void run() {
                       fillPaquetes(clasif);
                        bottomSheetBehavior4.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }, 500);
                    break;
            case R.id.armatupizza:/**este onclick es pára el modulo de arma tu pizza*/
                linearLayoutBSheet5.setVisibility(View.VISIBLE);
                Handler armatupizzahandler = new Handler();
                armatupizzahandler.postDelayed(new Runnable() {
                    public void run() {
                        bottomSheetBehavior5.setState(BottomSheetBehavior.STATE_EXPANDED);

                    }
                }, 500);

               fillmenucategories();


               presenter.getComplementosalone();

                break;
            case R.id.backtoCommerce:/**este onclick es pra regresar al comercio*/
                bottomSheetBehavior.setDraggable(true);/**   quitar si no va aqui*/
                rv.setVisibility(View.VISIBLE);
                rvSuc.setVisibility(View.GONE);
                backtoCommerce.setVisibility(View.GONE);
                break;
            case R.id.adomicilio:/**este on click es para seleccionar a domicilio*/
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
            case R.id.enrestaurante:/**este on click es pea seleccionar modo en restaurante*/
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
            case R.id.closesearchlocation: /** close search location*/
                        constrainScrolled.setVisibility(View.GONE);
                        constraintcollapse.setVisibility(View.GONE);
                        constraibexpanden.setVisibility(View.VISIBLE);
                        constrainsearchGoogle.setVisibility(View.GONE);
                        bottomSheetBehavior.setDraggable(true);
                        isdireccionpush=false;

                break;
            case R.id.closelocalmenu:  /**close menu to go companies*/

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
            case  R.id.markermanuallocation:   /** drag location*/

                /*BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.flag);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 80, 80, false);
                MarkerOptions flag = new MarkerOptions().position(place.getLatLng()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                flagdelivery = mMap.addMarker(flag);*/
                if(entregaMarker==null)
                {
                    Log.e("banderaenmapa2","status entregaMarker: "+entregaMarker);
                }else
                {
                    Log.e("banderaenmapa2","status entregaMarker: "+entregaMarker);

                }


                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.flag);
                Log.e("banderaenmapa","bandera creada");
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 110, 110, false);
                LatLng offset = new LatLng(mainIconMarker.getPosition().latitude+.0015000, mainIconMarker.getPosition().longitude-.0004500);//LatLng ofset = new LatLng(25.5393929, -103.4532015);
                entregaMarker= mMap.addMarker(new MarkerOptions()
                        .position(offset)
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                        .draggable(true));

                Geocoder geocoder = new Geocoder(getContext());

                List<Address> addressList;
                Address address = null;

                try {
                    addressList = geocoder.getFromLocation(entregaMarker.getPosition().latitude,entregaMarker.getPosition().longitude,1);
                    address = addressList.get(0);
//                            address.getLatitude();
//                            address.getLongitude();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                textViewLocation.setText(address.getAddressLine(0));
                SharedPreferences preferencias = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString(GeneralConstantsV2.DIRECCION_USER, address.getAddressLine(0));
                editor.commit();

                markermanuallocation.setEnabled(false);/** deabilitamos el boton*/
                mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDragStart(Marker arg0) {
                        // TODO Auto-generated method stub
                        Log.d("System out", "onMarkerDragStart..."+arg0.getPosition().latitude+"..."+arg0.getPosition().longitude);
                       // Log.e("banderaenmapa","bandera arrastrando");
                    }

                    @SuppressWarnings("unchecked")
                    @Override
                    public void onMarkerDragEnd(Marker arg0) {
                        // TODO Auto-generated method stub
                        Log.d("System out", "onMarkerDragEnd..."+arg0.getPosition().latitude+"..."+arg0.getPosition().longitude);

                        mMap.animateCamera(CameraUpdateFactory.newLatLng(arg0.getPosition()));

                        // double sphericald=SphericalUtil.computeDistanceBetween(entregaMarker.getPosition(),myloc);
/*                        float[] distances = new float[1];
                        Location.distanceBetween(entregaMarker.getPosition().latitude,entregaMarker.getPosition().longitude,myloc.latitude,myloc.longitude,distances);
                        Location locationA = new Location("LocationA");
                        locationA.setLatitude(myloc.latitude);
                        locationA.setLongitude(myloc.longitude);
                        Location locationB = new Location("LocationB");
                        locationB.setLatitude(entregaMarker.getPosition().latitude);
                        locationB.setLongitude(entregaMarker.getPosition().longitude);
                        float distance2 = locationA.distanceTo(locationB);
*/

                        Double esperical = SphericalUtil.computeDistanceBetween(myloc, entregaMarker.getPosition());
                        Double distance3 =esperical;

                        Geocoder geocoder = new Geocoder(getContext());
                    
                        List<Address> addressList;
                        Address address = null;

                        try {
                            addressList = geocoder.getFromLocation(entregaMarker.getPosition().latitude,entregaMarker.getPosition().longitude,1);
                            address = addressList.get(0);
//                            address.getLatitude();
//                            address.getLongitude();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (distance3 > 60) {
                            farfromtag = new maxDistance();
                            farfromtag.show(getActivity().getSupportFragmentManager(), maxDistance.TAG);
                        }
                        Log.e("banderaenmapa","bandera dragend "+" distancia entre flag y myloc: "+String.format("%.2f",distance3)+"m "+ " Direccion relativa: "+address.getAddressLine(0));
                        textViewLocation.setText(address.getAddressLine(0));
                        SharedPreferences preferencias = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferencias.edit();
                        editor.putString(GeneralConstantsV2.DIRECCION_USER, address.getAddressLine(0));
                        editor.commit();

                    }

                    @Override
                    public void onMarkerDrag(Marker arg0) {
                        // TODO Auto-generated method stub
                        Log.i("System out", "onMarkerDrag...");
                       // Log.e("banderaenmapa","bandera draginit");
                    }
                });
                    break;
            case R.id.constraindireccion:     /** iagen desplegada*/
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
                            Log.e("banderaenmapa","bandera tras searcbar");

                        }else {
                            if(flagdelivery==null) {
                                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.flag);
                                Bitmap b = bitmapdraw.getBitmap();
                                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 80, 80, false);
                                MarkerOptions flag = new MarkerOptions().position(place.getLatLng()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).draggable(true);
                                flagdelivery = mMap.addMarker(flag);//flagdelivery

                                Log.e("banderaenmapa","bandera null");
                            }else
                            {
                                Log.e("banderaenmapa","bandera rocolocada");
                                flagdelivery.setPosition(place.getLatLng());//flagdelivery
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

            case R.id.addarmatupizza:
                setnumberarmatupizza(1);
                break;
            case R.id.removearmatupizza:
                setnumberarmatupizza(-1);
                break;
            case R.id.addcomplements:
                if(addcomplements.getVisibility()==View.VISIBLE)
                {
                    hidervcomplemnetos.setVisibility(View.VISIBLE);
                    addcomplements.setVisibility(View.GONE);
                    ViewGroup.LayoutParams layoutParams = constrainCover.getLayoutParams();
                    begininglayoutparamswith=constrainCover.getLayoutParams().width;
                    layoutParams.width = 700;
                    constrainCover.setLayoutParams(layoutParams);
                    rvcomplementosarmatupizza.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.hidervcomplemnetos:
                if(hidervcomplemnetos.getVisibility()==View.VISIBLE)
                {
                    hidervcomplemnetos.setVisibility(View.GONE);
                    addcomplements.setVisibility(View.VISIBLE);
                    rvcomplementosarmatupizza.setVisibility(View.GONE);

                    ViewGroup.LayoutParams layoutParams = constrainCover.getLayoutParams();
                    layoutParams.width = begininglayoutparamswith;
                    constrainCover.setLayoutParams(layoutParams);

                    for(int i=0;i<aloneIngredients.size();i++)
                    {
                        aloneIngredients.get(i).setChecked(false);
                    }
                    adaptercomplementosonly.notifyDataSetChanged();
                }
                break;
            case R.id.agregarTodo:/** esto es para el modulo arma tu pizza*/
                List <dataIngredients> ingredientesExtra=new ArrayList<>();
                ingredientesExtra.clear();
                List<Complemento> complementos=new ArrayList<>();
                complementos.clear();

                for(int i=0;i<aloneIngredients.size();i++)
                {
                    if(aloneIngredients.get(i).getChecked()==true)
                    {
                        ingredientesExtra.add(aloneIngredients.get(i));

                    }
                }
                Log.e("armatupizzaAdd","agregar producto: "+clasifn.get(currentintexArmatupizza).getProducto().get(realposition).getNombre()
                        +" "+clasifn.get(currentintexArmatupizza).getProducto().get(realposition).getDescripcion()+"  complemento: "+ingredientesExtra.size());


                //TODO agregar complemento correctamente
                /**este primero lleva a agregarcompra que no requerimos*/
                 mainActivity.activityMenu3( String.valueOf(textViewLocation.getText()),idsuc,sucursalnam,mainIconMarker.getPosition().latitude,mainIconMarker.getPosition().longitude
                                        ,"producto",complementos,clasifn.get(currentintexArmatupizza).getProducto().get(realposition).getSku(),clasifn.get(currentintexArmatupizza).getProducto().get(realposition).getNombre()
                                        ,clasifn.get(currentintexArmatupizza).getProducto().get(realposition).getPrecio(),clasifn.get(currentintexArmatupizza).getProducto().get(realposition).getDescripcion()
                                        ,clasifn.get(currentintexArmatupizza).getProducto().get(realposition).getImagen());



                break;
        }
    }



}
//View view = findViewById(R.id.nutrition_bar_filled);
//LayoutParams layoutParams = view.getLayoutParams();
//layoutParams.width = newWidth;
//view.setLayoutParams(layoutParams);