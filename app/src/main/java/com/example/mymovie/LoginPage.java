package com.example.mymovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mymovie.Models.User;

import com.example.mymovie.databinding.ActivityLoginPageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class LoginPage extends AppCompatActivity {
    FirebaseDatabase database;
    private FirebaseAuth auth;
    ActivityLoginPageBinding binding;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(LoginPage.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("Plz Wait...");



        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String email = binding.username.getEditableText().toString();
              String password = binding.password.getEditableText().toString();

                confirmInput(email,password);
            }
        });



    }

    public void confirmInput(String email,String password) {
        if ( validateEmail(email) && validatePassword(password) ) {

            progressDialog.show();
            addUserData(email, password);
        } else {
            Toast.makeText(this, "Plz Try Again", Toast.LENGTH_SHORT).show();

        }
    }

    private boolean validateEmail(String email){
        if(email.isEmpty()){
            binding.username.setError("Field Can't Be Empty");
            return false;
        }
        else {
            binding.username.setError(null);

            return true;

        }
}

    private boolean validatePassword(String password){
        if(password.isEmpty()){
            binding.password.setError("Field Can't Be Empty");
            return false;
        }
        else {
            binding.password.setError(null);

            return true;

        }
    }

    private void addUserData(String a,String b) {
        auth.createUserWithEmailAndPassword(a,b)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                           // User user = new User(a, b);
                            String id = Objects.requireNonNull(task.getResult().getUser()).getUid();
                           // database.getReference().child("Users").child(id).setValue(user);
                            Toast.makeText(LoginPage.this, "Login Success", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(LoginPage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}