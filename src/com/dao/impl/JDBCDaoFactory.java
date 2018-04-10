package com.dao.impl;
import com.dao.*;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCDaoFactory{

    private static Connection connection;
    public static void instantiateConnection(String login, String password) throws Exception{
        if(connection == null) {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useSSL=false", login, password);
        }
    }

    public static Connection getConnection(){
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }
    public static void useAnnotations(Class[] transactionUsers){
        Proxy.newProxyInstance(JDBCDaoFactory.class.getClassLoader(), transactionUsers, new TransactionProxy());
    }
}