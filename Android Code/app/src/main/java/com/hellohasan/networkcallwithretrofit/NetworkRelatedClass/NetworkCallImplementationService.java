package com.hellohasan.networkcallwithretrofit.NetworkRelatedClass;

import com.hellohasan.networkcallwithretrofit.Model.ServerResponse;
import com.hellohasan.networkcallwithretrofit.Model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCallImplementationService implements MyApiService{

    @Override
    public void userValidityCheck(User userLoginCredential, final ResponseCallback<String> userValidityCheckListener) {

        RetrofitApiInterface retrofitApiInterface = RetrofitApiClient.getClient().create(RetrofitApiInterface.class);
        Call<ServerResponse> call = retrofitApiInterface.getUserValidity(userLoginCredential);

        call.enqueue(new Callback<ServerResponse>() {

            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse validity = response.body();
                if(validity!=null){
                    if(validity.isSuccess())
                        userValidityCheckListener.onSuccess(validity.getMessage());
                    else
                        userValidityCheckListener.onError(new Exception(validity.getMessage()));
                }
                else
                    userValidityCheckListener.onError(new Exception(response.message()));

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                userValidityCheckListener.onError(t);
            }
        });
    }

    @Override
    public void getJokeFromServer(String userId, final ResponseCallback<String> getJokeListener) {
        RetrofitApiInterface retrofitApiInterface = RetrofitApiClient.getClient().create(RetrofitApiInterface.class);
        Call<ServerResponse> call = retrofitApiInterface.getJoke(userId);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse validity = response.body();
                if(validity!=null){
                    if(validity.isSuccess())
                        getJokeListener.onSuccess(validity.getMessage());
                    else
                        getJokeListener.onError(new Exception(validity.getMessage()));
                }
                else
                    getJokeListener.onError(new Exception(response.message()));
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                getJokeListener.onError(t);
            }
        });
    }
}
