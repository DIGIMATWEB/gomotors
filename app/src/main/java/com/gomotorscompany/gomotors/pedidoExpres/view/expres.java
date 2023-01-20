package com.gomotorscompany.gomotors.pedidoExpres.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.gomotorscompany.gomotors.Login.view.LoginContainer;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.mainContent.view.mainContentViewImpl;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;


public class expres extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = expres.class.getSimpleName();
    private static final int REQUEST_CODE_SPEECH_INPUT = 1101 ;
    private TextView recordT;
    private ImageButton recordB,logout;
    private Bundle mainbundle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainbundle = savedInstanceState;
        setContentView(R.layout.pedido_expres);
        initView();
    }

    private void initView() {
        recordT=findViewById(R.id.recordT);
        recordB=findViewById(R.id.recordB);
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(this);
        recordB.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT:{
                if(resultCode== RESULT_OK && null!= data)
                {
                    ArrayList<String> result= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    recordT.setText(result.get(0));
                }
                break;
            }
        }
    }
    private void gotoLogin() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        //preferences.edit().clear().commit();

        Intent intent = new Intent(this, LoginContainer.class);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            extras.clear();
            mainbundle.clear();
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recordB:
                Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.LANGUAGE_MODEL_FREE_FORM, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"di hola");
                try {
                    startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);
                }catch(Exception e){
                    Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.logout:
                gotoLogin();

                break;
        }
    }
}
