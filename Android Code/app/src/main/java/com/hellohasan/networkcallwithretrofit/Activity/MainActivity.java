package com.hellohasan.networkcallwithretrofit.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hellohasan.networkcallwithretrofit.Model.User;
import com.hellohasan.networkcallwithretrofit.NetworkRelatedClass.MyApiService;
import com.hellohasan.networkcallwithretrofit.NetworkRelatedClass.NetworkCallImplementationService;
import com.hellohasan.networkcallwithretrofit.R;

public class MainActivity extends AppCompatActivity implements UserValidityCheckListener, GetJokeListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText userIdEditText;
    private EditText passwordEditText;
    private EditText jokeUserIdEditText;
    private TextView jokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            MyApiService myApiService = new NetworkCallImplementationService();
            myApiService.userValidityCheck(user, this); //user credential and listener

        }
        else {
            String userId;

            userId = jokeUserIdEditText.getText().toString();

            //call method of interface
            MyApiService myApiService = new NetworkCallImplementationService();
            myApiService.getJokeFromServer(userId, this); //user credential and listener

        }
        
    }


    /*
    implemented methods for user validity listener
     */
    @Override
    public void onSuccessUserValidity(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailureUserValidity(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    } //end of user validity listener's implemented methods


    /*
    implemented methods for getting joke listener
     */
    @Override
    public void onSuccessGetJoke(String joke) {
        jokeTextView.setText(joke);
    }

    @Override
    public void onFailureGetJoke(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    } //end of getting joke's implemented methods

}
