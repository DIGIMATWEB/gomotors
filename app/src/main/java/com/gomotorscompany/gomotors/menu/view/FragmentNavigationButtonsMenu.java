package com.gomotorscompany.gomotors.menu.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gomotorscompany.gomotors.Ordenar.view.ordenarViewImpl;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.Zonas.view.zonasViewImpl;
import com.gomotorscompany.gomotors.enprogreso.view.enprogreso;
import com.gomotorscompany.gomotors.miscompras.view.miscompras;
import com.gomotorscompany.gomotors.pedido.view.pedido;
import com.gomotorscompany.gomotors.perfil.view.Perfile;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;

import java.util.List;

public class FragmentNavigationButtonsMenu extends Fragment implements View.OnClickListener,menuView{
    public static final String TAG = FragmentNavigationButtonsMenu.class.getSimpleName();

    private ConstraintLayout home,ordenes, carrito,compras,operacion,perfil;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ImageView menu1,menu2,menu3,menu4,menu5,menu6;
    private TextView tmenu1,tmenu2,tmenu3,tmenu4,tmenu5,tmenu6;
    private Guideline guideline1, guideline2, guideline3, guideline4, guideline5, guideline6;

    public float a,b,c,d,e,f;
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_bar, container, false);
        initView(view);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void initView(View view) {
        home=view.findViewById(R.id.constraintUnits);
        home.setOnClickListener(this);
        ordenes=view.findViewById(R.id.constraintTracking);//pedido
        ordenes.setOnClickListener(this);
        carrito=view.findViewById(R.id.constraintNotifications);//carrito
        carrito.setOnClickListener(this);
        compras=view.findViewById(R.id.constrainZones);//progreso
        compras.setOnClickListener(this);
        operacion=view.findViewById(R.id.constrainContact);//completado
        operacion.setOnClickListener(this);
        perfil=view.findViewById(R.id.constrainProfile);
        perfil.setOnClickListener(this);

        menu1=view.findViewById(R.id.mainM);
        menu2=view.findViewById(R.id.mpedido);
        menu3=view.findViewById(R.id.miscompras);
        menu4=view.findViewById(R.id.Mordenes);
        menu5=view.findViewById(R.id.mzonas);;
        menu6=view.findViewById(R.id.mperfilicon);

        tmenu1=view.findViewById(R.id.txthome);
        tmenu2=view.findViewById(R.id.pedidos);
        tmenu3=view.findViewById(R.id.compras);
        tmenu4=view.findViewById(R.id.ordens);
        tmenu5=view.findViewById(R.id.zons);
        tmenu6=view.findViewById(R.id.profil);

        guideline1 = view.findViewById(R.id.guideline11g);
        guideline2 = view.findViewById(R.id.guideline12g);
        guideline3 = view.findViewById(R.id.guideline13g);
        guideline4 = view.findViewById(R.id.guideline14g);
        guideline5 = view.findViewById(R.id.guideline15g);
        guideline6 = view.findViewById(R.id.guideline16g);
        ConstraintLayout.LayoutParams params1 = (ConstraintLayout.LayoutParams) guideline1.getLayoutParams();
        ConstraintLayout.LayoutParams params2 = (ConstraintLayout.LayoutParams) guideline2.getLayoutParams();
        ConstraintLayout.LayoutParams params3 = (ConstraintLayout.LayoutParams) guideline3.getLayoutParams();
        ConstraintLayout.LayoutParams params4 = (ConstraintLayout.LayoutParams) guideline4.getLayoutParams();
        ConstraintLayout.LayoutParams params5 = (ConstraintLayout.LayoutParams) guideline5.getLayoutParams();
        ConstraintLayout.LayoutParams params6 = (ConstraintLayout.LayoutParams) guideline6.getLayoutParams();

        SharedPreferences preferencias = getActivity().getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String gerarquia     = preferencias.getString(GeneralConstantsV2.LEVEL_PERMISIONS, null);

        Log.e("credenciales","menurank  "+gerarquia);
        if(gerarquia!=null) {
            if (gerarquia.equals("1")) {
                a = .166f;
                b = .333f;
                c = .5f;
                d = .666f;
                e = .833f;
                f = 1;

                params1.guidePercent = a; // 45% // range: 0 <-> 1
                params2.guidePercent = b; // 45% // range: 0 <-> 1
                params3.guidePercent = c; // 45% // range: 0 <-> 1
                params4.guidePercent = d; // 45% // range: 0 <-> 1
                params5.guidePercent = e; // 45% // range: 0 <-> 1
                params6.guidePercent = f; // 45% // range: 0 <-> 1

            } else if (gerarquia.equals("3")) {
                a = .25f;
                b = .5f;
                c = .75f;
                d = .75f;
                e = .75f;
                f = 1;

                params1.guidePercent = a; // 45% // range: 0 <-> 1
                params2.guidePercent = b; // 45% // range: 0 <-> 1
                params3.guidePercent = c; // 45% // range: 0 <-> 1
                params4.guidePercent = d; // 45% // range: 0 <-> 1
                params5.guidePercent = e; // 45% // range: 0 <-> 1
                params6.guidePercent = f; // 45% // range: 0 <-> 1
            } else if (gerarquia.equals("2")) {
                a = 0f;
                b = .0f;
                c = .0f;
                d = .5f;
                e = .5f;
                f = 1;

                params1.guidePercent = a; // 45% // range: 0 <-> 1
                params2.guidePercent = b; // 45% // range: 0 <-> 1
                params3.guidePercent = c; // 45% // range: 0 <-> 1
                params4.guidePercent = d; // 45% // range: 0 <-> 1
                params5.guidePercent = e; // 45% // range: 0 <-> 1
                params6.guidePercent = f; // 45% // range: 0 <-> 1
                illuminateOrdenes();

            }
        }

    }

    @Override
    public void listItems(List<String> items) {

    }

    private void FragmentHome() {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        ordenarViewImpl fragmentDashboard = new ordenarViewImpl();
        transaction.add(R.id.ordenarViewImpl, fragmentDashboard, ordenarViewImpl.TAG).commit();
        illuminateMenu();
    }

    private void profile() {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        Perfile perfile = new Perfile();
        transaction.replace(R.id.ordenarViewImpl, perfile, Perfile.TAG).commit();
        illuminateprofile();
    }


    private void pedidos() {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        pedido pedido = new pedido();
        transaction.replace(R.id.ordenarViewImpl, pedido, pedido.TAG).commit();
        illuminatePedido();
    }

    private void miscompras() {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        miscompras miscompras = new miscompras();
        transaction.replace(R.id.ordenarViewImpl, miscompras, miscompras.TAG).commit();
        illuminateMisCompras();
    }

    private void enprogreso() {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        enprogreso enprogreso= new enprogreso();
        transaction.replace(R.id.ordenarViewImpl, enprogreso, enprogreso.TAG).commit();
        illuminateOrdenes();
    }

    private void completado() {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        zonasViewImpl completado= new zonasViewImpl();
        transaction.replace(R.id.ordenarViewImpl, completado, completado.TAG).commit();
        illuminateZonas();
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.constraintUnits:
                FragmentHome();
                break;
            case R.id.constrainProfile:
                profile();
                break;
            case R.id.constraintTracking:
                pedidos();
                break;
            case R.id.constraintNotifications:
                miscompras();
                break;
            case R.id.constrainZones:
                enprogreso();
                break;
            case R.id.constrainContact:
                completado();
                break;
        }
    }



    @SuppressLint("NewApi")
    public void illuminateMenu()//#E92F48
    {
        menu1.setImageResource(R.drawable.ic_inicio);
        menu1.setColorFilter(R.color.redmenus);
        menu2.setImageResource(R.drawable.ic_pizza);
        menu3.setImageResource(R.drawable.ic_compras);
        menu4.setImageResource(R.drawable.ic_ordenes);
        menu5.setImageResource(R.drawable.ic_zonas);
        menu6.setImageResource(R.drawable.ic_perfil);

        menu2.setColorFilter(R.color.black);
        menu3.setColorFilter(R.color.black);
        menu4.setColorFilter(R.color.black);
        menu5.setColorFilter(R.color.black);
        menu6.setColorFilter(R.color.black);

        tmenu1.setTextColor(getResources().getColor(R.color.colorOrangeYellow));
        tmenu2.setTextColor(getResources().getColor(R.color.black));
        tmenu3.setTextColor(getResources().getColor(R.color.black));
        tmenu4.setTextColor(getResources().getColor(R.color.black));
        tmenu5.setTextColor(getResources().getColor(R.color.black));
        tmenu6.setTextColor(getResources().getColor(R.color.black));
    }
    @SuppressLint("NewApi")
    public void illuminatePedido()
    {
        menu1.setImageResource(R.drawable.ic_inicio);
        menu2.setImageResource(R.drawable.ic_pizza);
        menu2.setColorFilter(R.color.redmenus);
        menu3.setImageResource(R.drawable.ic_compras);
        menu4.setImageResource(R.drawable.ic_ordenes);
        menu5.setImageResource(R.drawable.ic_zonas);
        menu6.setImageResource(R.drawable.ic_perfil);
        menu1.setColorFilter(R.color.black);
        menu3.setColorFilter(R.color.black);
        menu4.setColorFilter(R.color.black);
        menu5.setColorFilter(R.color.black);
        menu6.setColorFilter(R.color.black);

        tmenu1.setTextColor(getResources().getColor(R.color.black));
        tmenu2.setTextColor(getResources().getColor(R.color.colorOrangeYellow));
        tmenu3.setTextColor(getResources().getColor(R.color.black));
        tmenu4.setTextColor(getResources().getColor(R.color.black));
        tmenu5.setTextColor(getResources().getColor(R.color.black));
        tmenu6.setTextColor(getResources().getColor(R.color.black));
    }
    @SuppressLint("NewApi")
    public void illuminateMisCompras()
    {
        menu1.setImageResource(R.drawable.ic_inicio);
        menu2.setImageResource(R.drawable.ic_pizza);
        menu3.setImageResource(R.drawable.ic_compras);
        menu3.setColorFilter(R.color.redmenus);
        menu4.setImageResource(R.drawable.ic_ordenes);
        menu5.setImageResource(R.drawable.ic_zonas);
        menu6.setImageResource(R.drawable.ic_perfil);

        menu1.setColorFilter(R.color.black);
        menu2.setColorFilter(R.color.black);
        menu4.setColorFilter(R.color.black);
        menu5.setColorFilter(R.color.black);
        menu6.setColorFilter(R.color.black);

        tmenu1.setTextColor(getResources().getColor(R.color.black));
        tmenu2.setTextColor(getResources().getColor(R.color.black));
        tmenu3.setTextColor(getResources().getColor(R.color.colorOrangeYellow));
        tmenu4.setTextColor(getResources().getColor(R.color.black));
        tmenu5.setTextColor(getResources().getColor(R.color.black));
        tmenu6.setTextColor(getResources().getColor(R.color.black));
    }
    @SuppressLint("NewApi")
    public void illuminateOrdenes()
    {
        menu1.setImageResource(R.drawable.ic_inicio);
        menu2.setImageResource(R.drawable.ic_pizza);
        menu3.setImageResource(R.drawable.ic_compras);
        menu4.setImageResource(R.drawable.ic_ordenes);
        menu4.setColorFilter(R.color.redmenus);
        menu5.setImageResource(R.drawable.ic_zonas);
        menu6.setImageResource(R.drawable.ic_perfil);

        menu1.setColorFilter(R.color.black);
        menu2.setColorFilter(R.color.black);
        menu3.setColorFilter(R.color.black);
        menu5.setColorFilter(R.color.black);
        menu6.setColorFilter(R.color.black);

        tmenu1.setTextColor(getResources().getColor(R.color.black));
        tmenu2.setTextColor(getResources().getColor(R.color.black));
        tmenu3.setTextColor(getResources().getColor(R.color.black));
        tmenu4.setTextColor(getResources().getColor(R.color.colorOrangeYellow));
        tmenu5.setTextColor(getResources().getColor(R.color.black));
        tmenu6.setTextColor(getResources().getColor(R.color.black));
    }
    @SuppressLint("NewApi")
    public void illuminateZonas()
    {
        menu1.setImageResource(R.drawable.ic_inicio);
        menu2.setImageResource(R.drawable.ic_pizza);
        menu3.setImageResource(R.drawable.ic_compras);
        menu4.setImageResource(R.drawable.ic_ordenes);
        menu5.setImageResource(R.drawable.ic_zonas);
        menu5.setColorFilter(R.color.redmenus);
        menu6.setImageResource(R.drawable.ic_perfil);

        menu1.setColorFilter(R.color.black);
        menu2.setColorFilter(R.color.black);
        menu3.setColorFilter(R.color.black);
        menu4.setColorFilter(R.color.black);
        menu6.setColorFilter(R.color.black);

        tmenu1.setTextColor(getResources().getColor(R.color.black));
        tmenu2.setTextColor(getResources().getColor(R.color.black));
        tmenu3.setTextColor(getResources().getColor(R.color.black));
        tmenu4.setTextColor(getResources().getColor(R.color.black));
        tmenu5.setTextColor(getResources().getColor(R.color.colorOrangeYellow));
        tmenu6.setTextColor(getResources().getColor(R.color.black));
    }
    @SuppressLint("NewApi")
    public void illuminateprofile()
    {
        menu1.setImageResource(R.drawable.ic_inicio);
        menu2.setImageResource(R.drawable.ic_pizza);
        menu3.setImageResource(R.drawable.ic_compras);
        menu4.setImageResource(R.drawable.ic_ordenes);
        menu5.setImageResource(R.drawable.ic_zonas);
        menu6.setImageResource(R.drawable.ic_perfil);
        menu6.setColorFilter(R.color.redmenus);

        menu1.setColorFilter(R.color.black);
        menu2.setColorFilter(R.color.black);
        menu3.setColorFilter(R.color.black);
        menu4.setColorFilter(R.color.black);
        menu5.setColorFilter(R.color.black);

        tmenu1.setTextColor(getResources().getColor(R.color.black));
        tmenu2.setTextColor(getResources().getColor(R.color.black));
        tmenu3.setTextColor(getResources().getColor(R.color.black));
        tmenu4.setTextColor(getResources().getColor(R.color.black));
        tmenu5.setTextColor(getResources().getColor(R.color.black));
        tmenu6.setTextColor(getResources().getColor(R.color.colorOrangeYellow));
    }
}
