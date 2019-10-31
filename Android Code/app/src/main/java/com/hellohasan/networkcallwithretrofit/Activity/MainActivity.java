package com.hellohasan.networkcallwithretrofit.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hellohasan.networkcallwithretrofit.Interface.ApiInterface;
import com.hellohasan.networkcallwithretrofit.Model.User;
import com.hellohasan.networkcallwithretrofit.Model.ServerResponse;
import com.hellohasan.networkcallwithretrofit.R;
import com.hellohasan.networkcallwithretrofit.Retrofit.RetrofitApiClient;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ApiInterface apiInterface;
    private EditText userIdEditText;
    private EditText passwordEditText;
    private EditText jokeUserIdEditText;
    private TextView jokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create an instance of Interface
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        //Initialize the view like EditText, TextView
        viewInitialization();
    }

    // Login button event
    public void buttonClickEvent(View view){

        if(view.getId()==R.id.login_button){
            String userId;
            String password;
            User user = new User();

            userId = userIdEditText.getText().toString();
            password = passwordEditText.getText().toString();

            user.setUserId(userId);
            user.setPassword(password);

            checkUserValidity(user);
        } else {
            String userId;

            userId = jokeUserIdEditText.getText().toString();

            getJokeFromServer(userId);
        }
        
    }

    // GET method to get a Joke from remote server
    private void getJokeFromServer(String userId) {

        Call<ServerResponse> call = apiInterface.getJoke(userId);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                ServerResponse validity = response.body();
                if (validity != null)
                    jokeTextView.setText(validity.getMessage());
                else
                    jokeTextView.setText("Server response is null");
            }

            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    // POST method to determine user validity
    private void checkUserValidity(User userCredential){

        Call<ServerResponse> call = apiInterface.getUserValidity(userCredential);

        call.enqueue(new Callback<ServerResponse>() {

            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {

                ServerResponse validity = response.body();

                if (validity != null)
                    Toast.makeText(getApplicationContext(), validity.getMessage(), Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Server response is null", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void viewInitialization() {
        userIdEditText = findViewById(R.id.login_id);
        passwordEditText = findViewById(R.id.login_password);
        jokeUserIdEditText = findViewById(R.id.user_id_for_joke);
        jokeTextView = findViewById(R.id.jokeTextView);
    }
}
