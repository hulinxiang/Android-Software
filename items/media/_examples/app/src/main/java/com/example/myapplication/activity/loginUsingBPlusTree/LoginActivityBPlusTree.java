package com.example.myapplication.activity.loginUsingBPlusTree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.BPlusTree.User.BPlusTreeManagerUser;
import com.example.myapplication.R;
import com.example.myapplication.activity.HomeActivity;
import com.example.myapplication.src.SessionManager;
import com.example.myapplication.src.User;

/**
 * @author Linxiang Hu u7633783, Yingxuan Tang
 *
 * The LoginActivityBPlusTree class handles user login functionality using BPlus Tree for user management.
 * It provides an interface for users to enter their login credentials, validates these credentials,
 * and directs the user to the home activity upon successful login.
 */
public class LoginActivityBPlusTree extends AppCompatActivity {
    private LoginCheckService loginCheckService;

    // the needed attributes for login activity
    private EditText username;
    private EditText password;
    private Button login;
    private Button register;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState  If the activity is being re-initialized after previously being shut down,
     *                            this contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                            Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginCheckService = new LoginCheckService(this);
        init();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivityBPlusTree.this, RegisterActivityBPlusTree.class);
                startActivity(intent);
            }
        });

        // Set up click listener for login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = username.getText().toString();
                String pwd = password.getText().toString();
                // Check login credentials
                if (loginCheckService.loginCheck(name, pwd)) {
                    // Display login success message
                    Toast.makeText(LoginActivityBPlusTree.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    // Redirect to HomeActivity
                    Intent intent = new Intent(LoginActivityBPlusTree.this, HomeActivity.class);
                    startActivity(intent);
                    // Set the logged-in user
                    User loggedInUser = BPlusTreeManagerUser.getTreeInstance(getApplicationContext()).query(name).get(0);
                    SessionManager.getInstance().setUser(loggedInUser);
                } else {
                    // Display login failure message
                    Toast.makeText(LoginActivityBPlusTree.this, "UserName or Password is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Initializes the UI components.
     *
     * Method:
     * - Finds the UI components by their ID and assigns them to the corresponding attributes.
     */
    // Initialize UI components
    private void init() {
        // get the component by Id
        username = findViewById(R.id.registerEmail);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
    }
}