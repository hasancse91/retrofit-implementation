package com.hellohasan.networkcallwithretrofit.NetworkRelatedClass;

import com.hellohasan.networkcallwithretrofit.Model.User;

public interface MyApiService {
    void userValidityCheck(User userLoginCredential, ResponseCallback<String> callback);
    void getJokeFromServer(String userId, ResponseCallback<String> callback);
}
