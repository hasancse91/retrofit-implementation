package com.hellohasan.networkcallwithretrofit.Model;


import com.google.gson.annotations.SerializedName;

public class ServerResponse {

        @SerializedName("status")
        boolean statusString;
        @SerializedName("message")
        String messageString;

        public boolean isSuccess(){
            return statusString;
        }

        public String getMessage() {
        return messageString;
    }
}
