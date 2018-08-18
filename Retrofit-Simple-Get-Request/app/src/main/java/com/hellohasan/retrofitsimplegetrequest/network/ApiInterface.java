package com.hellohasan.retrofitsimplegetrequest.network;


import com.hellohasan.retrofitsimplegetrequest.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/json") //Here, `json` is the PATH PARAMETER
    Call<ServerResponse> getMyIp();
}