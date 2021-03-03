package com.foadia.champions.Ui.ViewModel;

import android.content.Context;
import android.widget.Toast;

import com.foadia.champions.Clients.CountryClient;
import com.foadia.champions.Models.CountryModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryViewModel extends ViewModel {
    private Context c;
    public MutableLiveData<List<CountryModel>> CountryListMutableLiveData = new MutableLiveData<>();

    public void GetCountries() {
        CountryClient.getINSTANCE().GetCountry().enqueue(new Callback<List<CountryModel>>() {
            @Override
            public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {

                if (response.isSuccessful()) {
                    try {
                        CountryListMutableLiveData.setValue(response.body());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(c, "Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<CountryModel>> call, Throwable t) {

            }
        });
    }

}
