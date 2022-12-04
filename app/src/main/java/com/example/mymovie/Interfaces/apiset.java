package com.example.mymovie.Interfaces;


import com.example.mymovie.Models.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiset  {
    @GET("products")
    Call<Response> getdata();
}
