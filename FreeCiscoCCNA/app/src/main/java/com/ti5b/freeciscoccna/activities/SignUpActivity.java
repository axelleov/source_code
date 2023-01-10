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
import com.ti5b.freeciscoccna.User;
import com.ti5b.freeciscoccna.utility.Utilities;
import com.ti5b.freeciscoccna.models.ValueNoData;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText etFullName, etEmail, etPassword, etConfirmPassword;
    private Button btnSignUp, btnSignIn;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRoot, mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRoot = firebaseDatabase.getReference();

        etFullName = findViewById(R.id.et_fullname);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnSignUp = findViewById(R.id.btn_signup);
        btnSignIn = findViewById(R.id.btn_signin);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = etFullName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();
                boolean bolehRegister = true;

                if (TextUtils.isEmpty(fullname)){
                    bolehRegister = false;
                    etFullName.setError("Enter your full name !");
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    bolehRegister = false;
                    etEmail.setError("Enter your email address !");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    bolehRegister = false;
                    etPassword.setError("Enter your password !");
                    return;
                }
                if (TextUtils.isEmpty(confirmPassword)){
                    bolehRegister = false;
                    etConfirmPassword.setError("Enter your confirm password !");
                    return;
                }
                if (password.length() < 6) {
                    bolehRegister = false;
                    etPassword.setError("Password too short, enter minimum 6 characters !");
                    return;
                }
                if (!confirmPassword.equals(password)) {
                    bolehRegister = false;
                    etConfirmPassword.setError("Password doesn't match !");
                    return;
                }
                if (bolehRegister) {
                    register(email);
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    StyleableToast.makeText(SignUpActivity.this, "Sign Up Sucessfully !", R.style.customtoastscucess).show();
//                                    Toast.makeText(SignUpActivity.this, "Sign Up Sucessfully !", Toast.LENGTH_SHORT).show();
                                    User user = new User(email, fullname);
                                    String userId = task.getResult().getUser().getUid();
                                    mRef = mRoot.child("users").child(userId);
                                    mRef.setValue(user);
                                    Utilities.setValue(SignUpActivity.this, "xEmail", email);
                                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    StyleableToast.makeText(SignUpActivity.this, "Sign Up Failed !", R.style.customtoastfail).show();
//                                    Toast.makeText(SignUpActivity.this, "Sign Up Failed !", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void register(String email) {
        progressBar.setVisibility(View.VISIBLE);
        APIService api = Utilities.getmRetrofit().create(APIService.class);
        Call<ValueNoData> call = api.register("freeciscoccna", email);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                progressBar.setVisibility(View.GONE);
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if (success == 1) {
                        StyleableToast.makeText(SignUpActivity.this, message, R.style.customtoastscucess).show();
//                        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
                        Utilities.setValue(SignUpActivity.this, "xEmail", email);
                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        StyleableToast.makeText(SignUpActivity.this, message, R.style.customtoastfail).show();
//                        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    StyleableToast.makeText(SignUpActivity.this, "Response" + response.code(), R.style.customtoastfail).show();
//                    Toast.makeText(SignUpActivity.this, "Response" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                System.out.println("Retrofit Error : " + t.getMessage());
                StyleableToast.makeText(SignUpActivity.this, "Retrofit Error : " + t.getMessage(), R.style.customtoastfail).show();
//                Toast.makeText(SignUpActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}