package org.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.*;

public class HotelReservation {
    public String hotelName;
    public Double price;
    public HotelReservation(String hotelName, Double price) {
        this.hotelName = hotelName;
        this.price = price;
    }
    public static HotelReservation createHotel(String hotelName, Double price) {
        return new HotelReservation(hotelName, price);
    }
    public static void main(String[] args) {
        HotelReservation ob1 = createHotel("Lakewood", 110.0);
        HotelReservation ob2 = createHotel("Bridgewood", 160.0);
        HotelReservation ob3 = createHotel("Ridgewood", 210.0);
        HashMap<String, HotelReservation> hm = new HashMap<>();
        hm.put(ob1.hotelName, ob1);
        hm.put(ob2.hotelName, ob2);
        hm.put(ob3.hotelName, ob3);
        for (Map.Entry<String, HotelReservation> entry : hm.entrySet()) {
            HotelReservation ob = entry.getValue();
            System.out.println(ob.hotelName + " " + ob.price);
        }
    }
}