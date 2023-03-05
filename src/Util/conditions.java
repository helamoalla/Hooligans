/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nadia
 */
public class conditions {
    
      Connection cnx = Maconnexion.getInstance().getCnx();
    public boolean ExisteProduit(String nom_prod) throws SQLException {
        String query = "SELECT nom_prod FROM produit WHERE nom_prod = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, nom_prod);
            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        }
    }
    
      public boolean VerifProduitIdExistDansLignePanier(int id_prod,int id_panier) throws SQLException {
        String query = "SELECT lp.id_produit FROM lignepanier lp JOIN panier p ON p.id_panier = lp.id_panier  WHERE lp.id_produit = ? AND lp.id_panier =? ";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, id_prod);
            ps.setInt(2, id_panier);
            try (ResultSet rs =ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    //Cette fonction va vérifier si le user existe ou pas dans panier 
    //de cette facon on va s'assurer que chaque id user doit etre contenu dans un seul panier 
    //Cette fonction va garantir que chaque user doit avoir un seul panier
    public boolean VerifUserIdExistDansPanier(int id_user) throws SQLException {
        String query = "SELECT id_user FROM panier WHERE id_user = ?";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, id_user);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    //Cette fonction vérifie si le panier existe dans la commande 
    public boolean VerifPanierIdExistDansCommande(int id_panier) throws SQLException {
        String query = "SELECT id_panier FROM commande WHERE id_panier = ?";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, id_panier);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
