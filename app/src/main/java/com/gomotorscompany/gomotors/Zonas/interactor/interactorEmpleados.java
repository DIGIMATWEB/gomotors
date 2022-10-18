package com.gomotorscompany.gomotors.Zonas.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.gomotorscompany.gomotors.Zonas.model.dataRapartidores;
import com.gomotorscompany.gomotors.Zonas.model.requestRepartidores;
import com.gomotorscompany.gomotors.Zonas.model.responseRepartidores;
import com.gomotorscompany.gomotors.Zonas.presenter.empleadorsPresenter;
import com.gomotorscompany.gomotors.Zonas.util.repartidores;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.gomotorscompany.gomotors.retrofit.RetrofitClientV3;
import com.gomotorscompany.gomotors.retrofit.RetrofitValidationsV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorEmpleados implements interactorEmpleadosInterface{
      private Context context;
        private empleadorsPresenter presenter;

    private Retrofit retrofitClient2;
    private repartidores service2;

    public interactorEmpleados(empleadorsPresenter presenter, Context context) {
        this.presenter=presenter;
        this.context=context;


        retrofitClient2= RetrofitClientV3.getRetrofitInstancev3();
        service2=retrofitClient2.create(repartidores.class);
    }

    @Override
    public void requesEmployes() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String tokenr     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(tokenr!=null)
        {
            requestRepartidores(tokenr);
        }
    }

    private void requestRepartidores(String tokenr) {
        requestRepartidores request= new requestRepartidores(tokenr);
        //  presenter.showProgressDialog();
        Call<responseRepartidores> call=service2.getRepartidores(request);
        call.enqueue(new Callback<responseRepartidores>() {
            @Override
            public void onResponse(Call<responseRepartidores> call, Response<responseRepartidores> response) {
                validateResponseRepartidores(response,context);
            }

            @Override
            public void onFailure(Call<responseRepartidores> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateResponseRepartidores(Response<responseRepartidores> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getrepartidoresdata(response,context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void getrepartidoresdata(Response<responseRepartidores> response, Context context) {
        responseRepartidores resp=response.body();
        if(resp!=null) {
            String message= resp.getMessage();
            String responsCode=resp.getResponseCode();
            if(responsCode.equals("S0015"))
            {
                List<dataRapartidores> data=resp.getData();
                if(data!=null)
                {
                    presenter.setRepartidores(data);
                }

                //    presenter.hideProgressDialog();

            }else
            {
                Log.e("repartidoresI"," codigoresponse diferente");
                //  presenter.hideProgressDialog();
            }
        }else
        {
            Log.e("repartidoresI"," response null");
            Toast.makeText(context, ""+response.code()+" " + response.message(), Toast.LENGTH_SHORT).show();
            // presenter.hideProgressDialog();
        }


    }
}
