package com.gomotorscompany.gomotors.miscompras.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;
import com.gomotorscompany.gomotors.miscompras.model.get.requestGetOrder;
import com.gomotorscompany.gomotors.miscompras.model.get.responseGetOrder;
import com.gomotorscompany.gomotors.miscompras.model.set.Complemento_startOrder;
import com.gomotorscompany.gomotors.miscompras.model.set.Paquete_startOrder;
import com.gomotorscompany.gomotors.miscompras.model.set.Producto_startOrder;
import com.gomotorscompany.gomotors.miscompras.model.set.cocina.requestCocina;
import com.gomotorscompany.gomotors.miscompras.model.set.cocina.responseCocina;
import com.gomotorscompany.gomotors.miscompras.model.set.requeststartOrder;
import com.gomotorscompany.gomotors.miscompras.model.set.response.responseStartOrder;
import com.gomotorscompany.gomotors.miscompras.presenter.presenterStartOrderImpl;
import com.gomotorscompany.gomotors.miscompras.util.serviceOrdersClient;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.gomotorscompany.gomotors.retrofit.RetrofitClientV3;
import com.gomotorscompany.gomotors.retrofit.RetrofitValidationsV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class misComprasInteractorImpl implements misComprasInteractor{
    private Context context;
    private presenterStartOrderImpl presenter;
    private serviceOrdersClient service;
    private Retrofit retrofitClient;
    public misComprasInteractorImpl(presenterStartOrderImpl presenter, Context context) {
        this.presenter=presenter;
        this.context=context;

        retrofitClient = RetrofitClientV3.getRetrofitInstancev3();
                    service = retrofitClient.create(serviceOrdersClient.class);
    }

    @Override
    public void requestCompras(requeststartOrder startOrder) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            startOrder.setToken(token);
            putNewOrder(startOrder);
        }
    }

    private void putNewOrder(requeststartOrder startOrder) {
        if(startOrder.getComplementos().isEmpty())
        {
            Log.e("startOrderd",""+startOrder.getComplementos());
            startOrder.getComplementos().add(new Complemento_startOrder("string",0,true));

        }
        if (startOrder.getPaquetes().isEmpty())
        {
            Log.e("startOrderd",""+startOrder.getPaquetes());
            startOrder.getPaquetes().add(0,new Paquete_startOrder(0,0,true));
        }
        if (startOrder.getProductos().isEmpty())
        {
            Log.e("startOrderd",""+startOrder.getPaquetes());

               startOrder.getProductos().add(0,new Producto_startOrder("string",0,true));
        }
        requeststartOrder request= new requeststartOrder(0,startOrder.getToken(),startOrder.getDireccionEntrega(),startOrder.getLatitud(),startOrder.getLongitud()
                ,startOrder.getSucursal(),startOrder.getPaquetes(),startOrder.getProductos(),startOrder.getComplementos());

        presenter.showprogresdialog();
        Call<responseStartOrder> call =service.putStartOrder(request);
        call.enqueue(new Callback<responseStartOrder>() {
            @Override
            public void onResponse(Call<responseStartOrder> call, Response<responseStartOrder> response) {
                 validatePutNewOrder(response,context);
            }

            @Override
            public void onFailure(Call<responseStartOrder> call, Throwable t) {
                Toast.makeText(context,"onFailure put newOrder"+t.getMessage(),Toast.LENGTH_SHORT).show();
                presenter.hideprogresdialog();
            }
        });

    }

    private void validatePutNewOrder(Response<responseStartOrder> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                setputNewOrder(response, context);
            } else {
                Toast.makeText(context, " fail at validatePutNewOrder" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setputNewOrder(Response<responseStartOrder> response, Context context) {

               responseStartOrder resp=response.body();
                if(resp!=null) {
                 String responsCode = resp.getResponseCode();
                    if (responsCode==null) {
                        presenter.hideprogresdialog();
                    }else if(responsCode.equals("S006.1"))
                    {
                        Toast.makeText(context, "Orden creada", Toast.LENGTH_SHORT).show();
                        presenter.setordenCreadaSucces("105");
                        presenter.hideprogresdialog();
                    }else
                    {
                        presenter.didnoSucced();
                        presenter.hideprogresdialog();
                    }
                }else
                {
                 //   presenter.setordenCreadaSucces("105");
                    presenter.hideprogresdialog();
                }


        //{
        //  "ResponseCode": "S006.1",
        //  "Message": "Orden agregada",
        //  "Activo": 1,
        //  "data": null
        //}
    }

    @Override
    public void requestOrders() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            postOrders(token);
        }
    }

    @Override
    public void mandarACocina(Integer ordenNum) {
//requestCocina
        //responseCocina
        requestCocina request=new requestCocina(ordenNum);
        Call<responseCocina> call=service.putIdOrder(request);
        call.enqueue(new Callback<responseCocina>() {
            @Override
            public void onResponse(Call<responseCocina> call, Response<responseCocina> response) {
              validatemandarACocina(response,context);
            }

            @Override
            public void onFailure(Call<responseCocina> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validatemandarACocina(Response<responseCocina> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getvalidatemandarACocina(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getvalidatemandarACocina(Response<responseCocina> response, Context context) {
        responseCocina respo=response.body();
        if(respo!=null)
        {
            String responseCode=respo.getResponseCode();
            if(responseCode.equals("En proceso")){
//                Log.e("sizeOrdenes", "Ir a reparto");
                presenter.gotoreparto();

            }
        }
    }

    private void postOrders(String token) {
        requestGetOrder request= new requestGetOrder(token);
        Call<responseGetOrder> call=service.getFullOrders1(request);
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

            }else if(responsCode.equals("OK"))
            {
                List<datagetOrders> data=resp.getData();
                if(data!=null)
                {
                    for(int i =0;i<data.size();i++) {

                    }
                    presenter.setdatatoView(data);
                }
            }
        }
    }
}