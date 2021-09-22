package com.example.car_service_wecarcare;

public class MainDsModel {
    String name;
    String phoneNo;
    String location;
    String brand;
    String vModel;
    String vMake;
    String fuelType;

    MainDsModel()
    {

    }

    public MainDsModel(String name, String phoneNo, String location, String brand, String vModel, String vMake, String fuelType, String date) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.location = location;
        this.brand = brand;
        this.vModel = vModel;
        this.vMake = vMake;
        this.fuelType = fuelType;
        this.date = date;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getvModel() {
        return vModel;
    }

    public void setvModel(String vModel) {
        this.vModel = vModel;
    }

    public String getvMake() {
        return vMake;
    }

    public void setvMake(String vMake) {
        this.vMake = vMake;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;
}
