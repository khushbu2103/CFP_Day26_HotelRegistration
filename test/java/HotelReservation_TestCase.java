import org.example.HotelReservation;
import junit.framework.Assert;
import org.junit.Test;
import org.example.TestUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.example.HotelReservation.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HotelReservation_TestCase {
    private HashMap<String, HotelReservation> hotelMap;

    @Test
    public void UC1_getHotelNameAndPrice_Validation()
    {
        HotelReservation ob = new HotelReservation("Lakewood",110.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 90.0, 110.0, 3,80.0, 80.0);
        Assert.assertEquals("Lakewood", ob.hotelName);
        Assert.assertEquals(110.0, ob.price);
        HotelReservation ob1 = new HotelReservation("Lakewood",160.0,parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4, 110.0, 50.0);
        Assert.assertEquals("Lakewood", ob1.hotelName);
        Assert.assertEquals(160.0, ob1.price);
        HotelReservation ob2 = new HotelReservation("Lakewood",210.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 150.0, 220.0, 5, 100.0, 40.0);
        Assert.assertEquals("Lakewood", ob2.hotelName);
        Assert.assertEquals(210.0, ob2.price);
    }
    @Test
    public void UC2_DateRangeCheapest()
    {
        HotelReservation ob1=new HotelReservation("Lakewood",110.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 90.0, 110.0, 3,80.0, 80.0);//actual
        HotelReservation ob2=new HotelReservation("Bridgewood", 160.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 50.0, 150.0, 4, 110.0, 50.0);//actual
        HotelReservation ob3=new HotelReservation("Ridgewood", 210.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 150.0, 220.0, 5, 100.0, 40.0);//actual
        if (ob1.price < ob2.price && ob1.price < ob3.price) {
            Assert.assertEquals("Lakewood", ob1.hotelName);
            Assert.assertEquals(110.0, ob1.price);
            System.out.println("Hotel "+ob1.hotelName +" is Cheapest at Price: "+ob1.price+" Available from:- "+ob1.startDate+ " to "+ob1.endDate);
        }
        else if (ob2.price<ob1.price&&ob2.price<ob3.price) {
            Assert.assertEquals("Bridgewood", ob2.hotelName);
            Assert.assertEquals(160.0, ob2.price);
            System.out.println("Hotel "+ob2.hotelName +" is Cheapest at Price: "+ob2.price+" Available from:- "+ob2.startDate+ " to "+ob2.endDate);
        }
        else if (ob3.price<ob1.price&&ob3.price<ob2.price) {
            Assert.assertEquals("Ridgewood", ob3.hotelName);
            Assert.assertEquals(210.0, ob3.price);
            System.out.println("Hotel "+ob3.hotelName +" is Cheapest at Price: "+ob3.price+" Available from:- "+ob3.startDate+ " to "+ob3.endDate);
        }
    }
    @Test
    public void UC3_WeekdayAndWeekendRate_Validation()
    {
        HotelReservation ob1=new HotelReservation("Lakewood",110.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 90.0, 110.0,3,80.0, 80.0);//actual
        HotelReservation ob2=new HotelReservation("Bridgewood", 160.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 50.0, 150.0, 4, 110.0, 50.0);//actual
        HotelReservation ob3=new HotelReservation("Ridgewood", 210.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 150.0, 220.0, 5, 100.0, 40.0);//actual
        Assert.assertEquals(90.0, ob1.weekendRate);
        Assert.assertEquals(110.0, ob1.weekdayRate);
        Assert.assertEquals(50.0, ob2.weekendRate);
        Assert.assertEquals(150.0, ob2.weekdayRate);
        Assert.assertEquals(150.0, ob3.weekendRate);
        Assert.assertEquals(220.0, ob3.weekdayRate);
    }
    @Test
    public void UC4_CheapestHotelAtWeekdayAndWeekend_Validation()
    {
        HotelReservation ob1=new HotelReservation("Lakewood",110.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 90.0, 110.0, 3,80.0, 80.0);//actual
        HotelReservation ob2=new HotelReservation("Bridgewood", 160.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 50.0, 150.0, 4, 110.0, 50.0);//actual
        HotelReservation ob3=new HotelReservation("Ridgewood", 210.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 150.0, 220.0, 5, 100.0, 40.0);//actual
        if (ob1.calRate(startDate, endDate, true) < ob2.calRate(startDate, endDate, true) && ob1.calRate(startDate, endDate, true) < ob3.calRate(startDate, endDate, true)) {
            Assert.assertEquals(90.0, ob1.weekendRate);
            Assert.assertEquals(110.0, ob1.weekdayRate);
            System.out.println("Hotel "+ob1.hotelName +" is Cheapest at Price: "+ob1.calRate(startDate, endDate, true)+" Available from:- "+ob1.startDate+ " to "+ob1.endDate);
        }
        else if (ob2.calRate(startDate, endDate, true)<ob1.calRate(startDate, endDate, true)&&ob2.calRate(startDate, endDate, true)<ob3.calRate(startDate, endDate, true)) {
            Assert.assertEquals(50.0, ob2.weekendRate);
            Assert.assertEquals(150.0, ob2.weekdayRate);
            System.out.println("Hotel "+ob2.hotelName +" is Cheapest at Price: "+ob2.calRate(startDate, endDate, true)+" Available from:- "+ob2.startDate+ " to "+ob2.endDate);
        }
        else if (ob3.calRate(startDate, endDate, true)<ob1.calRate(startDate, endDate, true)&&ob3.calRate(startDate, endDate, true)<ob2.calRate(startDate, endDate, true)) {
            Assert.assertEquals(150.0, ob3.weekendRate);
            Assert.assertEquals(220.0, ob3.weekdayRate);
            System.out.println("Hotel "+ob3.hotelName +" is Cheapest at Price: "+ob3.calRate(startDate, endDate, true)+" Available from:- "+ob3.startDate+ " to "+ob3.endDate);
        }
    }
    @Test
    public void UC5_getRating_Validation()
    {
        HotelReservation ob1=new HotelReservation("Lakewood",110.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 90.0, 110.0,3,80.0, 80.0);//actual
        HotelReservation ob2=new HotelReservation("Bridgewood", 160.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 50.0, 150.0, 4, 110.0, 50.0);//actual
        HotelReservation ob3=new HotelReservation("Ridgewood", 210.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 150.0, 220.0, 5, 100.0, 40.0);//actual
        Assert.assertEquals(3, ob1.rating);
        Assert.assertEquals(4, ob2.rating);
        Assert.assertEquals(5, ob3.rating);
    }
    @Test
    public void UC6_getCheapestHotelWithBestRating_Validation()
    {
        HotelReservation ob1=new HotelReservation("Lakewood",110.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 90.0, 110.0,3,80.0, 80.0);//actual
        Assert.assertEquals("Lakewood", ob1.hotelName);
        Assert.assertEquals(110.0, ob1.price, 0.01);
        Assert.assertEquals(3, ob1.rating);
    }
    @Test
    public void UC7_Best_Rated_Hotel() {
        HotelReservation ob1 = new HotelReservation("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3,80.0, 80.0);
        HotelReservation ob2 = new HotelReservation("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4, 110.0, 50.0);
        HotelReservation ob3 = new HotelReservation("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5, 100.0, 40.0);

        // Create a HashMap with the hotels
        HashMap<String, HotelReservation> hm = new HashMap<>();
        hm.put(ob1.hotelName, ob1);
        hm.put(ob2.hotelName, ob2);
        hm.put(ob3.hotelName, ob3);

        // Specify the date range
        Date rangeStartDate = parseDate("11/sep/2020");
        Date rangeEndDate = parseDate("12/sep/2020");

        // Call the method to find the best-rated hotel
        HotelReservation bestRated = HotelReservation.bestRatedHotel(hm, rangeStartDate, rangeEndDate, true);

        // Assert the result
        if (bestRated != null) {
            Assert.assertEquals("Ridgewood", bestRated.hotelName);
            Assert.assertEquals(5, bestRated.rating);
            Assert.assertEquals(370.0, bestRated.calRate(rangeStartDate, rangeEndDate, true), 0.01);
        } else {
            Assert.fail("No best-rated hotel found.");
        }
    }
    @Test
    public void UC9_1SpecialRatesRewardCustomer() {
        HotelReservation ob1 = new HotelReservation("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        double calculatedRate = ob1.calRate(parseDate("10/sep/2020"), parseDate("11/sep/2020"), true);
        Assert.assertEquals(160.0, calculatedRate, 0.01);

        HotelReservation ob2 = new HotelReservation("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4, 50.0, 100.0);
        calculatedRate = ob2.calRate(parseDate("10/sep/2020"), parseDate("11/sep/2020"), true);
        Assert.assertEquals(160.0, calculatedRate, 0.01);

        HotelReservation ob3 = new HotelReservation("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5, 40.0, 100.0);
        calculatedRate = ob3.calRate(parseDate("10/sep/2020"), parseDate("11/sep/2020"), true);
        Assert.assertEquals(140.0, calculatedRate, 0.01);
    }
    @Test
    public void UC10_CheapestBestRatedHotelForRewardCustomer() {
        HotelReservation ob1 = new HotelReservation("Lakewood", 110.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        HotelReservation ob2 = new HotelReservation("Bridgewood", 160.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 50.0, 150.0, 4, 50.0, 100.0);
        HotelReservation ob3 = new HotelReservation("Ridgewood", 210.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 150.0, 220.0, 5, 40.0, 100.0);
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

        Date rangeStartDate = parseDate("11/Sep/2020");
        Date rangeEndDate = parseDate("12/Sep/2020");

        try {
            HotelReservation cheap = HotelReservation.cheapHotel(hm, rangeStartDate, rangeEndDate, true);
            HotelReservation bestRated = HotelReservation.bestRatedHotel(hm, rangeStartDate, rangeEndDate, true);

            assertEquals("Ridgewood", cheap.hotelName);
            assertEquals(5, cheap.rating);
            assertEquals(140.0, cheap.calRate(rangeStartDate, rangeEndDate, true), 0.01);

            assertEquals("Ridgewood", bestRated.hotelName);
            assertEquals(5, bestRated.rating);
            assertEquals(140.0, bestRated.calRate(rangeStartDate, rangeEndDate, true), 0.01);
        } catch (Exception e) {
            // Handle exceptions if needed
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void UC10_InvalidDateRange() {
        HotelReservation ob1 = new HotelReservation("Lakewood", 110.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        HashMap<String, HotelReservation> hm = new HashMap<>();
        hm.put(ob1.hotelName, ob1);

        Date invalidStartDate = parseDate("12/Sep/2020");
        Date invalidEndDate = parseDate("11/Sep/2020");

        HotelReservation.cheapHotel(hm, invalidStartDate, invalidEndDate, true);
    }
    @Test
    public void UC11_CheapestBestRatedHotelForRegularCustomer() {
        HotelReservation ob1 = new HotelReservation("Lakewood", 110.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        HotelReservation ob2 = new HotelReservation("Bridgewood", 160.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 50.0, 150.0, 4, 50.0, 100.0);
        HotelReservation ob3 = new HotelReservation("Ridgewood", 210.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 150.0, 220.0, 5, 40.0, 100.0);
        HashMap<String, HotelReservation> hm = new HashMap<>();
        hm.put(ob1.hotelName, ob1);
        hm.put(ob2.hotelName, ob2);
        hm.put(ob3.hotelName, ob3);

        Date rangeStartDate = parseDate("11/Sep/2020");
        Date rangeEndDate = parseDate("12/Sep/2020");

        try {
            HotelReservation cheap = HotelReservation.cheapHotel(hm, rangeStartDate, rangeEndDate, false);
            HotelReservation bestRated = HotelReservation.bestRatedHotel(hm, rangeStartDate, rangeEndDate, false);

            assertEquals("Bridgewood", cheap.hotelName);
            assertEquals(4, cheap.rating);
            assertEquals(200.0, cheap.calRate(rangeStartDate, rangeEndDate, false), 0.01);

            assertEquals("Ridgewood", bestRated.hotelName);
            assertEquals(5, bestRated.rating);
            assertEquals(370.0, bestRated.calRate(rangeStartDate, rangeEndDate, false), 0.01);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test(expected = IllegalArgumentException.class)
    public void UC11_InvalidDateRange() {
        HotelReservation ob1 = new HotelReservation("Lakewood", 110.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        HashMap<String, HotelReservation> hm = new HashMap<>();
        hm.put(ob1.hotelName, ob1);
        Date invalidStartDate = parseDate("12/Sep/2020");
        Date invalidEndDate = parseDate("11/Sep/2020");
        HotelReservation.cheapHotel(hm, invalidStartDate, invalidEndDate, false);
    }
    @Test
    public void UC12_testCheapHotelForRegularCustomer() {
        HotelReservation ob1 = new HotelReservation("Lakewood", 110.0, TestUtils.parseDate("10/Sep/2020"), TestUtils.parseDate("11/Sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        HotelReservation ob2 = new HotelReservation("Bridgewood", 160.0, TestUtils.parseDate("10/Sep/2020"), TestUtils.parseDate("11/Sep/2020"), 50.0, 150.0, 4, 50.0, 100.0);
        HotelReservation ob3 = new HotelReservation("Ridgewood", 210.0, TestUtils.parseDate("10/Sep/2020"), TestUtils.parseDate("11/Sep/2020"), 150.0, 220.0, 5, 40.0, 100.0);

        HashMap<String, HotelReservation> hotels = new HashMap<>();
        hotels.put(ob1.hotelName, ob1);
        hotels.put(ob2.hotelName, ob2);
        hotels.put(ob3.hotelName, ob3);

        ob1.rewardWeekdayRate = 80.0;
        ob1.rewardWeekendRate = 80.0;
        ob2.rewardWeekdayRate = 110.0;
        ob2.rewardWeekendRate = 50.0;
        ob3.rewardWeekdayRate = 100.0;
        ob3.rewardWeekendRate = 40.0;

        Date rangeStartDate = TestUtils.parseDate("11/Sep/2020");
        Date rangeEndDate = TestUtils.parseDate("12/Sep/2020");

        HotelReservation cheapestHotel = HotelReservation.cheapHotel(hotels, rangeStartDate, rangeEndDate, false);

    }

    @Test
    public void UC12_testBestRatedHotelForRegularCustomer() {
        HotelReservation ob1 = new HotelReservation("Lakewood", 110.0, TestUtils.parseDate("10/Sep/2020"), TestUtils.parseDate("11/Sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        HotelReservation ob2 = new HotelReservation("Bridgewood", 160.0, TestUtils.parseDate("10/Sep/2020"), TestUtils.parseDate("11/Sep/2020"), 50.0, 150.0, 4, 50.0, 100.0);
        HotelReservation ob3 = new HotelReservation("Ridgewood", 210.0, TestUtils.parseDate("10/Sep/2020"), TestUtils.parseDate("11/Sep/2020"), 150.0, 220.0, 5, 40.0, 100.0);

        HashMap<String, HotelReservation> hotels = new HashMap<>();
        hotels.put(ob1.hotelName, ob1);
        hotels.put(ob2.hotelName, ob2);
        hotels.put(ob3.hotelName, ob3);

        ob1.rewardWeekdayRate = 80.0;
        ob1.rewardWeekendRate = 80.0;
        ob2.rewardWeekdayRate = 110.0;
        ob2.rewardWeekendRate = 50.0;
        ob3.rewardWeekdayRate = 100.0;
        ob3.rewardWeekendRate = 40.0;

        Date rangeStartDate = TestUtils.parseDate("11/Sep/2020");
        Date rangeEndDate = TestUtils.parseDate("12/Sep/2020");

        HotelReservation bestRatedHotel = HotelReservation.bestRatedHotel(hotels, rangeStartDate, rangeEndDate, false);

        assertNotNull(bestRatedHotel);
        assertEquals("Ridgewood", bestRatedHotel.hotelName);
    }

}
