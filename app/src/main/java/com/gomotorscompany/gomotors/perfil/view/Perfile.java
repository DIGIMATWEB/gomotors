package com.gomotorscompany.gomotors.perfil.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gomotorscompany.gomotors.Dialogs.RepartidorRegistro.keyRegister;
import com.gomotorscompany.gomotors.Login.view.LoginContainer;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.gomotorscompany.gomotors.webview.webViewChat;
import com.gomotorscompany.gomotors.webview.webViewGananciasAdeudos;
import com.gomotorscompany.gomotors.webview.webViewNotificaciones;
import com.gomotorscompany.gomotors.webview.webViewPerfilRepartidor;
import com.gomotorscompany.gomotors.webview.webViewTerminos;
import com.gomotorscompany.gomotors.webview.webViewTickets;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
public class Perfile extends Fragment implements View.OnClickListener {
    public static final String TAG = Perfile.class.getSimpleName();
    private String user, telephone, email;
    private TextView proifilename, txt_pi_email,cve;
    private GoogleSignInClient mSignInClient;
    private TextView textView2, textView3;
    private ImageView textView4, textView12, textView13, textView30, logout;
    private Button BLayoutlogout;
    private Bundle mainbundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainbundle = savedInstanceState;
        View view = inflater.inflate(R.layout.profile, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String gerarquia = preferences.getString(GeneralConstantsV2.LEVEL_PERMISIONS, null);
        String mcve =preferences.getString(GeneralConstantsV2.CVE,null);
        Log.e("gerarquiaP", "" + gerarquia);
        logout = view.findViewById(R.id.textViewlogout);//perfil reparitidor
        txt_pi_email = view.findViewById(R.id.txt_pi_email);
        textView2 = view.findViewById(R.id.textView2);  //tickets
        textView2.setOnClickListener(this);
        textView3 = view.findViewById(R.id.textView3); //registro repartidor
        textView3.setOnClickListener(this);
        textView4 = view.findViewById(R.id.textView4); //ganancias adeudos
        textView4.setOnClickListener(this);
        textView12 = view.findViewById(R.id.textView12); //notificaciones
        textView12.setOnClickListener(this);
        textView13 = view.findViewById(R.id.textView13); //Terminos y condiciones
        textView13.setOnClickListener(this);
        textView30 = view.findViewById(R.id.textView30); //chat
        textView30.setOnClickListener(this);
        cve= view.findViewById(R.id.cve);
        cve.setText(mcve);
        logout.setOnClickListener(this);
        BLayoutlogout = view.findViewById(R.id.BLayoutlogout);
        BLayoutlogout.setOnClickListener(this);
        user = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
        telephone = preferences.getString(GeneralConstantsV2.TELEPHONE_PREFERENCE, null);
        email = preferences.getString(GeneralConstantsV2.EMAIL_PREFERENCES, null);
        proifilename = view.findViewById(R.id.txt_pi_names);
        if (user != null) {
            proifilename.setText(user);
            txt_pi_email.setText(email);
        }
        if (!gerarquia.equals("2")) {
            textView4.setVisibility(View.GONE);
            textView12.setVisibility(View.GONE);
            textView30.setVisibility(View.GONE);
            logout.setVisibility(View.GONE);
            textView13.setVisibility(View.VISIBLE);
        }

    }

    private void logout() {
        mSignInClient.signOut().addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                // Toast.makeText(getContext(), "logout "+task.isComplete(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void gotoLogin() {
        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        //preferences.edit().clear().commit();

        Intent intent = new Intent(getActivity(), LoginContainer.class);
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
            case R.id.textView4://todo ganancias adeudos
                Intent intent1 = new Intent(getActivity(), webViewGananciasAdeudos.class);
                startActivity(intent1);
                getActivity().finish();
                break;
            case R.id.textView12://todo notificaciones
                Intent intent2 = new Intent(getActivity(), webViewNotificaciones.class);
                startActivity(intent2);
                getActivity().finish();
                break;
            case R.id.textView13:
                Intent intent3 = new Intent(getActivity(), webViewTerminos.class);
                startActivity(intent3);
                getActivity().finish();
                break;
            case R.id.textView30:
                Intent intent4 = new Intent(getActivity(), webViewChat.class);
                startActivity(intent4);
                getActivity().finish();
                break;
            case R.id.textViewlogout:
                Intent intent5 = new Intent(getActivity(), webViewPerfilRepartidor.class);
                startActivity(intent5);
                getActivity().finish();
                break;
            case R.id.BLayoutlogout:
                // Toast.makeText(getContext(), "logut", Toast.LENGTH_SHORT).show();
                gotoLogin();
                break;

            case R.id.textView2:// este link es el de tickets
                Intent intent = new Intent(getActivity(), webViewTickets.class);
                startActivity(intent);
                getActivity().finish();


               /* String username="";
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW, Uri.parse("http://200.92.216.34:83/Administrador/AdministradorLogin?usuario=qptest&&password=0A-61-5F-AB-0A-20-75-85-B8-91-8E-95-A7-A2-25-6D"));
                Bundle bundle = new Bundle();
                bundle.putString("iv-user", username);
                browserIntent.putExtra(Browser.EXTRA_HEADERS, bundle);
                getActivity().startActivity(browserIntent);
                getActivity().finish();*/
                break;
            case R.id.textView3://este dialogo es el key para pasar al registo
                keyRegister externalGPSDialog = new keyRegister();
                externalGPSDialog.show(getActivity().getSupportFragmentManager(), keyRegister.TAG);

                break;
        }
    }
}
