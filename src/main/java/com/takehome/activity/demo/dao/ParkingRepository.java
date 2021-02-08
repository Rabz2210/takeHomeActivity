package com.takehome.activity.demo.dao;

import com.takehome.activity.demo.model.ParkingLot;
import com.takehome.activity.demo.model.Users;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ParkingRepository {
    int save(ParkingLot parkingLot);
    Users remove(int id);
    int saveUsers(Users users);
    ParkingLot getSlotDetail(String VehicleNumber);
    List<ParkingLot> slotsOfParticularAge(int Age);
    List<Users> vehicleByDriverAge(int Age);
}
