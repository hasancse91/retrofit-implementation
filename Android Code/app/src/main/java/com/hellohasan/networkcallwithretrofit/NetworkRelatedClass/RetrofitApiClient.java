package com.hellohasan.networkcallwithretrofit.NetworkRelatedClass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
 
public class RetrofitApiClient {
 
        private static final String BASE_URL = "http://192.168.0.105/"; //address of your remote server. Here I used localhost
        private static Retrofit retrofit = null;
 
        private static Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        private RetrofitApiClient() {} // So that nobody can create an object with constructor
 
        public static synchronized Retrofit getClient() {
            if (retrofit==null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
            }
            return retrofit;
        }
 
}