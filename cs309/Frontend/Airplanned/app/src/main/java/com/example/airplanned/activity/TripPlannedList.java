package com.example.airplanned.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.airplanned.R;


import com.example.airplanned.adapter.TripAdapter;
import com.example.airplanned.api.ApiClientFactory;
import com.example.airplanned.model.Trip;
import com.example.airplanned.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Julie Duong
 */
public class TripPlannedList extends AppCompatActivity {


    Toolbar toolbar;
    FloatingActionButton addButton;
    RecyclerView recyclerView;
    TripAdapter tripAdapter;
    String email;
    int userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_planned_list);
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TripPlannedList.this, AddingTripActivity.class).putExtra("emailid", userid));
            }
        });

        userid = getIntent().getIntExtra("emailId", 0);

        recyclerView = findViewById(R.id.recycleView);
        toolbar = findViewById(R.id.toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        tripAdapter = new TripAdapter();

        getAllTrip();

    }


    public void getAllTrip() {
        Call<List<Trip>> call = ApiClientFactory.GetTripApi().getAllTrips();
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if(response.isSuccessful()) {
                    List<Trip> trips = response.body();
                    tripAdapter.setData(trips);
                    recyclerView.setAdapter(tripAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                Log.e("Fail", t.getLocalizedMessage());
            }
        });

    }

}
