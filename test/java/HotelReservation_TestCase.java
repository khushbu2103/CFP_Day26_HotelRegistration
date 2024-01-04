import org.example.HotelReservation;
import junit.framework.Assert;
import org.junit.Test;

public class HotelReservation_TestCase {
    @Test
    public void getHotelReservation_Validation()
    {
        HotelReservation ob = new HotelReservation("Lakewood",110.0);//actual
        Assert.assertEquals("Lakewood", ob.hotelName);
        Assert.assertEquals(110.0, ob.price);


    }
}
