package com.example.hotelreservation.Core;

import com.example.hotelreservation.Entities.IEntity;
import com.example.hotelreservation.Entities.Room;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
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
        preparedStatement=connection.prepareStatement("insert into "+tableName+" (" +
                I.getColumns()+") values("+ I.getValues()+")");
        int result=  preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }
    public int delete(Connection connection,String tableName,int id) throws SQLException {
        preparedStatement=connection.prepareStatement("delete from "+
                tableName+" where id="+id);
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
                "inner join \"HotelApp\".hotel h on r.\"hotelID\" = h.\"ID\""+script+" ;");

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
