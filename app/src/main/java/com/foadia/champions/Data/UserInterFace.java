package com.foadia.champions.Data;

import com.foadia.champions.Models.LoginModel;
import com.foadia.champions.Models.RegisterModel;
import com.foadia.champions.Models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserInterFace {

    @POST("auth/login")
    public  Call<LoginModel> Login(@Body LoginModel loginModel);

    @POST("auth/Register")
    public Call<RegisterModel> NewUser(@Body RegisterModel registerModel);

    @GET("User")
    public Call<List<UserModel>> GetUsers(@Header("authorization") String token);

    @GET("User/GetUsersByTalent/{id}")
    public Call<List<UserModel>> GetUserByTalent(@Path("id") int Id,@Header("authorization") String token);

    @GET("User/GetMaxRateUsersByTalent/{id}")
    public Call<List<UserModel>> GetUserRate(@Path("id") int Id, @Header("authorization") String token);

}

