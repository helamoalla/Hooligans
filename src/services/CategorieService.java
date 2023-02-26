/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import interfaces.InterfaceCRUD;
import util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Categorie;
import models.Produit;

/**
 *
 * @author asma
 */
public class CategorieService implements InterfaceCRUD <Categorie> {
 Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void insert(Categorie c) {
try {
            
            String req = "INSERT INTO `categorie`(`nom_categorie`, `description_categorie`,`type_categorie`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getNom_categorie());
            ps.setString(2, c.getDescription_categorie());
            ps.setString(3, c.getType_categorie());
            ps.executeUpdate();
            System.out.println("Categorie Added Successfully!");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }

    @Override
    public void delete(int id) {
try {
             String req = "Delete FROM `categorie` WHERE id_categorie='"+id+"';" ;

             Statement st = cnx.createStatement();
              st.executeUpdate(req);
              System.out.println("Categorie  supprimé avec succes");
          } catch (SQLException ex) {
                ex.printStackTrace();          }
    }

    @Override
    public void update(Categorie c) {
   try {
            String req ="UPDATE categorie SET `nom_categorie`= ? , `description_categorie`= ? ,`type_categorie`= ?  WHERE id_categorie = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,c.getNom_categorie());
            ps.setString(2, c.getDescription_categorie());
             ps.setString(3, c.getType_categorie());
            ps.setInt(4, c.getId_categorie());
            ps.executeUpdate();
            System.out.println("categorie updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }

    @Override
    public ArrayList<Categorie> readAll() {
   ArrayList<Categorie> categories = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                Categorie c = new Categorie();
                c.setId_categorie(rs.getInt(1));
                c.setNom_categorie(rs.getString(2));
                c.setDescription_categorie(rs.getString(3));
                c.setType_categorie(rs.getString(4));
               
                categories.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return  categories;

    }


    @Override
    public Categorie readById(int id) {
 Categorie c = new Categorie();
        try {
            
       String req="SELECT * FROM categorie WHERE `id_categorie`='"+id+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs.beforeFirst();
            rs.next();
                c.setId_categorie(rs.getInt(1));
                c.setNom_categorie(rs.getString(2));
                c.setDescription_categorie(rs.getString(3));           
                c.setType_categorie(rs.getString(4)); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  c;
    }

    @Override
    public ArrayList<Categorie> sortBy(String nom_column, String Asc_Dsc) {
    List<Categorie> ListeCategTriee=new ArrayList<>();
        try {
              String req="SELECT * FROM categorie ORDER BY "+nom_column+" "+Asc_Dsc ;
              Statement ste = cnx.createStatement();
              ResultSet res=ste.executeQuery(req);
              while(res.next()){
                  Categorie c=new Categorie();
                  c.setId_categorie(res.getInt(1));
                  c.setNom_categorie(res.getString(2));
                  c.setDescription_categorie(res.getString(3));
          c.setType_categorie(res.getString(4)); 

                  ListeCategTriee.add(c);
              }  
          } catch (SQLException ex) {
         ex.printStackTrace();         
          }
           return (ArrayList<Categorie>) ListeCategTriee ;

    }

   

     public ArrayList<Categorie> chercher(String nom_column, String valeur) {
 List<Categorie> ListeCategCherchee=new ArrayList<>();
        try {
              String req="SELECT * FROM categorie WHERE "+nom_column+" = '"+valeur+"'" ;
              Statement ste = cnx.createStatement();
              ResultSet res=ste.executeQuery(req);
              while(res.next()){
                  Categorie c=new Categorie();
                  c.setId_categorie(res.getInt(1));
                  c.setNom_categorie(res.getString(2));
                  c.setDescription_categorie(res.getString(3));
                  c.setType_categorie(res.getString(4));
                  ListeCategCherchee.add(c);
              }  
          } catch (SQLException ex) {
         ex.printStackTrace();         
          }
           return (ArrayList<Categorie>) ListeCategCherchee ;


    }
     
         public Categorie RetournerT(String s) {
        Categorie c = new Categorie();
        try {
            
       String req="SELECT * FROM categorie WHERE `nom_categorie`='"+s+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs.beforeFirst();
            rs.next();
                c.setId_categorie(rs.getInt(1));
                c.setNom_categorie(rs.getString(2));
                c.setDescription_categorie(rs.getString(3));           
                c.setType_categorie(rs.getString(4)); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  c;
        
    }   
}