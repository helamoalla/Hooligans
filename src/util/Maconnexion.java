/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author helam
 */
public class Maconnexion {


    //db credentials
    static final String URL ="jdbc:mysql://localhost:3306/pidev_maintenance";
    static final String USER ="root";
    static final String PASSWORD ="";
    
    //var
    private Connection cnx;
    static Maconnexion instance;


    public Maconnexion(){
     
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
              System.out.println("CONNEXION ETABLIE");
        } catch (SQLException ex) {
            Logger.getLogger(Maconnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public Connection getCnx() {
        return cnx;
    }


    public static Maconnexion getInstance() {
        if(instance == null)
            instance = new Maconnexion();
        
        return instance;
    }
    
    
    
}