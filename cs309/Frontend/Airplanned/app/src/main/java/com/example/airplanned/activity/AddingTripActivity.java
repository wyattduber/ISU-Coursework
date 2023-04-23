package com.example.airplanned.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.airplanned.R;
import com.example.airplanned.api.ApiClientFactory;
import com.example.airplanned.model.Flight;
import com.example.airplanned.model.Lodging;
import com.example.airplanned.model.LodgingType;
import com.example.airplanned.model.Trip;
import com.example.airplanned.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * This purpose of this activity is to create a trip from API flights and hotels
 * or directly typing the trip information from console and save them for specific user.
 * @author Julie Duong
 */

public class AddingTripActivity extends AppCompatActivity {

    EditText trip_location, trip_duration;
    EditText flightName, flightPrice, flightDate, flightDeparting, flightArriving;
    EditText hotelName, hotelPrice, hotelCheckIn, hotelCheckOut, hotelLocation, hotelType;
    Button  addingtrip_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_trip);

        trip_location = findViewById(R.id.plantrip_location);
        trip_duration = findViewById(R.id.length_of_trip);
        addingtrip_button = findViewById(R.id.addtrip_button);

        flightName = findViewById(R.id.addtrip_flight_name);
        flightPrice = findViewById(R.id.addtrip_flight_price);
        flightDate = findViewById(R.id.addtrip_flight_date);
        flightDeparting = findViewById(R.id.addtrip_flight_departing);
        flightArriving = findViewById(R.id.addtrip_flight_arriving);

        hotelName = findViewById(R.id.addtrip_lodging_name);
        hotelPrice = findViewById(R.id.addtrip_lodging_price);
        hotelCheckIn = findViewById(R.id.addtrip_lodging_checkIn);
        hotelCheckOut = findViewById(R.id.addtrip_lodging_checkOut);
        hotelLocation = findViewById(R.id.addtrip_lodging_location);
        hotelType = findViewById(R.id.addtrip_lodging_type);


        addingtrip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTrip(createTrip());
                startActivity(new Intent(AddingTripActivity.this, TripPlannedList.class));
            }
        });

    }


    public Trip createTrip() {
        Flight flight = new Flight();
//        flight.setFlightName(flightName.getText().toString());
//        flight.setFlightPrice(flightPrice.getInputType());
//        flight.setFlightDate(flightDate.getText().toString());
//        flight.setFlightDeparture(flightDeparting.getText().toString());
//        flight.setFlightArrival(flightArriving.getText().toString());
//        flight.setFlightPrice(Double.parseDouble(flightPrice.getText().toString()));
        Lodging hotel = new Lodging();
        hotel.setName(hotelName.getText().toString());
        hotel.setPrice(Double.parseDouble(hotelPrice.getText().toString()));
        hotel.setCheckIn(hotelCheckIn.getText().toString());
        hotel.setCheckOut(hotelCheckOut.getText().toString());
        if(hotelType.getText().toString().equalsIgnoreCase("CABIN")) {
            hotel.setType(LodgingType.CABIN);
        }else if(hotelType.getText().toString().equalsIgnoreCase("HOTEL")) {
            hotel.setType(LodgingType.HOTEL);
        }else if(hotelType.getText().toString().equalsIgnoreCase("BNB")) {
            hotel.setType(LodgingType.BNB);
        }
        hotel.setLocation(hotelLocation.getText().toString());
        Trip trip = new Trip();
        trip.setLocation(trip_location.getText().toString());
        trip.setDuration(trip_duration.getText().toString());
        trip.setLodging(hotel);
        trip.setFlight(flight);
        return trip;
    }

    public void saveTrip(Trip trip) {

        Call<Integer> tripResponse = ApiClientFactory.GetTripApi().saveTrip(trip);

        tripResponse.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddingTripActivity.this, "Saved Sucessfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AddingTripActivity.this, "Request Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(AddingTripActivity.this, "Request Failed" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
