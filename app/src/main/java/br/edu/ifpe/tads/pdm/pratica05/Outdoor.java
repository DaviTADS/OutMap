package br.edu.ifpe.tads.pdm.pratica05;



public class Outdoor {

    public double latitude;
    public double longitude;
    private boolean rented;
//    private Owner owner;
    public String owner;
    public String info;




    public Outdoor(){}

    public Outdoor(double latitude, double longitude, String owner , String info){

        this.latitude = latitude;
        this.longitude = longitude;
        this.rented = false;
        this.owner = owner;
        this.info = info;
//        this.owner = getOwner();


    }


//    public Owner getOwner() {
//        return owner;
//    }

//    public void setOwner(Owner owner) {
//        this.owner = owner;
//    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
