package com.takehome.activity.demo.dao;

import com.takehome.activity.demo.model.ParkingLot;
import com.takehome.activity.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcParkingRepository implements  ParkingRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Override
    public int save(ParkingLot parkingLot){
        return jdbcTemplate.update("insert into PARKTABLE (ParkingSlot,VehicleNumber) values" +
                "(?,?)",parkingLot.getParkingslotId(),parkingLot.getVehicleNumber());

    }

    @Override
    public Users remove(int id){
        String sql = "SELECT USERS.VehicleNumber,USERS.AGE FROM PARKTABLE ,USERS  WHERE PARKTABLE.ParkingSlot = ? and PARKTABLE.VehicleNumber = USERS.VehicleNumber";
        Users udetail = (Users) jdbcTemplate.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<>(Users.class));
        jdbcTemplate.update("delete from USERS WHERE VehicleNumber= ?",udetail.getVehicleNumber());
        jdbcTemplate.update("delete from PARKTABLE WHERE ParkingSlot= ?",id);
        return udetail;
    }

    @Override
    public int saveUsers(Users users){
        return jdbcTemplate.update("insert into USERS (VehicleNumber,AGE) values ( ?,? )",users.getVehicleNumber(),users.getAge());
    }


    @Override
    public ParkingLot getSlotDetail(String VehicleNumber) {
        String sql ="SELECT * FROM PARKTABLE WHERE VehicleNumber = ?";
        System.out.println("\""+VehicleNumber+"\"");
        ParkingLot pk = (ParkingLot) jdbcTemplate.queryForObject(sql,new Object[]{VehicleNumber},new BeanPropertyRowMapper<>(ParkingLot.class));
        return pk;
    }

    @Override
    public List<ParkingLot> slotsOfParticularAge(int Age) {
        String sql = "SELECT PARKTABLE.ParkingSlot FROM PARKTABLE ,USERS  WHERE USERS.AGE = ? AND PARKTABLE.VehicleNumber = USERS.VehicleNumber";
        List<ParkingLot>ls = jdbcTemplate.query(sql,new Object[]{Age},((resultSet, i) -> new ParkingLot(resultSet.getInt("ParkingSlot"))));
        return ls;
    }

    @Override
    public List<Users> vehicleByDriverAge(int Age) {
        String sql = "SELECT USERS.VehicleNumber FROM USERS WHERE USERS.AGE=?";
        List<Users> ls= jdbcTemplate.query(sql,new Object[]{Age},((resultSet, i) -> new Users(resultSet.getString("VehicleNumber"))));
        return ls;
    }
}
