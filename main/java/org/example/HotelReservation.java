package org.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.*;
import java.util.regex.Pattern;

public class HotelReservation {
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}/[a-zA-Z]{3}/\\d{4}");
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
    public static HotelReservation cheapHotel(HashMap<String, HotelReservation> hm, Date startDate, Date endDate, boolean isRewardCustomer) {
        validateDateRange(startDate, endDate);

        return hm.values()
                .stream()
                .min(Comparator.comparingDouble(hotel -> hotel.calRate(startDate, endDate, isRewardCustomer)))
                .orElse(null);
    }
    public static HotelReservation bestRatedHotel(HashMap<String, HotelReservation> hm, Date startDate, Date endDate, boolean isRewardCustomer) {
        validateDateRange(startDate, endDate);

        return hm.values()
                .stream()
                .filter(hotel -> isDateRangeOverlap(startDate, endDate, hotel.startDate, hotel.endDate))
                .max(Comparator.comparingInt(HotelReservation::getRating)
                        .thenComparingDouble(hotel -> hotel.calRate(startDate, endDate, isRewardCustomer)))
                .orElse(null);
    }
    public static HotelReservation cheapBestRatedHotelForRegularCustomer(Map<String, HotelReservation> hotels, Date startDate, Date endDate) {
        return hotels.values().stream()
                .filter(hotel -> hotel.isWithinDateRange(startDate, endDate))
                .min(Comparator.comparingInt(HotelReservation::getRating)
                        .thenComparingDouble(hotel -> hotel.calRate(startDate, endDate, false)))
                .orElse(null);
    }

    public boolean isWithinDateRange(Date startDate, Date endDate) {
        return (startDate.equals(this.startDate) || startDate.after(this.startDate)) &&
                (endDate.equals(this.endDate) || endDate.before(this.endDate));
    }

    public int getRating() {
        return rating;
    }
    public static void main(String[] args) {
        HotelReservation ob1 = new HotelReservation("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        HotelReservation ob2 = new HotelReservation("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4, 50.0, 100.0);
        HotelReservation ob3 = new HotelReservation("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5, 40.0, 100.0);
        HashMap<String, HotelReservation> hm = new HashMap<>();
        hm.put(ob1.hotelName, ob1);
        hm.put(ob2.hotelName, ob2);
        hm.put(ob3.hotelName, ob3);
        ob1.rewardWeekdayRate = 80.0;
        ob1.rewardWeekendRate = 80.0;
        ob2.rewardWeekdayRate = 110.0;
        ob2.rewardWeekendRate = 50.0;
        ob3.rewardWeekdayRate = 100.0;
        ob3.rewardWeekendRate = 40.0;
//        for (Map.Entry<String, HotelParameter> entry : hm.entrySet()) {
//            HotelParameter ob = entry.getValue();
//            System.out.println(ob.hotelName + " " + ob.price + " Start Date: " + ob.startDate + " End Date: " + ob.endDate);
//        }

        try {
            Date rangeStartDate = parseDate("11/Sep/2020");
            Date rangeEndDate = parseDate("12/Sep/2020");
            validateDateRange(rangeStartDate, rangeEndDate);
            HotelReservation cheap = cheapHotel(hm, rangeStartDate, rangeEndDate, true);
            System.out.println(cheap != null
                    ? "Cheapest Hotel: " + cheap.hotelName + " rating: " + cheap.rating + " " + cheap.calRate(rangeStartDate, rangeEndDate, true)
                    : "No Hotels Found...!!!");

            HotelReservation bestRated = bestRatedHotel(hm, rangeStartDate, rangeEndDate, true);
            System.out.println(bestRated != null
                    ? "Best Rated Hotel: " + bestRated.hotelName + " rating: " + bestRated.rating + " " + bestRated.calRate(rangeStartDate, rangeEndDate, true)
                    : "No Hotels Found...!!!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void validateDateRange(Date startDate, Date endDate) {
        if (startDate == null || endDate == null || startDate.after(endDate)) {
            throw new IllegalArgumentException("Invalid date range. Please provide valid start and end dates.");
        }
    }

    public static void showHotelByDateRange(HashMap<String, HotelReservation> hm, Date startDate, Date endDate) {
        for (HotelReservation hotel : hm.values()) {
            if (isDateRangeOverlap(startDate, endDate, hotel.startDate, hotel.endDate)) {
                System.out.println("Range: " + startDate + " - " + endDate + " " + hotel.hotelName + " " + hotel.calRate(startDate, endDate, true));
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