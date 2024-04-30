package com.example.myapplication.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        init();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginMainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pwd = password.getText().toString();
                if (loginCheck(name, pwd)) {
                    Toast.makeText(LoginMainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginMainActivity.this, com.example.myapplication.activity.HomeActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Finish the LoginMainActivity to prevent going back to it
                } else {
                    Toast.makeText(LoginMainActivity.this, "UserName or Password is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean loginCheck(String name, String pwd) {
        try {
            List<LoginNameBean> allRes = DBManager.getInstance(LoginMainActivity.this).queryAllData();
            Log.d("LoginCheck", "Checking credentials for user: " + name);
            if (allRes != null) {
                for (LoginNameBean user : allRes) {
                    if (user != null && user.loginName != null && user.pwd != null) {
                        Log.d("LoginCheck", "Checking user in DB: " + user.loginName);
                        if (user.loginName.equals(name) && user.pwd.equals(pwd)) {
                            Log.d("LoginCheck", "Login successful for user: " + name);
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e("LoginCheck", "Error during login: " + e.getMessage());
            e.printStackTrace();
        }
        Log.d("LoginCheck", "Login failed for user: " + name);
        return false;
    }


    private void init() {
        // get the component by Id
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
    }

}