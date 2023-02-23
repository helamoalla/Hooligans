/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;

/**
 *
 * @author asma
 */
public class MyConnection {
    
    //DB Credentials
     //const
    final String URL = "jdbc:mysql://localhost:3306/drift&race";
    final String USER = "root";
    final String PASSWORD = "";
    
    //var
    private Connection cnx;
    static MyConnection instance;
    private MyConnection(){
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion établie avec succée");
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(SEVERE, null, ex);     }
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

