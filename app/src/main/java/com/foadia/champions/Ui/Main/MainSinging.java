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

import com.foadia.champions.Adapters.SingerAdapter;
import com.foadia.champions.Adapters.SingerAdapterVr;
import com.foadia.champions.Models.UserModel;
import com.foadia.champions.R;
import com.foadia.champions.Ui.ViewModel.UsersViewModel;
import com.foadia.champions.util.Utilities;

import java.util.ArrayList;
import java.util.List;

public class MainSinging extends AppCompatActivity implements SingerAdapter.SingerAdapterListener, SingerAdapterVr.SingerAdapterVListener {
    UsersViewModel singersViewModel;
    private List<UserModel> singModelList = new ArrayList<>();
    SingerAdapter singerAdapter = new SingerAdapter(this, singModelList, this);
    SingerAdapterVr singerAdapterVr = new SingerAdapterVr(this, singModelList, this);

    EditText TxtSearch;
    RecyclerView recyclerViewSingH, recyclerViewSingV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singing);

        TxtSearch = findViewById(R.id.SingerSearch);
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
                singerAdapter.getFilter().filter(s);
                singerAdapterVr.getFilter().filter(s);
            }
        });
        singersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        singersViewModel.getUserByTalent(2);

        recyclerViewSingH =findViewById(R.id.recyclerSingHz);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewSingH.setLayoutManager(layoutManager);
        recyclerViewSingH.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSingH.setAdapter(singerAdapter);

        singersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        singersViewModel.getUserByTalent(2);

        recyclerViewSingV =findViewById(R.id.recyclerSingVr);
        LinearLayoutManager layoutManagerV = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewSingV.setLayoutManager(layoutManagerV);
        recyclerViewSingV.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSingV.setAdapter(singerAdapterVr);

        FillListAll();
        FillListAllV();

    }
    private void FillList() {
        singersViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                singerAdapter.setList((ArrayList<UserModel>) userModels);
            }
        });
    }
    private void FillListV() {
        singersViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                singerAdapterVr.setList((ArrayList<UserModel>) userModels);
            }
        });
    }

    public void FillListAll() {
        Utilities.showLoadingDialog(MainSinging.this);
        singersViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                singerAdapter.setListAll((ArrayList<UserModel>) userModels);
            }
        });

    }

    public void FillListAllV() {
        Utilities.showLoadingDialog(MainSinging.this);
        singersViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                singerAdapterVr.setListAll((ArrayList<UserModel>) userModels);
            }
        });

    }


    @Override
    public void onContactSelected(UserModel userModel) {
        Toast.makeText(MainSinging.this,""+ userModel.getMobile(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onContactVSelected(UserModel userModelV) {
        Toast.makeText(MainSinging.this,""+ userModelV.getMobile(),Toast.LENGTH_LONG).show();
    }
}
