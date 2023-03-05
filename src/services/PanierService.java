/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Util.Maconnexion;
import Util.conditions;
import interfaces.InterfaceCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.mail.Service;
import models.Categorie;
import models.Panier;

/**
 *
 * @author Nadia
 */
public class PanierService implements InterfaceCRUD <Panier>{
    
        Connection cnx = Maconnexion.getInstance().getCnx();
     
  
  //CRUD
  //Insert
    @Override
    public void insert(Panier p) {
                try {
            conditions c =new conditions();
            String req = "INSERT INTO `panier`(`id_user`) VALUES(?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            
          // if (c.VerifUserIdExistDansPanier(p.getUtilisateur().getId_user())==false){
            ps.setInt(1,3);
            ps.executeUpdate();
            System.out.println("Panier ajouté avec succés");
//            }
//            else {
//                 System.out.println("Cet utilisateur a deja un panier");
//            }
            
        } catch (SQLException ex) {
             ex.printStackTrace();  
        }}     

  


    @Override
    public ArrayList<Panier> sortBy(String nom_column, String Asc_Dsc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Panier RetournerT(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            ps.setInt(1, 3);   
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
                p.setUtilisateur(3);
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
                p.setUtilisateur(3);            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return  p;
      
    }

   
    
    //Métier 
    //Fonction qui calcule le montant total d'un panier 
    public double totalmontantPanier(int id_user){
        Double totalPrixPanier=0.0 ;
        try {
            String sql = "SELECT SUM( prix * quantite) AS total FROM panier JOIN lignepanier ON panier.id_panier = lignepanier.id_panier WHERE panier.user = "+ 3;
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

    @Override
    public ArrayList<Panier> chercher(String nom_column, String valeur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}


