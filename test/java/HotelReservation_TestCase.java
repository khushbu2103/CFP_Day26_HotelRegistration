import org.example.HotelReservation;
import junit.framework.Assert;
import org.junit.Test;
import java.util.HashMap;

import static org.example.HotelReservation.*;

public class HotelReservation_TestCase {
    private HashMap<String, HotelReservation> hotelMap;

    @Test
    public void UC1_getHotelNameAndPrice_Validation()
    {
        HotelReservation ob = new HotelReservation("Lakewood",110.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 90.0, 110.0);
        Assert.assertEquals("Lakewood", ob.hotelName);
        Assert.assertEquals(110.0, ob.price);
        HotelReservation ob1 = new HotelReservation("Lakewood",160.0,parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0);
        Assert.assertEquals("Lakewood", ob1.hotelName);
        Assert.assertEquals(160.0, ob1.price);
        HotelReservation ob2 = new HotelReservation("Lakewood",210.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 150.0, 220.0);
        Assert.assertEquals("Lakewood", ob2.hotelName);
        Assert.assertEquals(210.0, ob2.price);
    }
    @Test
    public void UC2_DateRangeCheapest()
    {
        HotelReservation ob1=new HotelReservation("Lakewood",110.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 90.0, 110.0);//actual
        HotelReservation ob2=new HotelReservation("Bridgewood", 160.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 50.0, 150.0);//actual
        HotelReservation ob3=new HotelReservation("Ridgewood", 210.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 150.0, 220.0);//actual
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
        HotelReservation ob1=new HotelReservation("Lakewood",110.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 90.0, 110.0);//actual
        HotelReservation ob2=new HotelReservation("Bridgewood", 160.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 50.0, 150.0);//actual
        HotelReservation ob3=new HotelReservation("Ridgewood", 210.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 150.0, 220.0);//actual
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
        HotelReservation ob1=new HotelReservation("Lakewood",110.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 90.0, 110.0);//actual
        HotelReservation ob2=new HotelReservation("Bridgewood", 160.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 50.0, 150.0);//actual
        HotelReservation ob3=new HotelReservation("Ridgewood", 210.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 150.0, 220.0);//actual
        if (ob1.calRate(startDate, endDate) < ob2.calRate(startDate, endDate) && ob1.calRate(startDate, endDate) < ob3.calRate(startDate, endDate)) {
            Assert.assertEquals(90.0, ob1.weekendRate);
            Assert.assertEquals(110.0, ob1.weekdayRate);
            System.out.println("Hotel "+ob1.hotelName +" is Cheapest at Price: "+ob1.calRate(startDate, endDate)+" Available from:- "+ob1.startDate+ " to "+ob1.endDate);
        }
        else if (ob2.calRate(startDate, endDate)<ob1.calRate(startDate, endDate)&&ob2.calRate(startDate, endDate)<ob3.calRate(startDate, endDate)) {
            Assert.assertEquals(50.0, ob2.weekendRate);
            Assert.assertEquals(150.0, ob2.weekdayRate);
            System.out.println("Hotel "+ob2.hotelName +" is Cheapest at Price: "+ob2.calRate(startDate, endDate)+" Available from:- "+ob2.startDate+ " to "+ob2.endDate);
        }
        else if (ob3.calRate(startDate, endDate)<ob1.calRate(startDate, endDate)&&ob3.calRate(startDate, endDate)<ob2.calRate(startDate, endDate)) {
            Assert.assertEquals(150.0, ob3.weekendRate);
            Assert.assertEquals(220.0, ob3.weekdayRate);
            System.out.println("Hotel "+ob3.hotelName +" is Cheapest at Price: "+ob3.calRate(startDate, endDate)+" Available from:- "+ob3.startDate+ " to "+ob3.endDate);
        }






    }

}
