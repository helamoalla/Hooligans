/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import interfaces.InterfaceCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Panier;
import util.Conditions;
import util.MyConnection;

/**
 *
 * @author asma
 */
public class PanierService implements InterfaceCRUD <Panier> {

  Connection cnx = MyConnection.getInstance().getCnx();
     
  
  //CRUD
  //Insert
    @Override
    public void insert(Panier p) {
                try {
            Conditions c =new Conditions();
            String req = "INSERT INTO `panier`(`id_user`) VALUES(?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            
            if(c.VerifUserIdExistDansPanier(p.getUtilisateur().getId_user())==false){
            ps.setInt(1, p.getUtilisateur().getId_user());
            ps.executeUpdate();
            System.out.println("Panier ajouté avec succés");
            }
            else {
                 System.out.println("Cet utilisateur a deja un panier");
            }
            
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
            String req ="UPDATE `panier` SET  `id_user`= ?  WHERE id_panier = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getUtilisateur().getId_user());   
            System.out.println("Panier mis à jour avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Read
    @Override
    public ArrayList readAll() {
         ArrayList<Panier> listPaniers = new ArrayList<>();
         UserService us = new UserService();

        try {
            
            String req = "SELECT * FROM panier";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                Panier p = new Panier();
                p.setId_panier(rs.getInt(1));
                p.setUtilisateur(us.readById(rs.getInt(2)));
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
        UserService us = new UserService();
        try {
            
       String req="SELECT * FROM panier WHERE `id_panier`='"+id+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs.beforeFirst();
            rs.next();
                p.setId_panier(rs.getInt(1));
                p.setUtilisateur(us.readById(rs.getInt(2)));            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return  p;
      
    }

    @Override
    public ArrayList sortBy(String nom_column, String Asc_Dsc) {
         List<Panier> ListePanierTriee=new ArrayList<>();
         UserService us = new UserService();
        try {
              String req="SELECT * FROM panier ORDER BY "+nom_column+" "+Asc_Dsc ;
              Statement ste = cnx.createStatement();
              ResultSet rs=ste.executeQuery(req);
              while(rs.next()){
               Panier p =new Panier();
                p.setId_panier(rs.getInt(1));
                p.setUtilisateur(us.readById(rs.getInt(2))); 
              }  
          } catch (SQLException ex) {
         ex.printStackTrace();         
          }
           return (ArrayList<Panier>) ListePanierTriee ;
    }
    
    //Métier 
    //Fonction qui calcule le montant total d'un panier 
    public double totalmontantPanier(int id_user){
        Double totalPrixPanier=0.0 ;
        try {
            String sql = "SELECT SUM(quantite * prix_u) AS total FROM panier JOIN lignepanier ON panier.id_panier = lignepanier.id_panier WHERE panier.id_user = "+ id_user;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                totalPrixPanier = rs.getDouble("total");//total est une colonne virtuell, elle n'existe pas dans la table panier
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPrixPanier;
    }
    
    
    //Fonction qui retourne le panier d'un user passé en paramétre 
        public int getPanierIdForUser(int id_user) {
        int panierId = 0;
        String query = "SELECT id_panier FROM panier WHERE id_user = ?";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, id_user);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                panierId = rs.getInt("id_panier");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return panierId;
    }
    
}
