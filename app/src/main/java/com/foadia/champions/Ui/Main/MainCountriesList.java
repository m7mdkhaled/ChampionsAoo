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

import com.foadia.champions.Adapters.CountryAdapter;
import com.foadia.champions.Models.CountryModel;
import com.foadia.champions.R;
import com.foadia.champions.Ui.ViewModel.CountryViewModel;
import com.foadia.champions.util.Utilities;

import java.util.ArrayList;
import java.util.List;

public class MainCountriesList extends AppCompatActivity implements CountryAdapter.CountryAdapterListener{
    CountryViewModel countryViewModel;
    private List<CountryModel> modelList = new ArrayList<>();
    CountryAdapter countryAdapter = new CountryAdapter(this, modelList,this);

    EditText EDSearchCountry;
    RecyclerView recyclerViewCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_countries_list);
        EDSearchCountry = findViewById(R.id.Ed_CountrySearch);

        EDSearchCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                FillList();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                countryAdapter.getFilter().filter(editable);
            }
        });

        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        countryViewModel.GetCountries();
        recyclerViewCountry = findViewById(R.id.RV_Country_List);

        LoadInRecycle();
        FillListAll();
    }

   private void LoadInRecycle(){

       LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
       recyclerViewCountry.setLayoutManager(layoutManager);
       recyclerViewCountry.setItemAnimator(new DefaultItemAnimator());
       recyclerViewCountry.setAdapter(countryAdapter);
    }

    private void FillList() {
        countryViewModel.CountryListMutableLiveData.observe(this, new Observer<List<CountryModel>>() {
            @Override
            public void onChanged(List<CountryModel> countryModels) {
                countryAdapter.setList((ArrayList<CountryModel>) countryModels);
            }
        });
    }

    public void FillListAll() {
//        Utilities.showLoadingDialog(MainDancing.this);
        countryViewModel.CountryListMutableLiveData.observe(this, new Observer<List<CountryModel>>() {
            @Override
            public void onChanged(List<CountryModel> countryModels) {
                countryAdapter.setListAll((ArrayList<CountryModel>) countryModels);
            }
        });

    }

    @Override
    public void onContactSelected(CountryModel countryModel) {
        Utilities.putIqama (MainCountriesList.this,countryModel.getCountryName());
    }
}