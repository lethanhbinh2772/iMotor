package com.example.imotor.Controller.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imotor.Model.Login;
import com.example.imotor.Model.LoginResult;
import com.example.imotor.R;
import com.example.imotor.Utilities.AppConfig;
import com.example.imotor.iMotorService.IMotorService;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();
        //login();
        getData();
        checkValidPhoneNumber();
    }

    private void getData() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetLoginResult getLoginResult = new GetLoginResult("0387490078", "hjgjhg", "android");
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("http://45.118.144.19:1904/api/")
                        .build();
                retrofit.create(IMotorService.class).getLoginResult(getLoginResult).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String strJson = response.body().string();
                            Gson gson = new Gson();
                            Login login = gson.fromJson(strJson, Login.class);

                            String userPhoneNo = login.getResult().getPhone();

                            if (edtPhoneNumber.getText().equals(userPhoneNo)) {
                                AppConfig.setPhoneNumber(userPhoneNo, LoginActivity.this);
                                Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Action failed! Re-attempt action", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }
        });
    }

    private void checkValidPhoneNumber() {

    }

    private void init() {
        btnLogin = findViewById(R.id.btn_login);
        edtPhoneNumber = findViewById(R.id.edt_phone_typein);
    }

    public class GetLoginResult {
        String phoneNumber, deviceID, Os;

        public GetLoginResult(String phoneNumber, String deviceID, String os) {
            this.phoneNumber = phoneNumber;
            this.deviceID = deviceID;
            Os = os;
        }
    }
}
