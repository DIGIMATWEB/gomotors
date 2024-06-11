package com.gomotorscompany.gomotors.enprodresodetail.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.gomotorscompany.gomotors.Dialogs.mapswaze.view.wazemaps;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.enprodresodetail.adapter.sectionsAdapter;
import com.gomotorscompany.gomotors.enprodresodetail.presenter.progresdetailpresenter;
import com.gomotorscompany.gomotors.enprodresodetail.presenter.progresdetailpresenterImpl;
import com.gomotorscompany.gomotors.mainContent.view.mainContentViewImpl;
import com.gomotorscompany.gomotors.miscompras.model.get.Complemeto;
import com.gomotorscompany.gomotors.miscompras.model.get.Paquete;
import com.gomotorscompany.gomotors.miscompras.model.get.ProductosU;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.bumptech.glide.Glide;
import com.gomotorscompany.gomotors.webview.webViewCancelaciones;
import com.gomotorscompany.gomotors.webview.webViewGananciasAdeudos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class progresodetail extends AppCompatActivity implements View.OnClickListener , LocationListener,progresdetailView {

    private Context context;
    private List<datagetOrders> ordenes;
    private String numerodeorden,fechadelpedido,statusdelaorden,direccion,sucursalId,iduser,telefono;
    private int semaforodelaorden;
    private Double latitud,longitud,latclient,longclient,latsuc,longsuc;
    private List<Paquete> getPaquete;
    private List<ProductosU> getProductosU;
    private List<Complemeto> getComplemeto;


    private TextView textoorden,textofecha,textostatus,textodireccion,textosucursal,textodetalles;
    private ImageView imagesemaforo;

    private Button buttonenpointOrder2,buttonenpointOrder,button2,button3,button;
    private double latitude,longitude;
    private LocationManager locationManager;
    private int gerarquiaint;
    private progresdetailpresenter presenter;
    private ProgressDialog progressDialog;

    private sectionsAdapter sA;
    private ViewPager2 pager;
    private View viewrecolectar,viewEncola,viewenprogreso,viewterminado;
    private  Drawable blackdot,whitedot;
    private ImageView warningstop;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.detail_order);

        checkpermisionslevel();
        initView();
    }

    private void initView() {
        Bundle bndl;
        bndl = getIntent().getExtras();
       // pager = (ViewPager2) findViewById(R.id.cardviewitem);
        viewrecolectar=(View)findViewById(R.id.viewrecolectar);
        viewEncola=(View)findViewById(R.id.viewEncola);
        viewenprogreso=(View)findViewById(R.id.viewenprogreso);
        viewterminado=(View)findViewById(R.id.viewterminado);
        blackdot= getResources().getDrawable(R.drawable.shape_circle_process,context.getTheme());
        whitedot= getResources().getDrawable(R.drawable.shape_circle_process_not,context.getTheme());

       // filldataAdapter();
        if(bndl!=null)
        {
            numerodeorden=bndl.getString("direccionBundle1");
            fechadelpedido=bndl.getString("direccionBundle2");
            statusdelaorden=bndl.getString("direccionBundle3");
            semaforodelaorden=bndl.getInt("direccionBundle4");

            getPaquete=( List<Paquete>)bndl.getSerializable("direccionBundle5");
            getProductosU=(List<ProductosU>)bndl.getSerializable("direccionBundle6");
            getComplemeto=(List<Complemeto>)bndl.getSerializable("direccionBundle7");

            latclient=bndl.getDouble("direccionBundle8");
            longclient=bndl.getDouble("direccionBundle9");

            direccion=bndl.getString("direccionBundle10");
            sucursalId=bndl.getString("direccionBundle11");
            iduser=bndl.getString("direccionBundle12");
            latsuc=bndl.getDouble("direccionBundle13");
            longsuc=bndl.getDouble("direccionBundle14");
            telefono=bndl.getString("direccionBundle15");
            Log.e("detalOrders",""+numerodeorden+"  "+fechadelpedido+"  "+statusdelaorden+"  "+semaforodelaorden+"  "+direccion+"  "+sucursalId+"  "+latclient+"  "+longclient+" "+getPaquete.size()+" "+getProductosU.size()+" "+getComplemeto.size());

        }else{
            Log.e("detalOrders","no existre el dato");
        }
        progressDialog = new ProgressDialog(this);
        presenter=new progresdetailpresenterImpl(this,context);
        settext();

    }

    private void checkpermisionslevel() {
        SharedPreferences preferencias = getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String gerarquia     = preferencias.getString(GeneralConstantsV2.LEVEL_PERMISIONS, null);

        if(gerarquia.equals("1")) {
            gerarquiaint=Integer.valueOf( gerarquia);
            Log.e("ordeneslista", "admministrador   " + gerarquia);
        }else if(gerarquia.equals("2"))
        {
            gerarquiaint=Integer.valueOf( gerarquia);
            Log.e("ordeneslista", "repartidores   " + gerarquia);
            locationManager= (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);


            // LocationListener locationListenerGPS = null;
            if (ActivityCompat.checkSelfPermission(this.getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((this), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

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
    private void settext() {
        textoorden=findViewById(R.id.textView28);
        textoorden.setText("Orden: "+numerodeorden);

        textofecha=findViewById(R.id.textView29);
        textofecha.setText(fechadelpedido);

        textostatus=findViewById(R.id.textStatus);
        textostatus.setText(statusdelaorden);

        textodireccion =findViewById(R.id.direccionsucursal);
        textodireccion.setText("Sucursal: "+sucursalId+'\n'+ direccion);
      //  textosucursal=findViewById(R.id.direccionsucursal);
        textodetalles=findViewById(R.id.txtdetail);
        textodetalles.setText("Paquetes: "+getPaquete.size()+"  Productos: "+getProductosU.size()+" Complemento: "+getComplemeto.size());
        imagesemaforo=findViewById(R.id.imagensemaforo);
        buttonenpointOrder2=findViewById(R.id.buttonenpointOrder2);
        buttonenpointOrder2.setOnClickListener(this);
        warningstop=findViewById(R.id.warningstop);
        warningstop.setOnClickListener(this);
        buttonenpointOrder=findViewById(R.id.buttonenpointOrder);
        buttonenpointOrder.setOnClickListener(this);
        button=findViewById(R.id.button);
        button.setOnClickListener(this);
        button2=findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3=findViewById(R.id.button3);
        button3.setOnClickListener(this);
        /**Aqui guardar los detalles*/
        //checkDateTime();
        setColorSemaforImage();

    }

    private void checkDateTime() {


        // Date from the method holder.fecha.getText() (assuming it's a string)
        String dateString = "2024-04-02T12:40:39.257";

        // Parse the date string manually
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime now = LocalDateTime.now();
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

        LocalDateTime methodDate = LocalDateTime.parse(dateString, formatter);

        // Compare the two dates
        boolean notEqual = !now.isEqual(methodDate);
        if(notEqual)
        {
            presenter.changeStatus(iduser,Integer.valueOf( numerodeorden),7);
            presenter.liberarRepartidor();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Call onBackPressed() here
                    onBackPressed();
                }
            }, 5000);
        }
        }else{
            Toast.makeText(context, "solicite a su administracion una revision del folio", Toast.LENGTH_LONG).show();
        }
    }

    private void hidebutonscallandmensage()
    {
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
    }
    private void showbutonscallandmensage()    {
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
    }
    private void hidechangestatus()
    {
        buttonenpointOrder2.setVisibility(View.GONE);
    }
    private void showchangestatus()
    {
        buttonenpointOrder2.setVisibility(View.VISIBLE);
    }
    private void hidecancelOrder(){
        buttonenpointOrder.setVisibility(View.GONE);
    }
    private void showcancelOrder(){
        buttonenpointOrder.setVisibility(View.VISIBLE);
    }
    private  void hidegpsbuttongps(){button.setVisibility(View.GONE);}
    private void setColorSemaforImage() {
        Log.e("valuesemaforo",""+semaforodelaorden);
        if(semaforodelaorden==1)
        {
            buttonenpointOrder2.setVisibility(View.VISIBLE);
            Glide.with(context).load(R.drawable.ic_goblack).into(imagesemaforo) ;
            buttonenpointOrder2.setText("Recolectar");/**  aceptar orden*/
            button.setText("GPS NEGOCIO");
            viewrecolectar.setBackground(blackdot);
            viewEncola.setBackground(whitedot);
            viewenprogreso.setBackground(whitedot);
            viewterminado.setBackground(whitedot);
            hidebutonscallandmensage();
            if(gerarquiaint==2)
            {
                hidecancelOrder();
                hidegpsbuttongps();
            }else{

            }

        }
        else if(semaforodelaorden==2)
        {
            button.setText("GPS NEGOCIO");
            buttonenpointOrder2.setVisibility(View.VISIBLE);//todo
            Glide.with(context).load(R.drawable.ic_gogray).into(imagesemaforo) ;//ic_pizzastatus1recolectar
            buttonenpointOrder2.setText("Recolectado");//En cola
            viewrecolectar.setBackground(blackdot);
            viewEncola.setBackground(whitedot);
            viewenprogreso.setBackground(whitedot);
            viewterminado.setBackground(whitedot);
            hidebutonscallandmensage();
            if(gerarquiaint==2)
            {
                hidecancelOrder();
            }else{

            }

        }else if(semaforodelaorden==3)
        {
            button.setText("GPS CLIENTE");
            buttonenpointOrder2.setVisibility(View.VISIBLE);
            Glide.with(context).load(R.drawable.ic_goyellow).into(imagesemaforo) ;//ic_pizzastatus2encola
            buttonenpointOrder2.setText("En camino");//en progreso
            viewrecolectar.setBackground(blackdot);
            viewEncola.setBackground(blackdot);
            viewenprogreso.setBackground(whitedot);
            viewterminado.setBackground(whitedot);
            hidebutonscallandmensage();
            if(gerarquiaint==2)
            {
                hidecancelOrder();
            }else{

            }
        }
        else if(semaforodelaorden==4)
        {
            Glide.with(context).load(R.drawable.ic_goyellow).into(imagesemaforo) ;//ic_pizzastatus3enprogreso
            buttonenpointOrder2.setText("Terminado");
            viewrecolectar.setBackground(blackdot);
            viewEncola.setBackground(blackdot);
            viewenprogreso.setBackground(blackdot);
            viewterminado.setBackground(whitedot);
            showbutonscallandmensage();
            button2.setVisibility(View.GONE);
            if(gerarquiaint==2)
            {
                buttonenpointOrder.setText("No entregado");
                showcancelOrder();
            }
            if(gerarquiaint==1)
            {
                buttonenpointOrder.setText("Cancerlar Orden");
            }
        }
        else if(semaforodelaorden==5)
        {
            Glide.with(context).load(R.drawable.ic_goblack).into(imagesemaforo) ;//ic_pizzastatus4terminado
            viewrecolectar.setBackground(blackdot);
            viewEncola.setBackground(blackdot);
            viewenprogreso.setBackground(blackdot);
            viewterminado.setBackground(blackdot);
            buttonenpointOrder2.setVisibility(View.GONE);
            buttonenpointOrder.setVisibility(View.GONE);
            hidecancelOrder();
            hidebutonscallandmensage();
            hidegpsbuttongps();
            if(gerarquiaint==2){
                presenter.liberarRepartidor();
            }
        }
        else if(semaforodelaorden==6)
        {
            Glide.with(context).load(R.drawable.ic_goblack).into(imagesemaforo) ;//ic_pizzastatus5noentregado
            viewrecolectar.setBackground(blackdot);
            viewEncola.setBackground(blackdot);
            viewenprogreso.setBackground(blackdot);
            viewterminado.setBackground(blackdot);
            buttonenpointOrder2.setVisibility(View.GONE);
            hidebutonscallandmensage();
            hidegpsbuttongps();
            if(gerarquiaint==2){
                hidecancelOrder();
                presenter.liberarRepartidor();
            }

        }else if(semaforodelaorden==7)
        {
            Glide.with(context).load(R.drawable.ic_goblack).into(imagesemaforo) ;//ic_pizzastatus6cancelado
            viewrecolectar.setBackground(blackdot);
            viewEncola.setBackground(blackdot);
            viewenprogreso.setBackground(blackdot);
            viewterminado.setBackground(blackdot);

            buttonenpointOrder2.setVisibility(View.GONE);
            hidecancelOrder();
            hidebutonscallandmensage();
            hidegpsbuttongps();
            if(gerarquiaint==2){
                presenter.liberarRepartidor();
            }
        }
        else
        {
            Glide.with(context).load(R.drawable.ic_goblack  ).into(imagesemaforo) ;//ic_pizzastatus6cancelado
            viewrecolectar.setBackground(blackdot);
            viewEncola.setBackground(blackdot);
            viewenprogreso.setBackground(blackdot);
            viewterminado.setBackground(blackdot);

            hidebutonscallandmensage();
            hidecancelOrder();
            hidegpsbuttongps();
            if(gerarquiaint==2){
                presenter.liberarRepartidor();
            }
        }
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        if(location.getLatitude()!=latitude&&gerarquiaint==2){
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

    @Override
    public void setChangeStatus(int code,int status) {
        if(code==105)
        {
            onBackPressed();
        }
    }

    @Override
    public void hideprogresdialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showprogresdialog() {
        progressDialog.setMessage("Cargando datos ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    void filldataAdapter()/// modulo de secciones adpater
    {
        sA=new sectionsAdapter(context);
        pager.setAdapter(sA);
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
            @Override
            public void onPageSelected(final int position) {
                super.onPageSelected(position);

                //posrv=position;
                sA.notifyDataSetChanged();


                Log.e("finalcheck",""+position);
                //movedots(position);
               // titlefileds.setText(dataQuestions1.get(position).getDescTripMgmSection());
//                if(position!=0) {
//                }
//                if(position==sizeArange-1)
//                {
//                    buttongochecklist.setVisibility(View.VISIBLE);
//                }
//                else
//                {
//                    buttongochecklist.setVisibility(View.GONE);
//                }
          }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonenpointOrder:
             //   Toast.makeText(context, "Cancelar", Toast.LENGTH_SHORT).show();
                if(gerarquiaint==2){
                    presenter.changeStatus(iduser,Integer.valueOf( numerodeorden),6);
                    presenter.liberarRepartidor();
                    Intent intent1 = new Intent(getApplicationContext(), webViewCancelaciones.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("detailOrderB", numerodeorden);
                    intent1.putExtras(bundle);
                    startActivity(intent1);
                    finish();
                }
                if(gerarquiaint==1)
                {
                    presenter.changeStatus(iduser,Integer.valueOf( numerodeorden),7);
                    presenter.liberarRepartidor();
                }

                break;
            case R.id.buttonenpointOrder2:

                if(semaforodelaorden!=5||semaforodelaorden!=6||semaforodelaorden!=7)
                {
                    int semaforonuevo=semaforodelaorden+1;
                    presenter.changeStatus(iduser,Integer.valueOf( numerodeorden),semaforonuevo);
                }else
                {
                    Toast.makeText(context, "status "+semaforodelaorden, Toast.LENGTH_SHORT).show();
                }
                break;
            case  R.id.warningstop:
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("Este boton termina la orden y notifica un problema con su unidad, ¿ Desea ejecutar esta accion?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        presenter.changeStatus(iduser,Integer.valueOf( numerodeorden),400);
                        presenter.liberarRepartidor();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                     dialogo1.dismiss();
                    }
                });
                dialogo1.show();
                break;
            case R.id.button:
                if(semaforodelaorden >= 3){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/?api=1&" + "origin=" + latitude + "," + longitude + "&" + "destination=" + latclient + "," + longclient + "&travelmode=driving"));
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/?api=1&" + "origin=" + latitude + "," + longitude + "&" + "destination=" + latsuc + "," + longsuc + "&travelmode=driving"));
                    startActivity(intent);
                }
                /*
                wazemaps externalGPSDialog = new wazemaps();
                externalGPSDialog.setLocationVehicle(latclient,longclient,latsuc, longsuc,semaforodelaorden);
                externalGPSDialog.show(this.getSupportFragmentManager(), wazemaps.TAG);
                */
                break;
            case R.id.button2:
                Intent intentDial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telefono));
                intentDial.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentDial);
                break;
            case R.id.button3:
               /* Intent intetnsms = new Intent(Intent.ACTION_SENDTO).setData(Uri.parse(String.format("smsto:%s", telefono))).putExtra("Mensaje", "Notificación gomotors");
                intetnsms.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intetnsms);*/
                String url = "https://api.whatsapp.com/send?phone="+"8717691978"+"&text="+"*De*:%20"+"iduser"+"%0A"+"*Asunto*:%20"+ "Soporte"+"%0A"+"%0A"+
                        "orden: "+numerodeorden;//5218124691084 525617224632
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                /**include  spinner to chat*/
                /**
                 *         asignando asignandopedido=new asignando();
                 *         asignandopedido.show(getActivity().getSupportFragmentManager(), asignando.TAG);
                 *         */
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, mainContentViewImpl.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}


//textView28  pedido
//textView29  fecha
//namerepartidorO nombre repartidor0
//direccionsucursal  dir
//txtdetail      detalle
//textStatus   status

//imagensemaforo