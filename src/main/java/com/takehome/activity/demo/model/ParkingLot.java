package com.takehome.activity.demo.model;

public class ParkingLot {
    private int ParkingslotId;
    private String VehicleNumber;


    public ParkingLot() {
    }

    public ParkingLot(int parkingslotId) {
        ParkingslotId = parkingslotId;

    }

    public int getParkingslotId() {
        return ParkingslotId;
    }

    public void setParkingslotId(int parkingslotId) {
        ParkingslotId = parkingslotId;
    }

    public String getVehicleNumber() {
        return VehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        VehicleNumber = vehicleNumber;
    }
}
