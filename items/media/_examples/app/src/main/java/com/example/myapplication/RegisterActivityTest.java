package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class RegisterActivityTest extends AppCompatActivity {

//    TextInputEditText editTextEmail, editTextPassword, editTextConfirmPassword, inputEmail, inputPassword;
    EditText editTextEmail, editTextPassword, editTextConfirmPassword, inputEmail, inputPassword;
    Button buttonRegister, btnRegister;
    FirebaseAuth auth;
    ProgressBar progressBar;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_test);
        auth = FirebaseAuth.getInstance();
        inputEmail = findViewById(R.id.editTextEmail);
        inputPassword = findViewById(R.id.editTextPassword);
        btnRegister = findViewById(R.id.buttonRegister);
        progressBar = findViewById(R.id.progressBar);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                // 检查邮箱和密码是否为空
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "请输入邮箱地址！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "请输入密码！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "密码长度太短，请输入至少6个字符！", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // 创建新用户
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivityTest.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterActivityTest.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                                // 注册成功
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegisterActivityTest.this, "注册失败：" + task.getException(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RegisterActivityTest.this, "注册成功！", Toast.LENGTH_SHORT).show();
//12
                                }
                            }
                        });
            }
        });
    }
}
