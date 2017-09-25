package com.hellohasan.networkcallwithretrofit.NetworkRelatedClass;

import com.hellohasan.networkcallwithretrofit.Activity.GetJokeListener;
import com.hellohasan.networkcallwithretrofit.Activity.UserValidityCheckListener;
import com.hellohasan.networkcallwithretrofit.Model.ServerResponse;
import com.hellohasan.networkcallwithretrofit.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCallImplementationService implements MyApiService{

    @Override
    public void userValidityCheck(User userLoginCredential, final UserValidityCheckListener userValidityCheckListener) {

        RetrofitApiInterface retrofitApiInterface = RetrofitApiClient.getClient().create(RetrofitApiInterface.class);
        Call<ServerResponse> call = retrofitApiInterface.getUserValidity(userLoginCredential);

        call.enqueue(new Callback<ServerResponse>() {

            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse validity = response.body();
                if(validity!=null){
                    if(validity.isSuccess())
                        userValidityCheckListener.onSuccessUserValidity(validity.getMessage());
                    else
                        userValidityCheckListener.onFailureUserValidity(validity.getMessage());
                }
                else
                    userValidityCheckListener.onFailureUserValidity(response.message());

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                userValidityCheckListener.onFailureUserValidity(t.getMessage());
            }
        });
    }

    @Override
    public void getJokeFromServer(String userId, final GetJokeListener getJokeListener) {
        RetrofitApiInterface retrofitApiInterface = RetrofitApiClient.getClient().create(RetrofitApiInterface.class);
        Call<ServerResponse> call = retrofitApiInterface.getJoke(userId);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse validity = response.body();
                if(validity!=null){
                    if(validity.isSuccess())
                        getJokeListener.onSuccessGetJoke(validity.getMessage());
                    else
                        getJokeListener.onFailureGetJoke(validity.getMessage());
                }
                else
                    getJokeListener.onFailureGetJoke(response.message());
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                getJokeListener.onFailureGetJoke(t.getMessage());
            }
        });
    }
}
