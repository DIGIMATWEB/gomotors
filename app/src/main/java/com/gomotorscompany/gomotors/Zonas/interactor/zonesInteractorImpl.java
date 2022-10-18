package com.gomotorscompany.gomotors.Zonas.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.gomotorscompany.gomotors.Zonas.model.Zona;
import com.gomotorscompany.gomotors.Zonas.model.datarequesZonas;
import com.gomotorscompany.gomotors.Zonas.model.requestEraseZone;
import com.gomotorscompany.gomotors.Zonas.model.requestUpdateZones;
import com.gomotorscompany.gomotors.Zonas.model.requestZonas;
import com.gomotorscompany.gomotors.Zonas.model.responseEraseZone;
import com.gomotorscompany.gomotors.Zonas.model.responseUpdateZones;
import com.gomotorscompany.gomotors.Zonas.model.responseZonas;
import com.gomotorscompany.gomotors.Zonas.presenter.zonesPresenter;
import com.gomotorscompany.gomotors.Zonas.util.zonesService;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.gomotorscompany.gomotors.retrofit.RetrofitClientV2;
import com.gomotorscompany.gomotors.retrofit.RetrofitValidationsV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class zonesInteractorImpl  implements zonesInteractor{
    private Context context;
    private zonesPresenter presenter;
    private Retrofit retrofitClient;
    private zonesService service;
    private String  token;
    public zonesInteractorImpl(zonesPresenter presenter,Context context)
    {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        service = retrofitClient.create(zonesService.class);


    }


    @Override
    public void requesZones() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            requestColonias(token);
        }
    }

    @Override
    public void updateZone(List<Zona> zonas) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            updateDotszone(token,zonas);

        }
    }

    @Override
    public void eraseZone(int id) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            erasetheZone(token,id);
            
        }
    }



    private void erasetheZone(String token, int id) {
        requestEraseZone request=new requestEraseZone(token,id);
        presenter.showProgressDialog();
        Call<responseEraseZone> call=service.eraseZone(request);
        call.enqueue(new Callback<responseEraseZone>() {
            @Override
            public void onResponse(Call<responseEraseZone> call, Response<responseEraseZone> response) {
                validateCodeEraseZone(response,context);
            }

            @Override
            public void onFailure(Call<responseEraseZone> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCodeEraseZone(Response<responseEraseZone> response, Context context) {

        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                eraseMZones(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }



    private void updateDotszone(String token, List<Zona> zonas) {
        requestUpdateZones request= new requestUpdateZones(token,zonas);
        presenter.showProgressDialog();
        Call<responseUpdateZones> call=service.updateZones(request);
        call.enqueue(new Callback<responseUpdateZones>() {
            @Override
            public void onResponse(Call<responseUpdateZones> call, Response<responseUpdateZones> response) {
                validateCodeupdateZone(response,context);
            }

            @Override
            public void onFailure(Call<responseUpdateZones> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
                presenter.hideProgressDialog();
            }
        });
    }

    private void validateCodeupdateZone(Response<responseUpdateZones> response, Context context) {

        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                updateMZones(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }



    private void requestColonias(String token) {
        requestZonas request= new requestZonas(token,true);
        presenter.showProgressDialog();
        Call<responseZonas> call=service.getZones(request);
        call.enqueue(new Callback<responseZonas>() {
            @Override
            public void onResponse(Call<responseZonas> call, Response<responseZonas> response) {
                validateCodeColonias(response,context);
            }

            @Override
            public void onFailure(Call<responseZonas> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCodeColonias(Response<responseZonas> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getColonias(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void eraseMZones(Response<responseEraseZone> response, Context context) {
        responseEraseZone resp=response.body();
        if(resp!=null) {
            String message= resp.getMessage();
            String responsCode=resp.getResponseCode();
            if(responsCode.equals("S0020"))
            {

                presenter.hideProgressDialog();
                presenter.reloadScreen();
            }else
            {
                Toast.makeText(context, ""+response.code()+" " + response.message(), Toast.LENGTH_SHORT).show();
                presenter.hideProgressDialog();
            }
        }else
        {
            Toast.makeText(context, ""+response.code()+" " + response.message(), Toast.LENGTH_SHORT).show();
            presenter.hideProgressDialog();
        }

        // presenter.hideProgressDialog();
        //                presenter.reloadScreen();
    }
    private void updateMZones(Response<responseUpdateZones> response, Context context) {
        responseUpdateZones res=response.body();
        if(res!=null) {
            String message= res.getMessage();
            String responsCode=res.getResponseCode();
            if(responsCode.equals("S001"))
            {

                presenter.hideProgressDialog();
                presenter.reloadScreen();
            }else
            {
                Toast.makeText(context, ""+response.code()+" " + response.message(), Toast.LENGTH_SHORT).show();
                presenter.hideProgressDialog();
            }
        }

    }
    private void getColonias(Response<responseZonas> response, Context context) {
        responseZonas respons=response.body();
        if(respons!=null)
        {
            String message= respons.getMessage();
            String responsCode=respons.getResponseCode();
            if(responsCode.equals("S0020"))
            {
                List<datarequesZonas> data=respons.getData();
                if(data!=null)
                {
                    presenter.setZones(data);
                    presenter.hideProgressDialog();
                }else
                {
                    Toast.makeText(context, "no existen zonas", Toast.LENGTH_SHORT).show();
                    presenter.hideProgressDialog();
                }

            }else
            {
                Toast.makeText(context, ""+response.code()+" " + response.message(), Toast.LENGTH_SHORT).show();
                presenter.hideProgressDialog();
            }
        }
    }
}
