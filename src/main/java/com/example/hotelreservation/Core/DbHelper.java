package com.example.hotelreservation.Core;

import com.example.hotelreservation.Entities.IEntity;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbHelper {
    private PreparedStatement preparedStatement;
    private String dbUserName;
    private String dbPassword;
    private String dbConnectionUrl;

    public DbHelper() {
        try {
            FileInputStream  file = new FileInputStream("src/main/resources/com/example/hotelreservation/database.config");
            Properties prop = new Properties();
            prop.load(file);

            this.dbUserName=prop.getProperty("db-username");
            this.dbPassword=prop.getProperty("db-password");
            this.dbConnectionUrl=prop.getProperty("db-url");
            this.preparedStatement=null;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(dbConnectionUrl, dbUserName, dbPassword);
    }
    public int insert(Connection connection, String tableName, IEntity I) throws SQLException {
        preparedStatement=connection.prepareStatement("insert into \"HotelApp\"."+tableName+" (" +
                I.getColumns()+") values("+ I.getValues()+")");
        int result=  preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }
    public int update(Connection connection, String tableName,String script,int ID) throws SQLException {
        preparedStatement=connection.prepareStatement("UPDATE \"HotelApp\"."+tableName+" SET " +
                script+" WHERE \"ID\"="+ID+";");
        int result=  preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }
    public int delete(Connection connection,String tableName,int reservationId) throws SQLException {
        preparedStatement=connection.prepareStatement("DELETE FROM \"HotelApp\".reservation\n" +
                "WHERE \"ID\"="+reservationId+";");
        int result= preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }
    public ResultSet getListOfRooms(Connection connection,String script) throws SQLException {
        ResultSet resultSet;
        Statement statement=connection.createStatement();
        resultSet = statement.executeQuery("select r.\"ID\" , h.\"name\" , r.\"type\" ,\n" +
                "h.address, h.\"rank\" , r.price , r.currency\n" +
                "FROM \"HotelApp\".room as r\n" +
                "inner join \"HotelApp\".hotel h on r.\"hotelID\" = h.\"ID\""+script+
                "WHERE r.\"isFull\"=false ;");

//        ArrayList<Room> roomsArrayList = new ArrayList<Room>();
//        while (resultSet.next())
//        {
//            roomsArrayList.add(new Room(
//                    resultSet.getInt(1),
//                    resultSet.getInt(2),
//                    resultSet.getString(3),
//                    resultSet.getBoolean(4),
//                    resultSet.getString(5),
//                    resultSet.getString(6),
//                    resultSet.getString(7),
//                    resultSet.getInt(8),
//                    resultSet.getString(9),
//                    resultSet.getDouble(10)
//            ));
//        }
        return resultSet;
    }
    public ResultSet getListOfOldReservations(Connection connection,int ID) throws SQLException {
        ResultSet resultSet;
        Statement statement=connection.createStatement();
        resultSet = statement.executeQuery("SELECT res.\"roomID\",res.\"ID\",h.\"name\",room.\"type\" ,h.address ,\n" +
                "res.\"checkInDate\", res.\"checkOutDate\",\n" +
                "room.price ,room.currency \n" +
                "FROM \"HotelApp\".reservation as res\n" +
                "inner join \"HotelApp\".room room on room.\"ID\" =res.\"roomID\"\n" +
                "inner join \"HotelApp\".hotel h on room.\"hotelID\" = h.\"ID\" \n" +
                "where res.\"customerID\" ="+ID+";");

        return resultSet;
    }
    public ResultSet getEntity(Connection connection,String tableName, IEntity I) throws SQLException {
        ResultSet resultSet;
        Statement statement=connection.createStatement();
        resultSet = statement.executeQuery("SELECT "+
                I.getColumns()+" FROM \"HotelApp\"."+tableName+";");
        return resultSet;
    }
    public ResultSet getEntity(Connection connection,String tableName, IEntity I, int ID) throws SQLException {
        ResultSet resultSet;
        Statement statement=connection.createStatement();
        resultSet = statement.executeQuery("SELECT "+
                I.getColumns()+" FROM \"HotelApp\"."+tableName+
                " WHERE \"ID\"="+ID+";");
        return resultSet;
    }
    public ResultSet getEntity(Connection connection,String tableName, IEntity I,String column, String text) throws SQLException {
        ResultSet resultSet;
        Statement statement=connection.createStatement();
        resultSet = statement.executeQuery("SELECT "+
                I.getColumns()+" FROM \"HotelApp\"."+tableName+
                " WHERE \""+column+"\"= '%"+text+"%' ;");
        return resultSet;
    }
    public ResultSet getEntity(Connection connection,String tableName, IEntity I, Double minPrice,Double maxPrice) throws SQLException {
        ResultSet resultSet;
        Statement statement=connection.createStatement();
        resultSet = statement.executeQuery("SELECT "+
                I.getColumns()+" FROM \"HotelApp\"."+tableName+
                " WHERE \"price\">= "+minPrice+" and \"price\"< "+maxPrice+";");
        return resultSet;
    }
    public ResultSet getEntity(Connection connection,String tableName, IEntity I, String script) throws SQLException {
        ResultSet resultSet;
        Statement statement=connection.createStatement();
        resultSet = statement.executeQuery("SELECT "+
                I.getColumns()+" FROM \"HotelApp\"."+tableName+" "+script+";");
        return resultSet;
    }
}
