/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import interfaces.InterfaceCRUD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Event;
import model.Ticket;
import util.MaConnexion;

/**
 *
 * @author User
 */
public class Service_ticket implements InterfaceCRUD<Ticket> {
Connection cnx = MaConnexion.getInstance().getCnx();
        

    @Override
    public void insert(Ticket t) {
        
    try {
        String req = "INSERT INTO `ticket`(`num_ticket`, `id_spectateur`, `id_e`, `image_qr`) VALUES ("+t.getNum_ticket()+","+t.getId_spectateur()+","+t.getEvent().getId_e()+",'"+t.getImage()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("ticket ajouter avec succes");
    } catch (SQLException ex) {
        Logger.getLogger(Service_ticket.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    }

    @Override
    public void delete(int id) {
    try {
        String req="DELETE FROM ticket WHERE (`id_ticket`='"+id+"' )";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("ticket supprimé avec succes");
    } catch (SQLException ex) {
        Logger.getLogger(Service_ticket.class.getName()).log(Level.SEVERE, null, ex);
    }
 }

    @Override
    public void update(Ticket t) {
    try {
        String req="UPDATE ticket SET `num_ticket`='"+t.getNum_ticket()+"' WHERE (`id_ticket`="+t.getId_ticket()+" )";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("ticket modifie avec succes");
    } catch (SQLException ex) {
        Logger.getLogger(Service_ticket.class.getName()).log(Level.SEVERE, null, ex);
    }
 }

    @Override
    public ArrayList<Ticket> readAll() {
        List<Ticket> le=new ArrayList<>(); 
        try {
        String req="SELECT * FROM `ticket`";
        Statement ste = cnx.createStatement();
        ResultSet res=ste.executeQuery(req);
        while(res.next()){
            Ticket t=new Ticket();
            InterfaceCRUD se = new Services_event();
            t.setId_ticket(res.getInt(1));
            t.setNum_ticket(res.getInt(2));
            t.setId_spectateur(res.getInt(3));
            Event e = (Event)se.readById(res.getInt(4));
            t.setEvent(e);
            t.setImage(res.getString(5));
            
            le.add(t);
            
        }   } catch (SQLException ex) {
        Logger.getLogger(Services_event.class.getName()).log(Level.SEVERE, null, ex);
    }


    return (ArrayList<Ticket>) le;
    }

    @Override
    public Ticket readById(int id) {
   Ticket t=new Ticket();
        try {
        
        
        String req="SELECT * FROM `ticket` WHERE `id_ticket`='"+id+"'";
        Statement ste = cnx.createStatement();
        ResultSet res=ste.executeQuery(req);
        while(res.next()){
            
            InterfaceCRUD se = new Services_event();
            t.setId_ticket(res.getInt(1));
            t.setNum_ticket(res.getInt(2));
            t.setId_spectateur(res.getInt(3));
            Event e = (Event)se.readById(res.getInt(4));
            t.setEvent(e);
            t.setImage(res.getString(5));
        }
        
        
    } catch (SQLException ex) {
        Logger.getLogger(Service_ticket.class.getName()).log(Level.SEVERE, null, ex);
    }
 return t;}

    @Override
    public ArrayList<Ticket> sortBy(String nom_column, String Asc_Dsc) {
List<Ticket> le=new ArrayList<>(); 
        try {
        String req="SELECT * FROM `ticket` ORDER BY `"+nom_column+"` "+Asc_Dsc+"";
        Statement ste = cnx.createStatement();
        ResultSet res=ste.executeQuery(req);
        while(res.next()){
            Ticket t=new Ticket();
             InterfaceCRUD se = new Services_event();
           t.setId_ticket(res.getInt(1));
            t.setNum_ticket(res.getInt(2));
            t.setId_spectateur(res.getInt(3));
            Event e = (Event)se.readById(res.getInt(4));
            t.setEvent(e);
            t.setImage(res.getString(5));
            le.add(t);
            
        }   } catch (SQLException ex) {
        Logger.getLogger(Services_event.class.getName()).log(Level.SEVERE, null, ex);
    }
     return (ArrayList<Ticket>) le; }
    
     
    public ArrayList<Ticket> chercher(int id) {
List<Ticket> lm=new ArrayList<>();
        try {
            Services_event se=new Services_event();
              String req="SELECT * FROM `ticket` WHERE ((`id_e`='"+id+"')) ";
              Statement ste = cnx.createStatement();
              ResultSet res=ste.executeQuery(req);
            
                   while(res.next()){
                       Ticket m=new Ticket();
                 m.setId_ticket(res.getInt(1));
                 m.setNum_ticket(res.getInt(2));
                  m.setId_spectateur(res.getInt(3));
                  Event e=se.readById(res.getInt(4));
                  m.setEvent(e);
                  m.setImage(res.getString(5));

                  lm.add(m);
                   }
          } catch (SQLException ex) {
              Logger.getLogger(Services_event.class.getName()).log(Level.SEVERE, null, ex);
          }
           return (ArrayList<Ticket>) lm;  
    }
    
}
