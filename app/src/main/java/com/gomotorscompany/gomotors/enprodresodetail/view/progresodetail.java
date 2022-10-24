package com.gomotorscompany.gomotors.enprodresodetail.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.gomotorscompany.gomotors.Dialogs.mapswaze.view.wazemaps;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.enprodresodetail.presenter.progresdetailpresenter;
import com.gomotorscompany.gomotors.enprodresodetail.presenter.progresdetailpresenterImpl;
import com.gomotorscompany.gomotors.mainContent.view.mainContentViewImpl;
import com.gomotorscompany.gomotors.miscompras.model.get.Complemeto;
import com.gomotorscompany.gomotors.miscompras.model.get.Paquete;
import com.gomotorscompany.gomotors.miscompras.model.get.ProductosU;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.bumptech.glide.Glide;

import java.util.List;

public class progresodetail extends AppCompatActivity implements View.OnClickListener , LocationListener,progresdetailView {

    private Context context;
    private List<datagetOrders> ordenes;
    private String numerodeorden,fechadelpedido,statusdelaorden,direccion,sucursalId,iduser;
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
        buttonenpointOrder=findViewById(R.id.buttonenpointOrder);
        buttonenpointOrder.setOnClickListener(this);
        button=findViewById(R.id.button);
        button.setOnClickListener(this);
        button2=findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3=findViewById(R.id.button3);
        button3.setOnClickListener(this);
        /**Aqui guardar los detalles*/
        setColorSemaforImage();
    }

    private void hidebutonscallandmensage()
    {
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
    }
    private void showbutonscallandmensage()
    {
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
        if(semaforodelaorden==1)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus6cancelado).into(imagesemaforo) ;
            buttonenpointOrder2.setText("Recolectar");/**  aceptar orden*/
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
            Glide.with(context).load(R.drawable.ic_pizzastatus1recolectar).into(imagesemaforo) ;
            buttonenpointOrder2.setText("En cola");
            hidebutonscallandmensage();
            if(gerarquiaint==2)
            {
                hidecancelOrder();
            }else{

            }

        }else if(semaforodelaorden==3)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus2encola).into(imagesemaforo) ;
            buttonenpointOrder2.setText("En progreso");
            hidebutonscallandmensage();
            if(gerarquiaint==2)
            {
                hidecancelOrder();
            }else{

            }
        }
        else if(semaforodelaorden==4)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus3enprogreso).into(imagesemaforo) ;
            buttonenpointOrder2.setText("Terminado");
            showbutonscallandmensage();
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
            Glide.with(context).load(R.drawable.ic_pizzastatus4terminado).into(imagesemaforo) ;
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
            Glide.with(context).load(R.drawable.ic_pizzastatus5noentregado).into(imagesemaforo) ;
            buttonenpointOrder2.setVisibility(View.GONE);
            hidebutonscallandmensage();
            hidegpsbuttongps();
            if(gerarquiaint==2){
                hidecancelOrder();
                presenter.liberarRepartidor();
            }

        }else if(semaforodelaorden==7)
        {
            Glide.with(context).load(R.drawable.ic_pizzastatus6cancelado).into(imagesemaforo) ;
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
            Glide.with(context).load(R.drawable.ic_pizzastatus6cancelado).into(imagesemaforo) ;
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
    public void setChangeStatus(int code) {
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonenpointOrder:
             //   Toast.makeText(context, "Cancelar", Toast.LENGTH_SHORT).show();
                if(gerarquiaint==2){
                    presenter.changeStatus(iduser,Integer.valueOf( numerodeorden),6);
                    presenter.liberarRepartidor();
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
            case R.id.button:
                wazemaps externalGPSDialog = new wazemaps();
                externalGPSDialog.setLocationVehicle(latclient,longclient,latsuc, longsuc,semaforodelaorden);/** esta es la ibicacion de la sucursal*/
                externalGPSDialog.show(this.getSupportFragmentManager(), wazemaps.TAG);
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