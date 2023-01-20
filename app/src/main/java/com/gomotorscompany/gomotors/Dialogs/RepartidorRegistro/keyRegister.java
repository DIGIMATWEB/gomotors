package com.gomotorscompany.gomotors.Dialogs.RepartidorRegistro;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.webview.webViewRegister;

public class keyRegister extends DialogFragment implements View.OnClickListener {
    public static final String TAG = keyRegister.class.getSimpleName();
    private Button iralmenu;
    private String secretWord;
    private EditText label;
    //  private mainContentViewImpl mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.key_introduce, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent);
        setCancelable(false);
        // mainActivity = (mainContentViewImpl) this.getActivity();
        initDialog(view);
        //setFonts();
        return view;
    }

    private void initDialog(View view) {
        label= view.findViewById(R.id.editText);
        iralmenu = view.findViewById(R.id.ValidateCode);
        iralmenu.setOnClickListener(this);

    }

    public void closeDialog() {
        this.dismiss();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.externalgpsconstraint:
//                closeDialog();
//                break;
            case R.id.ValidateCode:
                //todo valida el codigo y manda a la webview http://200,92,216,34:83/SOLICITUDES/RegistroSolicitudRepartidor
                //todo se necesita saber cual seria el usuario generico
                //todo se necesita saber cual sera la  palabra clave para acceder

                if(label.getText().toString().equals("GM-986")){
                    //Log.e("currentTexfield",""+label.getText());
                    Intent intent = new Intent(getActivity(), webViewRegister.class);
                    startActivity(intent);
                    getActivity().finish();
                    closeDialog();
                }else{
                    Toast.makeText(getContext(), "el codigo es incorrecto", Toast.LENGTH_SHORT).show();
                    label.setText("");
                    closeDialog();
                }
                // mainActivity.goTommenu();
                break;
        }
    }
}