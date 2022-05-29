package com.example.udhta_enl_app.TaiKhoan;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.udhta_enl_app.MainActivity;
import com.example.udhta_enl_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText edtEmaildk,edtPassworddk,edtConfirmPassworddk;
    Button btSignup;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        
        initUs();
        initListener();
    }


    private void initUs() {
        edtEmaildk=(EditText) findViewById(R.id.edtEmaildk);
        edtPassworddk=(EditText) findViewById(R.id.edtPassworddk);
        edtConfirmPassworddk=(EditText) findViewById(R.id.edtConfirmPassworddk);
        btSignup=(Button) findViewById(R.id.btSignup);

    }
    private void initListener() {
        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignUp();
            }
        });

    }
    public void User(TaiKhoan taiKhoan){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("User");

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

        String id =user.getUid();
        reference.child(id).setValue(taiKhoan, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

            }
        });
    }

    private void onClickSignUp() {
        String strEmail=edtEmaildk.getText().toString().trim();
        String strPassword=edtPassworddk.getText().toString().trim();
        String strConfirmPassword=edtConfirmPassworddk.getText().toString().trim();
        FirebaseAuth auth=FirebaseAuth.getInstance();
        showProgressDialog();
        if (strPassword.equals(strConfirmPassword)) {
            if (edtEmaildk.equals("") || edtPassworddk.equals("") || edtConfirmPassworddk.equals("")){
                progressDialog.dismiss();
                Toast.makeText(SignupActivity.this, "Chứa nhập thông tin tài khoản cần đăng ký", Toast.LENGTH_SHORT).show();
            }
            else {
                auth.createUserWithEmailAndPassword(strEmail, strPassword)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressDialog.dismiss();

                                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                                    TaiKhoan taiKhoan=new TaiKhoan(user.getUid(),"",
                                            user.getEmail()
                                            ,"0"
                                            ,"0","");
                                    User(taiKhoan);
                                    // Sign in success, update UI with the signed-in user's information
                                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignupActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        }else {
            progressDialog.dismiss();
            Toast.makeText(SignupActivity.this, "Đăng ký thấy bại, kiểm tra lại mật khẩu", Toast.LENGTH_SHORT).show();
        }


    }
    private void showProgressDialog(){
        progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.layout_load);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }
}