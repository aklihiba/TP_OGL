package test;

import com.example.model.City;
import com.example.service.DatabaseConnection;
import com.example.service.DatabaseService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DatabaseServiceExceptionTest {
    Connection conn;
    DatabaseConnection db;
    static final String user = "sa";
    static final String user2 = "";
    static final String JDBC_DRIVER = "org.h1.Driver";
    static final String DB_URL = "jdbc:h1:mem:test";

    @Before
    public  void setUp() throws Exception {
        db = new DatabaseConnection(user,user2,JDBC_DRIVER,DB_URL);
        conn = db.connect();
        db.createDb(conn);
    }
    @Test
    public void testAddCity() throws SQLException {
        City city = new City(1,"Alger",300000000,"Belle ville");
        int i = DatabaseService.addCity(conn,city);
        Assert.assertEquals(i,-1);
    }

    @Test(expected = Exception.class)
    public  void tearDown() throws Exception {
        db.disconnect(conn);
    }
    @Test(expected = Exception.class)
    public void getCityTestID()throws Exception{
            City city = DatabaseService.getCityByID(conn,1);

    }

    @Test(expected = Exception.class)
    public void getCitiesTest()throws Exception{
        List<City> city = DatabaseService.getCities(conn);
    }
}