package com.takehome.activity.demo.model;

public class Users {
    private String VehicleNumber;
    private int age;

    public Users() {
    }

    public Users(String vehicleNumber) {
        VehicleNumber = vehicleNumber;
    }

    public String getVehicleNumber() {
        return VehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        VehicleNumber = vehicleNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
