package com.takehome.activity.demo.service;

import com.takehome.activity.demo.dao.JdbcParkingRepository;
import com.takehome.activity.demo.dao.ParkingRepository;
import com.takehome.activity.demo.model.ParkingLot;
import com.takehome.activity.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.dao.DuplicateKeyException;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Component
public class Operations {

    private SortedSet<Integer> st;
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        this.setSt();
    }

    public SortedSet<Integer> getSt() {
        return st;
    }

    public void setSt() {
        this.st = new TreeSet<Integer>();
        for(int i=1;i<=this.size;i++)st.add(i);
    }

    @Autowired
    private ParkingRepository parkingRepository;

    //To park the vehicle
    public String park(String VehicleNumber,int Age){
        final ParkingLot obj = new ParkingLot();
        final Users uobj = new Users();
        obj.setVehicleNumber(VehicleNumber);
        uobj.setAge(Age);
        uobj.setVehicleNumber(VehicleNumber);
        if(this.getSt().size()>0){
            int id = this.getSt().first();
            obj.setParkingslotId(id);
            this.getSt().remove(id);
            try{parkingRepository.save(obj);
            parkingRepository.saveUsers(uobj);}catch (DuplicateKeyException exception){return("Vehicle with" +
                    "vehicle number"+"\""+VehicleNumber+"\""+"is already parked.");}
            return ("Car with vehicle registration number \""+VehicleNumber+"\" has been parked at slot number "+id);}
        else{
            return ("Could not park the vehicle with vehicle registration number \""+VehicleNumber+"\". Parking Full");
        }
    }

    //To leave the parking lot
    public String leave(int id){
        if(id>0 && id<=this.size) {
           Users us= parkingRepository.remove(id);
            this.getSt().add(id);
            return ("Slot number "+id+" evacated, the car with vehicle registration number \""+us.getVehicleNumber()+
                    "\" left the space, the driver of the car was of age "+ us.getAge());
        }
        else{return "Incorrect parking slot passed, Pleae pass correct parking slot between 0 and "+this.size;}
    }

    //Get Parked Slot number base on vehicle number
    public String parkedCarSlotNumber(String vehicleNumber){
        final ParkingLot slotDetil = parkingRepository.getSlotDetail(vehicleNumber);
        return ("Car with vehicle registration number  \""+slotDetil.getVehicleNumber()+"\" has been parked at slot number "+slotDetil.getParkingslotId());
    }

    //Get  parking slots of particular age group
    public void ParkedByAge(int age){
        if(age>=18){
            List<ParkingLot> slotList = parkingRepository.slotsOfParticularAge(age);
            if(slotList==null || slotList.size()==0)System.out.println("No car with given age has been parked");
            else{
                for(ParkingLot each:slotList){System.out.print(each.getParkingslotId()+",");}
            }
        }else{System.out.println("Illegal Age");}
        System.out.println("");
    }

    //Get vehicle numbers of all vehicles parked by a particular age group
    public void VehicleByAge(int age){
        if(age>=18){
            List<Users> vehicleList =parkingRepository.vehicleByDriverAge(age);
             if(vehicleList==null || vehicleList.size()==0)System.out.println("No car parked by driver of age "+age);
                else{
                    for(Users each:vehicleList){System.out.print(each.getVehicleNumber()+",");}
             }
        }else{
            System.out.println("Illegal Age");
        }
        System.out.println();
    }
}
