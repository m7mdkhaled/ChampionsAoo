package com.foadia.champions.Ui.ViewModel;

import android.content.Context;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.Toast;

import com.foadia.champions.Clients.UserClient;
import com.foadia.champions.Models.UserModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersViewModel extends ViewModel {
    public MutableLiveData<List<UserModel>> UserMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<UserModel>> TalentMutableLiveData = new MutableLiveData<>();

    //private Context c;
    public void getUsers() {

        UserClient.getINSTANCE().GetUsers().enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (response.isSuccessful()) {
                    try {
                        UserMutableLiveData.setValue(response.body());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                //    Toast.makeText(c, "Error", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
            //    Toast.makeText(c, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    public void getUserByTalent(int id) {

        UserClient.getINSTANCE().GetUserByTalent(id).enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                TalentMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                t.getMessage();
            }
        });
    }
}
