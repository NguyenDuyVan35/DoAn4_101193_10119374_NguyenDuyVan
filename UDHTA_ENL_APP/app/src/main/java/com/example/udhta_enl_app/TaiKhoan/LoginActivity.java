package com.example.udhta_enl_app.TaiKhoan;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.udhta_enl_app.MainActivity;
import com.example.udhta_enl_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText editTextEmail,editTextTextPassword;
    Button buttonLogin;
    LinearLayout layout_signup;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Anhxa();


        layout_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogin();
            }
        });

    }
    private void onClickLogin() {
        String strEmail=editTextEmail.getText().toString().trim();
        String strPassword=editTextTextPassword.getText().toString().trim();
        FirebaseAuth auth=FirebaseAuth.getInstance();
        showProgressDialog();
            auth.signInWithEmailAndPassword(strEmail, strPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finishAffinity();
                            } else {
                                progressDialog.dismiss();
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Thông tin tài khoản hoặc mật khẩu không chính xác"
                                        , Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
    }
    private void showProgressDialog(){
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.layout_load);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    private void Anhxa() {
        layout_signup=(LinearLayout) findViewById(R.id.layout_signup);
        editTextEmail=(EditText) findViewById(R.id.editTextEmail);
        editTextTextPassword=(EditText) findViewById(R.id.editTextTextPassword);
        buttonLogin=(Button) findViewById(R.id.buttonLogin);
    }
}