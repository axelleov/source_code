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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ti5b.freeciscoccna.services.APIService;
import com.ti5b.freeciscoccna.R;
import com.ti5b.freeciscoccna.utility.Utilities;
import com.ti5b.freeciscoccna.models.ValueNoData;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnSignUp, btnSignIn, btnForgotPassword;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRoot, mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRoot = firebaseDatabase.getReference();

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnForgotPassword = findViewById(R.id.btn_forgot_password);
        btnSignUp = findViewById(R.id.btn_signup);
        btnSignIn = findViewById(R.id.btn_signin);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                boolean bolehLogin = true;
                if (TextUtils.isEmpty(email)){
                    bolehLogin = false;
                    etEmail.setError("Enter your email address !");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    bolehLogin = false;
                    etPassword.setError("Enter your password !");
                    return;
                }
                if (bolehLogin) {
                    login(email);
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    StyleableToast.makeText(SignInActivity.this, "Sign In Sucessfully !", R.style.customtoastscucess).show();
                                    Utilities.setValue(SignInActivity.this, "xEmail", email);
                                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    StyleableToast.makeText(SignInActivity.this, "Sign In Failed, Check Your Email and Password !", R.style.customtoastfail).show();
//                                    Toast.makeText(SignInActivity.this, "Sign In Failed, Check Your Email and Password !", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void login(String email) {
        progressBar.setVisibility(View.VISIBLE);
        APIService api = Utilities.getmRetrofit().create(APIService.class);
        Call<ValueNoData> call = api.login("freeciscoccna"  , email);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {

                if (response.code() == 200) {
                    progressBar.setVisibility(View.GONE);
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1) {
                        StyleableToast.makeText(SignInActivity.this, message, R.style.customtoastscucess).show();
//                        Toast.makeText(SignInActivity.this, message, Toast.LENGTH_SHORT).show();
                        Utilities.setValue(SignInActivity.this, "xEmail", email);
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        StyleableToast.makeText(SignInActivity.this, message, R.style.customtoastfail).show();
//                        Toast.makeText(SignInActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    StyleableToast.makeText(SignInActivity.this, "Response" + response.code(), R.style.customtoastfail).show();
//                    Toast.makeText(SignInActivity.this, "Response" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                System.out.println("Retrofit Error : " + t.getMessage());
                StyleableToast.makeText(SignInActivity.this, "Retrofit Error : " + t.getMessage(), R.style.customtoastfail).show();
//                Toast.makeText(SignInActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}