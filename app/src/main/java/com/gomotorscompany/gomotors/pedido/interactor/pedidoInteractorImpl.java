package com.gomotorscompany.gomotors.pedido.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.gomotorscompany.gomotors.Zonas.model.dataRapartidores;
import com.gomotorscompany.gomotors.Zonas.model.requestRepartidores;
import com.gomotorscompany.gomotors.Zonas.model.responseRepartidores;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;
import com.gomotorscompany.gomotors.miscompras.model.get.requestGetOrder;
import com.gomotorscompany.gomotors.miscompras.model.get.responseGetOrder;
import com.gomotorscompany.gomotors.pedido.model.others.responseAsignaciondelaOrden;
import com.gomotorscompany.gomotors.pedido.model.others.setAginaciondeOrdenes;
import com.gomotorscompany.gomotors.pedido.model.repartidores.dataRepartidores;
import com.gomotorscompany.gomotors.pedido.model.repartidores.requestRepartidoresCercanos;
import com.gomotorscompany.gomotors.pedido.model.repartidores.responseRepartidoresCercanos;
import com.gomotorscompany.gomotors.pedido.presenter.presenterPedidoImpl;
import com.gomotorscompany.gomotors.pedido.util.servicePedido;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.gomotorscompany.gomotors.retrofit.RetrofitClientV3;
import com.gomotorscompany.gomotors.retrofit.RetrofitValidationsV2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class pedidoInteractorImpl implements pedidoInteractor {
    private Context context;
    private presenterPedidoImpl presenter;
    private servicePedido service;
    private Retrofit retrofitClient;

    public pedidoInteractorImpl(presenterPedidoImpl presenter, Context context) {
            this.presenter=presenter;
                this.context=context;
                    retrofitClient = RetrofitClientV3.getRetrofitInstancev3();
                    service = retrofitClient.create(servicePedido.class);
    }

    @Override
    public void requestAllpedidos() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null) {
            postOrders(token);
        }
    }

    @Override
    public void requestRepartidoresMasCercanos() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null) {
            requestRepartidores(token);
        }
    }


    private void requestRepartidores(String token) {
        requestRepartidoresCercanos request=new requestRepartidoresCercanos(token);
        Call<responseRepartidoresCercanos> call=service.getRepartidoresCercanos(request);
        call.enqueue(new Callback<responseRepartidoresCercanos>() {
            @Override
            public void onResponse(Call<responseRepartidoresCercanos> call, Response<responseRepartidoresCercanos> response) {
                validateRepartidoresCercanos(response,context);
            }

            @Override
            public void onFailure(Call<responseRepartidoresCercanos> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateRepartidoresCercanos(Response<responseRepartidoresCercanos> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getRepartidoresCercano(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getRepartidoresCercano(Response<responseRepartidoresCercanos> response, Context context) {
        responseRepartidoresCercanos respons=response.body();
        if(respons!=null)
        {
            String message = respons.getMessage();
            String responsCode = respons.getResponseCode();
            if (responsCode == null) {

            } else if (responsCode.equals("OK")) {
                List<dataRepartidores> data=respons.getData();
                if(data!=null)
                {
                    presenter.setRepartidoresCercanos(data);
                }
            }
        }

    }

    private void postOrders(String token) {
        requestGetOrder request = new requestGetOrder(token);
        Call<responseGetOrder> call = service.getFullOrders(request);
        call.enqueue(new Callback<responseGetOrder>() {
            @Override
            public void onResponse(Call<responseGetOrder> call, Response<responseGetOrder> response) {
                validateCodegetOrders(response, context);
            }

            @Override
            public void onFailure(Call<responseGetOrder> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
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
        responseGetOrder resp = response.body();
        if (resp != null) {
            String message = resp.getMessage();
            String responsCode = resp.getResponseCode();
            if (responsCode == null) {

            } else if (responsCode.equals("OK")) {
                List<datagetOrders> data = resp.getData();
                List<datagetOrders> datanoended =new ArrayList<>();
                datanoended.clear();
                if (data != null) {
                    for (int i = 0; i < data.size(); i++) {
                        if(data.get(i).getSemaforo()<5)
                        {
                            datanoended.add(data.get(i));
                        }

                    }
                    if(datanoended.isEmpty())
                    {
                        presenter.setDialog();
                    }else
                    {
                        presenter.setdatatoView(datanoended);
                    }

                }else
                {
                    presenter.setDialog();
                }
            }
        }
    }
    @Override
    public void requestAllRepartidores() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String tokenr     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(tokenr!=null)
        {
            requestRepartidoress(tokenr);
        }
    }

    @Override
    public void asignOrderRepartidor(String token, Integer ordenNum) {
        setOrdertoRepartidor(token,ordenNum);
        
    }

    @Override
    public void domatrix() {
//        //https://maps.googleapis.com/maps/api/directions/json?origin=18.955405,-98.9820052&destination=18.963622,-98.9888987&key=AIzaSyDcgqr2mrgWKqTgRl-wyqX6P2VY-8atODE
//        String url="https://maps.googleapis.com/maps/api/directions/json?origin="+latitudInicial+","+longitudInicial
//                +"&destination="+latitudFinal+","+longitudFinal;
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl(url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
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

    private void requestRepartidoress(String tokenr) {
        requestRepartidores request= new requestRepartidores(tokenr);
          presenter.showprogresdialog();
        Call<responseRepartidores> call=service.getRepartidores(request);
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
        responseRepartidores resp = response.body();
        if (resp != null) {
            String message = resp.getMessage();
            String responsCode = resp.getResponseCode();
            if (responsCode.equals("S0015")) {
                List<dataRapartidores> data = resp.getData();
                if (data != null) {
                    presenter.setRepartidores(data);
                    presenter.hideprogresdialog();
                }

                //    presenter.hideProgressDialog();

            } else {
                Log.e("repartidoresI", " codigoresponse diferente");
                presenter.hideprogresdialog();
                //  presenter.hideProgressDialog();
            }
        } else {
            Log.e("repartidoresI", " response null");
            Toast.makeText(context, "" + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
            presenter.hideprogresdialog();
            // presenter.hideProgressDialog();
        }
    }

}