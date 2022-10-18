package com.gomotorscompany.gomotors.Ordenar.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.gomotorscompany.gomotors.Ordenar.model.newmenu.menuDatan;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.requesMenun;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.responseMenun;
import com.gomotorscompany.gomotors.Ordenar.presenter.mainpresenterViewn;
import com.gomotorscompany.gomotors.Ordenar.util.fullMenuServicen;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.gomotorscompany.gomotors.retrofit.RetrofitClientV3;
import com.gomotorscompany.gomotors.retrofit.RetrofitValidationsV2;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class getfullMenuInteractorImplementsn implements  getfullMenuInteractor{

    private Context context;
    private mainpresenterViewn presenter;
    private fullMenuServicen service;
    private Retrofit retrofitClient;
    private String  token;
    public getfullMenuInteractorImplementsn(mainpresenterViewn presenter, Context context)
    {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV3.getRetrofitInstancev3();
        service = retrofitClient.create(fullMenuServicen.class);

    }


    @Override
    public void requestFullData() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            requestService(token);
        }

    }

    @Override
    public void requestProductos() {

    }

    @Override
    public void requestaloneComplements() {

    }

    private void requestService(String token) {
        requesMenun request=new requesMenun(token,1,0.0,0.0);
        presenter.showProgressDialog();
        Call<responseMenun> call= service.getFullMenu(request);
        call.enqueue(new Callback<responseMenun>() {
            @Override
            public void onResponse(Call<responseMenun> call, Response<responseMenun> response) {
                validateCodefullMenu(response,context);
            }

            @Override
            public void onFailure(Call<responseMenun> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCodefullMenu(Response<responseMenun> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getMenudata(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getMenudata(Response<responseMenun> response, Context context) {
        responseMenun resp=response.body();
        if(resp!=null)
        {
            String message= resp.getMessage();
            String responsCode=resp.getResponseCode();
            if(responsCode.equals("S001")||responsCode.equals("-2147467261"))
            {
                List<menuDatan> data=resp.getData();
                if(data!=null)
                {
                    SharedPreferences preferencias=context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES,Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferencias.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(data);
                    editor.putString("MyObject", json);
                    editor.commit();
                    //presenter.setComercios(data);
                }
                presenter.hideProgressDialog();
            }

        }

    }
}
