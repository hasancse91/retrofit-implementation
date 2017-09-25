package com.hellohasan.networkcallwithretrofit.Activity;

public interface GetJokeListener {
    void onSuccessGetJoke(String joke);
    void onFailureGetJoke(String message);
}
