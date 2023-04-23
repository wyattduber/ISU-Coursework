package com.example.airplanned.model;

/**
 *
 * @author Julie Duong
 */

public class Lodging {


    private int id;
    private String name;
    private double price;
    private String checkIn;
    private String checkOut;
    private String location; //Might eventually use some API to make this an actual location if we have extra time.
    private LodgingType type;


    private Trip trip;

    /**
     *
     * @param name name of the lodging
     * @param price price with two decimal points
     * @param checkIn date of check in in the format "mm/dd/yyyy"
     * @param checkOut date of check out in the format "mm/dd/yyyy"
     * @param location name of the place where the lodging is located
     * @param type the type of lodging
     */
    public Lodging(String name, double price, String checkIn, String checkOut, String location, LodgingType type){
        this.name = name;
        this.price = price;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.location = location;
        this.type = type;
    }

    public Lodging(){

    }

//    public Trip getTrip(){
//        return trip;
//    }
//
//    public void setTrip(Trip trip){
//        this.trip = trip;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LodgingType getType() {
        return type;
    }

    public void setType(LodgingType type) {
        this.type = type;
    }

    public String printable(){
        return "id: " + this.id
                + "\n Hotel Name: " + this.name
                + "\n Price: " + this.price
                + "\n Check In: " + this.checkIn
                + "\n Check Out: " + this.checkOut
                + "\n Location: " + this.location
                + "\n Type: " +this.type;

    }

}