package com.foadia.champions.Clients;



import android.content.Context;

import com.foadia.champions.Data.UserInterFace;
import com.foadia.champions.Models.UserModel;
import com.foadia.champions.util.Utilities;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserClient {


    public UserInterFace userInterFace;
    private static UserClient INSTANCE;
    public Context context;

    public UserClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConnection.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userInterFace = retrofit.create(UserInterFace.class);
    }


    public static UserClient getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new UserClient();
        }
        return INSTANCE;
    }

    public Call<List<UserModel>> GetUsers() {
        return userInterFace.GetUsers(Utilities.getToken(context) );
    }

    public Call<List<UserModel>> GetUserByTalent(int id) {
        return userInterFace.GetUserByTalent(id,Utilities.getToken(context));
    }
}
