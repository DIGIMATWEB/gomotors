package com.gomotorscompany.gomotors.Login.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.gomotorscompany.gomotors.Login.model.LoginRequestV2;
import com.gomotorscompany.gomotors.Login.model.LoginResponseV2;
import com.gomotorscompany.gomotors.Login.model.UserDataV2;
import com.gomotorscompany.gomotors.Login.model.isActiveResponse;
import com.gomotorscompany.gomotors.Login.model.isactiveRequest;
import com.gomotorscompany.gomotors.Login.presenter.LoginPresenter;
import com.gomotorscompany.gomotors.Login.utils.LoginServicesV2;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.gomotorscompany.gomotors.retrofit.RetrofitClientV3;
import com.gomotorscompany.gomotors.retrofit.RetrofitValidationsV2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginInteractorImpl implements LoginInteractor {
    private Context context;
    private LoginPresenter presenter;
    private Retrofit retrofitClient;
    private LoginServicesV2 service;
    public LoginInteractorImpl(LoginPresenter presenter,Context context)
    {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV3.getRetrofitInstancev3();
        service = retrofitClient.create(LoginServicesV2.class);
    }


    @Override
    public void requestLogin(String telephone,String pass   ) {

        if(!telephone.equals("")|!pass.equals(""))
        {
            requestokLogin(telephone,pass);
        }
        else{
            Toast.makeText(context, "Informacion incorrecta", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void checkStatus(String token) {
      isactiveRequest request= new isactiveRequest(token)  ;
      Call<isActiveResponse> call=service.isactives(request);
      call.enqueue(new Callback<isActiveResponse>() {
          @Override
          public void onResponse(Call<isActiveResponse> call, Response<isActiveResponse> response) {
              if(response.code()==200){
                  if(response.body().getActivo()==1){
                      Toast.makeText(context, "Tu usuario no se encuentra activo", Toast.LENGTH_LONG).show();
                  }else{
                      presenter.succesLogin();
                  }
              }else{
                  Toast.makeText(context, "response: "+response.message(), Toast.LENGTH_LONG).show();
              }
          }

          @Override
          public void onFailure(Call<isActiveResponse> call, Throwable t) {
              Toast.makeText(context, "response: "+t.getMessage(), Toast.LENGTH_LONG).show();
          }
      });
    }

    private void requestokLogin(String user, String pass) {
        LoginRequestV2 request= new LoginRequestV2(user,pass);
        presenter.showDialog();
        Call<LoginResponseV2> call=service.login(request);
        call.enqueue(new Callback<LoginResponseV2>() {
            @Override
            public void onResponse(Call<LoginResponseV2> call, Response<LoginResponseV2> response) {
             //   Toast.makeText(context, "response: "+response.body().getResponseCode(), Toast.LENGTH_SHORT).show();
                validationCodeLogin(response,context,user);

            }

            @Override
            public void onFailure(Call<LoginResponseV2> call, Throwable t) {
                Toast.makeText(context, "response: "+t.getMessage(), Toast.LENGTH_LONG).show();
                presenter.hideDialog();
            }
        });

    }

    private void validationCodeLogin(Response<LoginResponseV2> response, Context context, String email) {
        if (response != null) {
            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                loginresponseData(response,context,email);
            }else {
               // Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loginresponseData(Response<LoginResponseV2> response, Context context, String email) {
        LoginResponseV2 myresponse=response.body();
        if(myresponse!=null)
        {
            String code=myresponse.getResponseCode();
            String message=myresponse.getMessage();
            if(code.equals("S001"))
            {
                UserDataV2[] data=myresponse.getData();
                //Toast.makeText(context, "nomnbre "+data.getEmployeeName()+" telofono "+data.getTelefono()+"  token "+data.getToken(), Toast.LENGTH_SHORT).show();
                //Log.e("login",""+"nomnbre "+data.getEmployeeName()+" telofono "+data.getTelefono()+"  token "+data.getToken());

                 String nombre=data[0].getEmployeeName();
                 String telefono=data[0].getTelefono();
                 String token=data[0].getToken();
                 String cve=data[0].getCve();
                 int permisionID= data[0].getPermissionsId();
                Log.e("credenciales","value   "+permisionID);
                SharedPreferences preferencias=context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferencias.edit();

                editor.putString(GeneralConstantsV2.USER_PREFERENCES, nombre);
                editor.putString(GeneralConstantsV2.TELEPHONE_PREFERENCE, telefono);
                editor.putString(GeneralConstantsV2.EMAIL_PREFERENCES, email);
                editor.putString(GeneralConstantsV2.TOKEN_PREFERENCES, token);
                editor.putString(GeneralConstantsV2.LEVEL_PERMISIONS, String.valueOf(permisionID));
                editor.putString(GeneralConstantsV2.CVE, cve);
                editor.commit();
                presenter.succes();
                presenter.hideDialog();
                //Log.e("login",""+preferencias.getString(GeneralConstantsV2.USER_PREFERENCES, null));

            }else{
                Toast.makeText(context, "response : " + code+" "+message , Toast.LENGTH_SHORT).show();
                presenter.hideDialog();
            }
        }
    }
}
