/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Panier;
import util.MyConnection;

/**
 *
 * @author asma
 */
public class PanierService implements InterfaceCRUD <Panier> {

  Connection cnx = MyConnection.getInstance().getCnx();
     
  //insert
    @Override
    public void insert(Panier p) {
                try {
            String req = "INSERT INTO `panier`(`montant`, `nb_articles`) VALUES(?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setFloat(1, p.getMontant());
            ps.setInt(2, p.getNb_articles());
            ps.executeUpdate();
            System.out.println("Panier ajouté avec succés");
            
        } catch (SQLException ex) {
             ex.printStackTrace();  
        }     

    }

    //Delete
    @Override
    public void delete(int id) {
        try {
             String req = "Delete FROM panier WHERE id_panier ='"+id+"';" ;

             Statement st = cnx.createStatement();
              st.executeUpdate(req);
              System.out.println("Panier supprimé avec succés");
          } catch (SQLException ex) {
                ex.printStackTrace();        
          }
        
    }

    //Update
    @Override
    public void update(Panier p) {
         try {
            String req ="UPDATE `panier` SET  `montant`= ? ,`nb_articles`= ?  WHERE id_panier = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setFloat(1, p.getMontant());
            ps.setInt(2, p.getNb_articles());
            ps.setInt(3, p.getId_panier());
           
            System.out.println("Panier mis à jour avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Read
    @Override
    public ArrayList readAll() {
         ArrayList<Panier> listPaniers = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM panier";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                Panier p = new Panier();
                p.setId_panier(rs.getInt(1));
                p.setMontant(rs.getFloat(2));
                p.setNb_articles(rs.getInt(3));
                
                listPaniers.add(p);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  listPaniers; 
    }

    @Override
    public Panier readById(int id) {
        Panier p = new Panier();
        try {
            
       String req="SELECT * FROM panier WHERE `id_panier`='"+id+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs.beforeFirst();
            rs.next();
                p.setId_panier(rs.getInt(1));
                p.setMontant(rs.getFloat(2));
                p.setNb_articles(rs.getInt(3));    
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return  p;
      
    }

    @Override
    public ArrayList sortBy(String nom_column, String Asc_Dsc) {
         List<Panier> ListePanierTriee=new ArrayList<>();
        try {
              String req="SELECT * FROM panier ORDER BY "+nom_column+" "+Asc_Dsc ;
              Statement ste = cnx.createStatement();
              ResultSet rs=ste.executeQuery(req);
              while(rs.next()){
               Panier p =new Panier();
                p.setId_panier(rs.getInt(1));
                p.setMontant(rs.getFloat(2));
                p.setNb_articles(rs.getInt(3));
              }  
          } catch (SQLException ex) {
         ex.printStackTrace();         
          }
           return (ArrayList<Panier>) ListePanierTriee ;
    }
    
}
