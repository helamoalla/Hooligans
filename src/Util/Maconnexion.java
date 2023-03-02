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
 * @author Nadia
 */
public class Maconnexion {
    
    private static Maconnexion instance ;
    private Connection cnx ;
             
    
    
  final String URL="jdbc:mysql://localhost:3306/projet" ;
 final String USER="root" ;
 final String PASSWORD="";
 

 
 private Maconnexion(){
      try {

          cnx=DriverManager.getConnection(URL, USER , PASSWORD);
          System.out.println("Connection etablie avec succes !");
                  
                  } catch (SQLException ex) {
          Logger.getLogger(Maconnexion.class.getName()).log(Level.SEVERE, null, ex);
      }
 }
 
  public Connection getCnx() {
        return cnx;
    }

    //3
    public static Maconnexion getInstance() {
        if(instance == null)
            instance = new Maconnexion();
        
        return instance;
    }
 
}