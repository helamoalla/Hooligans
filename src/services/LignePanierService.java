/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Util.Maconnexion;
import Util.conditions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.LignePanier;

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
     public void Create_LignePanier(int id_prod) {
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
    
}
