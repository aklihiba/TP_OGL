package com.example.service;

import com.example.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseService {
    public static final Logger LOGGER = Logger.getLogger( DatabaseService.class.getName() );

    public static final String IDCITY = "idCity";
    public static final String NAME = "name";
    public static final String TOURISTNUMBER = "touristNumber";
    public static final String DESCRIPTION = "description";

    private DatabaseService(){
    }
    public static int addCity(Connection conn, City city){
        String sql = "INSERT INTO City " + "VALUES (?,?,?,?)";
        int i = -1;
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, city.getIdCity());
            pstmt.setString(2, city.getName());
            pstmt.setInt(3, city.getTouristNumber());
            pstmt.setString(4, city.getDescription());
            i= pstmt.executeUpdate();
        } catch (SQLException se) {
            LOGGER.log(Level.FINE, "SQL exception in addCity");
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "exception in addCity");
        }
        return i;
    }

    //the throwable exception is from rs.close() in the finally block
    //and the exception throwed by conn.prepareStatement or executeQuery is catched and saved in the logger

    private static City getCity(Connection conn,String idorname,String type, String sql, ResultSet rs)throws SQLException{
        City city= new City();
        try (PreparedStatement pstmt=conn.prepareStatement(sql)){
            if (type=="id")pstmt.setInt(1, Integer.parseInt(idorname));
            if (type=="name") pstmt.setString(1,idorname);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                city.setIdCity(rs.getInt(IDCITY));
                city.setName(rs.getString(NAME));
                city.setTouristNumber(rs.getInt(TOURISTNUMBER));
                city.setDescription(rs.getString(DESCRIPTION));
            }
            rs.close();
        } catch (SQLException se) {
            LOGGER.log(Level.FINE, "SQL exception in getcity");
        } finally {
            if( rs != null) {
                rs.close();
            }
        }
        return city;
    }

   public static City getCityByID(Connection conn,int idCity)  throws SQLException{
        ResultSet rs = null;
       String sql = "SELECT * FROM City where idCity=?";
        return  getCity(conn,String.valueOf(idCity),"id",sql,rs);
    }

    //throws the exception if it couldn't close the rs in the finally block
    public static List<City> getCities(Connection conn) throws SQLException {
        List<City> cities = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM City";
        try(Statement stmt = conn.createStatement()) {
            rs= stmt.executeQuery(sql);
            while (rs.next()) {
                City city = new City();
                city.setIdCity(rs.getInt(IDCITY));
                city.setName(rs.getString(NAME));
                city.setTouristNumber(rs.getInt(TOURISTNUMBER));
                city.setDescription(rs.getString(DESCRIPTION));
                cities.add(city);
            }
            rs.close();

        } catch (SQLException se) {
            LOGGER.log(Level.FINE, se.getMessage());
        } finally {
           if (rs != null){
               rs.close();
           }
        }
        return  cities;
    }
// New method
    public static City getCityByName(Connection conn,String name)  throws SQLException{

        ResultSet rs = null;
        String sql = "SELECT * FROM City where name=?";

        return getCity(conn,name,"name",sql,rs);
    }


}