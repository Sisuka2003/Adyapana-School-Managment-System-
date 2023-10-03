/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class dbConnection {
    private static Connection conn;
    
    
    public static Connection getConnection(){
        String url="jdbc:mysql://localhost:3307/adyapana?useSSL=false";
        String us="root";
        String pw="20030909";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection(url,us, pw);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
    return conn;
    }
    
    public static void main(String[] args) {
        try{
        System.out.println("Database name :"+ getConnection().getCatalog());
        }catch(Exception e){
        e.printStackTrace();
            System.out.println("error on print statement");
        }
    }
}