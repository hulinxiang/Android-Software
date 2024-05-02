package com.example.myapplication.activity.loginUsingBPlusTree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.BPlusTree.BPlusTree;
import com.example.myapplication.BPlusTree.User.BPlusTreeManagerUser;
import com.example.myapplication.R;
import com.example.myapplication.src.User;

import java.util.List;

public class LoginActivityBPlusTree extends AppCompatActivity {

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
                Intent intent = new Intent(LoginActivityBPlusTree.this, RegisterActivityBPlusTree.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pwd = password.getText().toString();
                if (loginCheck(name, pwd)) {
                    Toast.makeText(LoginActivityBPlusTree.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    //跳转到新的activity
                } else {
                    Toast.makeText(LoginActivityBPlusTree.this, "UserName or Password is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean loginCheck(String name, String pwd) {
        BPlusTree<String, User> tree = BPlusTreeManagerUser.getTreeInstance(this);
        List<User> allUsers = tree.query(name);
        if (allUsers.size() != 0) {
            for (User user : allUsers) {
                if (user != null && user.getEmail() != null && user.getPasswordHash() != null) {
                    Log.d("LoginCheck", "Checking user in BPlusTree: " + user.getEmail());
                    if (user.getEmail().equals(name) && user.getPasswordHash().equals(User.hashPassword(pwd))) {
                        Log.d("LoginCheck", "Login successful for user: " + name);
                        return true;
                    } else {
                        Log.d("LoginFailed", "False Password: " + User.hashPassword(pwd) + "  Right Password: " + user.getPasswordHash());
                    }
                }
            }
        }else {
            Log.d("Empty User List","The User List is empty");
        }
        Log.d("LoginCheck", "Login failed for user: " + name + "  password: " + pwd);

        return false;
    }


    private void init() {
        // get the component by Id
        username = findViewById(R.id.registerEmail);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
    }

}