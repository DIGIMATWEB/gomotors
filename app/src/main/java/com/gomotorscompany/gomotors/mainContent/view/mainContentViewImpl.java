package com.gomotorscompany.gomotors.mainContent.view;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gomotorscompany.gomotors.Ordenar.model.newmenu.Complemento;
import com.gomotorscompany.gomotors.Ordenar.view.ordenarViewImpl;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.Splash.view.Splahs;
import com.gomotorscompany.gomotors.Zonas.view.zonasViewImpl;
import com.gomotorscompany.gomotors.agregarCompra.view.agregarcompra;
import com.gomotorscompany.gomotors.enprogreso.view.enprogreso;
import com.gomotorscompany.gomotors.menu.view.FragmentNavigationButtonsMenu;
import com.gomotorscompany.gomotors.miscompras.view.miscompras;
import com.gomotorscompany.gomotors.pedido.view.pedido;
import com.gomotorscompany.gomotors.pedidoExpres.view.expres;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.Serializable;
import java.util.List;

public class mainContentViewImpl extends AppCompatActivity  {
    public static final String TAG = mainContentViewImpl.class.getSimpleName();
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Context context;
    private FrameLayout agregar, menu;
    private agregarInterface viewInterface;

private  FragmentNavigationButtonsMenu fg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.dinamic_menu);
        askNotificationPermission();

        SharedPreferences preferencias = getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String gerarquia = preferencias.getString(GeneralConstantsV2.LEVEL_PERMISIONS, null);

        Log.e("credenciales", "menurank  " + gerarquia);
        Bundle bndlcompra;
        bndlcompra = getIntent().getExtras();
        if(bndlcompra!=null) {
            Log.e("bundlemainmenu", "--  " + bndlcompra.getString("descripcionCarrito"));/**aqui se debe mandar start order*/

            goTomiscompras();
        }
        else {
            if(gerarquia!=null) {
                if (gerarquia.equals("1")) {
                    ordenes();// showFragmentDashboard();// zonas(); solucionar permisos d
                    showFragmentNavigationButtons();
                } else if (gerarquia.equals("2")) {
                    ordenes();
                    showFragmentNavigationButtons();
                } else if (gerarquia.equals("3")) {
                    ordenes();//showFragmentDashboard();
                    showFragmentNavigationButtons();
                }else if (gerarquia.equals("4")) {
                    Intent intent = new Intent(this, expres.class);
                    startActivity(intent);
                    finish();
                }
            }else
            {
                Log.e("bundlemainmenu", "else maincontent" );
            }
        }


        agregar = findViewById(R.id.buttonagregar);
        menu = findViewById(R.id.menuQuesi);
        viewInterface = new agregarInterface() {
            @Override
            public void agregar(String clave, String nombre, String precio) {

            }
        };
        // completado();

//region firebase
        FirebaseMessaging.getInstance().subscribeToTopic("all")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                        Log.e("firebase", "suscription: " + msg);
                    }
                });

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.e(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        String token = task.getResult();
                        Log.d("tokenfirebase", token);
                        Log.e("firebase", "token firebase: " + token);
                    }
                });
        //endregion firebase
    }

    private void showFragmentDashboard() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        ordenarViewImpl fragmentDashboard = new ordenarViewImpl();
        transaction.replace(R.id.ordenarViewImpl, fragmentDashboard, ordenarViewImpl.TAG).commit();
    }

    private void ordenes() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        enprogreso enprogreso = new enprogreso();
        transaction.replace(R.id.ordenarViewImpl, enprogreso, enprogreso.TAG).commit();

    }
    private void zonas() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        zonasViewImpl completado= new zonasViewImpl();
        transaction.replace(R.id.ordenarViewImpl, completado, completado.TAG).commit();
        }

    private void showFragmentNavigationButtons() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        FragmentNavigationButtonsMenu fragmentNavigationButtonsMenu = new FragmentNavigationButtonsMenu();
        fg=fragmentNavigationButtonsMenu;
        transaction.replace(R.id.menuQuesi, fragmentNavigationButtonsMenu, FragmentNavigationButtonsMenu.TAG).commit();
    }
    private void completado() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        zonasViewImpl completado= new zonasViewImpl();
        transaction.replace(R.id.ordenarViewImpl, completado, completado.TAG).commit();
    }

