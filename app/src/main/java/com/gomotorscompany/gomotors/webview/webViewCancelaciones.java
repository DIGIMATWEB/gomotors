package com.gomotorscompany.gomotors.webview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class webViewCancelaciones extends AppCompatActivity {
    public static final String TAG = webViewCancelaciones.class.getSimpleName();
    private WebView myWebView;

    private String numerodeorden;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        myWebView = findViewById(R.id.webviewid);
        Bundle bndl;
        bndl = getIntent().getExtras();//detailOrderB
        numerodeorden = bndl.getString("detailOrderB");
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        // loading http://www.google.com url in the WebView.
        //http://200.92.216.34:83/MODULOS/funcion?cve_objeto=M_W01_035&&token=  http://200.92.216.34:83/Administrador/AdministradorLogin?usuario=qptest&&password=0A-61-5F-AB-0A-20-75-85-B8-91-8E-95-A7-A2-25-6D
        myWebView.loadUrl("http://quesipizzasmx-001-site16.dtempurl.com/MODULOS/Cancelaciones_ordenes?token=" + token + "&&id_orden=" + numerodeorden);
        Log.e("consultaWEbC", "http://quesipizzasmx-001-site16.dtempurl.com/MODULOS/Cancelaciones_ordenes?token=" + token + "id_orden=" + numerodeorden);
        // this will enable the javascript.
        myWebView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.getSettings().setSupportMultipleWindows(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // returnPage();
    }

    private void returnPage() {
//        Bundle bundleorder = new Bundle();
//        bundleorder.putString("webviewReturn", "true");
//        Intent intent = new Intent(this, detialOrder.class);
//        intent.putExtras(bundleorder);
//        startActivity(intent);
    }
}