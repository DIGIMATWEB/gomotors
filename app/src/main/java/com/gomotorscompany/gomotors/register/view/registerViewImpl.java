package com.gomotorscompany.gomotors.register.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gomotorscompany.gomotors.Login.view.LoginViewImpl;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.mainContent.view.mainContentViewImpl;
import com.gomotorscompany.gomotors.register.presenter.presenter;
import com.gomotorscompany.gomotors.register.presenter.presenterImpl;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;


public class registerViewImpl extends Fragment implements  View.OnClickListener ,viewregister {
    public static final String TAG = registerViewImpl.class.getSimpleName();
    private ImageView iconoatras;
    private Switch repartidor,cliente,administrador;
    private TextInputEditText name,apellido,correo,telefono,fecha,password;
    private DatePickerDialog.OnDateSetListener mDateSetListenr;


    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]{2,}";
    private String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private String datePattern ="^\\d{2}-\\d{2}-\\d{4}";
    private String datePattern2 ="^\\d{2}/\\d{2}/\\d{4}";
    private String datePattern3 ="^\\d{4}-\\d{2}-\\d{2}";
    private String datePattern4 ="^\\d{4}/\\d{2}/\\d{2}";

    private boolean regexName,regexApellido,regexCorreo,regexTelefono,regexFecha,regexPassword,regexSwitch=false;
    private CardView registroCrearCuenta;
    private presenter mpresenter;
    private int mtoogle =0;
    private ImageView okchec1,okchec2,okchec3,okchec4,okchec5;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login_register, container, false);
        initLoginViewImpl(view);
        return view;
    }

    private void initLoginViewImpl(View view) {
        iconoatras=view.findViewById(R.id.regresara_login);
        iconoatras.setOnClickListener(this);

        name=view.findViewById(R.id.edtUserOrEmail);
        apellido=view.findViewById(R.id.apellido);
        correo=view.findViewById(R.id.email);
        telefono=view.findViewById(R.id.telefono);
        fecha=view.findViewById(R.id.fechadenacimiento);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListenr, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                Log.e("dialogpickerregistro","date: ");
            }
        });
        password=view.findViewById(R.id.edtPassword);

        repartidor=view.findViewById(R.id.switchrepa);
        cliente=view.findViewById(R.id.switchcliente);
        administrador=view.findViewById(R.id.switchadmin);
        registroCrearCuenta=view.findViewById(R.id.registroCrearCuenta);
        okchec1=view.findViewById(R.id.okchec1);
        okchec2=view.findViewById(R.id.okchec2);
        okchec3=view.findViewById(R.id.okchec3);
        okchec4=view.findViewById(R.id.okchec4);
        okchec5=view.findViewById(R.id.okchec5);
        registroCrearCuenta.setOnClickListener(this);

        mpresenter=new presenterImpl(this,getContext());
        datePickerListener();
        listenerName();/** solo letras no simbolos*/
        listenerApellido(); /**solo letras no simbolos*/
        listenerCorreo(); /**formato de correo valido*/
        listenertelefono();/**no letras solo numeros*/
        listenerFecha();/**ntroducir dialog de fecha*/
        listenerPassword();/***/
        listenersSwitch();/**solo un swtch*/


    }

    private void listenerName() {
        name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(name.getText().length()>2){
                    regexName=true;
                    okchec1.setVisibility(View.VISIBLE);
                }else
                {
                    Toast.makeText(getContext(), "numero de caracteres invalido para nombre", Toast.LENGTH_SHORT).show();
                    regexName=false;
                    okchec1.setVisibility(View.GONE);
                    name.setText("");
                }
                return false;
            }
        });

    }
    private void listenerApellido() {
        apellido.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(apellido.getText().length()>2) {
                    regexApellido=true;
                    okchec2.setVisibility(View.VISIBLE);
                }else
                {
                    Toast.makeText(getContext(), "numero de caracteres invalido para apellido", Toast.LENGTH_SHORT).show();
                    regexApellido=false;
                    okchec2.setVisibility(View.GONE);
                    apellido.setText("");
                }
                return false;
            }
        });

    }
    private void listenerCorreo() {
        correo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                String textEmail=String.valueOf( correo.getText());
                if (textEmail.trim().matches(emailPattern) || textEmail.trim().matches(emailPattern2)) {
                    regexCorreo = true;
                    okchec3.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getContext(), "formato no valido para el correo", Toast.LENGTH_SHORT).show();
                    regexCorreo = false;
                    okchec3.setVisibility(View.GONE);
                    correo.setText("");
                }
                return false;
            }
        });
    }
    private void listenertelefono() {
        telefono.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(telefono.getText().length()==5) {
                    regexTelefono=true;
                    okchec4.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(getContext(), "el numero de digitos es incorrecto intenta de nuevo", Toast.LENGTH_SHORT).show();
                    regexTelefono=false;
                    okchec4.setVisibility(View.GONE);
                    telefono.setText("");
                }
                return false;
            }
        });
    }
    private void listenerFecha() {

        fecha.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String fechastring=String.valueOf(fecha.getText());
                if (fechastring.trim().matches(datePattern)||fechastring.trim().matches( datePattern2) ||  fechastring.trim().matches(datePattern3)||fechastring.trim().matches(datePattern4)) {
                    regexFecha = true;
                    okchec5.setVisibility(View.VISIBLE);
                } else {
                    regexFecha = false;
                    okchec5.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "formato no valido para la fecha", Toast.LENGTH_SHORT).show();
                   // fecha.setText("");
                }
                return false;
            }
        });
    }
    private void datePickerListener() {
        mDateSetListenr = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String sDay = "" + dayOfMonth;
                String sMonth = "" + (month+1);
                String years=""+year;
                String yearsdata = "";
                if (sDay.length() == 1) {
                    sDay = "0" + sDay;
                }
                if (sMonth.length() == 1) {
                    sMonth = "0" + sMonth;
                }

               String mainDate = sDay + "-" + sMonth + "-" + year;
               fecha.setText(mainDate);
            }};
    }
    private void listenerPassword() {
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(password.getText().length()>7) {
                    regexPassword=true;
                }else
                {
                    Toast.makeText(getContext(), "numero de caracteres invalido para la contraseña", Toast.LENGTH_SHORT).show();
                    regexPassword=false;
                    password.setText("");
                }
                return false;
            }
        });

    }

    public void initLoginContainerView() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(LoginViewImpl.TAG);
        transaction.replace(R.id.login_containerF, new LoginViewImpl()).commit();


    }
    private void gotomain()
    {
        Intent intent = new Intent(getActivity(), mainContentViewImpl.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);//
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void listenersSwitch() {
        repartidor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true) {
                    if (cliente.isChecked() || administrador.isChecked()) {
                        cliente.setChecked(false);
                        administrador.setChecked(false);
                        mtoogle=2;
                        // repartidor.setChecked(true);
                    }
                }
            }
        });
        cliente.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true) {
                    if (repartidor.isChecked() || administrador.isChecked()) {
                        repartidor.setChecked(false);
                        administrador.setChecked(false);
                        mtoogle=3;
                        //  cliente.setChecked(true);
                    }
                }
            }
        });
        administrador.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true) {
                    if (repartidor.isChecked() || cliente.isChecked()) {
                        repartidor.setChecked(false);
                        cliente.setChecked(false);
                        mtoogle=1;
                        // administrador.setChecked(true);
                    }
                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.regresara_login:
                initLoginContainerView();
                break;
            case R.id.registroCrearCuenta:
                checknametext();
                checkApellido();
                checkTelephone();
                checkEmail();
                checkFecha();
                checkPassword();
                checkToogle();
                if(regexName==true&&regexApellido==true&&regexTelefono==true&&regexPassword==true)//regexFecha==true&&
                {
                    Log.e("registroCrearCuenta","condicion cumplida");
                //    if(mtoogle!=0)
                //    {
                        mpresenter.requestRegister(String.valueOf( name.getText()),String.valueOf( apellido.getText()),
                                String.valueOf( correo.getText()),String.valueOf( telefono.getText()),String.valueOf( fecha.getText()),String.valueOf( password.getText()),3);//mtoogle);
                //    }else
                //    {
                //        Log.e("registroCrearCuenta","condicion nocumplida true");
                //    }

                }else
                {
                   Log.e("registroCrearCuenta","condicion nocumplida false");
                }
                break;
        }
    }

    private void checkToogle() {
        if(administrador.isChecked())
        {
            mtoogle=1;
        }else if(cliente.isChecked()){
            mtoogle=3;
        }else if(repartidor.isChecked())
        {
            mtoogle=2;
        }else
        {
            mtoogle=0;
        }
    }

    private void checkPassword() {
        if(password.getText().length()>7) {
            regexPassword=true;
        }else
        {
            Toast.makeText(getContext(), "numero de caracteres invalido para la contraseña", Toast.LENGTH_SHORT).show();
            regexPassword=false;
            password.setText("");
        }
    }

    private void checknametext() {
        if(name.getText().length()>2){
            regexName=true;
            okchec1.setVisibility(View.VISIBLE);
        }else
        {
            Toast.makeText(getContext(), "numero de caracteres invalido para nombre", Toast.LENGTH_SHORT).show();
            regexName=false;
            okchec1.setVisibility(View.GONE);
            name.setText("");
        }
    }
    private void checkApellido() {
        if(apellido.getText().length()>2) {
            regexApellido=true;
            okchec2.setVisibility(View.VISIBLE);
        }else
        {
            Toast.makeText(getContext(), "numero de caracteres invalido para apellido", Toast.LENGTH_SHORT).show();
            regexApellido=false;
            okchec2.setVisibility(View.GONE);
            apellido.setText("");
        }
    }
    private void checkTelephone() {
        if(telefono.getText().length()==10) {
            regexTelefono=true;
            okchec4.setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(getContext(), "el numero de digitos es incorrecto intenta de nuevo", Toast.LENGTH_SHORT).show();
            regexTelefono=false;
            okchec4.setVisibility(View.GONE);
            telefono.setText("");
        }

    }
    private void checkEmail()
    {
        String textEmail=String.valueOf( correo.getText());
        if (textEmail.trim().matches(emailPattern) || textEmail.trim().matches(emailPattern2)) {
            regexCorreo = true;
            okchec3.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(getContext(), "formato no valido para el correo", Toast.LENGTH_SHORT).show();
            regexCorreo = false;
            okchec3.setVisibility(View.GONE);
            correo.setText("");
        }
    }
    private void checkFecha() {
        String fechastring=String.valueOf(fecha.getText());
        if (fechastring.trim().matches(datePattern)||fechastring.trim().matches( datePattern2) ||  fechastring.trim().matches(datePattern3)||fechastring.trim().matches(datePattern4)) {
            regexFecha = true;
            okchec5.setVisibility(View.VISIBLE);
        } else {
            regexFecha = false;
            okchec5.setVisibility(View.GONE);
            Toast.makeText(getContext(), "formato no valido para la fecha", Toast.LENGTH_SHORT).show();
            // fecha.setText("");
        }
    }

    @Override
    public void setresponseRegister(int response) {
        if(response==105)
        {

           mtoogle=0;
           gotomain();
        }

    }
}
