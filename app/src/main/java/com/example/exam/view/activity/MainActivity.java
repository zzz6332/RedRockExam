package com.example.exam.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.exam.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private boolean firstLogin;
    private EditText et_user;
    private EditText et_password;
    private String spName = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences(spName,MODE_PRIVATE);
        et_user = findViewById(R.id.et_user_name);
        et_password = findViewById(R.id.et_password);
        firstLogin = sp.getBoolean("isFirstLogin",true);
        if (!firstLogin){
            et_user.setText(sp.getString("user",null));
            et_password.setText(sp.getString("password",null));
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                if (firstLogin){
                    editor = sp.edit();
                    editor.putBoolean("isFirstLogin",false);
                    editor.putString("user",et_user.getText().toString());
                    editor.putString("password",et_password.getText().toString());
                    editor.commit();
                }
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
        }
    }
}
