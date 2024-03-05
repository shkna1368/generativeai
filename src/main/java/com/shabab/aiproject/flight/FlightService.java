package com.shabab.aiproject.flight;

import org.checkerframework.checker.units.qual.A;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class FlightService {


public static List<Flight> flightList=new ArrayList<>();

    public FlightService() {
        seed();
    }

    public List<Flight> save(JSONObject data) {

       String from= data.getString("from");
       String to=data.getString("to");
       String date=data.getString("date");
       String passport=data.getString("passport");
       String passengerName=data.getString("passengerName");

        final Random random = new Random();
        final int millisInDay = 24*60*60*1000;
       String time = new Time((long)random.nextInt(millisInDay)).toString();

        Flight flight=new Flight(from,to,date,passengerName,passport,time);

        flightList.add(flight);


        return flightList;


    }

    public List<Flight> search(JSONObject data) {
        String passport=data.getString("passport");
      return   flightList.stream().filter(flight -> flight.getPassport().equals(passport)).toList();



    }

    public List<Flight> delete(JSONObject data) {
        String passport=data.getString("passport");
       flightList.removeIf(flight -> flight.getPassport().equals(passport));

        return flightList;

    }


    public List<Flight> getAll() {

        return flightList;
    }



    public void seed() {
    Flight f1=new Flight("Sanadaj","Tokyo","2024-07-02","Shabab koohi","V5498787","10:30")  ;
    Flight f2=new Flight("Tokyo","Mahabad","2024-07-03","Beritan Koohi","V546379","12:30")  ;
    Flight f3=new Flight("Marivan","Dubai","2024-08-02","Rebwar azizi","V5466787","19:30")  ;
    Flight f4=new Flight("Oshnavieh","Zurikh","2024-09-02","Awin Amiri","V5422787","19:30")  ;
flightList.add(f1);
flightList.add(f2);
flightList.add(f3);
flightList.add(f4);



    }





}
