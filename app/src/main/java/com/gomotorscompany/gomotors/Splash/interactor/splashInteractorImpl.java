package com.gomotorscompany.gomotors.Splash.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.gomotorscompany.gomotors.Dialogs.Alert.model.requestAvailable;
import com.gomotorscompany.gomotors.Dialogs.Alert.model.responseAvailable;
import com.gomotorscompany.gomotors.Dialogs.Alert.util.availableService;
import com.gomotorscompany.gomotors.Splash.model.dataSplash;
import com.gomotorscompany.gomotors.Splash.model.requestSplash;
import com.gomotorscompany.gomotors.Splash.model.responseSplash;
import com.gomotorscompany.gomotors.Splash.presenter.presentersplash;
import com.gomotorscompany.gomotors.Splash.utils.splashService;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.gomotorscompany.gomotors.retrofit.RetrofitClientADMIN;
import com.gomotorscompany.gomotors.retrofit.RetrofitClientV3;
import com.gomotorscompany.gomotors.retrofit.RetrofitValidationsV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class splashInteractorImpl  implements  splashInteractor{


    private presentersplash presenter;
    private Context context;
    private Retrofit retrofitClient,retrofitClientAdmin;
    private splashService service;
    private availableService service2;

    public splashInteractorImpl(presentersplash presenter, Context context)
    {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV3.getRetrofitInstancev3();
        service = retrofitClient.create(splashService.class);

        retrofitClientAdmin= RetrofitClientADMIN.getRetrofitInstance();
        service2 = retrofitClientAdmin.create(availableService.class);
    }

    @Override
    public void requestsplashData() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            getplashData(token);
        }
    }

    @Override
    public void getAvailable() {
        requestAvailable request= new requestAvailable("3","gomotors");
        Call<responseAvailable> call = service2.getAvaileble(request);
        call.enqueue(new Callback<responseAvailable>() {
            @Override
            public void onResponse(Call<responseAvailable> call, Response<responseAvailable> response) {
                if(response.body().getResconseCode()==200){
                    if(response.body().getData().getAvailable().equals("0")){
                        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove(GeneralConstantsV2.TOKEN_PREFERENCES);
                        editor.apply();
                        presenter.setDialog();
                    }else{
                        Log.e("available",""+response.body().getData().getAvailable());
                    }
                }else {
                    Log.e("available",""+response.body().getResconseCode());
                }
            }

            @Override
            public void onFailure(Call<responseAvailable> call, Throwable t) {
                Log.e("available",""+t.getMessage());
            }
        });
    }

    private void getplashData(String token) {
        requestSplash request= new requestSplash(token);
        Call<responseSplash> call=service.getSplashData(request);
        call.enqueue(new Callback<responseSplash>() {
            @Override
            public void onResponse(Call<responseSplash> call, Response<responseSplash> response) {
                validateCodesplashdata(response,context);
            }

            @Override
            public void onFailure(Call<responseSplash> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCodesplashdata(Response<responseSplash> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getSplashdata(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getSplashdata(Response<responseSplash> response, Context context) {
        responseSplash resp=response.body();
        if(resp!=null)
        {
            String message= resp.getMessage();
            String responsCode=resp.getResponseCode();
            if(responsCode.equals("S001"))
            {
                List<dataSplash> data=resp.getData();
                if(data!=null)
                {
                    String nameapp= data.get(0).getAppName();
                    String colorApp= data.get(0).getRgbaColor();
                    String Imageapp= data.get(0).getUrlsplashimage();


                    SharedPreferences preferencias=context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES,Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferencias.edit();

                    editor.putString(GeneralConstantsV2.APP_NAME, nameapp);
                    editor.putString(GeneralConstantsV2.RGBA_COLOR, colorApp);
                    editor.putString(GeneralConstantsV2.URL_SPLASH_IMAGE, Imageapp);

                    editor.commit();
                    presenter.setImageSplash(Imageapp);
                    presenter.setMaincolor(colorApp);
                }
            }
        }
    }
}
