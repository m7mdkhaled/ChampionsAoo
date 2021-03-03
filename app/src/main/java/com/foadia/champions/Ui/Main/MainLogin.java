package com.foadia.champions.Ui.Main;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.foadia.champions.Clients.ApiConnection;
import com.foadia.champions.Data.UserInterFace;
import com.foadia.champions.Models.LoginModel;
import com.foadia.champions.R;
import com.foadia.champions.util.ActivitiesLauncher;
import com.foadia.champions.util.Utilities;

public class MainLogin extends AppCompatActivity implements View.OnClickListener {

    private Button BtnRegister;
    EditText ED_User,ED_PassWord;
    UserInterFace userInterFace;
    ImageView IMLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        ED_User = findViewById(R.id.EnterUser);
        ED_PassWord = findViewById(R.id.EnterPassword);
        BtnRegister = findViewById(R.id.BtnRegister);
        IMLogin = findViewById(R.id.IMLogin);
        IMLogin.setOnClickListener(this);

        BtnRegister.setOnClickListener(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConnection.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userInterFace = retrofit.create(UserInterFace.class);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BtnRegister:
                Register();
                break;
            case R.id.IMLogin:
                if(ED_User.getText().equals(null) || ED_PassWord.getText().equals(null)) {
                    Toast.makeText(MainLogin.this,"Enter User Name Or Password",Toast.LENGTH_SHORT).show();
                }else {
                    Login();
                }
                break;
        }
    }

    public void Login(){
        Utilities.showLoadingDialog(MainLogin.this);
        LoginModel loginModel = new LoginModel(ED_User.getText().toString(), ED_PassWord.getText().toString());

        Call<LoginModel> call = userInterFace.Login(loginModel);

        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                try {
                    Utilities.putToken(MainLogin.this,"Bearer "+ response.body().token);
                    Utilities.putUserId(MainLogin.this,response.body().getId());
//                    Utilities.putName(MainLogin.this,ED_User.getText().toString());
                    ActivitiesLauncher.openHomeActivity (MainLogin.this);
                    Utilities.dismissLoadingDialog();
                    Toast.makeText(MainLogin.this,"Done Success",Toast.LENGTH_LONG).show();
                    Utilities.dismissLoadingDialog();
                    finish();

                }catch (Exception e){
                    Toast.makeText(MainLogin.this,"Error For User Name Or Password",Toast.LENGTH_LONG).show();
                    Utilities.dismissLoadingDialog();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(MainLogin.this,t.getMessage(),Toast.LENGTH_LONG).show();
                Utilities.dismissLoadingDialog();
            }
        });


    }

    public void Register(){

                    Utilities.showLoadingDialog(MainLogin.this);
                    ActivitiesLauncher.openRegisterActivity (MainLogin.this);
                    Utilities.dismissLoadingDialog();
                    finish();
    }
}