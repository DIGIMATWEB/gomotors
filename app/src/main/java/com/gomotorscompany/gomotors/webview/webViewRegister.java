package com.gomotorscompany.gomotors.webview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gomotorscompany.gomotors.Login.view.LoginContainer;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.mainContent.view.mainContentViewImpl;

public class webViewRegister extends AppCompatActivity {
    public static final String TAG = webViewRegister.class.getSimpleName();
    private WebView myWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        myWebView = findViewById(R.id.webviewid);

        // loading http://www.google.com url in the WebView.
        myWebView.loadUrl("http://gomosystem-001-site3.etempurl.com/SOLICITUDES/RegistroSolicitudRepartidor?codigo_plataforma=ZP-986");
        Log.e("consultaWEb","http://gomosystem-001-site3.etempurl.com/SOLICITUDES/RegistroSolicitudRepartidor?codigo_plataforma=ZP-986");
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
        returnPage();
    }

    private void returnPage() {
        Bundle bundleorder = new Bundle();
       // bundleorder.putString("webviewReturn", "true");//todo el bundle no es necesario ya que se regresa a la pantalla de login
        Intent intent = new Intent(this, LoginContainer.class);
        intent.putExtras(bundleorder);
        startActivity(intent);
    }


}
