package com.gomotorscompany.gomotors.enprogreso.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.gomotorscompany.gomotors.enprodresodetail.model.repartidor.requetsliberar;
import com.gomotorscompany.gomotors.enprodresodetail.model.repartidor.responseLiberar;
import com.gomotorscompany.gomotors.enprogreso.model.chekpending.dataorderspending;
import com.gomotorscompany.gomotors.enprogreso.model.chekpending.requestpending;
import com.gomotorscompany.gomotors.enprogreso.model.chekpending.responsependientes;
import com.gomotorscompany.gomotors.enprogreso.model.responseNewLocationDriver;
import com.gomotorscompany.gomotors.enprogreso.model.setNewlocationDriver;
import com.gomotorscompany.gomotors.enprogreso.presenter.presenterrequestOrders;
import com.gomotorscompany.gomotors.enprogreso.util.servicegetOrders;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;
import com.gomotorscompany.gomotors.miscompras.model.get.requestGetOrder;
import com.gomotorscompany.gomotors.miscompras.model.get.responseGetOrder;
import com.gomotorscompany.gomotors.pedido.model.others.responseAsignaciondelaOrden;
import com.gomotorscompany.gomotors.pedido.model.others.setAginaciondeOrdenes;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.gomotorscompany.gomotors.retrofit.RetrofitClientV3;
import com.gomotorscompany.gomotors.retrofit.RetrofitValidationsV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ordersInteractorImpl  implements ordersInteractor{

    private Context context;
    private presenterrequestOrders presenter;
    private servicegetOrders service;
    private Retrofit retrofitClient;
    public  ordersInteractorImpl(presenterrequestOrders presenter,Context context)
    {
        this.presenter=presenter;
        this.context=context;
            retrofitClient = RetrofitClientV3.getRetrofitInstancev3();
            service = retrofitClient.create(servicegetOrders.class);
    }


    @Override
    public void setpositionDriver(double latitude, double longitude) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            Log.e("token",""+token);
            setNewPosition(token,latitude,longitude);
        }

    }

    private void setNewPosition(String token, double latitude, double longitude) {
        setNewlocationDriver request=new setNewlocationDriver(token,String.valueOf(latitude),String.valueOf(longitude));
        Call<responseNewLocationDriver> call=service.setLocationNew(request);
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
            //    Toast.makeText(context, "succesnew location driver", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void requesAllOrders() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            postOrders(token);
        }
    }

    private void postOrders(String token) {
        requestGetOrder request= new requestGetOrder(token);
        presenter.showprogresdialog();
        Call<responseGetOrder> call=service.getFullOrders(request);
        call.enqueue(new Callback<responseGetOrder>() {
            @Override
            public void onResponse(Call<responseGetOrder> call, Response<responseGetOrder> response) {
                validateCodegetOrders(response,context);
            }

            @Override
            public void onFailure(Call<responseGetOrder> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
     private void validateCodegetOrders(Response<responseGetOrder> response, Context context) {
            if (response != null) {

                if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                    getOrdersdata(response, context);
                } else {
                    Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
                }
            }
        }

    private void getOrdersdata(Response<responseGetOrder> response, Context context) {
        responseGetOrder resp=response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            String responsCode = resp.getResponseCode();
            if (responsCode==null) {
                presenter.hideprogresdialog();
            }else if(responsCode.equals("OK"))
            {
                List<datagetOrders> data=resp.getData();
                if(data!=null)
                {
                    for(int i =0;i<data.size();i++) {

                    }
                    presenter.setdatatoView(data);
                    presenter.hideprogresdialog();
                }else
                {

                    presenter.hideprogresdialog();
                }
            }
        }else{ presenter.hideprogresdialog();}
    }
    @Override
    public void liberarRepartidor() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null) {
            liberarRepartidorrequest(token);
        }
    }

    @Override
    public void checkencola() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null) {
            checkencolarequest(token);
        }
    }

    private void checkencolarequest( String token) {
        requestpending request=new requestpending(token);
        Call<responsependientes> call=service.checkpendings(request);
        call.enqueue(new Callback<responsependientes>() {
            @Override
            public void onResponse(Call<responsependientes> call, Response<responsependientes> response) {
                if (response != null) {

                    if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                        succescheckpendings(response, context);
                    } else {
                        Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<responsependientes> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        } );
    }

    private void succescheckpendings(Response<responsependientes> response, Context context) {
/***/
        responsependientes respons=response.body();
        if(respons!=null)
        {
            String message = respons.getMessage();
            String responsCode = respons.getResponseCode();
            if (responsCode==null) {

            }else if(responsCode.equals("OK"))
            {
                List<dataorderspending> data=respons.getData();
                if(data!=null)
                {
                   // Toast.makeText(context, ""+data.size(), Toast.LENGTH_SHORT).show();
                    presenter.setPendingsToview(data);
                }
                //Toast.makeText(context, "Liberar repartidor", Toast.LENGTH_SHORT).show();
            }
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

    @Override
    public void asignOrderRepartidor(int ordenNum) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null) {
            setOrdertoRepartidor(token, ordenNum);
        }

    }
    private void setOrdertoRepartidor(String token, Integer ordenNum) {
        setAginaciondeOrdenes request=new setAginaciondeOrdenes(token,ordenNum);/**cambiar endpoint*/
        Call<responseAsignaciondelaOrden> call=service.setReapartidorToOrder(request);
        call.enqueue(new Callback<responseAsignaciondelaOrden>() {
            @Override
            public void onResponse(Call<responseAsignaciondelaOrden> call, Response<responseAsignaciondelaOrden> response) {
                validatesetRepartidores(response,context);
            }

            @Override
            public void onFailure(Call<responseAsignaciondelaOrden> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void validatesetRepartidores(Response<responseAsignaciondelaOrden> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                setrepartidoretoorder(response,context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setrepartidoretoorder(Response<responseAsignaciondelaOrden> response, Context context) {
        responseAsignaciondelaOrden resp = response.body();
        if (resp != null) {
            String message = resp.getMessage();
            String responsCode = resp.getResponseCode();
            if (responsCode == null) {

            } else if (responsCode.equals("OK")) {

               presenter.succesAsignRepartidor(200);

            }
        }
    }
}
