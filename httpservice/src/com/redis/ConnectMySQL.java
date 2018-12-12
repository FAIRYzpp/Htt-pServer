package com.redis;

import httpservice.Request;
import httpservice.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectMySQL {
    public static List fetchdata(Connection connection) throws Exception{
            List list = new ArrayList();
            Statement statement = connection.createStatement();
            String sql = "select user_id from user ";
            ResultSet rs = null;
            rs = statement.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            try {
            String id = null;
            String user_id = null;
            //String group_id = null;
            while (rs.next()){
                id = rs.getString("id");
                user_id = rs.getString("user_id");
                //group_id = rs.getString("group_id");
                System.out.println(user_id);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (rs != null){
                rs.close();
                connection.close();
            }
        }
        return list;
    }

    //读取数据
    public static void readUser(){
        Connection connection;
        String driver = "com.mysql.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8";
        String user = "root";
        String password = "19970102";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            if(!connection.isClosed()){
                System.out.println("Succeeded connecting the database!");
            }
            fetchdata(connection);
            connection.close();

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        ConnectMySQL connectMySQL = new ConnectMySQL();
        connectMySQL.readUser();
    }

}
