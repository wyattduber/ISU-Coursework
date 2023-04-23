package com.example.airplanned.activity;

import static com.example.airplanned.api.ApiClientFactory.GetUserApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.airplanned.R;
import com.example.airplanned.model.GlobalVariables;
import com.example.airplanned.model.User;


import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class displays the homepage by initially welcoming the user back
 * then displaying most recent trip planned
 * also has a bottom navigation
 * and links to the profile page
 * @author Saiyara Iftekharuzzaman
 */
public class HomePageActivity extends AppCompatActivity {

    private TextView welcomeTxt;
    private TextView plannedFlightsTxt;
    private ImageView hp_iv_profile;
    private ImageView hp_iv_flight;
    private ImageView hp_iv_post;
    private ImageView hp_iv_archPost;
    private ImageView hp_iv_hotel;
    private ImageView hp_iv_trip;
    private ImageView hp_iv_vacaImage;
    private Button hp_btn_recVaca;
    private Button hp_btn_chat;
    private TextView hp_tv_recTitle;
    private ImageView hp_iv_happy;
    private ImageView hp_iv_sad;
    private ImageView hp_iv_neutral;


    /**
     * this method creates the home page and links layout to functionality
     * @param savedInstanceState
     * current saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        welcomeTxt = findViewById(R.id.tv_homePage_welcome);
        plannedFlightsTxt = findViewById(R.id.tv_homePage_RecentTrip);
        hp_iv_profile = findViewById(R.id.iv_homePage_profile);
        hp_iv_flight = findViewById(R.id.iv_homePage_flight);
        hp_iv_post = findViewById(R.id.iv_homePage_explore);
        hp_iv_archPost = findViewById(R.id.iv_homepage_archive);
        hp_iv_trip = findViewById(R.id.iv_homePage_planner);
        hp_iv_hotel = findViewById(R.id.iv_homePage_hotel);
        hp_iv_vacaImage = findViewById(R.id.iv_homePage_ReccomendedImage);
        hp_btn_recVaca = findViewById(R.id.btn_homePage_recPage);
        hp_tv_recTitle = findViewById(R.id.tv_homePage_ReccomendedTrip);
        hp_iv_happy = findViewById(R.id.iv_homepage_happy);
        hp_iv_sad = findViewById(R.id.iv_homepage_sad);
        hp_iv_neutral = findViewById(R.id.iv_homepage_neutral);
        hp_btn_chat = findViewById(R.id.btn_homePage_chat);


        //gets the string from login and assigns to variable
        Intent intent = getIntent();
        String emailFromLogin;
        if (intent.getStringExtra("email_key") == null){
           emailFromLogin = intent.getStringExtra("email_key1");
        }
        else {
            emailFromLogin = intent.getStringExtra("email_key");
        }

        //retrofit call
        welcomeCurrentUser(emailFromLogin);


        /**
         * clicks happy
         */
    hp_iv_happy.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(),"Thank you for using our app!",Toast.LENGTH_SHORT).show();

        }
    });

        /**
         * clicks sad
         */
        hp_iv_sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"We hope to improve your experience!",Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * clicks a neutral
         */
        hp_iv_neutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"It's the perfect time to book a trip!",Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * sends to chat
         */
        hp_btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),ChatPageActivity.class));
            }
        });

        /**
         * button click to recommend a place to visit
         */
        hp_btn_recVaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hp_tv_recTitle.setText("Check this out!: ");

                int[] images = {R.drawable.austrailia,R.drawable.greece,R.drawable.ireland, R.drawable.spain,R.drawable.mexico};

                String[] countries = {"Australia","Greece","Ireland","Italy","Mexico"};

                Random rand = new Random();
                int numb = rand.nextInt(images.length);

                hp_iv_vacaImage.setImageResource(images[numb]);
                hp_tv_recTitle.append(countries[numb]);

            }
        });



        /**
         * button click sends to profile page
         */
        hp_iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),ProfilePageActivity.class));
            }
        });

        /**
         * button click sends to post page
         */
        hp_iv_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),PostPageActivity.class));
            }
        });

        /**
         * button click sends to flight page
         */
        hp_iv_flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),FlightPageActivity.class));
            }
        });

        /**
         * button click sends to archived post page
         */
        hp_iv_archPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),ArchivedPostPageActivity.class));
            }
        });

        /**
         * button click sends to trip page
         */
        hp_iv_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),TripPlannedList.class));
            }
        });

        /**
         * button click sends to hotel page
         */
        hp_iv_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),LodgingPageActivity.class));
            }
        });

    }

    /**
     * This method gets the current user and simply displays their name to welcome them back to the
     * app
     * @param email
     *  email from login or registration page
     */
    public void welcomeCurrentUser(String email){
        GetUserApi().getUserByEmail(email).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    welcomeTxt.setText("welcome back!" + "\n" + response.body().getEmailId());
                    GlobalVariables globalVariables = (GlobalVariables) getApplicationContext();
                    globalVariables.setCurrent(response.body());
                }
                else{
                    //this is just for testing purposes
                    welcomeTxt.setText("Welcome to Airplanned!: " + response.body().getEmailId());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                welcomeTxt.setText("Welcome!");
            }
        });
    }
}
