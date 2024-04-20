package com.example.myapplication.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.DBManager;
import com.example.myapplication.entity.LoginNameBean;

import java.util.List;

public class LoginMainActivity extends AppCompatActivity {

    // the needed attributes for login activity
    private EditText username;
    private EditText password;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginMainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        String name = username.getText().toString();
        String pwd = password.getText().toString();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginCheck(name, pwd)) {
                    //跳转到新的activity
                } else {
                    Toast.makeText(LoginMainActivity.this, "UserName or Password is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean loginCheck(String name, String pwd) {
        List<LoginNameBean> allRes = DBManager.getInstance(LoginMainActivity.this).queryAllData();
        for (LoginNameBean user : allRes) {
            if (user.loginName.equals(name) && user.pwd.equals(pwd)) {
                return true;
            }
        }
        return false;
    }


    private void init() {
        // get the component by Id
//        username = findViewById(R.id.xxx);
//        password = findViewById(R.id.xxx);
//        login = findViewById(R.id.xxx);
//        register = findViewById(R.id.xxx);
    }

}