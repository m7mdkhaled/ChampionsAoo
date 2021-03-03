package com.foadia.champions.Ui.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.foadia.champions.Clients.UserClient;
import com.foadia.champions.Models.UserModel;
import com.foadia.champions.Models.UserTalents;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TalentViewModel extends ViewModel {
    private Context context;
    public MutableLiveData<List<UserTalents>> UserTalentMutableLiveData = new MutableLiveData<>();
//    public void GetUserByTalent(int id) {
//        UserClient.getINSTANCE().getUserTalents(id).enqueue(new Callback<List<UserModel>>() {
//            @Override
//            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<List<UserModel>> call, Throwable t) {
//
//            }
//        });
//    }
}
