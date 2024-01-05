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
    public static Date startDate;
    public static Date endDate;
    public Double weekendRate;
    public Double weekdayRate;
    public HotelReservation(String hotelName, Double price, Date startDate, Date endDate, Double weekendRate, Double weekdayRate) {
        this.hotelName = hotelName;
        this.price = price;
        this.startDate=startDate;
        this.endDate=endDate;
        this.weekendRate = weekendRate;
        this.weekdayRate = weekdayRate;
    }
    public static HotelReservation createHotel(String hotelName, Double price,Date startDate, Date endDate, Double weekendRate, Double weekdayRate) {
        return new HotelReservation(hotelName, price, startDate, endDate, weekendRate, weekdayRate);
    }
    public static HotelReservation cheapHotel(HashMap<String, HotelReservation>hm, Date startDate, Date endDate)
    {
        HotelReservation cheap=null;
        for(HotelReservation hotel:hm.values())
        {
            double totalRate = hotel.calRate(startDate, endDate);
            if(cheap == null || totalRate < cheap.calRate(startDate, endDate))
                cheap=hotel;
        }
        return cheap;
    }
    public static void main(String[] args) {
        HotelReservation ob1 = createHotel("Lakewood", 110.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 90.0, 110.0);
        HotelReservation ob2 = createHotel("Bridgewood", 160.0,parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0);
        HotelReservation ob3 = createHotel("Ridgewood", 210.0,parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0);
        HashMap<String, HotelReservation> hm = new HashMap<>();
        hm.put(ob1.hotelName, ob1);
        hm.put(ob2.hotelName, ob2);
        hm.put(ob3.hotelName, ob3);
//        for (Map.Entry<String, HotelParameter> entry : hm.entrySet()) {
//            HotelParameter ob = entry.getValue();
//            System.out.println(ob.hotelName + " " + ob.price + " Start Date: " + ob.startDate + " End Date: " + ob.endDate);
//        }

        Date rangeStartDate = parseDate("11/sep/2020");
        Date rangeEndDate = parseDate("12/sep/2020");
        showHotelByDateRange(hm, rangeStartDate, rangeEndDate);
        HotelReservation cheap=cheapHotel(hm, rangeStartDate, rangeEndDate);
        if (cheap != null) {
            System.out.println("Cheapest Hotel: " + cheap.hotelName +" " + cheap.calRate(rangeStartDate, rangeEndDate));
        } else {
            System.out.println("No Hotels Found...!!!");
        }
    }

    public static void showHotelByDateRange(HashMap<String, HotelReservation> hm, Date startDate, Date endDate) {
        for (HotelReservation hotel : hm.values()) {
            if (isDateRangeOverlap(startDate, endDate, hotel.startDate, hotel.endDate)) {
                System.out.println("Range: " + startDate + " - " + endDate + " " + hotel.hotelName + " " + hotel.calRate(startDate, endDate));
            }
        }
    }

    public static boolean isDateRangeOverlap(Date targetStartDate, Date targetEndDate, Date startDate, Date endDate) {
        return (targetStartDate.compareTo(endDate) <= 0 && targetEndDate.compareTo(startDate) >= 0);
    }
    public static Date parseDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public double calRate(Date startDate, Date endDate) {
        int weekdays = 0;
        int weekends = 0;
        Date currentDate = startDate;
        while (!currentDate.after(endDate)) {
            int dayOfWeek = currentDate.getDay();
            if (dayOfWeek >= 1 && dayOfWeek <= 5)
                weekdays++;
            else
                weekends++;
            currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000);
        }
        return (weekdays * weekdayRate + weekends * weekendRate);
    }
}