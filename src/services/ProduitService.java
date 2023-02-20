/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Util.Maconnexion;
import interfaces.InterfaceCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Produit;


/**
 *
 * @author Nadia
 */
public class ProduitService implements InterfaceCRUD <Produit> {
    
    Connection cnx = Maconnexion.getInstance().getCnx();

    @Override
    public void insert(Produit p) {
try {
            
            String req = "INSERT INTO `produit`(`nom_prod`, `prix_prod`,`description_prod`,`quantite_prod`,`id_categorie`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getNom_prod());
            ps.setDouble(2, p.getPrix_prod());
            ps.setString(3, p.getDescription_prod());
            ps.setInt(4, p.getQuantite());
            ps.setInt(5, p.getCategorie().getId_categorie());
            ps.executeUpdate();
            System.out.println("Produit Added Successfully!");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }

    @Override
    public ArrayList <Produit> readAll() {

    ArrayList<Produit> produits = new ArrayList<>();
    CategorieService cs = new CategorieService() ;
        try {
            
            String req = "SELECT * FROM produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                Produit p = new Produit();
                p.setId_prod(rs.getInt(1));
                p.setNom_prod(rs.getString(2));
                p.setPrix_prod(rs.getDouble(3));
                p.setDescription_prod(rs.getString(4));
                p.setQuantite(rs.getInt(5));
                p.setCategorie(cs.readById(rs.getInt(6)));
                
                produits.add(p);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return  produits;


    }

   @Override
   public void update(Produit p) {
        
           
     
         try {
            String req ="UPDATE produit SET `nom_prod`= ? ,`prix_prod`= ? ,`description_prod`= ? ,`quantite_prod`= ? ,`id_categorie`= ? WHERE id_prod = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,p.getNom_prod());
            ps.setDouble(2, p.getPrix_prod());
            ps.setString(3, p.getDescription_prod());
            ps.setInt(4, p.getQuantite());
            ps.setInt(5, p.getCategorie().getId_categorie());
            ps.setInt(6, p.getId_prod());
            ps.executeUpdate();
            System.out.println("produit updated successfully !");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
       
      

    @Override
    public void delete(int id) {
 
         try {
             String req = "Delete FROM `produit` WHERE id_prod ='"+id+"';" ;

             Statement st = cnx.createStatement();
              st.executeUpdate(req);
              System.out.println("Produit  supprimé avec succes");
          } catch (SQLException ex) {
                ex.printStackTrace();          }
    }

     @Override
    public Produit readById(int id) {  
        Produit p = new Produit();
         CategorieService cs = new CategorieService() ;

        try {
            
       String req="SELECT * FROM produit WHERE `id_prod`='"+id+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            rs.beforeFirst();
            rs.next();
                p.setId_prod(rs.getInt(1));
                p.setNom_prod(rs.getString(2));
                p.setPrix_prod(rs.getDouble(3));
                p.setDescription_prod(rs.getString(4));
                p.setQuantite(rs.getInt(5));
                p.setCategorie(cs.readById(rs.getInt(6)));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return  p;

    }
    


    @Override
    public ArrayList<Produit> sortBy(String nom_column, String Asc_Dsc) {
        
        List<Produit> ListeProdTriee=new ArrayList<>();
        CategorieService cs = new CategorieService() ;

        try {
              String req="SELECT * FROM produit ORDER BY "+nom_column+" "+Asc_Dsc ;
              Statement ste = cnx.createStatement();
              ResultSet res=ste.executeQuery(req);
              while(res.next()){
                  Produit p=new Produit();
                  p.setId_prod(res.getInt(1));
                  p.setNom_prod(res.getString(2));
                  p.setPrix_prod(res.getDouble(3));
                  p.setDescription_prod(res.getString(4));
                  p.setQuantite(res.getInt(5));
                  p.setCategorie(cs.readById(res.getInt(6)));

                  ListeProdTriee.add(p);
              }  
          } catch (SQLException ex) {
         ex.printStackTrace();         
          }
           return (ArrayList<Produit>) ListeProdTriee ;

    }

    @Override
    public ArrayList<Produit> chercher(String nom_column ,String valeur ) {
        
        List<Produit> ListeProdCherchee=new ArrayList<>();
          CategorieService cs = new CategorieService() ;

        try {
              String req="SELECT * FROM produit WHERE "+nom_column+" = '"+valeur+"'" ;
              Statement ste = cnx.createStatement();
              ResultSet res=ste.executeQuery(req);
              while(res.next()){
                  Produit p=new Produit();
                  p.setId_prod(res.getInt(1));
                  p.setNom_prod(res.getString(2));
                  p.setPrix_prod(res.getDouble(3));
                  p.setDescription_prod(res.getString(4));
                  p.setQuantite(res.getInt(5));
                 p.setCategorie(cs.readById(res.getInt(6)));

                  ListeProdCherchee.add(p);
              }  
          } catch (SQLException ex) {
         ex.printStackTrace();         
          }
           return (ArrayList<Produit>) ListeProdCherchee ;

    }
    
    }