package com.example.mymovie;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mymovie.databinding.LoginFragmentBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class LoginTabFragment extends Fragment {
    Context context;
    public LoginTabFragment(Context context) {
    this.context=context;
    }
    LoginFragmentBinding binding;
    FirebaseDatabase database;
    private FirebaseAuth auth;
    ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login to your account. Plz wait...");
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.email.getEditableText().toString();
                String password = binding.password.getEditableText().toString();
                confirmInput(email,password);

            }
        });



    }
    public void validateUserData(String email,String password){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful())
                {
                    Intent intent=new Intent(context,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void confirmInput(String email, String password) {
        if (validateEmail(email) && validatePassword(password)) {

            progressDialog.show();
            validateUserData(email, password);
        } else {
            if (!validateEmail(email))
                Toast.makeText(getContext(), "Enter valid email details", Toast.LENGTH_SHORT).show();

             if (!validatePassword(password))
                Toast.makeText(getContext(), "Enter valid password details", Toast.LENGTH_SHORT).show();

        }
    }
    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            binding.email.setError("Field Can't Be Empty");
            return false;
        } else {
            binding.email.setError(null);

            return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();


        }
    }

    private boolean validatePassword(String password) {
        if (password.isEmpty()) {
            binding.password.setError("Field Can't Be Empty");

            return false;
        } else {
            binding.password.setError(null);

            return true;
        }
    }



}
