package test;

import com.example.model.City;
import org.junit.Assert;
import org.junit.Test;
import java.util.Objects;

public class Citytest{
    @Test
    public void testHashCode(){
        City city = new City(1,"Alger",300000000,"Belle ville");
        int id =1;
        Assert.assertEquals(city.hashCode(), Objects.hash(id));
    }
}