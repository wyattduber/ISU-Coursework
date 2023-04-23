package com.example.experiment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etPassword;
    private Button btnLogin;

    //compare for testing
    private String compUser = "Saiyara";
    private String compPass = "myPassword";

    private boolean isValid = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //trying to attach variable to element in layout
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        //when button is clicked it gets the text from the username and password boxes
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = etUserName.getText().toString();
                String inputPassword = etPassword.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter text",Toast.LENGTH_SHORT).show();
                }
                else{
                    isValid = correctCredentials(inputName, inputPassword);
                    if(isValid == false){
                        Toast.makeText(MainActivity.this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(MainActivity.this,"Successful Login",Toast.LENGTH_SHORT).show();

                        //send user to home page
                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        startActivity(intent);

                    }
                }
            }
        });
    }
    //function to compare the password to what is in the system
    private boolean correctCredentials(String name, String pass){
        if(name.equals(compUser)&& pass.equals(compPass)){
            return true;
        }
        else {
            return false;
        }
    }

}