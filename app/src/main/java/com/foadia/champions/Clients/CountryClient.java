package com.foadia.champions.Clients;

import com.foadia.champions.Data.CountryInterFace;
import com.foadia.champions.Models.CountryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryClient {

    public CountryInterFace countryInterFace;
    private static CountryClient INSTANCE;

    public CountryClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConnection.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        countryInterFace = retrofit.create(CountryInterFace.class);
    }

    public static CountryClient getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new CountryClient();
        }
        return INSTANCE;
    }

    public Call<List<CountryModel>> GetCountry() {
        return countryInterFace.GetCountry();
    }
}
