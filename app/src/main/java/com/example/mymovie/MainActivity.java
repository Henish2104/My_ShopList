package com.example.mymovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mymovie.Adapters.ResponceAdapter;

import com.example.mymovie.Models.Response;
import com.example.mymovie.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ProgressDialog dialog;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Loading Data");
        dialog.setMessage("Plz Wait...");

        dialog.show();
        binding.recview.setHasFixedSize(true);
        binding.recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        processdata();

        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auth.getInstance().signOut();
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


    }

    private void processdata() {

        Call<Response> call=ApiController.getInstance().getapi().getdata();
  call.enqueue(new Callback<Response>() {
      @Override
      public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
            dialog.dismiss();
          if(!response.isSuccessful()){
              Log.d("response","Something went wrong...");
              return;
          }
          Response list = response.body();


          ResponceAdapter adapter= new ResponceAdapter(list,MainActivity.this);
          binding.recview.setAdapter(adapter);


      }

      @Override
      public void onFailure(Call<Response> call, Throwable t) {
          dialog.dismiss();
          Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

      }
  });


    }
}