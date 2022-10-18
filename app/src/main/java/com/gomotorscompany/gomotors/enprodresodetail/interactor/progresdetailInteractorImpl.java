package com.gomotorscompany.gomotors.enprodresodetail.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.gomotorscompany.gomotors.enprodresodetail.model.repartidor.requetsliberar;
import com.gomotorscompany.gomotors.enprodresodetail.model.repartidor.responseLiberar;
import com.gomotorscompany.gomotors.enprodresodetail.model.responseChangeStatus;
import com.gomotorscompany.gomotors.enprodresodetail.model.setStatustoOrder;
import com.gomotorscompany.gomotors.enprodresodetail.presenter.progresdetailpresenterImpl;
import com.gomotorscompany.gomotors.enprodresodetail.util.servicedetail;
import com.gomotorscompany.gomotors.enprogreso.model.responseNewLocationDriver;
import com.gomotorscompany.gomotors.enprogreso.model.setNewlocationDriver;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.gomotorscompany.gomotors.retrofit.RetrofitClientV3;
import com.gomotorscompany.gomotors.retrofit.RetrofitValidationsV2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class progresdetailInteractorImpl implements progresdetailInteractor{
    private  progresdetailpresenterImpl presenter;
    private Context context;
    private servicedetail service;
    private Retrofit retrofitClient;

    public progresdetailInteractorImpl(progresdetailpresenterImpl presenter, Context context) {
            this.presenter=presenter;
            this.context=context;
            retrofitClient = RetrofitClientV3.getRetrofitInstancev3();
            service = retrofitClient.create(servicedetail.class);
    }

    @Override
    public void changeStatus(String clientid, int idorder, int status) {
        setStatustoOrder request= new setStatustoOrder(clientid,idorder,status);
        Call<responseChangeStatus> call=service.setNewStatustoOrder(request);
        call.enqueue(new Callback<responseChangeStatus>() {
            @Override
            public void onResponse(Call<responseChangeStatus> call, Response<responseChangeStatus> response) {
                validateresponsechangestatus(response,context);
            }

            @Override
            public void onFailure(Call<responseChangeStatus> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void validateresponsechangestatus(Response<responseChangeStatus> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getdata(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getdata(Response<responseChangeStatus> response, Context context) {
          responseChangeStatus respons=response.body();
                if(respons!=null)
                {
                    String message = respons.getMessage();
                    String responsCode = respons.getResponseCode();
                    if (responsCode==null) {

                    }else if(responsCode.equals("OK"))
                    {
                        presenter.succesChangeStatus(105);
                        Toast.makeText(context, "succesnew location driver", Toast.LENGTH_SHORT).show();
                    }
                }
    }

    @Override
    public void setnewposition(double latitude, double longitude) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            setNewPosition(token,latitude,longitude);
        }
    }

    @Override
    public void liberarRepartidor() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null) {
            liberarRepartidorrequest(token);
        }
    }

    private void liberarRepartidorrequest(String token) {
        requetsliberar requets=new requetsliberar(token);
        Call<responseLiberar> call=service.requestLiberar(requets);
        call.enqueue(new Callback<responseLiberar>() {
            @Override
            public void onResponse(Call<responseLiberar> call, Response<responseLiberar> response) {
                if (response != null) {

                    if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                        setsuccesLierar(response, context);
                    } else {
                        Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<responseLiberar> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setsuccesLierar(Response<responseLiberar> response, Context context) {
          responseLiberar respons=response.body();
                        if(respons!=null)
                        {
                            String message = respons.getMessage();
                            String responsCode = respons.getResponseCode();
                            if (responsCode==null) {

                            }else if(responsCode.equals("OK"))
                            {

                                //Toast.makeText(context, "Liberar repartidor", Toast.LENGTH_SHORT).show();
                            }
                        }
    }

    private void setNewPosition(String token, double latitude, double longitude) {
        setNewlocationDriver request=new setNewlocationDriver(token,String.valueOf(latitude),String.valueOf(longitude));
        Call<responseNewLocationDriver> call=service.setLocationmiscomprasdetail(request);
        call.enqueue(new Callback<responseNewLocationDriver>() {
            @Override
            public void onResponse(Call<responseNewLocationDriver> call, Response<responseNewLocationDriver> response) {
                validateNewLocationSet(response,context);
            }

            @Override
            public void onFailure(Call<responseNewLocationDriver> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void validateNewLocationSet(Response<responseNewLocationDriver> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                setLocation(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setLocation(Response<responseNewLocationDriver> response, Context context) {
        responseNewLocationDriver respons=response.body();
        if(respons!=null)
        {
            String message = respons.getMessage();
            String responsCode = respons.getResponseCode();
            if (responsCode==null) {

            }else if(responsCode.equals("S0017"))
            {
               // Toast.makeText(context, "succesnew location driver", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
