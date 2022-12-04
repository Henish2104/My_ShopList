package com.example.mymovie;

import com.example.mymovie.Interfaces.apiset;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    public static final String url="https://dummyjson.com/";
    public static ApiController clientobject;
    public static Retrofit retrofit;
    ApiController(){
        retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();

    }
    public apiset getapi() {
        return retrofit.create(apiset.class);
    }
    public static synchronized ApiController getInstance(){
        if(clientobject==null)
            clientobject=new ApiController();


        return clientobject;


    }


}
