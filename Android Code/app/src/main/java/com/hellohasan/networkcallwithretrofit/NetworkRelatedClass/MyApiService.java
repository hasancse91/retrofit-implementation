package com.hellohasan.networkcallwithretrofit.NetworkRelatedClass;

import com.hellohasan.networkcallwithretrofit.Activity.GetJokeListener;
import com.hellohasan.networkcallwithretrofit.Activity.UserValidityCheckListener;
import com.hellohasan.networkcallwithretrofit.Model.User;

public interface MyApiService {
    void userValidityCheck(User userLoginCredential, UserValidityCheckListener userValidityCheckListener);
    void getJokeFromServer(String userId, GetJokeListener getJokeListener);
}
