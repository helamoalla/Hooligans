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
import util.MaConnexion;

/**
 *
 * @author User
 */
public class Services_event implements InterfaceCRUD<Event> {

Connection cnx = MaConnexion.getInstance().getCnx();

    
@Override
    public void insert(Event e) {
         try {
            String req = "INSERT INTO `event`(`nom_event`, `date_debut`, `date_fin`, `lieu_event`, `type_event` , `image_event` , `prix_event`) VALUES ('"+ e.getNom_event()+"','"+e.getDate_debut()+"','"+e.getDate_fin()+"','"+e.getLieu_event()+"','"+e.getType_event()+"','"+ e.getImage()+"','"+e.getPrix()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Event ajouté avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(Services_event.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
        String req="DELETE FROM event WHERE (`id_e`='"+id+"' )";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Event supprimé avec succes");
    } catch (SQLException ex) {
        Logger.getLogger(Services_event.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

   
@Override
    public void update(Event e) {
        
 
    try {
        String req="UPDATE `event` SET `nom_event`='"+e.getNom_event()+"', `date_debut`='"+e.getDate_debut()+"', `date_fin`='"+e.getDate_fin()+"', `lieu_event`='"+e.getLieu_event()+"' ,`type_event`='"+e.getType_event()+"' ,`image_event`='"+e.getImage()+"' ,`prix_event`="+e.getPrix()+"WHERE `id_e`="+e.getId_e()+" ";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Event modifie avec succes");
    } catch (SQLException ex) {
        Logger.getLogger(Services_event.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public ArrayList readAll() {
            List<Event> le=new ArrayList<>(); 
        try {
        String req="SELECT * FROM `event`";
        Statement ste = cnx.createStatement();
        ResultSet res=ste.executeQuery(req);
        while(res.next()){
            Event e=new Event();
            e.setId_e(res.getInt(1));
            e.setNom_event(res.getString(2));
            e.setDate_debut(res.getDate(3));
            e.setDate_fin(res.getDate(4));
            e.setLieu_event(res.getString(5));
            e.setType_event(res.getString(6));
            e.setImage(res.getString(7));
             e.setPrix(res.getDouble(8));
            
            le.add(e);
            
        }   } catch (SQLException ex) {
        Logger.getLogger(Services_event.class.getName()).log(Level.SEVERE, null, ex);
    }


    return (ArrayList<Event>) le;
    }

    @Override
    public Event readById(int id) {
       Event e=new Event();
        try {
        String req="SELECT * FROM `event` WHERE `id_e`='"+id+"'";
        Statement ste = cnx.createStatement();
        ResultSet res=ste.executeQuery(req);
        while(res.next()){
            
            e.setId_e(res.getInt(1));
            e.setNom_event(res.getString(2));
            e.setDate_debut(res.getDate(3));
            e.setDate_fin(res.getDate(4));
            e.setLieu_event(res.getString(5));
            e.setType_event(res.getString(6));
            e.setImage(res.getString(7));
                         e.setPrix(res.getDouble(8));

            
        }
           } catch (SQLException ex) {
        Logger.getLogger(Services_event.class.getName()).log(Level.SEVERE, null, ex);
    }
       return e;
    }

    @Override
    public ArrayList sortBy(String nom_column, String Asc_Dsc) {
List<Event> le=new ArrayList<>(); 
        try {
        String req="SELECT * FROM `event` ORDER BY `"+nom_column+"` "+Asc_Dsc+"";
        Statement ste = cnx.createStatement();
        ResultSet res=ste.executeQuery(req);
        while(res.next()){
            Event e=new Event();
            e.setId_e(res.getInt(1));
            e.setNom_event(res.getString(2));
            e.setDate_debut(res.getDate(3));
            e.setDate_fin(res.getDate(4));
            e.setLieu_event(res.getString(5));
            e.setType_event(res.getString(6));
              e.setImage(res.getString(7));
                         e.setPrix(res.getDouble(8));
            le.add(e);
            
        }   } catch (SQLException ex) {
        Logger.getLogger(Services_event.class.getName()).log(Level.SEVERE, null, ex);
    }
     return (ArrayList<Event>) le; }

  
    }
    

