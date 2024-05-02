package com.example.myapplication.activity.loginUsingBPlusTree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.BPlusTree.BPlusTree;
import com.example.myapplication.BPlusTree.User.BPlusTreeManagerUser;
import com.example.myapplication.R;
import com.example.myapplication.src.SearchManager;
import com.example.myapplication.src.User;

import java.util.List;

public class RegisterActivityBPlusTree extends AppCompatActivity {


    private EditText email;
    private EditText password;

    private Button register;

    private View returnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registerEmail = email.getText().toString();
                String pwd = password.getText().toString();

                if (checkValid(registerEmail, pwd)) {
                    User user = new User(registerEmail, pwd);
                    BPlusTreeManagerUser.getTreeInstance(RegisterActivityBPlusTree.this).insert(registerEmail, user);
                    Toast.makeText(RegisterActivityBPlusTree.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivityBPlusTree.this, "Duplicate usernames or passwords are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean checkValid(String name, String pwd) {
        BPlusTree<String, User> tree = BPlusTreeManagerUser.getTreeInstance(this);
        List<User> user = tree.query(name);
        if (user.size() != 0) {
            return false;
        }
        return !pwd.isEmpty() && SearchManager.validateUsername(name) && SearchManager.validatePassword(pwd);
    }


    private void init() {
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        returnButton = findViewById(R.id.returnButton);
    }

}