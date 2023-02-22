/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author User
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
        
        

/**
 *
 * @author helam
 */
public class MaConnexion {

    //db credentials
    static final String URL ="jdbc:mysql://localhost:3306/pidev_event";
    static final String USER ="root";
    static final String PASSWORD ="";
    
    //var
    private Connection cnx;
    static MaConnexion instance;


    public MaConnexion(){
     
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
              System.out.println("CONNEXION ETABLIE");
        } catch (SQLException ex) {
            Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public Connection getCnx() {
        return cnx;
    }


    public static MaConnexion getInstance() {
        if(instance == null)
            instance = new MaConnexion();
        
        return instance;
    }
    
    
    
}