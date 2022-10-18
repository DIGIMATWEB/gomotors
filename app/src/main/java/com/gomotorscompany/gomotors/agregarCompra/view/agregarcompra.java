package com.gomotorscompany.gomotors.agregarCompra.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.Ordenar.model.newmenu.Complemento;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.agregarCompra.adapter.adaptercomplementos;
import com.gomotorscompany.gomotors.agregarCompra.model.Complementoold;
import com.gomotorscompany.gomotors.mainContent.view.mainContentViewImpl;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class agregarcompra extends AppCompatActivity  implements View.OnClickListener{
    public static final String TAG = agregarcompra.class.getSimpleName();

    private BottomSheetBehavior bottomSheetBehavior;
    private ConstraintLayout linearLayoutBSheet;
    private adaptercomplementos adapter;
    private RecyclerView rv;
    private List<Complemento> comp;
    private Button buttonaddcompra;
    private TextView texproducto,textdescproducto,precioAgregar;
    private String nombre,descripcion,precio,clave,categoria,direccionentrega,nombresucursal,imagen;
    private String idsucursal;
    private Double latitude,longitude;
    private List<Complementoold>  listacomplementos=new ArrayList<>();
    private TextView numberProducts;
    private ImageView bakgrounproduct,closeagregar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityagregar);
        Bundle bndl;
        bndl = getIntent().getExtras();

        direccionentrega=bndl.getString("direccionentrega");
        idsucursal=bndl.getString("idsucursal");
        nombresucursal=bndl.getString("nombresucursal");
        latitude=bndl.getDouble("latcliente");
        longitude=bndl.getDouble("longcliente");
        categoria= bndl.getString("categoria");

        clave = bndl.getString("clave");
        nombre= bndl.getString("nombre");
        precio  = bndl.getString("precio");
        descripcion = bndl.getString("descripcion");
       imagen = bndl.getString("imagen");
    /*

       // Serializable complementos = bndl.getSerializable("Complementos");
    */

        comp = (List<Complemento>) bndl.getSerializable("Complementos");
      //  Log.e("mainActivityInteraction", "directo al activity 23 " + clave + "   " + nombre + "   " + precio + "  " + descripcion + "   " + imagen + "   S" + comp.size() + "   " + comp.get(0).getNombre());

        initView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_animation, R.anim.slide_down);
    }

    private void initView() {
        listacomplementos.clear();
        linearLayoutBSheet=findViewById(R.id.bottomSheetadd);
        buttonaddcompra=findViewById(R.id.buttonaddcompra);
        texproducto=findViewById(R.id.texproducto);
        textdescproducto=findViewById(R.id.textdescproducto);
        precioAgregar=findViewById(R.id.precioAgregar);
        bakgrounproduct=findViewById(R.id.bakgrounproduct);
        closeagregar=findViewById(R.id.closeagregar);
        closeagregar.setOnClickListener(this);
        texproducto.setText( nombre);
        textdescproducto.setText( descripcion);
        Glide.with(getApplicationContext()).load(imagen).into(bakgrounproduct);
        buttonaddcompra.setOnClickListener(this);

        numberProducts=findViewById(R.id.countitemsagregar);

        bottomSheetSettings();

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        }, 500);

        rv=findViewById(R.id.rvcomplementos);
        fillrv();
        String valorcorregido=precio.substring(1);
        precioAgregar.setText(valorcorregido);

    }

    private void fillrv() {
                adapter=new adaptercomplementos(this,comp,this.getApplicationContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(adapter);
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

                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;

                }



            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
    public void checkColection(List<Complementoold> complementosdata) {
        Log.e("bundleComplementos"," data "+ complementosdata);
    }
    public void setalldataprices(List<Complementoold>  complementosdata) {
        List<Complementoold> newlistacomplementos = new ArrayList<>();
        newlistacomplementos.clear();
      if (complementosdata != null) {
                  for (int k = 0; k < complementosdata.size(); k++) {
                    if (complementosdata.get(k).getCantidad() != 0) {
                        if(listacomplementos.isEmpty())
                        {
                            listacomplementos.add(complementosdata.get(k));
                        }else{
                        for (int l = 0; l < listacomplementos.size(); l++) {
                            if (listacomplementos.get(l).getSkuComplemento().equals(complementosdata.get(k).getSkuComplemento()) && listacomplementos.get(l).getCantidad() != complementosdata.get(k).getCantidad()) {
                                Log.e("1listacomplementos", "si lo contiene");
                                listacomplementos.get(l).setCantidad(complementosdata.get(k).getCantidad());
                                break;
                            } else if (listacomplementos.get(l).getSkuComplemento().equals(complementosdata.get(k).getSkuComplemento()) && listacomplementos.get(l).getCantidad() == complementosdata.get(k).getCantidad()) {
                                Log.e("1listacomplementos", "no lo modificas  a "+listacomplementos.get(l));
                                break;
                            } else {

                                if (listacomplementos.get(l).getSkuComplemento().equals(complementosdata.get(k).getSkuComplemento()))
                                {
                                    Log.e("1listacomplementos", "no hagas nada ");
                                }
//                                else if(listacomplementos.get(l).getCantidad() != complementosdata.get(k).getCantidad())
//                                {
//                                    Log.e("1listacomplementos", "caso set ");
//                                    listacomplementos.get(l).setCantidad(complementosdata.get(k).getCantidad());
//                                }
                                else if(listacomplementos.get(l).getCantidad() == complementosdata.get(k).getCantidad())
                                {
                                    Log.e("1listacomplementos", "no hagas nada caso 2 ");
                                }else if(listacomplementos.contains(complementosdata.get(k)))
                                {
                                    Log.e("1listacomplementos", "no hagas nada caso 3 ");
                                }
                                else
                                {
                                Log.e("1listacomplementos", "no lo contiene agregalo " + complementosdata.get(k).getSkuComplemento());
                                listacomplementos.add(complementosdata.get(k));
                                }
                                break;


                            }
                        }}
                    }
                }



          }
        for (int l = 0; l < listacomplementos.size(); l++) {
            Log.e("1listacomplementos", " FINAL " + " size: "
                    + listacomplementos.size() + "  "
                    + listacomplementos.get(l).getSkuComplemento() + " "
                    + listacomplementos.get(l).getCantidad() + " "
                    + listacomplementos.get(l).getStatus() + "  "
                    + listacomplementos.get(l).getCategoria());//i
        }

    }




    public void addtofinalValue(Double valorAgregado) {
        Log.e("bundlemainmenu"," valorAgregado "+ valorAgregado);
        String preciocorregido=precio.substring(1);
        Double precioFinal=Double.valueOf( preciocorregido);
        precioFinal=precioFinal+valorAgregado;

        DecimalFormat df = new DecimalFormat("###.##");

        precioAgregar.setText(String.valueOf( df.format(precioFinal)));
        precio= "$"+String.valueOf(df.format(precioFinal));
        Log.e("bundlemainmenu"," valorAgregado "+ precioAgregar.getText());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.closeagregar:
                onBackPressed();
                overridePendingTransition(R.anim.no_animation, R.anim.slide_down);
            break;
            case R.id.buttonaddcompra:
            //    Toast.makeText(getApplicationContext(), "add", Toast.LENGTH_SHORT).show();


                Bundle bundleorder = new Bundle();
                bundleorder.putString("nombreP", nombre);
                bundleorder.putString("descripcion", descripcion);
                bundleorder.putString("costofinal",String.valueOf(precioAgregar.getText()));
                bundleorder.putString("idsucursal",idsucursal);
                bundleorder.putString("nombresucursal",nombresucursal);
                bundleorder.putString("carritoDireccionentrega",direccionentrega);
                bundleorder.putDouble("carritolatitude",latitude);
                bundleorder.putDouble("carritolongitud",longitude);
                bundleorder.putString("carritocategoria",categoria);
                bundleorder.putString("porductos", clave);
                bundleorder.putSerializable("datalistacomplementos", (Serializable) listacomplementos);
                Intent intent = new Intent(this, mainContentViewImpl.class);
                intent.putExtras(bundleorder);

                startActivity(intent);

                break;
        }
    }

//
//        bundleorder.putString("direccionentrega", ubicacion);
//        bundleorder.putString("idsucursal", idsuc);
//        bundleorder.putString("nombresucursal", sucursalnam);
//        bundleorder.putDouble("latcliente", latitude);
//        bundleorder.putDouble("longcliente", longitude);
//        bundleorder.putString("categoria", category);


}

