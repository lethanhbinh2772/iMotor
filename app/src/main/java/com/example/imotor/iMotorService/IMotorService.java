package com.example.imotor.iMotorService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IMotorService {
    @POST("Service/Login")
    Call<ResponseBody> getLoginResult(@Body Object object);

    @POST("Service/GetPriceListMaterial")
    Call<ResponseBody> getPriceListMaterial(@Body Object object);

    @POST("Service/GetPriceListMaterialFollow")
    Call<ResponseBody> getPriceListMaterialFollow(@Body Object object);
}
