package com.foadia.champions.Ui.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.foadia.champions.Adapters.CountryAdapter;
import com.foadia.champions.Clients.UserClient;
import com.foadia.champions.Models.CountryModel;
import com.foadia.champions.Models.RegisterModel;
import com.foadia.champions.R;
import com.foadia.champions.Ui.ViewModel.CountryViewModel;
import com.foadia.champions.util.Utilities;

import java.util.ArrayList;
import java.util.Calendar;

public class MainRegister extends AppCompatActivity implements View.OnClickListener  {

    EditText FName, Email, Mobile, Password;
    EditText EdCountry;
    TextView EdCity;
    EditText BOF;
    Button BtnReg;
    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main_register);

        FName = (EditText) findViewById(R.id.FName);
        Password = (EditText) findViewById(R.id.Password);
        Mobile = (EditText) findViewById(R.id.Mobile);
        Email = (EditText) findViewById(R.id.Email);
        BOF = (EditText) findViewById(R.id.BOF);
        EdCountry =  findViewById(R.id.SelectCountry);
        EdCity = findViewById(R.id.SelectCity);

        BtnReg = (Button) findViewById(R.id.BtnRegistration);
        BtnReg.setOnClickListener(this);
        BOF.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainRegister.this, new DatePickerDialog.OnDateSetListener() {
                    @Override

                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        BOF.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        EdCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(MainRegister.this, "Done", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        LoadCountry();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BtnRegistration:
                Register();
                break;
        }
    }

    private void Register() {

        Utilities.showLoadingDialog(MainRegister.this);
        UserClient userClient = new UserClient();
        RegisterModel registerModel = new RegisterModel(1, FName.getText().toString(), Password.getText().toString(), Mobile.getText().toString(), Email.getText().toString(), BOF.getText().toString(), "0", "1", "", "" + EdCountry.getText().toString(), "" + EdCity.getText().toString(), "0");
        Call<RegisterModel> call = userClient.userInterFace.NewUser(registerModel);

        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                if (response.message().equals("OK")) {
                    Utilities.dismissLoadingDialog();
                    Utilities.putName(MainRegister.this, Mobile.getText().toString());
                    Utilities.putPassword(MainRegister.this, Password.getText().toString());
                    finish();
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                Toast.makeText(MainRegister.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void LoadCountry() {


    }

}