//    @SuppressLint("NewApi")
    private void miscompras() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        miscompras miscompras = new miscompras();
        transaction.replace(R.id.ordenarViewImpl, miscompras, miscompras.TAG).commit();
      //  fg.illuminateMisCompras();

    }
 //   @SuppressLint("NewApi")
    private  void  pedido()
    {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        pedido pedido = new pedido();
        transaction.replace(R.id.ordenarViewImpl, pedido, pedido.TAG).commit();
     //   fg.illuminatePedido();
    }
    public  void gotoPedido()
    {
        pedido();
    }
   /* public void activityMenu(String ubicacion, String idsuc, String sucursalnam, double latitude, double longitude, String category, String clave,
                             String nombre, String precio, String descripcion, List<Complementos> complementos, String imagen) {
        Bundle bundleorder = new Bundle();


        bundleorder.putString("direccionentrega", ubicacion);
        bundleorder.putString("idsucursal", idsuc);
        bundleorder.putString("nombresucursal", sucursalnam);
        bundleorder.putDouble("latcliente", latitude);
        bundleorder.putDouble("longcliente", longitude);
        bundleorder.putString("categoria", category);

        bundleorder.putString("clave", clave);

        bundleorder.putString("nombre", nombre);
        bundleorder.putString("precio", precio);
        bundleorder.putString("descripcion", descripcion);
        bundleorder.putString("imagen", imagen);
        bundleorder.putSerializable("Complementos", (Serializable) complementos);
//        Log.e("mainActivityInteraction","directo al activity 1 "+complementos.size());
        Intent intent = new Intent(this, agregarcompra.class);
        intent.putExtras(bundleorder);

        //viewInterface.agregar(clave,nombre,precio);
        // intent.putExtras(viewInterface)
        startActivity(intent);
        overridePendingTransition( R.anim.slide_up, R.anim.no_animation);
    }

   */
    public void activityMenu2(String ubicacion, String idsuc, String sucursalnam, double latitude, double longitude ,String category, List<Complemento> complementos, String sku, String nombre, String precio, String descripcion, String imagen) {
        Bundle bundleorder = new Bundle();
        bundleorder.putString("direccionentrega", ubicacion);
        bundleorder.putString("idsucursal", idsuc);
        bundleorder.putString("nombresucursal", sucursalnam);
        bundleorder.putDouble("latcliente", latitude);
        bundleorder.putDouble("longcliente", longitude);
        bundleorder.putString("categoria", category);

        bundleorder.putString("clave", sku);
        bundleorder.putString("nombre", nombre);
        bundleorder.putString("precio", precio);
        bundleorder.putString("descripcion", descripcion);
        bundleorder.putString("imagen", imagen);

        bundleorder.putSerializable("Complementos", (Serializable) complementos);
        Intent intent = new Intent(this, agregarcompra.class);
        intent.putExtras(bundleorder);

        startActivity(intent);
        overridePendingTransition( R.anim.slide_up, R.anim.no_animation);
    }
    public  void activityMenu3(String ubicacion, String idsuc, String sucursalnam, double latitude, double longitude ,String category, List<Complemento> complementos, String sku, String nombre, String precio, String descripcion, String imagen)
    {
        Bundle bundleorder = new Bundle();

        bundleorder.putString("nombreP", nombre);
        bundleorder.putString("descripcion", descripcion);
        bundleorder.putString("costofinal",precio);
        bundleorder.putString("idsucursal",idsuc);
        bundleorder.putString("nombresucursal",sucursalnam);
        bundleorder.putString("carritoDireccionentrega",ubicacion);
        bundleorder.putDouble("carritolatitude",latitude);
        bundleorder.putDouble("carritolongitud",longitude);
        bundleorder.putString("carritocategoria",category);
        bundleorder.putString("porductos", sku);
        bundleorder.putSerializable("datalistacomplementos", (Serializable) complementos);


        //bundleorder.putString("direccionentrega", ubicacion);
        //bundleorder.putString("idsucursal", idsuc);
       // bundleorder.putString("nombresucursal", sucursalnam);
        //bundleorder.putDouble("latcliente", latitude);
       // bundleorder.putDouble("longcliente", longitude);
        //bundleorder.putString("categoria", category);
       // bundleorder.putString("clave", sku);
        //bundleorder.putString("nombre", nombre);
       // bundleorder.putString("precio", precio);
        //bundleorder.putString("descripcion", descripcion);
        // bundleorder.putSerializable("Complementos", (Serializable) complementos);
        //todo esta imagen debe agregarce a mis compras
        bundleorder.putString("imagen", imagen);

        Intent intent =getIntent();
        intent.putExtras(bundleorder);
        goTomiscompras();
    }

    public void goTomiscompras()
    {
        miscompras();
    }
    public void goTommenu()
    {
        showFragmentDashboard();
    }
    public void goToordenes(){ordenes();}



//region firebase
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (!isGranted) {
                    // Inform user that your app will not show notifications.
                    Toast.makeText(context, "No se pudiron habilitar las notificaciones", Toast.LENGTH_SHORT).show();
                }
            });

    private void askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                    PackageManager.PERMISSION_GRANTED) {
                // Inform the user about why you need notification permissions
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Notification Permissions");
                builder.setMessage("To receive important updates and alerts, please enable notification permissions.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Request notification permissions
                        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle permission denied by user
                        // You can provide instructions or inform the user about the consequences of denying
                        Toast.makeText(getApplicationContext(), "Notifications disabled. You may miss important updates.", Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        }
    }
      //endregion
    }
