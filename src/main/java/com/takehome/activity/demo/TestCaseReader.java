package com.takehome.activity.demo;


import com.takehome.activity.demo.service.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.TreeSet;

@Component
public class TestCaseReader {

    @Autowired
    private Operations operations;

    private String filePath;

    public void readInput()throws Exception{

        String path = "C:\\Users\\mohammadrabbani\\Documents\\testFolder\\input.txt";
        String ParkinglotCreation = null;
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e1) {
            System.out.println("The requird file is not present at the location or does not have a read permission on it"+"\n"+"Terminating the programme");
            try {Thread.sleep(1000);} catch (InterruptedException e) {}
            System.exit(0);
        }
        try { ParkinglotCreation = br.readLine();} catch (Exception e) {}

        if(ParkinglotCreation!=null && ParkinglotCreation.split(" ").length==2 &&
                ParkinglotCreation.split(" ")[0].equalsIgnoreCase("Create_parking_lot") &&  Integer.parseInt(ParkinglotCreation.split(" ")[1])>0 && br!=null){
            System.out.println("Create Parking lot of size"+ParkinglotCreation.split(" ")[1]);
            int size = Integer.parseInt(ParkinglotCreation.split(" ")[1]);
            operations.setSize(size);

            String st;
            while((st=br.readLine())!=null){
                String[] command = st.split(" ");
                switch(command[0]){
                    case "Park"              : System.out.println(operations.park(command[1],Integer.parseInt(command[3])));break;
                    case "Leave"              : System.out.println(operations.leave(Integer.parseInt(command[1])));break;
                    case "Slot_number_for_car_with_number" : System.out.println(operations.parkedCarSlotNumber(command[1]));break;
                    case "Slot_numbers_for_driver_of_age" : operations.ParkedByAge(Integer.parseInt(command[1]));break;
                    case "Vehicle_registration_number_for_driver_of_age": operations.VehicleByAge(Integer.parseInt(command[1]));break;
                }
            }
        }
        else{
            System.out.println("Provide correctly formatted input file or a parking lot with size greater than 0");
        }

        }
    }


