package com.example.airplanned.model;

/**
 * The purpose of this class is to list all retrofit requests for flights
 * @author Julie Duong
 */

public class Flight {

    private int id;
    private String airlineName;
    private double price;
    private String date;
    private String departing; //Might use some API to make this an actual location if we have extra time.
    private String arriving; //Might use some API to make this an actual location if we have extra time.
    //More stuff for this class to be made later


    private Trip trip;

    /**
     *
     * @param airlineName name of the airline
     * @param price price with two decimal points
     * @param date date in the format "mm/dd/yyyy"
     * @param departing location the flight is departing from
     * @param arriving location the flight is arriving to
     */
    public Flight(String airlineName, double price, String date, String departing, String arriving){
        this.airlineName = airlineName;
        this.price = price;
        this.date = date;
        this.departing = departing;
        this.arriving = arriving;
    }

    public Flight(){

    }

    public Flight(Flight flight) {
        this.airlineName = flight.airlineName;
        this.price = flight.price;
        this.date = flight.date;
        this.departing = flight.departing;
        this.arriving = flight.arriving;
    }

    public Trip getTrip(){
        return trip;
    }

    public void setTrip(Trip trip){
        this.trip = trip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeparting() {
        return departing;
    }

    public void setDeparting(String departing) {
        this.departing = departing;
    }

    public String getArriving() {
        return arriving;
    }

    public void setArriving(String arriving) {
        this.arriving = arriving;
    }

    public String printable(){
        return "id: " + this.id
                + "\n Flight Name: " + this.airlineName
                + "\n Price: " + this.price
                + "\n Date: " + this.date
                + "\n Departing: " + this.departing
                + "\n Arriving: " + this.arriving;
    }


}
