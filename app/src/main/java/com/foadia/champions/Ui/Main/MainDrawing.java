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

import com.foadia.champions.Adapters.PainterAdapter;
import com.foadia.champions.Adapters.PainterAdapterVr;
import com.foadia.champions.Models.UserModel;
import com.foadia.champions.R;
import com.foadia.champions.Ui.ViewModel.UsersViewModel;
import com.foadia.champions.util.Utilities;

import java.util.ArrayList;
import java.util.List;

public class MainDrawing extends AppCompatActivity implements PainterAdapter.PainterAdapterListener, PainterAdapterVr.PainterAdapterVListener{

    UsersViewModel paintersViewModel;
    private List<UserModel> drawModelList = new ArrayList<>();
    PainterAdapter painterAdapter = new PainterAdapter(this, drawModelList, this);
    PainterAdapterVr painterAdapterVr = new PainterAdapterVr(this, drawModelList, this);

    EditText TxtSearch;
    RecyclerView recyclerViewDrawH, recyclerViewDrawV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        TxtSearch = findViewById(R.id.PainterSearch);
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
                painterAdapter.getFilter().filter(s);
                painterAdapterVr.getFilter().filter(s);
            }
        });
        paintersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        paintersViewModel.getUserByTalent(3);

        recyclerViewDrawH =findViewById(R.id.recyclerDrawHz);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewDrawH.setLayoutManager(layoutManager);
        recyclerViewDrawH.setItemAnimator(new DefaultItemAnimator());
        recyclerViewDrawH.setAdapter(painterAdapter);

        paintersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        paintersViewModel.getUserByTalent(3);

        recyclerViewDrawV =findViewById(R.id.recyclerDrawVr);
        LinearLayoutManager layoutManagerV = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewDrawV.setLayoutManager(layoutManagerV);
        recyclerViewDrawV.setItemAnimator(new DefaultItemAnimator());
        recyclerViewDrawV.setAdapter(painterAdapterVr);

        FillListAll();
        FillListAllV();
    }
    private void FillList() {
        paintersViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                painterAdapter.setList((ArrayList<UserModel>) userModels);
            }
        });
    }
    private void FillListV() {
        paintersViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                painterAdapterVr.setList((ArrayList<UserModel>) userModels);
            }
        });
    }

    public void FillListAll() {
        Utilities.showLoadingDialog(MainDrawing.this);
        paintersViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                painterAdapter.setListAll((ArrayList<UserModel>) userModels);
            }
        });
    }

    public void FillListAllV() {
        Utilities.showLoadingDialog(MainDrawing.this);
        paintersViewModel.TalentMutableLiveData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                painterAdapterVr.setListAll((ArrayList<UserModel>) userModels);
            }
        });
    }

    @Override
    public void onContactSelected(UserModel userModel) {
        Toast.makeText(MainDrawing.this,""+ userModel.getMobile(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onContactVSelected(UserModel userModelV) {
        Toast.makeText(MainDrawing.this,""+ userModelV.getMobile(),Toast.LENGTH_LONG).show();
    }
}
