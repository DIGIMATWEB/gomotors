package com.gomotorscompany.gomotors.Zonas.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.Ordenar.model.historicData;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.Zonas.adapter.dotsAdapter;
import com.gomotorscompany.gomotors.Zonas.adapter.repartidoresAdapter;
import com.gomotorscompany.gomotors.Zonas.adapter.zonesAdapter;
import com.gomotorscompany.gomotors.Zonas.model.Location;
import com.gomotorscompany.gomotors.Zonas.model.Puntos;
import com.gomotorscompany.gomotors.Zonas.model.Zona;
import com.gomotorscompany.gomotors.Zonas.model.dataRapartidores;
import com.gomotorscompany.gomotors.Zonas.model.datarequesZonas;
import com.gomotorscompany.gomotors.Zonas.presenter.empleadorsPresenter;
import com.gomotorscompany.gomotors.Zonas.presenter.empleadosPresenterImpl;
import com.gomotorscompany.gomotors.Zonas.presenter.zonesPresenter;
import com.gomotorscompany.gomotors.Zonas.presenter.zonesPresenterImpl;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class zonasViewImpl extends Fragment implements View.OnClickListener, OnMapReadyCallback,zonesView {
    public static final String TAG = zonasViewImpl.class.getSimpleName();
    private GoogleMap mMap;
    private MapView mView;
    private BottomSheetBehavior bottomSheetBehavior,bottomSheetBehavior2;
    private List<datarequesZonas> dataZones;
    private ProgressDialog progressDialog;
    private zonesPresenter presenter;
    private empleadorsPresenter presenter2;
    private int red,blue,ratio,black,alfa;
    private RecyclerView rv,rvdots,rvRepartidores;
    private zonesAdapter adapter;
    private repartidoresAdapter adapterR;
    private dotsAdapter Adapterdots;
    private ImageView eye,driver,addzone,dots,edit,erase    ,tipezone;
    private boolean valueeye=true;
    private boolean valuedriver=true;
    private ConstraintLayout constraineditcreateZone,linearLayoutBSheet,linearLayoutBSheet2;
    private CardView searchbar;
    private Button cancelareditzone,saveZones;
    private Marker mainmarker;
    private boolean isnew;
    private ImageButton addnewitemlocation;
    private List<Location> location = new ArrayList<>();
    private List<historicData> historicdrivers=new ArrayList<>();
    private int iteration=0;
    private Switch tipeSwitch;

    private SearchView searchViewZones,searchViewRepartidores;
    private TextView textView18,textViewRatio,nameEstablecimiento1;
    private EditText EditTextRatio;
    private Circle newCircle;
    private Marker centerDot,poligonDot;
    private boolean ispoligon=false;
    private LatLng triangleDot1,triangleDot2,triangleDot3,triangleDotNew;
    private boolean isedited=false;
    private boolean saveonedited=false;
    private int coloniaIDedited=0;
    private String nameColoniaEdited;
    private int countifedited=0;

    private boolean truefalseCircles=false;

    private zonasViewImpl mainView;
    private List<Location> locationE;
    private boolean poligonTyp;
    private int positionE;
    private List<Location> nlocation = new ArrayList<>();
    private String nameZoneEdited="";

    private List<dataRapartidores> listRepartidores;
    private List<Marker>  mainIconMarkers=new ArrayList<>();
    private Marker mainIconMarker;
    private CardView zoneB,empleadosB;
    private TextView txtempleados,txtZonas;
    private Handler handler = new Handler();
    private Runnable runnable;
    private Boolean statusVehicles=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.completado, container, false);
        initView(view);
        mView.onCreate(savedInstanceState);
        if (mView != null) {
            mView.getMapAsync(this);
        }
        return view;
    }

    private void initView(View view) {
        mainView=this;
        mainIconMarkers.clear();
        historicdrivers.clear();
        mView = view.findViewById(R.id.mapsZonas);
        linearLayoutBSheet = view.findViewById(R.id.bottomSheetZones);
        linearLayoutBSheet2 = view.findViewById(R.id.bottomSheetworkes);

        progressDialog = new ProgressDialog(getActivity());
        presenter=new zonesPresenterImpl(this,getContext());
        presenter2=new empleadosPresenterImpl(this,getContext());
        black=ContextCompat.getColor(getContext(), R.color.black);
        red=ContextCompat.getColor(getContext(), R.color.redalfa);
        blue=ContextCompat.getColor(getContext(), R.color.bluealfa);
        alfa=ContextCompat.getColor(getContext(), R.color.alfa);
        rv= view.findViewById(R.id.listazonas);
        rvRepartidores= view.findViewById(R.id.listaRepartidores);
        rvdots= view.findViewById(R.id.recyclerViewdots);
        ratio=350;
        presenter.getZones();
        presenter2.getEmployes();
        zoneB= view.findViewById(R.id.zoneB);
        empleadosB= view.findViewById(R.id.empleadosB);
        zoneB.setOnClickListener(this);
        empleadosB.setOnClickListener(this);
        txtempleados= view.findViewById(R.id.txtempleados);
        txtZonas= view.findViewById(R.id.txtZonas);

        eye=view.findViewById(R.id.eye_zones);
        eye.setOnClickListener(this);
        driver=view.findViewById(R.id.driver_zones);
        driver.setOnClickListener(this);
        addzone=view.findViewById(R.id.addzone);
        addzone.setOnClickListener(this);
        constraineditcreateZone=view.findViewById(R.id.editZone);
        cancelareditzone=view.findViewById(R.id.cancelarnewzones);
        cancelareditzone.setOnClickListener(this);
        saveZones=view.findViewById(R.id.buttonsave);
        saveZones.setOnClickListener(this);
        searchbar =view.findViewById(R.id.cardViewBusquedazons);
        addnewitemlocation=view.findViewById(R.id.addnewitemlocation);
        addnewitemlocation.setOnClickListener(this);
        textView18=view.findViewById(R.id.textView18);
        nameEstablecimiento1=view.findViewById(R.id.nameEstablecimiento1);
        textViewRatio=view.findViewById(R.id.textViewRatio);
        EditTextRatio =view.findViewById(R.id.EditTextRatio);
        EditTextRatio.setInputType(InputType.TYPE_CLASS_NUMBER);
        EditTextRatio.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

                  Toast.makeText(getContext(), ""+ v.getText(), Toast.LENGTH_SHORT).show();
                    newCircle.setRadius(Integer.valueOf(v.getText().toString()));

                    return true;
                }
                return false;
            }});

        tipeSwitch=view.findViewById(R.id.tipeSwitch);
        tipezone=view.findViewById(R.id.tipezone);
        tipeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("saveZone"," cuenta de clicks"+countifedited);
             if(isedited==false) {

                        if (isChecked) {
                            ispoligon = true;
                            mMap.clear();
                            drawmlocations();

                            tipezone.setImageResource(R.drawable.poligons);
                            textView18.setText("Poligono");
                            textViewRatio.setVisibility(View.GONE);
                            EditTextRatio.setVisibility(View.GONE);
                            addnewitemlocation.setVisibility(View.VISIBLE);
                            location.clear();
                            triangleDot1 = new LatLng(mainmarker.getPosition().latitude + .0135000, mainmarker.getPosition().longitude - .0184500);
                            triangleDot2 = new LatLng(mainmarker.getPosition().latitude + .0135000, mainmarker.getPosition().longitude - .0004500);
                            triangleDot3 = new LatLng(mainmarker.getPosition().latitude + .0389000, mainmarker.getPosition().longitude - .0004500);

                            Location newdot0 = new Location(String.valueOf(triangleDot1.latitude), String.valueOf(triangleDot1.longitude));
                            Location newdot1 = new Location(String.valueOf(triangleDot2.latitude), String.valueOf(triangleDot2.longitude));
                            Location newdot2 = new Location(String.valueOf(triangleDot3.latitude), String.valueOf(triangleDot3.longitude));
                            location.add(newdot0);
                            location.add(newdot1);
                            location.add(newdot2);
                            List<LatLng> latLngList = new ArrayList<>();
                            LatLng latLng0 = new LatLng(triangleDot1.latitude, triangleDot1.longitude);
                            LatLng latLng1 = new LatLng(triangleDot2.latitude, triangleDot2.longitude);
                            LatLng latLng2 = new LatLng(triangleDot3.latitude, triangleDot3.longitude);  /**   x   es   y  esta es el superior en el trangulo**/
                            latLngList.add(latLng0);
                            latLngList.add(latLng1);
                            latLngList.add(latLng2);
                            DrawnewPolygon(latLngList, "#6A03A9F4");
                            Adapterdots.notifyDataSetChanged();

                        } else {
                            ispoligon = false;
                            mMap.clear();
                            drawmlocations();

                            tipezone.setImageResource(R.drawable.circles);
                            textView18.setText("Circulo");
                            textViewRatio.setVisibility(View.VISIBLE);
                            EditTextRatio.setVisibility(View.VISIBLE);
                            addnewitemlocation.setVisibility(View.GONE);

                            location.clear();
                            Location newdot = new Location(String.valueOf(mainmarker.getPosition().latitude + .0235000), String.valueOf(mainmarker.getPosition().longitude - .0004500));
                            location.add(newdot);
                            /**modulo de circyulo*/
                            newCircle(mainmarker.getPosition().latitude + .0235000, mainmarker.getPosition().longitude - .0004500, 1000);
                            Adapterdots.notifyDataSetChanged();
                        }
                    }else
             {

                 countifedited=countifedited+1;
                 if(countifedited==1)
                 {
                     saveonedited=true;
                     Log.e("saveZone1"," 1 "+countifedited);
                    if(isChecked)
                    {
                        Log.e("saveZone1"," "+isChecked+"  "+truefalseCircles);
                        ispoligon=true;
                        if(truefalseCircles==true&&ispoligon&&isChecked)
                        {
                            ispoligon = true;
                            mMap.clear();
                            drawmlocations();
                            nlocation = new ArrayList<>();
                            tipezone.setImageResource(R.drawable.poligons);
                            textView18.setText("Poligono");
                            textViewRatio.setVisibility(View.GONE);
                            EditTextRatio.setVisibility(View.GONE);
                            addnewitemlocation.setVisibility(View.VISIBLE);
                            nlocation.clear();
                            triangleDot1 = new LatLng(mainmarker.getPosition().latitude + .0135000, mainmarker.getPosition().longitude - .0184500);
                            triangleDot2 = new LatLng(mainmarker.getPosition().latitude + .0135000, mainmarker.getPosition().longitude - .0004500);
                            triangleDot3 = new LatLng(mainmarker.getPosition().latitude + .0389000, mainmarker.getPosition().longitude - .0004500);

                            Location newdot0 = new Location(String.valueOf(triangleDot1.latitude), String.valueOf(triangleDot1.longitude));
                            Location newdot1 = new Location(String.valueOf(triangleDot2.latitude), String.valueOf(triangleDot2.longitude));
                            Location newdot2 = new Location(String.valueOf(triangleDot3.latitude), String.valueOf(triangleDot3.longitude));
                            nlocation.add(newdot0);
                            nlocation.add(newdot1);
                            nlocation.add(newdot2);
                            List<LatLng> latLngList = new ArrayList<>();
                            LatLng latLng0 = new LatLng(triangleDot1.latitude, triangleDot1.longitude);
                            LatLng latLng1 = new LatLng(triangleDot2.latitude, triangleDot2.longitude);
                            LatLng latLng2 = new LatLng(triangleDot3.latitude, triangleDot3.longitude);  /**   x   es   y  esta es el superior en el trangulo**/
                            latLngList.add(latLng0);
                            latLngList.add(latLng1);
                            latLngList.add(latLng2);
                            EditPolygon(latLngList, "#6A03A9F4",nlocation);
                            Adapterdots=new dotsAdapter(mainView,nlocation,getContext());
                            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                            rvdots.setLayoutManager(layoutManager);
                            rvdots.setAdapter(Adapterdots);
                            Adapterdots.notifyDataSetChanged();
                        }else
                        {
                            editCurrentZone(poligonTyp, positionE, locationE, nameZoneEdited);
                        }
                    }else
                    {
                        Log.e("saveZone1"," "+isChecked+"  "+truefalseCircles);
                        ispoligon=false;
                    }

                 }
                 else if(countifedited==2)
                 {
                     Log.e("saveZone1"," "+countifedited);
                     countifedited=0;
                     if(isChecked)
                     {
                         Log.e("saveZone1"," "+isChecked+"  "+truefalseCircles);
                         ispoligon=true;
                     }else
                     {
                         Log.e("saveZone1f"," "+isChecked+"  "+truefalseCircles);
                         ispoligon=false;
                         if(truefalseCircles==true) {
                             editCurrentZone(poligonTyp, positionE, locationE, nameZoneEdited);
                         }else
                         {
                            mMap.clear();
                            drawmlocations();

                            tipezone.setImageResource(R.drawable.circles);
                            textView18.setText("Circulo");
                            textViewRatio.setVisibility(View.VISIBLE);
                            EditTextRatio.setVisibility(View.VISIBLE);
                            addnewitemlocation.setVisibility(View.GONE);

                            nlocation.clear();
                            Location newdot = new Location(String.valueOf(locationE.get(0).getLatitud()), String.valueOf(locationE.get(0).getLongitud()));
                            nlocation.add(newdot);
                            /**modulo de circyulo*/
                             editCircle(Double.parseDouble(nlocation.get(0).getLatitud()), Double.parseDouble(nlocation.get(0).getLongitud()), 1000,nlocation);
                             Adapterdots=new dotsAdapter(mainView,nlocation,getContext());
                             LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                             rvdots.setLayoutManager(layoutManager);
                             rvdots.setAdapter(Adapterdots);
                            Adapterdots.notifyDataSetChanged();
                         }

                     }
                 }else
                 {
                     Log.e("saveZone1"," "+countifedited);
                 }

             }
                }
        });
        searchViewZones= (SearchView) view.findViewById(R.id.searchZones);
        searchViewZones.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
                List<datarequesZonas> dataZone = filterZones(dataZones, newText);//List<datarequesZonas> dataZones
                               if(dataZones!=null) {
                                   adapter.setFilter(dataZone);
                                   mMap.clear();
                                   if(dataZone.size()>0)
                                   {
                                       drawzones(dataZone);
                                   }
                                   drawmlocations();
                               }
               return false;
           }});
        searchViewRepartidores = (SearchView) view.findViewById(R.id.searchtrabajadores);
        searchViewRepartidores.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<dataRapartidores> repartidoresFilter =filterRepartidores(listRepartidores,newText);
                if(listRepartidores!=null)
                {
                    adapterR.setFilter(repartidoresFilter);
                    /**falta pintar las motos filtradas*/
                }
                return false;
            }
        });
                                                   /**
        dots=view.findViewById(R.id.dots1);
        dots.setOnClickListener(this);
        edit=view.findViewById(R.id.edit1);
        edit.setOnClickListener(this);
        erase=view.findViewById(R.id.erase1);
        erase.setOnClickListener(this);

        dots.setVisibility(View.VISIBLE);
        edit.setVisibility(View.GONE);
        erase.setVisibility(View.GONE);*/
        bottomSheetSettings();
        bottomSheetSettings2();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

    }

    private void bottomSheetSettings2() {
        bottomSheetBehavior2 = BottomSheetBehavior.from(linearLayoutBSheet2);
        bottomSheetBehavior2.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:

                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mainmarker.getPosition(),12f));
                        mMap.animateCamera(CameraUpdateFactory.scrollBy(0.0f, -500));
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mainmarker.getPosition(),12f));
                        mMap.animateCamera(CameraUpdateFactory.scrollBy(0.0f, 500));
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

                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                       // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mainmarker.getPosition(),12f));
                        mMap.animateCamera(CameraUpdateFactory.scrollBy(0.0f, -500));
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mainmarker.getPosition(),12f));
                         mMap.animateCamera(CameraUpdateFactory.scrollBy(0.0f, 500));
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

