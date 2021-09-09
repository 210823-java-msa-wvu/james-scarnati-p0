package dev.scarnati.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    private static ConnectionUtil cu = null;
    private static Properties props;

    //private constructor so we can control the creation of any instances
    //(Creational Design Pattern - Singleton)
    private ConnectionUtil(){

        props = new Properties();

        InputStream dbprops = ConnectionUtil.class.getClassLoader().getResourceAsStream("connection.properties");
        try {
            props.load(dbprops);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static synchronized ConnectionUtil ConnectionUtil(){

        if(cu == null){

            cu = new ConnectionUtil();
        }

        return cu;

    }

    public static ConnectionUtil getConnectionUtil() {

        if(cu == null){

            cu = new ConnectionUtil();
        }

        return cu;
    }

    public Connection getConnection(){

        Connection Conn = null;

        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        try {
            Conn = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Conn;
    }

//    public static void main(String[] args){
//
//        Connection connection = ConnectionUtil().getConnection();
//
//        if(connection != null){
//            System.out.println("Connection Successful!");
//        }
//        else{
//            System.out.println("Something Went Wrong! =(");
//        }
//
//    }



}
