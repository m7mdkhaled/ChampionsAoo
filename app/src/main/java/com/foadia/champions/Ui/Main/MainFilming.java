package com.foadia.champions.Ui.Main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foadia.champions.Adapters.ActorAdapter;
import com.foadia.champions.Adapters.ActorAdapterVr;
import com.foadia.champions.Models.UserModel;
import com.foadia.champions.R;
import com.foadia.champions.Ui.ViewModel.UsersViewModel;
import com.foadia.champions.util.Utilities;

import java.util.ArrayList;
import java.util.List;

public class MainFilming extends AppCompatActivity implements ActorAdapter.ActorAdapterListener, ActorAdapterVr.ActorAdaptorVListener {
    UsersViewModel actorsViewModel;
    private List<UserModel> filmModelList = new ArrayList<>();
    ActorAdapter actorAdapter = new ActorAdapter(this, filmModelList, this);
    ActorAdapterVr actorAdapterVr = new ActorAdapterVr(this, filmModelList, this);

    EditText TxtSearch;
    RecyclerView recyclerViewFilmH, recyclerViewFilmV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filming);
        TxtSearch = findViewById(R.id.ActorSearch);
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
                actorAdapter.getFilter().filter(s);
                actorAdapterVr.getFilter().filter(s);
            }
        });
        actorsViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        actorsViewModel.getUserByTalent(4);

        recyclerViewFilmH = findViewById(R.id.recyclerFilmHz);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFilmH.setLayoutManager(layoutManager);
        recyclerViewFilmH.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFilmH.setAdapter(actorAdapter);

        actorsViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        actorsViewModel.getUserByTalent(4);

        recyclerViewFilmV = findViewById(R.id.recyclerFilmVr);
        LinearLayoutManager layoutManagerV = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewFilmV.setLayoutManager(layoutManagerV);
        recyclerViewFilmV.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFilmV.setAdapter(actorAdapterVr);

        FillListAll();
        FillListAllV();
    }

    private void FillList() {
        actorsViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                actorAdapter.setList((ArrayList<UserModel>) userModels);
            }
        });
    }

    private void FillListV() {
        actorsViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                actorAdapterVr.setList((ArrayList<UserModel>) userModels);
            }
        });
    }

    public void FillListAll() {
        Utilities.showLoadingDialog(MainFilming.this);
        actorsViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                actorAdapter.setListAll((ArrayList<UserModel>) userModels);
            }
        });


    }
    public void FillListAllV() {
        Utilities.showLoadingDialog(MainFilming.this);
        actorsViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                actorAdapterVr.setListAll((ArrayList<UserModel>) userModels);
            }
        });
    }

    @Override
    public void onContactSelected(UserModel userModel) {
        Toast.makeText(MainFilming.this,""+ userModel.getMobile(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onContactVSelected(UserModel userModelV) {
        Toast.makeText(MainFilming.this,""+ userModelV.getMobile(),Toast.LENGTH_LONG).show();
    }
}
