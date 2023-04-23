package com.example.airplanned.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.airplanned.R;
import com.example.airplanned.model.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * the purpose of this class is to adapt the flight objects into a layout for
 * the flight pages functionality
 *
 * @author Saiyara Iftekharuzzaman
 */

public class FlightAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<Flight> flightList;
    ArrayList<Flight> arrayFlightList;

    /**
     * constructor
     * @param context
     * current context
     * @param flightList
     * list of flights that need to be put into the layout
     */
    public FlightAdapter(Context context, List<Flight> flightList) {
        mContext = context;
        this.flightList = flightList;
        inflater = LayoutInflater.from(mContext);
        this.arrayFlightList = new ArrayList<>();
        this.arrayFlightList.addAll(flightList);
    }

    /**
     * this class takes the xml page items and makes them into variables for the adapter to use
     */
    public class ViewHolder{
        TextView mTitleTv, mTimeTv, mArrivalTv, mDepartingTv, mPriceTv;

        //currently do not have images for each flight
        ImageView mIconIv;
    }

    /**
     * returns the size of the flight list
     * @return
     * the flight list size
     */
    @Override
    public int getCount() {
        return flightList.size();
    }

    /**
     * returns a flight object
     * @param i
     * takes an index
     * @return
     * the specific flight at that index
     */
    @Override
    public Object getItem(int i) {
        return flightList.get(i);
    }

    /**
     * returns the list item index
     * @param i
     * index of item
     * @return
     * index
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * sets the flight object information into a specific flight row layout that was created
     * @param position
     * flight row's position
     * @param view
     * current flight row view
     * @param parent
     * flight row parent
     * @return
     *  view
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;


        if (view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.flightrow, null);


            //locate the views in flight.row.xml
            holder.mTitleTv = view.findViewById(R.id.api_flightname);
            holder.mTimeTv = view.findViewById(R.id.api_flightDate);
            holder.mArrivalTv = view.findViewById(R.id.api_flightTo);
            holder.mDepartingTv = view.findViewById(R.id.api_flightFrom);
            holder.mPriceTv = view.findViewById(R.id.api_flightprice);
            //holder.mIconIv = view.findViewById(R.id.fli);

            view.setTag(holder);

        }
        else{
          holder = (ViewHolder)view.getTag();

        }

        //set the results into text views
        holder.mTitleTv.setText(flightList.get(position).getAirlineName());
        holder.mTimeTv.setText(flightList.get(position).getDate());
        holder.mArrivalTv.setText(flightList.get(position).getArriving());
        holder.mDepartingTv.setText(flightList.get(position).getDeparting());
        holder.mPriceTv.setText((int) flightList.get(position).getPrice());

        //holder.mIconIv.setImageResource(flightList.get(position).getIcon);

        //listView item clicks
            //I need a box to pop up which asks the user if they want to add the trip
            //then it will add the flight object to public variable of trip
            //this can be removed or updated in the trip class (methods in trip class)
        view.setOnClickListener(new View.OnClickListener() {

            /**
             * this method tracks on click
             * @param view
             * current view
             */
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext.getApplicationContext(), "Selected: " + flightList.get(position).getAirlineName() +", from " + flightList.get(position).getDeparting() + " to " +flightList.get(position).getArriving(),Toast.LENGTH_SHORT).show();

                //I need to send this flight object to the trip page to be saved there
                Flight curr = new Flight(flightList.get(position));

                int id = flightList.get(position).getId();

            }
        });
        return view;
    }


    /**
     * filters the list by user search
     * @param charText
     * character the user inputs
     */
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        flightList.clear();
        if(charText.length()==0) {
            flightList.addAll(arrayFlightList);
        }

            else{
                for (Flight flight : arrayFlightList){
                    //checks location or title of flight
                    if ((flight.getAirlineName().toLowerCase(Locale.getDefault()).contains(charText))
                            ||(flight.getArriving().toLowerCase(Locale.getDefault()).contains(charText))){
                        flightList.add(flight);
                    }
                }
            }

            notifyDataSetChanged();
        }

}
