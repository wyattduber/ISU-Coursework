package com.example.airplanned.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.airplanned.R;
import com.example.airplanned.api.ApiClientFactory;
import com.example.airplanned.model.User;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The purpose of this activity is to let the user have own profile page
 * The user can log out, change password, delete account.
 * @author Julie Duong
 */

public class ProfilePageActivity extends AppCompatActivity {
    TextView userEmail, passwordUser, userName;
    TextView friendList;
    ImageView backButton;
    TextView userPassword;
    Button deleteAccount, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        logout = findViewById(R.id.logout);
        friendList = findViewById(R.id.friendList);
        backButton = findViewById(R.id.backButton);
        userEmail = findViewById(R.id.email_user);
        userName = findViewById(R.id.nameUser);
        userPassword = findViewById(R.id.user_password);
        passwordUser = findViewById(R.id.user_password);
        deleteAccount = findViewById(R.id.deleteAccount);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePageActivity.this, HomePageActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePageActivity.this, LoginPageActivity.class));
            }
        });

        friendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePageActivity.this, FriendListPageActivity.class));
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePageActivity.this, HomePageActivity.class));
            }
        });

    }
}