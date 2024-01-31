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
    public Double rewardWeekendRate;
    public Double rewardWeekdayRate;
    public int rating;
    public HotelReservation(String hotelName, Double price, Date startDate, Date endDate, Double weekendRate, Double weekdayRate, int rating,  Double rewardWeekendRate, Double rewardWeekdayRate) {
        this.hotelName = hotelName;
        this.price = price;
        this.startDate=startDate;
        this.endDate=endDate;
        this.weekendRate = weekendRate;
        this.weekdayRate = weekdayRate;
        this.rating = rating;
        this.rewardWeekendRate = rewardWeekendRate;
        this.rewardWeekdayRate = rewardWeekdayRate;
    }
    public static HotelReservation createHotel(String hotelName, Double price,Date startDate, Date endDate, Double weekendRate, Double weekdayRate, int rating,  Double rewardWeekendRate,
                                               Double rewardWeekdayRate) {
        return new HotelReservation(hotelName, price, startDate, endDate, weekendRate, weekdayRate, rating, rewardWeekendRate, rewardWeekdayRate);
    }
    public static HotelReservation cheapHotel(HashMap<String, HotelReservation>hm, Date startDate, Date endDate)
    {
        HotelReservation cheap=null;
        for(HotelReservation hotel:hm.values())
        {
            double totalRate = hotel.calRate(startDate, endDate, true);
            if(cheap == null || totalRate < cheap.calRate(startDate, endDate, true))
                cheap=hotel;
        }
        return cheap;
    }
    public static HotelReservation bestRatedHotel(HashMap<String, HotelReservation> hm, Date startDate, Date endDate) {
        HotelReservation bestRated = null;
        int highestRating = Integer.MIN_VALUE;

        for (HotelReservation hotel : hm.values()) {
            if (isDateRangeOverlap(startDate, endDate, hotel.startDate, hotel.endDate)) {
                if (hotel.rating > highestRating || (hotel.rating == highestRating && hotel.calRate(startDate, endDate) < bestRated.calRate(startDate, endDate))) {
                    bestRated = hotel;
                    highestRating = hotel.rating;
                }
            }
        }

        return bestRated;
    }
    public static void main(String[] args) {
        HotelReservation ob1 = createHotel("Lakewood", 110.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 80.0, 110.0, 3);
        HotelReservation ob2 = createHotel("Bridgewood", 160.0,parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4);
        HotelReservation ob3 = createHotel("Ridgewood", 210.0,parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5);
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
            System.out.println("Cheapest Hotel: " + cheap.hotelName+ " rating: " + cheap.rating +" " + cheap.calRate(rangeStartDate, rangeEndDate));
        } else {
            System.out.println("No Hotels Found...!!!");
        }
        HotelReservation bestRated = bestRatedHotel(hm, rangeStartDate, rangeEndDate);

        if (bestRated != null) {
            System.out.println("Best Rated Hotel: " + bestRated.hotelName + " rating: " + bestRated.rating + " " + bestRated.calRate(rangeStartDate, rangeEndDate));
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
    public double calRate(Date startDate, Date endDate, boolean isRewardCustomer) {
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
        if (isRewardCustomer) {
            return weekdays * rewardWeekdayRate + weekends * rewardWeekendRate;
        } else {
            return weekdays * weekdayRate + weekends * weekendRate;
        }
    }
}