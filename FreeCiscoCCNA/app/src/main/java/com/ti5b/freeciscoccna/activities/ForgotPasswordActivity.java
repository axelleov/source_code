package com.ti5b.freeciscoccna.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.ti5b.freeciscoccna.R;

import io.github.muddz.styleabletoast.StyleableToast;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnSignUp, btnSignIn, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firebaseAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.et_email);
        btnResetPassword = findViewById(R.id.btn_reset_password);
        btnSignUp = findViewById(R.id.btn_signup);
        btnSignIn = findViewById(R.id.btn_signin);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();

                if (TextUtils.isEmpty(email)){
                    etEmail.setError("Enter your email address !");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {
                                    StyleableToast.makeText(ForgotPasswordActivity.this, "Open your email to reset password !", R.style.customtoastscucess).show();
//                                    Toast.makeText(ForgotPasswordActivity.this, "Open your email to reset password !", Toast.LENGTH_SHORT).show();
                                } else {
                                    StyleableToast.makeText(ForgotPasswordActivity.this, "Failed to send email reset password !", R.style.customtoastfail).show();
//                                    Toast.makeText(ForgotPasswordActivity.this, "Failed to send email reset password !", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPasswordActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPasswordActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}