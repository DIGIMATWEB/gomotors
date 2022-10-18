package com.gomotorscompany.gomotors.register.util;



import com.gomotorscompany.gomotors.register.model.requestRegister;
import com.gomotorscompany.gomotors.register.model.responseRegister;
import com.gomotorscompany.gomotors.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface utilRegister {
    @POST(RetrofitEndPointsV2.SET_NEWUSER)
    Call<responseRegister> setNewUser(@Body requestRegister request);
}
