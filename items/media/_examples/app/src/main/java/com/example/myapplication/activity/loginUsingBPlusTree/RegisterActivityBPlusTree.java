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
import com.example.myapplication.src.Firebase.PostManager.FirebasePostManager;
import com.example.myapplication.src.Firebase.UserManager.FirebaseUserHelper;
import com.example.myapplication.src.Firebase.UserManager.FirebaseUserManager;
import com.example.myapplication.src.SearchManager;
import com.example.myapplication.src.User;
import com.google.firebase.FirebaseApp;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class RegisterActivityBPlusTree extends AppCompatActivity {
    private LoginCheckService loginCheckService;
    private EditText email;
    private EditText password;
    private Button register;
    private View returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        loginCheckService = new LoginCheckService(this);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String registerEmail = email.getText().toString();
//                String pwd = password.getText().toString();
//
//                if (checkValid(registerEmail, pwd)) {
//                    User user = new User(registerEmail, pwd);
//                    BPlusTreeManagerUser.getTreeInstance(RegisterActivityBPlusTree.this).insert(registerEmail, user);
//                    //firebase更新
//                    FirebaseUserManager.getInstance(getApplicationContext()).addUser(user);
//                    Toast.makeText(RegisterActivityBPlusTree.this, "Register Successfully", Toast.LENGTH_SHORT).show();
//                    finish();
//                } else {
//                    Toast.makeText(RegisterActivityBPlusTree.this, "Duplicate usernames or passwords are empty", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


// 在 register 按钮的点击事件中，修改 User 对象的创建部分
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registerEmail = email.getText().toString();
                String pwd = password.getText().toString();

                if (loginCheckService.checkValid(registerEmail, pwd)) {
                    String encryptedPassword = DigestUtils.sha256Hex(pwd);
                    User user = new User(registerEmail, encryptedPassword);

                    BPlusTreeManagerUser.getTreeInstance(RegisterActivityBPlusTree.this).insert(registerEmail, user);
                    FirebaseUserManager.getInstance(getApplicationContext()).addUser(user);

                    Toast.makeText(RegisterActivityBPlusTree.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivityBPlusTree.this, "Duplicate usernames or passwords are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
//
//    public boolean checkValid(String name, String pwd) {
//        BPlusTree<String, User> tree = BPlusTreeManagerUser.getTreeInstance(this);
//        List<User> user = tree.query(name);
//        if (user.size() != 0) {
//            return false;
//        }
//        return !pwd.isEmpty() && SearchManager.validateUsername(name) && SearchManager.validatePassword(pwd);
//    }


    private void init() {
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        returnButton = findViewById(R.id.returnButton);
    }

}