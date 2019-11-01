package br.edu.ifpe.tads.pdm.pratica05;



public class Outdoor {

    public double latitude;
    public double longitude;
    private boolean rented;
    private Owner owner;


    public Outdoor(){}

    public Outdoor(double latitude, double longitude){

        this.latitude = latitude;
        this.longitude = longitude;
        this.rented = false;
        this.owner = getOwner();


    }


    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}
