package com.example.udhta_enl_app.TaiKhoan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.udhta_enl_app.MainActivity;
import com.example.udhta_enl_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.HashMap;


public class UpdateUserActivity extends AppCompatActivity {

    ImageView ivAvatarUpdate;
    EditText edtTenND;
    TextView tvEmail;
    Button btUpdatePF;

    Uri uri;
    public static final int MY_REQEST_CODE=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        Anhxa();
        setUserInformation();
        initListener();
    }

    private void Anhxa() {
        ivAvatarUpdate=findViewById(R.id.ivAvatarUpdate);
        edtTenND=findViewById(R.id.edtTenND);
        tvEmail=findViewById(R.id.tvEmail);
        btUpdatePF=findViewById(R.id.btUpdatePF);
    }
    private void setUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            return;
        }

        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();
        uri= photoUrl;
        edtTenND.setText(name);
        tvEmail.setText(email);
        Glide.with(getApplicationContext()).load(photoUrl).error(R.drawable.image_avatar).into(ivAvatarUpdate);

    }
    private void initListener() {
        ivAvatarUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });
        btUpdatePF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpadate();
            }
        });
    }

    private void onClickUpadate() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }
        String name = edtTenND.getText().toString().trim();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(uri)
                .build();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User");;

        HashMap hashMap=new HashMap();
        hashMap.put("name", name);
        hashMap.put("avatar", uri+"");
        databaseReference.child(firebaseUser.getUid()).updateChildren(hashMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
            }
        });

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UpdateUserActivity.this, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(UpdateUserActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permissions, MY_REQEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==MY_REQEST_CODE){
            if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                openGallery();

            }else {
                Toast.makeText(UpdateUserActivity.this, "Please allow access to the photo gallery", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openGallery() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
    }
    public void setBitmapImageView(Bitmap bitmapImageView){
        ivAvatarUpdate.setImageBitmap(bitmapImageView);
    }
    final private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData();
                if (intent == null) {
                    return;
                }
                uri = intent.getData();
                setUri(uri);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    setBitmapImageView(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    public void setUri(Uri uri) {
        this.uri = uri;
    }
}