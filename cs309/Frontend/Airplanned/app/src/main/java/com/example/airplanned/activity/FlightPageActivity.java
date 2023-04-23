package com.example.airplanned.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import static com.example.airplanned.api.ApiClientFactory.GetFLightApi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.airplanned.adapter.FlightAdapter;
import com.example.airplanned.R;
import com.example.airplanned.model.Flight;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * the purpose of this activity is to handle the searching of flights function
 * @author Saiyara Iftekharuzaman
 */
public class FlightPageActivity extends AppCompatActivity {
    // need to re add the back button but don't know where to place it
    ImageView backbtn;


    ListView listView;
    FlightAdapter adapter;


    Flight selected = null;

    String[] flightNames;
    String[] flightTimes;
    String[] flightArrival;
    String[] flightDeparture;
    double[] flightPrice;

    List<Flight> flightArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_page);


        flightNames = new String[]{"United", "Canada Airlines", "Delta", "Frontier", "American Airlines"
        };

        flightTimes = new String[]{"9:00 am", "8:00 pm", "11:00 am", "3:00 pm", "9:00 am"
        };

        flightDeparture = new String[]{"Mexico", "LA", "Sweden", "Canada", "Italy"};
        flightArrival = new String[]{"USA","SF","England", "USA" , "Australia"};
        flightPrice = new double[]{275, 45, 780, 456, 300};

        Flight test1 = new Flight("Airline",400,"1-8-22","La","SF");
        Flight test2 = new Flight("Airline B",470,"1-8-22","Mexico","Florida");
        Flight test3 = new Flight("Airline C",600,"1-8-22","Canada","Italy");

        listView = findViewById(R.id.listView);
        backbtn = findViewById(R.id.iv_flightPage_return);

        flightArrayList.add(test1);
        flightArrayList.add(test2);
        flightArrayList.add(test3);


        for(int i =0; i<flightNames.length; i++){
            Flight flight = new Flight(flightNames[i],flightPrice[i],flightTimes[i],flightDeparture[i],flightArrival[i]);
            //bind all strings in an array
            flightArrayList.add(flight);
        }



//        GetFLightApi().getFlights().enqueue(new Callback<List<Flight>>() {
//            @Override
//            public void onResponse(Call<List<Flight>> call, Response<List<Flight>> response) {
//                System.out.print(response.body());
//                //i need to make it so it converts it to a flight object from the list
//                flightArrayList = response.body();
//                //pass result to listViewAdapter class
//                adapter = new FlightAdapter(getApplicationContext(), flightArrayList);
//
//                //bind the adapter to the listview
//                listView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Flight>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"Failure:" + t.getMessage(),Toast.LENGTH_SHORT).show();
//
//            }
//        }
//        );






        //back button
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),HomePageActivity.class));
            }
        });


    }

    /**
     * this function creates a menu and handles searching of the user for flights
     * @param menu
     * current menu instance
     * @return
     * boolean if the char exists in the list
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.flightmenu, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(TextUtils.isEmpty(s)){
                    adapter.filter("");
                    listView.clearTextFilter();
                }
                else{
                    adapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }

    /**
     * this function creates the menu and connects the layout with functionality
     * @param item
     *  specific item that is clicked and tracks which flight the user has selected
     * @return
     *  boolean if something is selected
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        Toast.makeText(getApplicationContext(), "id:  " + id , Toast.LENGTH_SHORT).show();

        if(id==R.id.action_flights){
            //do your functionality here

            String name = flightArrayList.get(id).getAirlineName();
            double price = flightArrayList.get(id).getPrice();
            String date = flightArrayList.get(id).getDate();
            String departure = flightArrayList.get(id).getDeparting();
            String arrival = flightArrayList.get(id).getArriving();



            selected =  new Flight(name,price, date,departure,arrival);

            Toast.makeText(getApplicationContext(), "Flight selected:  " + name, Toast.LENGTH_SHORT).show();
            //now how do i send this object to the trip page


            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method makes a retrofit call to retrieve all the flights in the database
     * turns each flight into a flight object then displays the flights on screen by adding them
     * to a flight array
     */
//    public void displayFlights(){
//        //retrieves all flights in database
//        GetFLightApi().getFlights().enqueue(new Callback<List<Flight>>() {
//            @Override
//            public void onResponse(Call<List<Flight>> call, Response<List<Flight>> response) {
//                //i need to make it so it converts it to a flight object from the list
//                flightArrayList = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<List<Flight>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"Failure:" + t.getMessage(),Toast.LENGTH_SHORT).show();
//
//            }
//        }
//        );
//    }
}