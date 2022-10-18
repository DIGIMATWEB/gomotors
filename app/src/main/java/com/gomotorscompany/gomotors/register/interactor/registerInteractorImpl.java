package com.gomotorscompany.gomotors.register.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.gomotorscompany.gomotors.register.model.requestRegister;
import com.gomotorscompany.gomotors.register.model.responseRegister;
import com.gomotorscompany.gomotors.register.presenter.presenter;
import com.gomotorscompany.gomotors.register.util.utilRegister;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.gomotorscompany.gomotors.retrofit.RetrofitClientV3;
import com.gomotorscompany.gomotors.retrofit.RetrofitValidationsV2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class registerInteractorImpl  implements registerInteractor{
    private presenter mpresenter;
    private Context context;
    private utilRegister service;
    private Retrofit retrofitClient;
    public  registerInteractorImpl(presenter mpresenter,Context context)
    {
        this.mpresenter=mpresenter;
        this.context=context;
        retrofitClient = RetrofitClientV3.getRetrofitInstancev3();
        service = retrofitClient.create(utilRegister.class);

    }
    @Override
    public void requestRegister(String name, String apellido, String correo, String telefono, String fecha, String contraseña, int mtoogle) {
        requestRegister request=new requestRegister(name,apellido,correo,telefono,fecha,contraseña,mtoogle);
        Call<responseRegister> call=service.setNewUser(request);
        call.enqueue(new Callback<responseRegister>() {
            @Override
            public void onResponse(Call<responseRegister> call, Response<responseRegister> response) {
                if (response != null) {

                            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                                register(response, context);
                            } else {
                                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
                            }
                        }
            }

            @Override
            public void onFailure(Call<responseRegister> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void register(Response<responseRegister> response, Context context) {
        responseRegister resp=response.body();
         if(resp!=null) {
                    String message = resp.getMessage();
                    String responsCode = resp.getResponseCode();
                        if(responsCode!=null) {
                            if (responsCode.equals("S0032")) {

                                String nombre=resp.getNombre();
                                String telefono=resp.getTelefono();
                                String token=resp.getToken();
                                int permisionID= resp.getPermision();
                                Log.e("credenciales","value   "+permisionID);
                                SharedPreferences preferencias=context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES,Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=preferencias.edit();

                                editor.putString(GeneralConstantsV2.USER_PREFERENCES, nombre);
                                editor.putString(GeneralConstantsV2.TELEPHONE_PREFERENCE, telefono);
                                editor.putString(GeneralConstantsV2.TOKEN_PREFERENCES, token);
                                editor.putString(GeneralConstantsV2.LEVEL_PERMISIONS, String.valueOf(permisionID));

                                editor.commit();


                                mpresenter.responseRegister(105);
                            }
                        }else
                        {
                            Toast.makeText(context, "responseCode null", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

}
