package com.foadia.champions.Data;

import com.foadia.champions.Models.CountryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryInterFace {

    @GET("Country")
    public Call<List<CountryModel>> GetCountry();
}
