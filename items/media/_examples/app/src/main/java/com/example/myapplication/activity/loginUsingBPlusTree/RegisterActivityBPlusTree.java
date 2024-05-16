package com.example.myapplication.activity.loginUsingBPlusTree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myapplication.BPlusTree.User.BPlusTreeManagerUser;
import com.example.myapplication.R;
import com.example.myapplication.src.Firebase.UserManager.FirebaseUserManager;
import com.example.myapplication.src.User;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Linxiang Hu u7633783, Yingxuan Tang u7670526
 *
 * The RegisterActivityBPlusTree class handles user registration using BPlus Tree for user management.
 * It provides an interface for users to enter their registration details, validates these details,
 * encrypts the password, and inserts the new user into the BPlus Tree and Firebase upon successful registration.
 */
public class RegisterActivityBPlusTree extends AppCompatActivity {
    private LoginCheckService loginCheckService;
    private EditText email;
    private EditText password;
    private Button register;
    private View returnButton;

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
        setContentView(R.layout.activity_register);
        init();

        loginCheckService = new LoginCheckService(this);

        // Set click listener for the return button
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Set click listener for the register button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registerEmail = email.getText().toString();
                String pwd = password.getText().toString();

                // Check if the email and password are valid
                if (loginCheckService.checkValid(registerEmail, pwd)) {
                    // Encrypt the password using SHA-256
                    String encryptedPassword = DigestUtils.sha256Hex(pwd);
                    // Create a new User object
                    User user = new User(registerEmail, encryptedPassword);

                    // Insert the user into the BPlusTree and Firebase
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

    /**
     * Initializes the UI components.
     *
     * Method:
     * - Finds the UI components by their ID and assigns them to the corresponding attributes.
     */
    private void init() {
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        returnButton = findViewById(R.id.returnButton);
    }
}