/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Util.Data;
import Util.Maconnexion;
import Util.conditions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.LignePanier;
import models.Panier;
import models.Produit;

/**
 *
 * @author Nadia
 */
public class LignePanierService {
    
      //Var
    Connection cnx = Maconnexion.getInstance().getCnx();
    conditions c =new conditions();
    LignePanier lp=new LignePanier();
    //CRUD de l'entité ligne panier 
    //Create ligne panier
     public void Create_LignePanier(Produit p, int quantiteprod) {
        try {
            
       
    if(c.VerifProduitIdExistDansLignePanier(p.getId_prod(),Data.getId_user())==false){
        String req = "INSERT INTO `lignepanier`(`id_produit`, `id_panier`, `quantite`, `prix`, `nom_produit`, `description_prod`, `image`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1,p.getId_prod());
        ps.setInt(2,Data.getId_user());
        ps.setInt(3,quantiteprod);
        ps.setDouble(4, p.getPrix_prod());
        ps.setString(5, p.getNom_prod());
        ps.setString(6,p.getDescription_prod());
        ps.setString(7, p.getImage());
        System.out.println(c.VerifProduitIdExistDansLignePanier(p.getId_prod(),Data.getId_user()));
        ps.executeUpdate();} else {System.out.println(c.VerifProduitIdExistDansLignePanier(p.getId_prod(),3));
    //String req3 = "Update `lignepanier`(`id_produit`, `id_panier`, `quantite`, `prix`, `nom_produit`, `description_prod`, `image`) VALUES (?,?,?,?,?,?,?)";
     String req3 ="UPDATE lignepanier SET quantite = quantite + ?  WHERE id_produit =? AND id_panier ='"+ Data.getId_user()+"'";
                    PreparedStatement ps3 = cnx.prepareStatement(req3);
                    ps3.setInt(1,quantiteprod);
                    ps3.setInt(2, p.getId_prod());
                    ps3.executeUpdate();
    }
    
        
        String req1 ="UPDATE produit SET quantite_prod = quantite_prod - ?  WHERE id_prod = ? ";
        PreparedStatement ps1 = cnx.prepareStatement(req1);
        ps1.setInt(1, quantiteprod);
        ps1.setInt(2,p.getId_prod());
        ps1.executeUpdate();
        System.out.println("ligne panier remplie Successfully!");
        
                } catch (SQLException ex) {
                    Logger.getLogger(LignePanierService.class.getName()).log(Level.SEVERE, null, ex);
                }

    }
     
public List<LignePanier> AfficherPanierbyiduser(int id_user) {
        List<LignePanier> listlp = new ArrayList<>();
      //  ProduitService ps =new ProduitService();
PanierService ps = new PanierService();
        try {
 //p -->panier
 //lp-->lignepanier 
 //pr-->produit
            String sql = "SELECT p.id_panier, lp.quantite, pr.* FROM produit pr JOIN lignepanier lp ON pr.id_prod = lp.id_produit JOIN panier p ON lp.id_panier = p.id_panier WHERE p.id_user = "+ id_user ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Produit prod =new Produit();
                LignePanier lp = new LignePanier();
                lp.setQuantite(rs.getInt("lp.quantite"));
                Panier p = ps.readById(rs.getInt("p.id_panier"));
                lp.setPanier(p);
                prod.setId_prod(rs.getInt("pr.id_prod"));
                prod.setNom_prod(rs.getString("pr.nom_prod"));
                prod.setPrix_prod(rs.getDouble("pr.prix_prod"));
                prod.setDescription_prod(rs.getString("pr.description_prod"));
                prod.setQuantite(rs.getInt("pr.quantite_prod"));
                prod.setImage(rs.getString("pr.image"));
                lp.setProduit(prod);
                listlp.add(lp);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listlp;}


     
     
     
  //tzid +1 qauntite fi "panier"
    public void QantitePlus1(int id_panier,int id_prod) throws SQLException {
        String query = "UPDATE lignepanier lp JOIN panier p ON lp.id_panier = p.id_panier SET lp.quantite = lp.quantite + ? WHERE lp.id_produit =? AND p.id_panier =? ";

            PreparedStatement st = cnx.prepareStatement(query);
            st.setInt(1, 1);
            st.setInt(2, id_prod);
            st.setInt(3,id_panier );
            st.executeUpdate();
            System.out.println("Quatité a été ajouté");

    }
    
    //Soustrait -1 de la quantité du produit dans ligne panier 
    public void Qantitemoins1(int id_panier,int id_prod) throws SQLException {
        String query = "UPDATE lignepanier lp JOIN panier p ON lp.id_panier = p.id_panier SET lp.quantite = lp.quantite - ? WHERE lp.id_produit =? AND p.id_panier =? AND lp.quantite > 1 ";
        //condition si quantite =1
        PreparedStatement st = cnx.prepareStatement(query);
        st.setInt(1, 1);
        st.setInt(2, id_prod);
        st.setInt(3,id_panier );
        st.executeUpdate();
        System.out.println("Quantite a été diminué");

    }
    
      // Supprimer tous les produits de la ligne panier(vider panier)
    public void ViderLigne_panier(int id_panier) {
        try {
            String sql ="DELETE lp FROM lignepanier lp JOIN panier p ON p.id_panier = lp.id_panier WHERE p.id_panier =  "+ id_panier;
            PreparedStatement p  = cnx.prepareStatement(sql);
            p.executeUpdate();
            System.out.println("la ligne panier est vider  "+id_panier);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     // Supprimer un produit de ligne panier
    public void SupprimerProduit_de_LignePanier(int id_panier, int id_prod) {
        try {
            //String sql = "DELETE lp FROM ligne_panier lp JOIN panier p ON lp.id_panier = p.id_panier WHERE p.id_client = ? AND lp.id_article = ?";
            String req = "DELETE lp FROM lignepanier lp JOIN panier p ON lp.id_panier = p.id_panier WHERE p.id_panier= ? AND lp.id_produit = ?";
            PreparedStatement ps  = cnx.prepareStatement(req);
            ps.setInt(1, id_panier);
            ps.setInt(2, id_prod);
            ps.executeUpdate();
            System.out.println("produit d'ID "+ id_prod +" " + "supprimé du panier  d'ID "+id_panier);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    
}