//    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
//            ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
//        @Override
//        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//            int fromPosition=viewHolder.getAdapterPosition();
//            int toPosition = target.getAdapterPosition();
//            Collections.swap (moviesList, fromPosition, toPosition);
//            recyclerView.getAdapter().notifyItemMoved (fromPosition, toPosition);
//            return false;
//            return false;
//        }
//
//        @Override
//        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//        }
//    };

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng torreon = new LatLng(25.5393929, -103.4532015);
        drawmlocations();

        //mMap.addMarker(new MarkerOptions().position(torreon).title("Bienvenido a quesipizzas"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(torreon,13f));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12), 2000, null);
    }
    private void drawmlocations()
    {
        LatLng torreon = new LatLng(25.5393929, -103.4532015);
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bluedot);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 50, 50, false);
        mainmarker=mMap.addMarker(new MarkerOptions().position(torreon).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

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
        handler.postDelayed(runnable,10000);
        Log.e("onResume", "OK");



    }

    private void loopHandler() {
        handler.postDelayed(runnable= new Runnable() {
            public void run() {
                iteration=iteration+1;
                presenter2.getEmployes();
                Log.e("motos","cargando ubicacion de motos");
                handler.postDelayed(this,10000);/**este ciclo esta mal revisar el loop de handler*/
            }
        }, 10000);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

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
    public void setZones( List<datarequesZonas> data) {
        this.dataZones=data;
        Log.e("Zonas",""+dataZones.size());
        if(dataZones.size()>0&&dataZones!=null)
        {
            drawzones(dataZones);
            loopHandler();

        }
    }


    private void drawzones(List<datarequesZonas> dataZones) {
        for(int i=0 ;i< dataZones.size();i++)
        {

            Log.e("poligonstart","crea un circulo "+ i +"  "+dataZones.get(i).getLocation().size());

            if(dataZones.get(i).getLocation().size()==1) {
            Circle nC=    mMap.addCircle(new CircleOptions()
                        .center(new LatLng(Double.parseDouble(dataZones.get(i).getLocation().get(0).getLatitud()), Double.parseDouble(dataZones.get(i).getLocation().get(0).getLongitud())))
                        .radius(ratio)
                        .strokeColor(black)
                        .strokeWidth(4)
                        .fillColor(blue).clickable(true));
            nC.setTag(dataZones.get(i).getNombre());
            }
            else
            {

                Log.e("poligonstart","crea un poligono");
                List<Location> mnewlocation=new ArrayList<>();
                List<LatLng> latLngList=new ArrayList<>();
                mnewlocation.clear();
                latLngList.clear();
                for(int j=0;j<dataZones.get(i).getLocation().size();j++)
                {
                    LatLng newP=new LatLng(Double.parseDouble( dataZones.get(i).getLocation().get(j).getLatitud()),Double.parseDouble( dataZones.get(i).getLocation().get(j).getLongitud()));
                    latLngList.add(newP);
                }
                //(latLngList,"#6A03A9F4");
                String myColor="#6A03A9F4";
                int alphaColor = ColorUtils.setAlphaComponent(Color.parseColor(myColor),60);
                Polygon polygon2 = mMap.addPolygon(new PolygonOptions()
                        .addAll(latLngList)
                        .strokeColor(Color.RED)
                        .fillColor(alphaColor));
                polygon2.setStrokeWidth(3);
                polygon2.setStrokeColor(Color.BLACK);
                polygon2.setFillColor(alphaColor);

            }

        }
        mMap.setOnCircleClickListener(new GoogleMap.OnCircleClickListener() {
            @Override
            public void onCircleClick(Circle circle) {
                Toast.makeText(getContext(), ""+ circle.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
        fillRv(dataZones);
    }
    public void mapClearaftertrash()
    {
        mMap.clear();
        drawmlocations();
    }
    public void EditPolygon(List<LatLng> latLngList, String colors, List<Location> Mlocation){//,String ColorPolygon) {
        String myColor=colors;
        int alphaColor = ColorUtils.setAlphaComponent(Color.parseColor(myColor),60);
        Polygon polygon2 = mMap.addPolygon(new PolygonOptions()
                .addAll(latLngList)
                .strokeColor(Color.RED)
                .fillColor(alphaColor));
        polygon2.setStrokeWidth(3);
        polygon2.setStrokeColor(Color.BLACK);
        polygon2.setFillColor(alphaColor);
        for (int i=0;i<Mlocation.size();i++)
        {

            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.target);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, 80, 80, false);
            LatLng offset = new LatLng(Double.parseDouble(Mlocation.get(i).getLatitud()), (Double.parseDouble(Mlocation.get(i).getLongitud())));//LatLng ofset = new LatLng(25.5393929, -103.4532015);
            poligonDot = mMap.addMarker(new MarkerOptions()
                    .position(offset)
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                    .anchor(0.5f,0.5f)
                    .draggable(true))
            ;
            poligonDot.setTag(i);
            mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

                @Override
                public void onMarkerDragStart(Marker marker) {

                }

                @Override
                public void onMarkerDrag(Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(Marker marker) {/***EL PROBLEMA ES POS*/
                    int pos=0;
                    Log.e("ivalue","markrt tag "+marker.getTag());
                    Log.e("valor_Mpoligono","markrt     "+marker.getPosition());
                    Log.e("valor_Mpoligono","offseet    "+offset);
                    pos=Integer.valueOf((Integer) marker.getTag());
                    Log.e("valor_Mpoligono","markerpos "+pos);
                    Location newL=new Location(String.valueOf(marker.getPosition().latitude),String.valueOf(marker.getPosition().longitude));
                    LatLng newP=new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);

                    Mlocation.set(pos,newL);
                    latLngList.set(pos,newP);
                    mMap.clear();
                    drawmlocations();
                    EditPolygon(latLngList,"#6A03A9F4",Mlocation);
                    Adapterdots.notifyDataSetChanged();
//                                    LatLng centerpos = new LatLng(centerDot.getPosition().latitude,centerDot.getPosition().longitude);
//                                    newCircle.setCenter(marker.getPosition());
                }
            });
        }

    }


    public void DrawnewPolygon(List<LatLng> latLngList, String colors){//,String ColorPolygon) {
        String myColor=colors;
        int alphaColor = ColorUtils.setAlphaComponent(Color.parseColor(myColor),60);
        Polygon polygon2 = mMap.addPolygon(new PolygonOptions()
                .addAll(latLngList)
                .strokeColor(Color.RED)
                .fillColor(alphaColor));
        polygon2.setStrokeWidth(3);
        polygon2.setStrokeColor(Color.BLACK);
        polygon2.setFillColor(alphaColor);
        for (int i=0;i<location.size();i++)
        {

            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.target);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, 80, 80, false);
            LatLng offset = new LatLng(Double.parseDouble(location.get(i).getLatitud()), (Double.parseDouble(location.get(i).getLongitud())));//LatLng ofset = new LatLng(25.5393929, -103.4532015);
            poligonDot = mMap.addMarker(new MarkerOptions()
                    .position(offset)
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                    .anchor(0.5f,0.5f)
                    .draggable(true))
            ;
            poligonDot.setTag(i);
            mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

                @Override
                public void onMarkerDragStart(Marker marker) {

                }

                @Override
                public void onMarkerDrag(Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(Marker marker) {/***EL PROBLEMA ES POS*/
                   int pos=0;
                    Log.e("ivalue","markrt tag "+marker.getTag());
                    Log.e("valor_Mpoligono","markrt     "+marker.getPosition());
                    Log.e("valor_Mpoligono","offseet    "+offset);
                    pos=Integer.valueOf((Integer) marker.getTag());
                    Log.e("valor_Mpoligono","markerpos "+pos);
                    Location newL=new Location(String.valueOf(marker.getPosition().latitude),String.valueOf(marker.getPosition().longitude));
                    LatLng newP=new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);

                    location.set(pos,newL);
                    latLngList.set(pos,newP);
                    mMap.clear();
                    drawmlocations();
                    DrawnewPolygon(latLngList,"#6A03A9F4");
                    Adapterdots.notifyDataSetChanged();
//                                    LatLng centerpos = new LatLng(centerDot.getPosition().latitude,centerDot.getPosition().longitude);
//                                    newCircle.setCenter(marker.getPosition());
                }
            });
        }

    }

    public void editCircle(Double lat, Double longitud, int ratio, List<Location> nlocation)
    {
        EditTextRatio.setText(String.valueOf( ratio));
        if(EditTextRatio.getText().toString().isEmpty())
        {
            EditTextRatio.setText("400");
        }
        newCircle= mMap.addCircle(new CircleOptions()
                .center(new LatLng( lat, longitud))
                .radius(ratio)
                .strokeColor(alfa)
                .strokeWidth(0)
                .strokeWidth(Float.valueOf(String.valueOf(EditTextRatio.getText())))
                .fillColor(blue));


        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.target);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 110, 110, false);
        LatLng offset = new LatLng(lat, longitud);//LatLng ofset = new LatLng(25.5393929, -103.4532015);
        centerDot = mMap.addMarker(new MarkerOptions()
                .position(offset)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                .anchor(0.5f,0.5f)
                .draggable(true))
        ;

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng centerpos = new LatLng(centerDot.getPosition().latitude,centerDot.getPosition().longitude);
                newCircle.setCenter(marker.getPosition());
                Location newL=new Location(String.valueOf(marker.getPosition().latitude),String.valueOf(marker.getPosition().longitude));
                nlocation.set(0,newL);
                Adapterdots.notifyDataSetChanged();
            }
        });

    }

    public void newCircle(Double lat,Double longitud,int ratio)
    {
        EditTextRatio.setText(String.valueOf( ratio));
        if(EditTextRatio.getText().toString().isEmpty())
        {
            EditTextRatio.setText("400");
        }
        newCircle= mMap.addCircle(new CircleOptions()
                .center(new LatLng( lat, longitud))
                .radius(ratio)
                .strokeColor(alfa)
                .strokeWidth(0)
                .strokeWidth(Float.valueOf(String.valueOf(EditTextRatio.getText())))
                .fillColor(blue));


        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.target);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 110, 110, false);
        LatLng offset = new LatLng(lat, longitud);//LatLng ofset = new LatLng(25.5393929, -103.4532015);
        centerDot = mMap.addMarker(new MarkerOptions()
                .position(offset)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                .anchor(0.5f,0.5f)
                .draggable(true))
       ;

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng centerpos = new LatLng(centerDot.getPosition().latitude,centerDot.getPosition().longitude);
                newCircle.setCenter(marker.getPosition());
                Location newL=new Location(String.valueOf(marker.getPosition().latitude),String.valueOf(marker.getPosition().longitude));
                location.set(0,newL);
                Adapterdots.notifyDataSetChanged();
            }
        });

    }
    public void zoomtoDot(String latitud, String longitud) {
        Log.e("circle",""+latitud+"   "+longitud);
        LatLng dotpos = new LatLng(Double.parseDouble(latitud), Double.parseDouble(longitud));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dotpos, 14f));
        mMap.animateCamera(CameraUpdateFactory.scrollBy(0.0f, 500));

    }

    public void zoomRepartidor(String latitud, String longitud) {
        LatLng dotpos = new LatLng(Double.parseDouble(latitud), Double.parseDouble(longitud));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dotpos, 17f));
        mMap.animateCamera(CameraUpdateFactory.scrollBy(0.0f, 500));
    }
    private void fillRv(List<datarequesZonas> dataZones) {
        adapter= new  zonesAdapter(this, dataZones,getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

    }
    private  void fillRVrepartidores(List<dataRapartidores> listRepartidores)
    {
        adapterR=new repartidoresAdapter(this,listRepartidores,getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rvRepartidores.setLayoutManager(layoutManager);
        rvRepartidores.setAdapter(adapterR);
    }
    private void fillDosts(boolean isnew) {

        if (this.isnew = true){
            location.clear();
            Location loc = new Location(String.valueOf(mainmarker.getPosition().latitude+.0015000), String.valueOf(mainmarker.getPosition().longitude-.0004500));
            location.add(loc);
            Adapterdots=new dotsAdapter(this,location,getContext());
            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
            rvdots.setLayoutManager(layoutManager);
            rvdots.setAdapter(Adapterdots);
            }

    }


    @Override
    public void showProgressDialog() {
        progressDialog.setMessage("Cargando Zonas");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void reloadScreen() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        zonasViewImpl completado= new zonasViewImpl();
        transaction.replace(R.id.ordenarViewImpl, completado, completado.TAG).commit();
    }

    @Override
    public void setDrivers(List<dataRapartidores> repartidores) {

        this.listRepartidores=repartidores;
        repartidores();
        fillRVrepartidores(listRepartidores);

    }
    private void repartidores()
    {
        if(listRepartidores!=null)
        {

            for(int i=0;i<listRepartidores.size();i++)
            {

                    Log.e("repartidoresV", "" + listRepartidores.get(i).getNombre());


                    LatLng driver = new LatLng(Double.parseDouble(listRepartidores.get(i).getLatitud()), Double.parseDouble(listRepartidores.get(i).getLongitud()));
                    BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.do1);
                    Bitmap b = bitmapdraw.getBitmap();
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, 180, 180, false);

                    if (mMap != null) {
                        //mainIconMarker
                        if(mainIconMarkers.size()!=listRepartidores.size()) {
                           // mainIconMarker = mMap.addMarker(new MarkerOptions().position(driver).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                          //  mainIconMarker.setTag(listRepartidores.get(i).getId());

                            if(mainIconMarkers.isEmpty())
                            {

                                mainIconMarkers.add(mMap.addMarker(new MarkerOptions().position(driver).icon(BitmapDescriptorFactory.fromBitmap(smallMarker))));
                                mainIconMarkers.get(0).setTag(listRepartidores.get(i).getId());
                                historicdrivers.add(new historicData(iteration,listRepartidores.get(i).getId(),Double.parseDouble(listRepartidores.get(i).getLatitud()), Double.parseDouble(listRepartidores.get(i).getLongitud())));
                            }else{
                                for(int k=0;k<mainIconMarkers.size();k++)
                                {
                                    if (mainIconMarkers.get(k).getTag().equals(listRepartidores.get(i).getId())) {

                                        mainIconMarker.setPosition(new LatLng(Double.parseDouble(listRepartidores.get(i).getLatitud()), Double.parseDouble(listRepartidores.get(i).getLongitud())));
                                        mainIconMarkers.set(k,mMap.addMarker(new MarkerOptions().position(driver).icon(BitmapDescriptorFactory.fromBitmap(smallMarker))));
                                        mMap.addMarker(new MarkerOptions().position(mainIconMarkers.get(k).getPosition()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                                    } else {
                                        Marker newMarker=mMap.addMarker(new MarkerOptions().position(driver).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                                        newMarker.setTag(listRepartidores.get(i).getId());
                                        mainIconMarkers.add(newMarker);
                                        historicdrivers.add(new historicData(iteration,listRepartidores.get(i).getId(),Double.parseDouble(listRepartidores.get(i).getLatitud()), Double.parseDouble(listRepartidores.get(i).getLongitud())));
                                        Log.e("motoslista",  "   "+k+"  "+i+"   " + mainIconMarkers.size());
                                        break;
                                    }
                                }
                            }
                        }else
                        {
                            for(int k=0;k<mainIconMarkers.size();k++) {
                                if (mainIconMarkers.get(k).getTag().equals(listRepartidores.get(i).getId())) {
                                    historicdrivers.add(new historicData(iteration,listRepartidores.get(i).getId(),Double.parseDouble(listRepartidores.get(i).getLatitud()), Double.parseDouble(listRepartidores.get(i).getLongitud())));
                                      /**manejar que se mueva lento la moto*/
                                    mainIconMarkers.get(k).setPosition(new LatLng(Double.parseDouble(listRepartidores.get(i).getLatitud()), Double.parseDouble(listRepartidores.get(i).getLongitud())));


                                }
                            }
                       //     Log.e("motoslista", "" + mainIconMarkers.size());

                        }


                    }
                }
            Log.e("motoslista1", "" + historicdrivers.size());
            }

        }


    private  List<dataRapartidores> filterRepartidores(List<dataRapartidores> dataRapartidores,String text)
    {
        List<dataRapartidores> filterRepartidores=new ArrayList<>();
        text =  text.toLowerCase();
        if(dataRapartidores!=null){
            for(dataRapartidores repartidor: dataRapartidores)
            {
                if(repartidor.getNombre()!=null)
                {
                    String repartidorName=repartidor.getNombre().toLowerCase();
                    if(repartidorName!=null)
                    {
                        if(repartidorName.contains(text))
                        {
                            filterRepartidores.add(repartidor);
                        }
                    }
                }
            }

        }
        return filterRepartidores;
    }

    private List<datarequesZonas> filterZones(List<datarequesZonas> dataZones, String text){
        List<datarequesZonas> visitedDataList = new ArrayList<>();
        text =  text.toLowerCase();
        if (dataZones != null){
            for (datarequesZonas zones : dataZones){
                if (zones.getNombre() != null){//
                    String zoneName = zones.getNombre().toLowerCase();
                    if (zoneName != null) {
                        if (zoneName.contains(text)) {
                            visitedDataList.add(zones);
                        }
                    }
                }
            }
        }
        return visitedDataList;
    }

 private  void firstCircles()
 {
     tipezone.setImageResource(R.drawable.circles);
     textView18.setText("Circulo");
     textViewRatio.setVisibility(View.VISIBLE);
     EditTextRatio.setVisibility(View.VISIBLE);
     addnewitemlocation.setVisibility(View.GONE);

     location.clear();
     Location newdot=new Location(String.valueOf(mainmarker.getPosition().latitude+.0235000), String.valueOf(mainmarker.getPosition().longitude-.0004500));
     location.add(newdot);
     /***/
     newCircle(mainmarker.getPosition().latitude+.0235000, mainmarker.getPosition().longitude-.0004500,1000);
     Adapterdots.notifyDataSetChanged();
 }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.eye_zones:
                if(valueeye==true)
                {
                    valueeye=false;
                    eye.setImageResource(R.drawable.ic_eye_close);
                    mMap.clear();
                    drawmlocations();
                    repartidores();
                }
                else
                {
                    valueeye=true;
                    eye.setImageResource(R.drawable.ic_eye);
                    mMap.clear();
                    if(dataZones.size()>0)
                    {
                        drawzones(dataZones);
                    }
                    drawmlocations();
                    repartidores();
                }
                break;
            case R.id.driver_zones:
                if(valuedriver==true)
                {
                    valuedriver=false;
                    driver.setImageResource(R.drawable.ic_driver_hide);
                    mMap.clear();
                    if(dataZones.size()>0)
                    {
                        drawzones(dataZones);
                    }
                    drawmlocations();
                }
                else
                {
                    valuedriver=true;
                    driver.setImageResource(R.drawable.ic_driver);
                    mMap.clear();
                    if(dataZones.size()>0)
                    {
                        drawzones(dataZones);
                    }
                    drawmlocations();
                    repartidores();
                }
                break;
            case R.id.zoneB:
//                txtUbicacion.setTextColor(getResources().getColor(R.color.white));
//                txtRestaurante.setTextColor(getResources().getColor(R.color.black));
//                ensucursal.setCardBackgroundColor(getResources().getColor(R.color.alfa));
//                ensucursal.setCardElevation(0);
                zoneB.setCardBackgroundColor(getResources().getColor(R.color.colorRed));
                empleadosB.setCardBackgroundColor(getResources().getColor(R.color.alfa));
                txtZonas.setTextColor(getResources().getColor(R.color.white));
                txtempleados.setTextColor(getResources().getColor(R.color.black));
                    linearLayoutBSheet2.setVisibility(View.GONE);
                    linearLayoutBSheet.setVisibility(View.VISIBLE);
                break;
            case R.id.empleadosB:
                empleadosB.setCardBackgroundColor(getResources().getColor(R.color.colorRed));
                zoneB.setCardBackgroundColor(getResources().getColor(R.color.alfa));
                zoneB.setCardElevation(0);
                txtempleados.setTextColor(getResources().getColor(R.color.white));
                txtZonas.setTextColor(getResources().getColor(R.color.black));
                    linearLayoutBSheet.setVisibility(View.GONE);
                    linearLayoutBSheet2.setVisibility(View.VISIBLE);


                break;
            case R.id.addzone:
//                rv.setVisibility(View.GONE);
//                addzone.setVisibility(View.GONE);
//                searchbar.setVisibility(View.GONE);
//                constraineditcreateZone.setVisibility(View.VISIBLE);
//                nameEstablecimiento1.setText("Nombre: ");
//                isnew=true;
//                fillDosts(isnew);
//                mMap.clear();
//
//                drawmlocations();
//                tipeSwitch.setChecked(false);
//                firstCircles();

                Toast.makeText(getContext(), "Pendiente definicion endpoint agregar Zona", Toast.LENGTH_LONG).show();
                break;
            case R.id.cancelarnewzones:
                countifedited=0;
                tipeSwitch.setChecked(false);
                if(newCircle!=null){
                newCircle.remove();}
                if(centerDot!=null){
                centerDot.remove();}
                location.clear();
                Adapterdots.notifyDataSetChanged();
                mMap.clear();
                rv.setVisibility(View.VISIBLE);
                addzone.setVisibility(View.VISIBLE);
                searchbar.setVisibility(View.VISIBLE);
                constraineditcreateZone.setVisibility(View.GONE);

                if(dataZones.size()>0)
                {
                    drawzones(dataZones);
                }
                drawmlocations();

                isedited=false;
                countifedited=0;
                truefalseCircles=false;
                nameZoneEdited="";
                break;
            case R.id.buttonsave:

                if(saveonedited==true){
                    Log.e("saveZone2","edited id "+coloniaIDedited+"  name"+nameColoniaEdited+" location size "+location.size()+" ispoligon   "+ispoligon+"  truefalseCircles  "+truefalseCircles);

                    /**AQUI VA EL PRESENTER CON EL ENVIO DE LA ZONA EDITADA*/
                    if(ispoligon) {
                        if(truefalseCircles==true){
                            List<Zona> zonas = new ArrayList<>();
                            zonas.clear();
                            List<Puntos> updatedots = new ArrayList<>();
                            updatedots.clear();
                            for (int i = 0; i < nlocation.size(); i++) {
                                Puntos dots = new Puntos(nlocation.get(i).getLatitud(), nlocation.get(i).getLongitud());
                                updatedots.add(dots);

                            }
                            Zona updateZone = new Zona(coloniaIDedited, nameColoniaEdited, updatedots);
                            zonas.add(updateZone);
                            presenter.saveDotsZone(zonas);
                        }else {
                            List<Zona> zonas = new ArrayList<>();
                            zonas.clear();
                            List<Puntos> updatedots = new ArrayList<>();
                            updatedots.clear();
                            for (int i = 0; i < location.size(); i++) {
                                Puntos dots = new Puntos(location.get(i).getLatitud(), location.get(i).getLongitud());
                                updatedots.add(dots);

                            }
                            Zona updateZone = new Zona(coloniaIDedited, nameColoniaEdited, updatedots);
                            zonas.add(updateZone);
                            presenter.saveDotsZone(zonas);
                        }
                    }else
                    {
                        List<Zona> zonas=new ArrayList<>();
                    zonas.clear();
                    List<Puntos> updatedots=new ArrayList<>();
                    updatedots.clear();
                    Puntos dots=new Puntos(location.get(0).getLatitud(),location.get(0).getLongitud());
                    updatedots.add(dots);
                    Zona updateZone=new Zona(coloniaIDedited,nameColoniaEdited,updatedots);
                    zonas.add(updateZone);
                    presenter.saveDotsZone(zonas);
                    }
                    saveonedited=false;
                    countifedited=0;
                    truefalseCircles=false;

                }else
                {
                    Log.e("saveZone1","save new zone");
                }
                nameZoneEdited="";
                break;
            case R.id.addnewitemlocation:
                if(isedited==true){
                    if(truefalseCircles) {
                        Log.e("saveZone1","nuevo punto circulo a poligono");


                        mMap.clear();
                        drawmlocations();
                        List<LatLng> latLngList = new ArrayList<>();
                        latLngList.clear();
                        Location newdot = new Location(String.valueOf(mainmarker.getPosition().latitude), String.valueOf(mainmarker.getPosition().longitude));
                        nlocation.add(newdot);
                        double multiplo_distancia = .01 ;//* (nlocation.size() - 3);
                        int tamaño=nlocation.size()-1;
                        for (int i = 0; i < nlocation.size(); i++) {
                            if (i ==tamaño) {
                                LatLng latLngN = new LatLng(Double.parseDouble(nlocation.get(i - 1).getLatitud()), Double.parseDouble(nlocation.get(i - 1).getLongitud()) - multiplo_distancia);/**/
                                latLngList.add(latLngN);
                                Location nL = new Location(String.valueOf(nlocation.get(i - 1).getLatitud()), String.valueOf(Double.parseDouble(nlocation.get(i - 1).getLongitud()) - multiplo_distancia));
                                nlocation.set(i, nL);
                            } else {
                                LatLng latLngN = new LatLng(Double.parseDouble(nlocation.get(i).getLatitud()), Double.parseDouble(nlocation.get(i).getLongitud()));/**/
                                latLngList.add(latLngN);
                                Location nL = new Location(String.valueOf(nlocation.get(i).getLatitud()), String.valueOf(Double.parseDouble(nlocation.get(i).getLongitud())));
                            }


                        } EditPolygon(latLngList, "#6A03A9F4",nlocation);


                    }else{
                        Log.e("saveZone1","nuevo punto edit poligon");

                    mMap.clear();
                    drawmlocations();
                    List<LatLng> latLngList = new ArrayList<>();
                    latLngList.clear();
                    Location newdot = new Location(String.valueOf(mainmarker.getPosition().latitude), String.valueOf(mainmarker.getPosition().longitude));
                    location.add(newdot);
                    double multiplo_distancia = .01 * (location.size() - 3);
                    int tamaño=location.size()-1;
                    for (int i = 0; i < location.size(); i++) {
                            if (i ==tamaño) {
                                LatLng latLngN = new LatLng(Double.parseDouble(location.get(i - 1).getLatitud()), Double.parseDouble(location.get(i - 1).getLongitud()) - multiplo_distancia);/**/
                                latLngList.add(latLngN);
                                Location nL = new Location(String.valueOf(location.get(i - 1).getLatitud()), String.valueOf(Double.parseDouble(location.get(i - 1).getLongitud()) - multiplo_distancia));
                                location.set(i, nL);
                            } else {
                                LatLng latLngN = new LatLng(Double.parseDouble(location.get(i).getLatitud()), Double.parseDouble(location.get(i).getLongitud()));/**/
                                latLngList.add(latLngN);
                                Location nL = new Location(String.valueOf(location.get(i).getLatitud()), String.valueOf(Double.parseDouble(location.get(i).getLongitud())));
                            }


                    } DrawnewPolygon(latLngList, "#6A03A9F4");
                    }
                }else {
                    if (ispoligon) {
                        //region newpoligon
                        mMap.clear();
                        drawmlocations();


                        Location newdot = new Location(String.valueOf(mainmarker.getPosition().latitude), String.valueOf(mainmarker.getPosition().longitude));
                        location.add(newdot);
                        double multiplo_distancia = 0.0000;
                        List<LatLng> latLngList = new ArrayList<>();
                        latLngList.clear();
                        if (location.size() > 3) {
                            multiplo_distancia = .01 * (location.size() - 3);
                            Log.e("valor_Mpoligono", "" + multiplo_distancia);
                            for (int i = 0; i < location.size(); i++) {
                                int position = i;
                                if (i == 0 && triangleDot1.latitude == Double.parseDouble(location.get(i).getLatitud()))/** el and es para que se genere el poligono primordial**/ {
                                    Log.e("ivalue", i + " 1 " + triangleDot1.latitude + "    " + Double.parseDouble(location.get(i).getLatitud()));
                                    LatLng latLng0 = new LatLng(triangleDot1.latitude, triangleDot1.longitude);
                                    latLngList.add(0, latLng0);
                                } else if (i == 1 && triangleDot2.latitude == Double.parseDouble(location.get(i).getLatitud())) {
                                    Log.e("ivalue", i + " 2 " + triangleDot2.latitude + "    " + Double.parseDouble(location.get(i).getLatitud()));
                                    LatLng latLng1 = new LatLng(triangleDot2.latitude, triangleDot2.longitude);
                                    latLngList.add(1, latLng1);
                                } else if (i == 2 && triangleDot3.latitude == Double.parseDouble(location.get(i).getLatitud())) {
                                    Log.e("ivalue", i + " 3 " + triangleDot3.latitude + "    " + Double.parseDouble(location.get(i).getLatitud()));
                                    LatLng latLng2 = new LatLng(triangleDot3.latitude, triangleDot3.longitude);
                                    latLngList.add(2, latLng2);
                                } else {
                                    if (i == 0) {
                                        Log.e("ivalue", i + " n0 " + triangleDot2.latitude + "    " + Double.parseDouble(location.get(i).getLatitud()));
                                        LatLng latLngN = new LatLng(Double.parseDouble(location.get(i).getLatitud()), Double.parseDouble(location.get(i).getLongitud()));/**/
                                        latLngList.add(latLngN);
                                        Location nL = new Location(String.valueOf(location.get(i).getLatitud()), String.valueOf(Double.parseDouble(location.get(i).getLongitud())));
                                        if (i == location.size() - 1) {
                                            location.set(i, nL);
                                        }
                                    } else {


                                        if (i == location.size() - 1) {
                                            Log.e("ivalue", i + " n1 " + triangleDot2.latitude + "    " + Double.parseDouble(location.get(i).getLatitud()));
                                            LatLng latLngN = new LatLng(Double.parseDouble(location.get(i - 1).getLatitud()), Double.parseDouble(location.get(i - 1).getLongitud()) - multiplo_distancia);/**/
                                            latLngList.add(latLngN);
                                            Location nL = new Location(String.valueOf(location.get(i - 1).getLatitud()), String.valueOf(Double.parseDouble(location.get(i - 1).getLongitud()) - multiplo_distancia));
                                            location.set(i, nL);
                                        } else {
                                            Log.e("ivalue", i + " m1 " + triangleDot2.latitude + "    " + Double.parseDouble(location.get(i).getLatitud()));
                                            LatLng latLngN = new LatLng(Double.parseDouble(location.get(i).getLatitud()), Double.parseDouble(location.get(i).getLongitud()));/**/
                                            latLngList.add(latLngN);
                                            Location nL = new Location(String.valueOf(location.get(i).getLatitud()), String.valueOf(Double.parseDouble(location.get(i).getLongitud())));
                                        }
                                    }
                                }
                            }
                            Log.e("valor_Mpoligono", "" + latLngList);
                            DrawnewPolygon(latLngList, "#6A03A9F4");
                        }
                        //enregion


                        Log.e("newDot", "nuevo punto en poligono");
                    } else {/**este caso no se ejecuta ya que el simbolo de mas se oculta para circulos*/
                        // Toast.makeText(getContext(), "agregarnuevo punto", Toast.LENGTH_SHORT).show();
                        Location newdot = new Location(String.valueOf(mainmarker.getPosition().latitude), String.valueOf(mainmarker.getPosition().longitude));
                        location.add(newdot);
                    }

                }
                Adapterdots.notifyDataSetChanged();
                break;
        }
    }
public void eraseZone(int id)
{
    //Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();
    Log.e("eliminarZona",""+id);

    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom);
    builder.setTitle("¿Seguro que quieres eliminar la zona?");
    builder.setMessage("Esto eliminara los puntos y asignaciones a la zona");
    builder.setCancelable(true);
    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Log.e("eliminarZona",""+id);
           Toast.makeText(getContext(), "autorizar habilitar eliminar Zona con id: "+id, Toast.LENGTH_LONG).show();
           //presenter.eraseZona(id);
        }
    });
    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    });
    builder.create();
    builder.show();
}

    public void editCurrentZone(boolean poligonType, int position, List<Location> locationE, String nombre) {
       // Toast.makeText(getContext(), "edit dots V", Toast.LENGTH_SHORT).show();

        this.locationE=locationE;
        this.poligonTyp=poligonType;
        this.nameZoneEdited=nombre;
        this.positionE=position;
        coloniaIDedited=dataZones.get(position).getColoniaID();
        nameColoniaEdited=dataZones.get(position).getNombre();
        isedited=true;
        rv.setVisibility(View.GONE);
        addzone.setVisibility(View.GONE);
        constraineditcreateZone.setVisibility(View.VISIBLE);
        nameEstablecimiento1.setText(nameZoneEdited);
        isnew=false;

        mMap.clear();
        drawmlocations();
        searchbar.setVisibility(View.GONE);
        tipeSwitch.setChecked(poligonType);

        if(poligonType==false) {
            truefalseCircles=true;
            tipeSwitch.setChecked(true);
            tipeSwitch.setChecked(false);
            ispoligon=false;
            mMap.clear();
            drawmlocations();
            tipezone.setImageResource(R.drawable.circles);
            textView18.setText("Circulo");
            textViewRatio.setVisibility(View.VISIBLE);
            EditTextRatio.setVisibility(View.VISIBLE);
            addnewitemlocation.setVisibility(View.GONE);
            location.clear();
            Location newdot=new Location(String.valueOf(locationE.get(0).getLatitud()), String.valueOf(locationE.get(0).getLongitud()));
            location.add(newdot);

            newCircle(Double.parseDouble( location.get(0).getLatitud()), Double.parseDouble( location.get(0).getLongitud()),1000);
            Adapterdots=new dotsAdapter(this,location,getContext());
            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
            rvdots.setLayoutManager(layoutManager);
            rvdots.setAdapter(Adapterdots);
            Adapterdots.notifyDataSetChanged();
        }else if(poligonType==true)
        {
            truefalseCircles=false;
            ispoligon=true;
            mMap.clear();
            drawmlocations();

            tipezone.setImageResource(R.drawable.poligons);
            textView18.setText("Poligono");
            textViewRatio.setVisibility(View.GONE);
            EditTextRatio.setVisibility(View.GONE);
            addnewitemlocation.setVisibility(View.VISIBLE);
            location.clear();

            if(locationE.size()>0)
            {
                List<LatLng> latLngList=new ArrayList<>();
                for(int i=0;i<locationE.size();i++)
                {
                    Location newdot=new Location(locationE.get(i).getLatitud(),locationE.get(i).getLongitud());
                    location.add(newdot);
                    LatLng latLngN=new LatLng(Double.parseDouble(locationE.get(i).getLatitud()),Double.parseDouble(locationE.get(i).getLongitud()));
                    latLngList.add(latLngN);
                }
                DrawnewPolygon(latLngList,"#6A03A9F4");
                Adapterdots=new dotsAdapter(this,location,getContext());
                LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                rvdots.setLayoutManager(layoutManager);
                rvdots.setAdapter(Adapterdots);
                Adapterdots.notifyDataSetChanged();
            }

        }


    }

}