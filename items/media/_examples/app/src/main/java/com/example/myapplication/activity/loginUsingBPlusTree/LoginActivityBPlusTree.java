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
import com.example.myapplication.BPlusTree.BPlusTreeManager;
import com.example.myapplication.R;
import com.example.myapplication.entity.LoginNameBean;

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
        BPlusTree<String, LoginNameBean> tree = BPlusTreeManager.getTreeInstance(this);
        List<LoginNameBean> allUsers = tree.query(name);
        if (allUsers.size() != 0) {
            for (LoginNameBean user : allUsers) {
                if (user != null && user.loginName != null && user.pwd != null) {
                    Log.d("LoginCheck", "Checking user in BPlusTree: " + user.loginName);
                    if (user.loginName.equals(name) && user.pwd.equals(pwd)) {
                        Log.d("LoginCheck", "Login successful for user: " + name);
                        return true;
                    }
                }
            }
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

    @Override
    protected void onPause() {
        //onPause是Android生命周期方法之一，它会在Activity即将进入“停止”状态前自动被调用。
        // 你不需要手动调用onPause，Android系统会在适当的时刻自动调用它。
        // 这个方法的使用是正确的，它的目的是确保在用户离开此Activity（例如按Home键或者开启新Activity等）时，能够保存数据。
        super.onPause();
        // 保存B+树的状态，如果在登录过程中有修改
        BPlusTreeManager.saveTree(this);
    }
}