package com.gomotorscompany.gomotors.perfil.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gomotorscompany.gomotors.Login.view.LoginContainer;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Perfile extends Fragment implements View.OnClickListener{
    public static final String TAG = Perfile.class.getSimpleName();
    private String user,telephone;
    private TextView proifilename;
    private GoogleSignInClient mSignInClient;
    private TextView logout;
    private Button BLayoutlogout;
    private Bundle mainbundle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainbundle=savedInstanceState;
        View view = inflater.inflate(R.layout.profile, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        logout=view.findViewById(R.id.textViewlogout);
        logout.setOnClickListener(this);
        BLayoutlogout=view.findViewById(R.id.BLayoutlogout);
        BLayoutlogout.setOnClickListener(this);
        user      =  preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
        telephone =preferences.getString(GeneralConstantsV2.TELEPHONE_PREFERENCE, null);
        proifilename=view.findViewById(R.id.txt_pi_names);
        if(user!=null)
        {
            proifilename.setText(user);
        }

    }
    private  void logout()
    {
        mSignInClient.signOut().addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               // Toast.makeText(getContext(), "logout "+task.isComplete(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void gotoLogin()
    {
        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
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

         case R.id.BLayoutlogout:
            // Toast.makeText(getContext(), "logut", Toast.LENGTH_SHORT).show();
             gotoLogin();
         break;
            case R.id.textViewlogout:
                // Toast.makeText(getContext(), "logut", Toast.LENGTH_SHORT).show();
                gotoLogin();
                break;
        }
    }
}
