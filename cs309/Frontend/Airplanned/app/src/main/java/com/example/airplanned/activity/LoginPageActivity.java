package com.example.airplanned.activity;

import static com.example.airplanned.api.ApiClientFactory.GetUserApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.airplanned.R;
import com.example.airplanned.model.GlobalVariables;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class takes user input of username and password
 * and then sends it to backend to check if the
 * user exists and or if the password is correct
 * @author Saiyara Iftekharuzzaman
 */
public class LoginPageActivity extends AppCompatActivity {

    private TextView etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;
    private String emailInput;
    private String passwordInput;

    /**
     * this method connects layout to functionality of the login page
     * @param savedInstanceState
     * current instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_LoginScreen_Username);
        etPassword = findViewById(R.id.et_LoginScreen_Password);
        btnLogin = findViewById(R.id.btn_LoginScreen_Login);
        btnRegister = findViewById(R.id.btn_LoginScreen_Register);

        GlobalVariables globalVariables = (GlobalVariables) getApplicationContext();



        //when button is clicked
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emailInput = etEmail.getText().toString();
                passwordInput = etPassword.getText().toString();


                //method with retrofit POST call
                checkCredentials(emailInput,passwordInput,view);

            }
        });


        //sends to register screen
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),RegisterPageActivity.class));
            }
        });
    }

    /**
     * this method sends the user input to a POST call
     * to server to check if the user exists
     * @param email
     * input of email
     * @param password
     * input of password
     * @param view
     * current view
     */
    public void checkCredentials(String email, String password, View view){

//        private final int FAIL = 0;
//        private final int SUCCESS = 1;
//        private final int BAD_PASSWORD = 2;
//        private final int USER_DOES_NOT_EXIST = 3;
//        private final int USER_IS_NOT_ACTIVE = 4;


        startActivity(new Intent(view.getContext(),HomePageActivity.class));

        GetUserApi().PostUserByBodyLog(email,password).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                if(response.body() == 1){
                    Toast.makeText(getApplicationContext(),"Successful login",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(view.getContext(),HomePageActivity.class).putExtra("email_key1",email));

                }
                else if (response.body()== 0){
                    Toast.makeText(getApplicationContext(),"Invalid login",Toast.LENGTH_SHORT).show();
                }
                else if (response.body()== 2){

                    Toast.makeText(getApplicationContext(),"Invalid password",Toast.LENGTH_SHORT).show();
                }
                else if (response.body()== 3){
                    Toast.makeText(getApplicationContext(),"User does not exist",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.d("error message", t.getMessage());
                Toast.makeText(getApplicationContext(),"Failure:" + t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}


