package br.edu.ifpe.tads.pdm.pratica05;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Outdoor {

    private double latitude;
    private double longitude;
    private boolean rented;
    private Owner owner;
//    private Date dataAluguelInicio;
//    private Date dataAluguelFim;
//    private float fixedFee;
//    private float totalFee;

    public Outdoor(){

        this.latitude = getLatitude();
        this.longitude = getLongitude();
        this.rented = false;
        this.owner = getOwner();
//        this.fixedFee = 20;

    }

//    public float getTotalFee() {
//        return totalFee;
//    }
//
//    public void setTotalFee(float totalFee) {
//        this.totalFee = totalFee;
//    }
//
//    public float getFixedFee() {
//        return fixedFee;
//    }
//
//    public void setFixedFee(float fixedFee) {
//        this.fixedFee = fixedFee;
//    }
//
//    public Date getDataAluguelInicio() {
//        return dataAluguelInicio;
//    }
//
//    public void setDataAluguelInicio(Date dataAluguelInicio) {
//        this.dataAluguelInicio = dataAluguelInicio;
//    }

//    public Date getDataAluguelFim() {
//        return dataAluguelFim;
//    }
//
//    public void setDataAluguelFim(Date dataAluguelFim) {
//        this.dataAluguelFim = dataAluguelFim;
//    }

//    private List<Company> companies = new ArrayList<Company>();

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

//    public List<Company> getCompanies() {
//        return companies;
//    }
//
//    public void setCompanies(List<Company> companies) {
//        this.companies = companies;
//    }

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
