/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azizh
 */
public class MyConnection {
    //DB PARAM
    static final String URL ="jdbc:mysql://localhost:3306/Holigans";
    static final String USER ="root";
    static final String PASSWORD ="";
    //var
    private Connection cnx;
    
    static MyConnection instance;
    
    private MyConnection(){
        try {
            cnx=DriverManager.getConnection(URL, USER,PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getCnx() {
        return cnx;
    }
    
     public static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();
        
        return instance;
    }
}