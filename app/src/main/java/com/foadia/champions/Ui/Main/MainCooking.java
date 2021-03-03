package com.foadia.champions.Ui.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.foadia.champions.Adapters.ChefAdapterVr;
import com.foadia.champions.Adapters.ChefAdapter;
import com.foadia.champions.Models.UserModel;
import com.foadia.champions.R;
import com.foadia.champions.Ui.ViewModel.TalentViewModel;
import com.foadia.champions.Ui.ViewModel.UsersViewModel;
import com.foadia.champions.util.Utilities;

import java.util.ArrayList;
import java.util.List;


public class MainCooking extends AppCompatActivity implements ChefAdapter.ChefAdapterListener, ChefAdapterVr.ChefAdapterVListener {
    TalentViewModel cookTalentViewModel;
    UsersViewModel chefsViewModel;
    private List<UserModel> cookModelList = new ArrayList<>();
    ChefAdapter chefAdapter = new ChefAdapter(this, cookModelList, this);
    ChefAdapterVr chefAdapterVr = new ChefAdapterVr(this, cookModelList, this);

    EditText TxtSearch;
    RecyclerView recyclerViewCookH, recyclerViewCookV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);
        TxtSearch = findViewById(R.id.ChefSearch);
        TxtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                FillList();
                FillListV();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                chefAdapter.getFilter().filter(s);
                chefAdapterVr.getFilter().filter(s);
            }
        });
        chefsViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        chefsViewModel.getUserByTalent(5);

        recyclerViewCookV = findViewById(R.id.recyclerCookVr);
        LinearLayoutManager layoutManagerV = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCookV.setLayoutManager(layoutManagerV);
        recyclerViewCookV.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCookV.setAdapter(chefAdapterVr);


        chefsViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        chefsViewModel.getUserByTalent(5);

        recyclerViewCookH = findViewById(R.id.recyclerCookHz);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCookH.setLayoutManager(layoutManager);
        recyclerViewCookH.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCookH.setAdapter(chefAdapter);



        FillListAll();
        FillListAllV();
    }



    private void FillList() {
        chefsViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                chefAdapter.setList((ArrayList<UserModel>) userModels);
            }
        });
    }

    private void FillListV() {
        chefsViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                chefAdapterVr.setList((ArrayList<UserModel>) userModels);
            }
        });
    }

    public void FillListAll() {
        Utilities.showLoadingDialog(MainCooking.this);
        chefsViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                chefAdapter.setListAll((ArrayList<UserModel>) userModels);
            }
        });
    }

    public void FillListAllV() {
        Utilities.showLoadingDialog(MainCooking.this);
        chefsViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                chefAdapterVr.setListAll((ArrayList<UserModel>) userModels);
            }
        });
    }

    @Override
    public void onContactSelected(UserModel userModel) {
        Toast.makeText(com.foadia.champions.Ui.Main.MainCooking.this, "" + userModel.getMobile(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onContactVSelected(UserModel userModelV) {
        Toast.makeText(com.foadia.champions.Ui.Main.MainCooking.this, "" + userModelV.getMobile(), Toast.LENGTH_LONG).show();
    }
}
