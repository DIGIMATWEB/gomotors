package com.gomotorscompany.gomotors.Ordenar.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.gomotorscompany.gomotors.Ordenar.model.ingredientesAlone.dataIngredients;
import com.gomotorscompany.gomotors.Ordenar.model.ingredientesAlone.requestIngredients;
import com.gomotorscompany.gomotors.Ordenar.model.ingredientesAlone.responseIngredients;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.Logo;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.Sucursale;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.menuDatan;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.requesMenun;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.responseMenun;
import com.gomotorscompany.gomotors.Ordenar.model.productslist.datatipeproducts;
import com.gomotorscompany.gomotors.Ordenar.model.productslist.requestlistproducts;
import com.gomotorscompany.gomotors.Ordenar.model.productslist.responselistaproducts;
import com.gomotorscompany.gomotors.Ordenar.presenter.mainpresenterView;
import com.gomotorscompany.gomotors.Ordenar.util.fullMenuServicen;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.gomotorscompany.gomotors.retrofit.RetrofitClientV3;
import com.gomotorscompany.gomotors.retrofit.RetrofitValidationsV2;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class getfullMenuInteractorImplements  implements  getfullMenuInteractor{

    private Context context;
    private mainpresenterView presenter;
    private fullMenuServicen service;
    private Retrofit retrofitClient;
    private String  token;
    public getfullMenuInteractorImplements(mainpresenterView presenter, Context context)
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
        if(token!=null)
        {
            requestproductos(token);
        }
    }



    private void requestproductos(String token) {
        requestlistproducts req=new requestlistproducts(token);
        presenter.showProgressDialog();
        Call<responselistaproducts> call=service.listaProducts(req);
        call.enqueue(new Callback<responselistaproducts>() {
            @Override
            public void onResponse(Call<responselistaproducts> call, Response<responselistaproducts> response) {
                validateCodecategories(response,context);
            }

            @Override
            public void onFailure(Call<responselistaproducts> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCodecategories(Response<responselistaproducts> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getCategoriesdata(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCategoriesdata(Response<responselistaproducts> response, Context context) {
        responselistaproducts resp=response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            String responsCode = resp.getResponseCode();
            if (responsCode.equals("S001") ) {
                List<datatipeproducts> typecategory=resp.getData();
                if(typecategory!=null) {
                    List<String> categoriasDisponibles=new ArrayList<>();
                    categoriasDisponibles.clear();
                    for(int i=0;i<typecategory.size();i++)
                    {
                        categoriasDisponibles.add(typecategory.get(i).getCategoria());

                    }

                    presenter.setCategorias(categoriasDisponibles);
                    presenter.hideProgressDialog();
                }
            }
        }

    }

    private void requestService(String token) {
        requesMenun request=new requesMenun(token,1,0.0,0.0);// requesMenu request=new requesMenu(token);
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
            if(responsCode.equals("S001")||responsCode.equals("-2147467261")||responsCode.equals("EXITO"))
            {
                List<menuDatan> data=resp.getData();
                if(data!=null)
                {
                    SharedPreferences preferencias=context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES,Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferencias.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(resp);

                    editor.putString(GeneralConstantsV2.JSON_MENU, json);
                    editor.commit();

                    List<Logo>  infoinside=new ArrayList<>();
                    List<Sucursale> sucursales=new ArrayList<>();
                    infoinside.clear();
                    sucursales.clear();
                    for(int i=0;i<data.size();i++)
                    {   infoinside.addAll(data.get(i).getLogo());

                    }
                    for(int k=0;k<infoinside.size();k++)
                    {
                        sucursales.addAll(infoinside.get(k).getSucursales());
                    }
                    presenter.setComercios(data,sucursales);
                }
                presenter.hideProgressDialog();
            }

        }

    }
    @Override
    public void requestaloneComplements() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            requestSingleIngredients(token);
        }
    }

    private void requestSingleIngredients(String token) {
        requestIngredients request= new requestIngredients(token);
        presenter.showProgressDialog();
        Call<responseIngredients> call= service.getIngredients(request);
        call.enqueue(new Callback<responseIngredients>() {
            @Override
            public void onResponse(Call<responseIngredients> call, Response<responseIngredients> response) {
                if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                    getSingleIngredients(response, context);
                } else {
                    Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<responseIngredients> call, Throwable t) {
                Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSingleIngredients(Response<responseIngredients> response, Context context) {
          responseIngredients resp=response.body();
                if(resp!=null) {
                    String message = resp.getMessage();
                    String responsCode = resp.getResponseCode();
                    if (responsCode.equals("S0020")) {
                        List<dataIngredients> data=resp.getData();
                        if(data!=null)
                        {
                            List<dataIngredients> filterdata=new ArrayList<>();

                            for (int i=0;i<data.size();i++)
                            {
                                if(data.get(i).getNameIngrediente().toLowerCase().equals("cajeta")||data.get(i).getNameIngrediente().toLowerCase().equals("extra")||
                                data.get(i).getNameIngrediente().toLowerCase().equals("extra topping")||data.get(i).getNameIngrediente().toLowerCase().equals("topping")||
                                data.get(i).getNameIngrediente().toLowerCase().equals("extra cereza"))
                                {
                                  //  Log.e("getNameIngrediente",""+data.get(i).getNameIngrediente());
                                }else
                                {
                                    data.get(i).setChecked(false);
                                    filterdata.add(data.get(i));
                                }
                            }
                            presenter.setIngredientesIndividuales(filterdata);
                            presenter.hideProgressDialog();
                        }
                    }
                }

    }
}
