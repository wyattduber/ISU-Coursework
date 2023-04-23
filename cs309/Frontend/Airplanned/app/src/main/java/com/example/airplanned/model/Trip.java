package com.example.airplanned.model;



/**
 * @author Julie Duong
 */
public class Trip{


    private int id;
    private boolean isArchived;
    private String duration;
    private String location;
    private Flight flight;
    private Lodging lodging;
    Flight aflight;
    Lodging hotel;


//    private double cpuClock;
//    private int cpuCores;
//    private int ram;
//    private String manufacturer;
//    private int cost;

    /*
     * @OneToOne creates a relation between the current entity/table(Laptop) with the entity/table defined below it(User)
     * @JsonIgnore is to assure that there is no infinite loop while returning either user/laptop objects (laptop->user->laptop->...)
     */

    private User user;


    /**
     *
     * @param lodging the lodging associated with this trip. Can be null if this trip does not have a lodging.
     * @param flight the flight associated with this trip. Can be null if this trip does not have a flight.
     * @param duration the time between checkin and checkout.
     * @param location The location of the trip. Same as the location of the lodging if present.
     */
    public Trip(Lodging lodging, Flight flight, String duration, String location) {
        this.lodging = lodging;
        this.flight = flight;
        this.duration = duration;
        this.location = location;
        isArchived = false;
    }

    public Trip() {
    }

    // =============================== Getters and Setters for each field ================================== //

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Flight getFlight(){
        return flight;
    }

    public void setFlight(Flight flight){
        this.flight = flight;
    }

    public Lodging getLodging(){
        return lodging;
    }

    public void setLodging(Lodging lodging){
        this.lodging = lodging;
    }

    public boolean getIsArchived(){
        return isArchived;
    }

    public void setIsArchived(boolean isArchived){
        this.isArchived = isArchived;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String printable(){
        return "id: " + this.id
                + "\n isArchive: " + this.isArchived
                + "\n Flight: " + this.flight
                + "\n Hotel: " + this.lodging
                + "\n Duration: " + this.duration
                + "\n Location: " + this.location;
    }
}
