package com.hellohasan.networkcallwithretrofit.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.hellohasan.networkcallwithretrofit.Model.User;
import com.hellohasan.networkcallwithretrofit.NetworkRelatedClass.MyApiService;
import com.hellohasan.networkcallwithretrofit.NetworkRelatedClass.NetworkCall;
import com.hellohasan.networkcallwithretrofit.NetworkRelatedClass.ResponseCallback;
import com.hellohasan.networkcallwithretrofit.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {

    private EditText userIdEditText;
    private EditText passwordEditText;
    private EditText jokeUserIdEditText;
    private TextView jokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.addLogAdapter(new AndroidLogAdapter());

        //Initialize the view like EditText, TextView
        userIdEditText = (EditText) findViewById(R.id.login_id);
        passwordEditText = (EditText) findViewById(R.id.login_password);
        jokeUserIdEditText = (EditText) findViewById(R.id.user_id_for_joke);
        jokeTextView = (TextView) findViewById(R.id.jokeTextView);

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

            //call method of interface
            MyApiService myApiService = new NetworkCall();
            myApiService.userValidityCheck(user, new ResponseCallback<String>() {
                @Override
                public void onSuccess(String msg) {
                    showToast(msg);
                    Logger.d("Activity: onSuccess of userValidity method is called");
                }

                @Override
                public void onError(Throwable th) {
                    showToast(th.getMessage());
                    Logger.d("Activity: onError of userValidity method is called");
                }
            }); //user credential and listener

        }
        else {
            String userId;

            userId = jokeUserIdEditText.getText().toString();

            //call method of interface
            MyApiService myApiService = new NetworkCall();
            myApiService.getJokeFromServer(userId, new ResponseCallback<String>() {
                @Override
                public void onSuccess(String joke) {
                    jokeTextView.setText(joke);
                    Logger.d("Activity: onSuccess of getJoke method is called");
                }

                @Override
                public void onError(Throwable th) {
                    showToast(th.getMessage());
                    Logger.d("Activity: onError of getJoke method is called");
                }
            }); //user credential and listener

        }
        
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

}
