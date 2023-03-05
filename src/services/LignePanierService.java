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
import java.util.logging.Level;
import java.util.logging.Logger;
import models.LignePanier;
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
            //        try {
//            //Si l'article n'existe pas on ajoute une ligne panier
//            if(c.VerifProduitIdExistDansLignePanier(id_prod,lp.getPanier().getId_panier())==false){
//                String sql = "insert into lignepanier(id_panier,id_prod,nom_produit,description,prix_u, quantite,image) values (?,?,?,?,?,?,?)";
//                PreparedStatement st = cnx.prepareStatement(sql);
//                st.setInt(1, lp.getPanier().getId_panier());
//                st.setInt(2, id_prod);
//                st.setString(3, lp.getProduit().getNom_prod());
//                st.setString(4, lp.getProduit().getDescription_prod());
//                st.setDouble(5, lp.getProduit().getPrix_prod());
//                st.setDouble(6, lp.getQuantite());
//                st.setString(7, lp.getProduit().getImage());
//                
//                st.executeUpdate();
//                System.out.println("Produit ajouté dans ligne panier associé au panier  d'Id "+ lp.getPanier().getId_panier());
//                
//            }else{
//                //Si l'article existe dans ligne panier on met à jour sa quantité
//                String sql = "UPDATE lignepanier lp JOIN panier p ON lp.id_panier = p.id_panier SET lp.quantite = lp.quantite + ? WHERE lp.id_prod =? AND p.id_panier =? ";
//                PreparedStatement st = cnx.prepareStatement(sql);
//                st.setInt(1, lp.getQuantite());
//                st.setInt(2, id_prod);
//                st.setInt(3,lp.getPanier().getId_panier());
//                st.executeUpdate();
//                System.out.println("La quantite a été mise à jour avec succés");
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
       
    if(c.VerifProduitIdExistDansLignePanier(p.getId_prod(),3)==false){
        String req = "INSERT INTO `lignepanier`(`id_produit`, `id_panier`, `quantite`, `prix`, `nom_produit`, `description_prod`, `image`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1,p.getId_prod());
        ps.setInt(2,3);
        ps.setInt(3,quantiteprod);
        ps.setDouble(4, p.getPrix_prod());
        ps.setString(5, p.getNom_prod());
        ps.setString(6,p.getDescription_prod());
        ps.setString(7, p.getImage());
        System.out.println(c.VerifProduitIdExistDansLignePanier(p.getId_prod(),3));
        ps.executeUpdate();} else {System.out.println(c.VerifProduitIdExistDansLignePanier(p.getId_prod(),3));
    //String req3 = "Update `lignepanier`(`id_produit`, `id_panier`, `quantite`, `prix`, `nom_produit`, `description_prod`, `image`) VALUES (?,?,?,?,?,?,?)";
     String req3 ="UPDATE lignepanier SET quantite = quantite + ?  WHERE id_produit =? AND id_panier =3  ";
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
    
}
