package com.example.myapplication.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.DBManager;
import com.example.myapplication.entity.LoginNameBean;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    private List<LoginNameBean> allUsers;

    private Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pwd = password.getText().toString();

                if (checkValid(name, pwd)) {
                    DBManager.getInstance(RegisterActivity.this).insertMessage(name, pwd);
                    Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    //跳回登录界面
                } else {
                    Toast.makeText(RegisterActivity.this, "Duplicate usernames or passwords are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean checkValid(String name, String pwd) {
        for (LoginNameBean user : allUsers) {
            if (user.loginName.equals(name)) {
                return false;
            }
        }
        return !pwd.isEmpty();
    }


    private void init() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        allUsers = DBManager.getInstance(RegisterActivity.this).queryAllData();
    }


}