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

import com.foadia.champions.Adapters.DancerAdapter;
import com.foadia.champions.Adapters.DancerAdapterV;
import com.foadia.champions.Models.UserModel;
import com.foadia.champions.R;
import com.foadia.champions.Ui.ViewModel.TalentViewModel;
import com.foadia.champions.Ui.ViewModel.UsersViewModel;
import com.foadia.champions.util.Utilities;

import java.util.ArrayList;
import java.util.List;

public class MainDancing extends AppCompatActivity implements DancerAdapter.UserAdapterListener, DancerAdapterV.UserAdapterVListener {
    TalentViewModel danceTalentViewModel;
    UsersViewModel usersViewModel;
    private List<UserModel> modelList = new ArrayList<>();
    DancerAdapter dancerAdapter = new DancerAdapter(this, modelList,this);
    DancerAdapterV dancerAdapterV = new DancerAdapterV(this, modelList,this);

    EditText TxtSearch;
    RecyclerView recyclerViewH,recyclerViewV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dancing);

        TxtSearch = findViewById(R.id.EDSearchDancing);
        TxtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                FillList();
                FillListV();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dancerAdapter.getFilter().filter(editable);
                dancerAdapterV.getFilter().filter(editable);
            }
        });

        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        usersViewModel.getUserByTalent(1);

        recyclerViewH =findViewById(R.id.RVPupularDancing);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewH.setLayoutManager(layoutManager);
        recyclerViewH.setItemAnimator(new DefaultItemAnimator());
        recyclerViewH.setAdapter(dancerAdapter);

        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        usersViewModel.getUserByTalent(1);

        recyclerViewV =findViewById(R.id.RVAllDancing);
        LinearLayoutManager layoutManagerV = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewV.setLayoutManager(layoutManagerV);
        recyclerViewV.setItemAnimator(new DefaultItemAnimator());
        recyclerViewV.setAdapter(dancerAdapterV);

//        danceTalentViewModel = ViewModelProviders.of(this).get(TalentViewModel.class);
//        danceTalentViewModel.GetUserByTalent(Utilities.getTalent(MainDancing.this));
//
//        danceTalentViewModel.UserTalentMutableLiveData.observe(this, new Observer<Object>() {
//            @Override
//            public void onChanged(UserModel userModel) {
//            }
//        });

        FillListAll();
        FillListAllV();

    }

    private void FillList() {
        usersViewModel.UserMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                dancerAdapter.setList((ArrayList<UserModel>) userModels);
            }
        });
    }
    private void FillListV() {
        usersViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                dancerAdapterV.setList((ArrayList<UserModel>) userModels);
            }
        });
    }

    public void FillListAll() {
        Utilities.showLoadingDialog(MainDancing.this);
        usersViewModel.UserMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                dancerAdapter.setListAll((ArrayList<UserModel>) userModels);
            }
        });

    }

    public void FillListAllV() {
        Utilities.showLoadingDialog(MainDancing.this);
        usersViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                dancerAdapterV.setListAll((ArrayList<UserModel>) userModels);
            }
        });

    }

    @Override
    public void onContactSelected(UserModel userModel) {
        Toast.makeText(MainDancing.this,""+ userModel.getMobile(),Toast.LENGTH_LONG).show();

    }

    @Override
    public void onContactVSelected(UserModel userModelV) {
        Toast.makeText(MainDancing.this,""+ userModelV.getMobile(),Toast.LENGTH_LONG).show();

    }
}