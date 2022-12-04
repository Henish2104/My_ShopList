package com.example.mymovie;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mymovie.Models.User;
import com.example.mymovie.databinding.SignupFragnmentBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Pattern;

public class SignupTabFragment extends Fragment {
    Context context;

    public SignupTabFragment(Context context) {
        this.context = context;
    }

   private boolean isAtleast8 , hasUppercase, hasNumber, hasSymbol;


    SignupFragnmentBinding binding;
    ProgressDialog progressDialog;
    FirebaseDatabase database;
    private FirebaseAuth auth;

   

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("Plz Wait...");
        inputChange();

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.email.getEditableText().toString();
                String password = binding.password.getEditableText().toString();
                String confirmpassword= binding.confirmPassword.getEditableText().toString();
                String username=binding.username.getEditableText().toString();
                String phonenumber=binding.phoneNo.getEditableText().toString();
                confirmInput(username,phonenumber,email,password,confirmpassword);
            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding = SignupFragnmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            binding.email.setError("Field Can't Be Empty");
            return false;
        } else {
            binding.email.setError(null);

            return emailcheak(email);

        }
    }

    private boolean emailcheak(String emailvalidate) {



            return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(emailvalidate).matches();
        }


    private boolean validatePassword(String password) {
        if (!passwordCheak() && password.length()<8) {
            if(password.isEmpty()) {
                binding.password.setError("Field Can't Be Empty");
                binding.passLinLay.setVisibility(View.GONE);
            }
          else {
                binding.password.setError("Password requirement doesn't match");

            }
            return false;

        }
        else {

            binding.password.setError(null);
               return true;
        }
    }
    public boolean passwordCheak(){
        binding.passLinLay.setVisibility(View.VISIBLE);


        String password= binding.password.getEditableText().toString();


            if (password.length() >= 8) {
                isAtleast8 = true;
                binding.cheakCharLength.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_verified_user_24,0,0,0);

                binding.cheakCharLength.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.green));
                binding.cheakCharLength.setTextColor(Color.GREEN);

            } else {
                binding.cheakCharLength.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_close_24,0,0,0);

                binding.cheakCharLength.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.red));
                binding.cheakCharLength.setTextColor(Color.RED);


                isAtleast8 = false;
            }

            if (password.matches("(.*[A-Z].*)")) {
                hasUppercase = true;
                binding.cheakUppercase.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_verified_user_24,0,0,0);
                binding.cheakUppercase.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.green));
                binding.cheakUppercase.setTextColor(Color.GREEN);


            } else {
                binding.cheakUppercase.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_close_24,0,0,0);

                binding.cheakUppercase.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.red));
                binding.cheakUppercase.setTextColor(Color.RED);

                hasUppercase = false;

            }
            if (password.matches("(.*[0-9].*)")) {
                binding.cheakNumber.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_verified_user_24,0,0,0);

                hasNumber = true;
                binding.cheakNumber.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.green));
                binding.cheakNumber.setTextColor(Color.GREEN);


            } else {
                binding.cheakNumber.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_close_24,0,0,0);

                binding.cheakNumber.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.red));
                binding.cheakNumber.setTextColor(Color.RED);

                hasNumber = false;

            }
            if (password.matches("(.*[#?!@$%^&*-].*)")) {
                binding.cheakSymbol.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_verified_user_24,0,0,0);
                binding.cheakSymbol.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.green));
                binding.cheakSymbol.setTextColor(Color.GREEN);
                hasSymbol = true;



            } else {
                binding.cheakSymbol.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_close_24,0,0,0);
                binding.cheakSymbol.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.red));
                binding.cheakSymbol.setTextColor(Color.RED);

                hasSymbol = false;

            }
        return isAtleast8 && hasSymbol && hasNumber && hasUppercase;


    }

    public void confirmInput(String username, String phonenumber , String email, String password, String confirmpassword) {
        if (validateEmail(email) && validatePassword(password) && validateconfirmpassword(password,confirmpassword) && validateusername(username) && validatephonenumber(phonenumber)) {

            progressDialog.show();
            addUserData(username,phonenumber,email,confirmpassword);
        } else {
            if(!validateEmail(email) && !validatePassword(password) && !validateusername(username) && !validatephonenumber(phonenumber) && !validateconfirmpassword(password,confirmpassword)) {

                Toast.makeText(getContext(), "Enter valid details", Toast.LENGTH_SHORT).show();

            }else {
                if (!validatePassword(password))
                    Toast.makeText(getContext(), "Enter valid password details", Toast.LENGTH_SHORT).show();
                if (!validateEmail(email))
                    Toast.makeText(getContext(), "Enter valid email details", Toast.LENGTH_SHORT).show();
                if (!validateconfirmpassword(password,confirmpassword))
                    Toast.makeText(getContext(), "Enter valid confirm password details", Toast.LENGTH_SHORT).show();
                if (!validatephonenumber(phonenumber))
                    Toast.makeText(getContext(), "Enter valid phonenumber details", Toast.LENGTH_SHORT).show();
                if (!validateusername(username))
                    Toast.makeText(getContext(), "Enter valid username details", Toast.LENGTH_SHORT).show();

            }


        }
    }

    private boolean validateusername(String username) {
        if (username.length() < 5) {
          if(username.isEmpty())     {
            binding.username.setError("Field Can't Be Empty");
          return false;
          }

          binding.username.setError("Atleast 5 character long required");
          return false;
        }
         else {
                binding.username.setError(null);

                return true;
            }
        }


    private boolean validatephonenumber(String phonenumber){
        if(phonenumber.length()<10){

        if (phonenumber.isEmpty()) {
            binding.phoneNo.setError("Field Can't Be Empty");


            return false;
        }
            binding.phoneNo.setError("Enter valid phone number");
        return false;
        } else {

            binding.phoneNo.setError(null);
            return true;

        }
    }

    private boolean validateconfirmpassword(String password,String confirmpassword) {


        if (confirmpassword.isEmpty() && !passwordCheak()) {

            binding.confirmPassWarning.setText("Try after enter correct password detail");
            binding.confirmPassWarning.setVisibility(View.VISIBLE);
            binding.confirmPassword.setError("Field Can't Be Empty");
            binding.password.setError("Password requirement doesn't match");

        return false;
        }
        if(confirmpassword.isEmpty() && passwordCheak()){
                binding.confirmPassword.setError("Field Can't Be Empty");
                return  false;
        }
        else{
            if(confirmpassword.equals(password) && validatePassword(password))
                return true;
            binding.password.setError(null);
            return true;
        }

    }

    private void addUserData(String username, String phonenumber,String email,String confirmpassword ) {
        auth.createUserWithEmailAndPassword(email, confirmpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            User user = new User(username,phonenumber,email,confirmpassword);
                            String id = Objects.requireNonNull(task.getResult().getUser()).getUid();
                            database.getReference().child("Users").child(id).setValue(user);
                            Toast.makeText(getContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
    public void inputChange(){
        binding.password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordCheak();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String passwrord = binding.password.getEditableText().toString();
                String confirmpassword=binding.confirmPassword.getEditableText().toString();
                if (editable.length() > 0 && confirmpassword.length()>=passwrord.length() && passwordCheak()  ) {
                    binding.confirmPassWarning.setTextColor(Color.RED);
                    binding.confirmPassWarning.setText("* Those passwords didn't match. Try again.");

                    if(!confirmpassword.equals(passwrord)){
                        binding.confirmPassWarning.setVisibility(View.VISIBLE);
                    }

                    else {

                        binding.confirmPassWarning.setText("Password verification successful");
                        binding.confirmPassWarning.setTextColor(Color.GREEN);
                        binding.confirmPassWarning.setVisibility(View.VISIBLE);




                    }
                }
                else {
                    if(!passwordCheak())
                    {
                        binding.confirmPassWarning.setTextColor(Color.RED);
                        binding.confirmPassWarning.setText("Try after enter correct password detail");
                        binding.password.setError("Password requirement doesn't match");


                        binding.confirmPassWarning.setVisibility(View.VISIBLE);



                        if(confirmpassword.length()==0)
                            binding.confirmPassWarning.setVisibility(View.GONE);


                        return;
                    }else{
                        if(confirmpassword.length()==0 || confirmpassword.length()<passwrord.length())
                            binding.confirmPassWarning.setVisibility(View.GONE);


                    }

                }
            }

        });

    }
   
    
}
