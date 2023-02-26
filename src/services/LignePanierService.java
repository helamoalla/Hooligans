/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import models.LignePanier;
import models.Produit;
import util.Conditions;
import util.MyConnection;

/**
 *
 * @author asma
 */
public class LignePanierService {
    
    
    //Var
    Connection cnx = MyConnection.getInstance().getCnx();
    Conditions c =new Conditions();

    //CRUD de l'entité ligne panier 
    //Create ligne panier
     public void Create_LignePanier(LignePanier lp, int id_prod) {
        try {
            //Si l'article n'existe pas on ajoute une ligne panier 
            if(c.VerifProduitIdExistDansLignePanier(id_prod,lp.getPanier().getId_panier())==false){
                String sql = "insert into lignepanier(id_panier,id_prod,nom_produit,description,prix_u, quantite,image) values (?,?,?,?,?,?,?)";
                PreparedStatement st = cnx.prepareStatement(sql);
                st.setInt(1, lp.getPanier().getId_panier());
                st.setInt(2, id_prod);
                st.setString(3, lp.getProduit().getNom_prod());
                st.setString(4, lp.getProduit().getDescription_prod());
                st.setDouble(5, lp.getProduit().getPrix_prod());
                st.setDouble(6, lp.getQuantite());
                st.setString(7, lp.getProduit().getImage());
                
                st.executeUpdate();
                System.out.println("Produit ajouté dans ligne panier associé au panier  d'Id "+ lp.getPanier().getId_panier());
                
            }else{
                //Si l'article existe dans ligne panier on met à jour sa quantité
                String sql = "UPDATE lignepanier lp JOIN panier p ON lp.id_panier = p.id_panier SET lp.quantite = lp.quantite + ? WHERE lp.id_prod =? AND p.id_panier =? ";
                PreparedStatement st = cnx.prepareStatement(sql);
                st.setInt(1, lp.getQuantite());
                st.setInt(2, id_prod);
                st.setInt(3,lp.getPanier().getId_panier());
                st.executeUpdate();
                System.out.println("La quantite a été mise à jour avec succés");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     // Supprimer un produit de ligne panier
    public void deleteProduit_de_LignePanier(int id_panier, int id_prod) {
        try {
            //String sql = "DELETE lp FROM ligne_panier lp JOIN panier p ON lp.id_panier = p.id_panier WHERE p.id_client = ? AND lp.id_article = ?";
            String sql = "DELETE lp FROM lignepanier lp JOIN panier p ON lp.id_panier = p.id_panier WHERE p.id_panier= ? AND lp.id_prod = ?";
            PreparedStatement p  = cnx.prepareStatement(sql);
            p.setInt(1, id_panier);
            p.setInt(2, id_prod);
            p.executeUpdate();
            System.out.println("produit d'ID "+ id_prod +" " + "supprimé du panier  d'ID "+id_panier);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Supprimer tous les produits de la ligne panier
    public void ViderLigne_panier(int id_panier) {
        try {
            //String sql = "DELETE lp FROM ligne_panier lp JOIN panier p ON lp.id_panier = p.id_panier WHERE p.id_client = ?"; //cvlient fi parametre 9bal
            //String sqls = "DELETE lp FROM ligne_panier lp JOIN panier p ON lp.id_panier = p.id_panier WHERE p.id_panier = ?";
            String sql ="DELETE lp FROM lignepanier lp JOIN panier p ON p.id_panier = lp.id_panier WHERE p.id_panier =  "+ id_panier;
            PreparedStatement p  = cnx.prepareStatement(sql);
            p.executeUpdate();
            System.out.println("la ligne panier est vider  "+id_panier);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//Afficher le panier d'un user 
public List<LignePanier> AfficherPanierbyiduser(int id_user) {
        List<LignePanier> listlp = new ArrayList<>();
      //  ProduitService ps =new ProduitService();

        try {
 //p -->panier
 //lp-->lignepanier 
 //pr-->produit
            String sql = "SELECT p.id_panier, lp.quantite, pr.* FROM produit pr JOIN lignepanier lp ON pr.id_prod = lp.id_prod JOIN panier p ON lp.id_panier = p.id_panier WHERE p.id_user = "+ id_user ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Produit prod =new Produit();
                LignePanier lp = new LignePanier();
                lp.setQuantite(rs.getInt("lp.quantite"));
                //lp.setPanier(rs.getInt("p.id_panier"));
                prod.setId_prod(rs.getInt("pr.id_prod"));
                prod.setNom_prod(rs.getString("pr.nom_prod"));
                prod.setPrix_prod(rs.getDouble("pr.prix_prod"));
                prod.setDescription_prod(rs.getString("pr.description_prod"));
                prod.setQuantite(rs.getInt("pr.quantity"));
                lp.setProduit(prod);
                listlp.add(lp);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listlp;
    }
        //tzid +1 qauntite fi "panier"
//Ajoute +1 à la quantité du produit dans ligne panier 
    public void updatequantitywithPlus1(int id_panier,int id_prod) throws SQLException {
        String query = "UPDATE lignepanier lp JOIN panier p ON lp.id_panier = p.id_panier SET lp.quantite = lp.quantite + ? WHERE lp.id_prod =? AND p.id_panier =? ";

            PreparedStatement st = cnx.prepareStatement(query);
            st.setInt(1, 1);
            st.setInt(2, id_prod);
            st.setInt(3,id_panier );
            st.executeUpdate();
            System.out.println("Quatité a été ajouté");

    }
    
    //Soustrait -1 de la quantité du produit dans ligne panier 
        //tna9es -1 qauntite fi "panier"
    public void updatequantitywithMinus1(int id_panier,int id_prod) throws SQLException {
        String query = "UPDATE lignepanier lp JOIN panier p ON lp.id_panier = p.id_panier SET lp.quantite = lp.quantite - ? WHERE lp.id_prod =? AND p.id_panier =? AND lp.quantite > 1 ";
        //condition si quantite =1
        PreparedStatement st = cnx.prepareStatement(query);
        st.setInt(1, 1);
        st.setInt(2, id_prod);
        st.setInt(3,id_panier );
        st.executeUpdate();
        System.out.println("Quantite a été diminué");

    }
    
    
}
