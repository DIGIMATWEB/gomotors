package com.gomotorscompany.gomotors.Splash.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.gomotorscompany.gomotors.Dialogs.Alert.warningAlert;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.Login.view.LoginContainer;
import com.gomotorscompany.gomotors.Splash.presenter.presenterspalshImplements;
import com.gomotorscompany.gomotors.Splash.presenter.presentersplash;
import com.gomotorscompany.gomotors.mainContent.view.mainContentViewImpl;
import com.gomotorscompany.gomotors.pedidoExpres.view.expres;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.bumptech.glide.Glide;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Splahs extends AppCompatActivity implements  splashView{

    private String user,telephone,token,urlImage,rgbaColor;
    private presentersplash presenter;
    private ConstraintLayout backgroundColor;
    private ImageView imageBackground;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashcreen);
        initSplashScreenActivity();
    }
    /**aqui deberia de ir por los Recursos de internet la primera vez y luego setearlos para el administrador*/
    private void initSplashScreenActivity() {
        backgroundColor=findViewById(R.id.backgroundColor);
        imageBackground =findViewById(R.id.logotipo);
        imageBackground.setVisibility(View.VISIBLE);
        presenter=new presenterspalshImplements(this,getApplicationContext());
       // presenter.getAvailable();
        presenter.requestsplashConfig();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, +WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Glide.with(this).load(R.mipmap.ic_logo).into(imageBackground);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        user      =  preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
        telephone =preferences.getString(GeneralConstantsV2.TELEPHONE_PREFERENCE, null);
        token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        urlImage = preferences.getString(GeneralConstantsV2.URL_SPLASH_IMAGE, null);
        rgbaColor= preferences.getString(GeneralConstantsV2.RGBA_COLOR, null);

        continueSplash();

   /*     if(urlImage  !=null) {
            if (!urlImage.equals("")) {
                imageBackground.setVisibility(View.VISIBLE);
                Glide.with(getApplicationContext()).asGif().load(urlImage).into(imageBackground);
                continueSplash();
                if (!rgbaColor.equals("")) {
                    backgroundColor.setBackgroundColor(Color.parseColor(rgbaColor));

                }
            } else {

            }
        }else
        {
            Glide.with(this).asGif().load("http://quesipizzasmx-001-site11.dtempurl.com/Imagenes/logotiposapp/zpidi.gif").into(imageBackground);
            backgroundColor.setBackgroundColor(Color.parseColor("#F36C23"));
            imageBackground.setVisibility(View.VISIBLE);
            continueSplash();

        }*/




    }
    private void goToLoginContainer() {
        Intent intent = new Intent(Splahs.this, LoginContainer.class);
        startActivity(intent);
        finish();

    }
    private void goToMenu(){
        Intent intent = new Intent(Splahs.this, mainContentViewImpl.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);//
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void setSplashImge(String imageapp) {
        urlImage=imageapp;

    }

    @Override
    public void setColorAPP(String colorApp) {
        rgbaColor=colorApp;

    }

    @Override
    public void setDialog() {
        warningAlert dialogFragment = new warningAlert();
        dialogFragment.show(getSupportFragmentManager(), "warningAlert");
    }

    private void continueSplash()
    {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(token!=null){
                    goToMenu();
                }
                else
                {
                    goToLoginContainer();
                }
               /* Intent intent = new Intent(getApplicationContext(), expres.class);
                startActivity(intent);
                finish();*/
            }
        },3000);
    }
}
