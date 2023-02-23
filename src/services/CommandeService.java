/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.MyConnection;
import models.Commande;
import java.util.List;


/**
 *
 * @author asma
 */
public class CommandeService implements InterfaceCRUD <Commande>{

    Connection cnx = MyConnection.getInstance().getCnx();


    @Override
    //insert
    public void insert(Commande c) {
        try {
            String req = "INSERT INTO `commande`(`montant`, `qte_produit`, `etat_commande`,`id_panier`) VALUES(?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setFloat(1, c.getMontant());
            ps.setFloat(2, c.getQte_produit());
            ps.setString(3, c.getEtat_commande());
            //ps.setDate(4, (java.sql.Date) c.getDate_commande());
            ps.setInt(4, c.getPanier().getId_panier());
            ps.executeUpdate();
            System.out.println("Commande ajoutée avec succée");
            
        } catch (SQLException ex) {
        }     

    }

    //delete
    @Override
    public void delete(int id) {
        try {
             String req = "Delete FROM commande WHERE id_commande ='"+id+"';" ;

             Statement st = cnx.createStatement();
              st.executeUpdate(req);
              System.out.println("Commande  supprimée avec succés");
          } catch (SQLException ex) {
                ex.printStackTrace();        
          }
    }
    

    @Override
    //update
    public void update(Commande c) {
        try {
            String req ="UPDATE `commande` SET  `montant`= ? ,`qte_produit`= ? ,`etat_commande`= ? ,`id_panier`= ?   WHERE id_commande = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setFloat(1, c.getMontant());
            ps.setFloat(2, c.getQte_produit());
            ps.setString(3, c.getEtat_commande());
            //ps.setDate(4, (java.sql.Date) c.getDate_commande());
            ps.setInt(4, c.getPanier().getId_panier());
            ps.setInt(5,c.getId_commande());
            ps.executeUpdate();
            System.out.println("Commande mise à jour avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Read
    @Override
    public ArrayList readAll() {
        ArrayList<Commande> commandes = new ArrayList<>();
        PanierService ps = new PanierService();
        try {
            
            String req = "SELECT * FROM commande";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                Commande c = new Commande();
                c.setId_commande(rs.getInt(1));
                c.setMontant(rs.getFloat(2));
                c.setQte_produit(rs.getFloat(3));
                c.setEtat_commande(rs.getString(4));
                //c.setDate_commande(rs.getDate(5));
                c.setPanier(ps.readById(rs.getInt(6)));
                
                commandes.add(c);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  commandes;

    }

    @Override
    public Commande readById(int id) {
        Commande c = new Commande();
        PanierService ps = new PanierService();
        try {
            
       String req="SELECT * FROM commande WHERE `id_commande`='"+id+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs.beforeFirst();
            rs.next();
                c.setId_commande(rs.getInt(1));
                c.setMontant(rs.getFloat(2));
                c.setQte_produit(rs.getFloat(3));
                c.setEtat_commande(rs.getString(4));
                //c.setDate_commande(rs.getDate(5));
                c.setPanier(ps.readById(rs.getInt(6)));
                   
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return  c;
    }

    @Override
    public ArrayList sortBy(String nom_column, String Asc_Dsc) {
    
        List<Commande> ListeCommandeTriee=new ArrayList<>();
        PanierService ps = new PanierService();
        try {
              String req="SELECT * FROM commande ORDER BY "+nom_column+" "+Asc_Dsc ;
              Statement ste = cnx.createStatement();
              ResultSet rs=ste.executeQuery(req);
              while(rs.next()){
               Commande c=new Commande();
                c.setId_commande(rs.getInt(1));
                c.setMontant(rs.getFloat(2));
                c.setQte_produit(rs.getFloat(3));
                c.setEtat_commande(rs.getString(4));
                //c.setDate_commande(rs.getDate(5));
                c.setPanier(ps.readById(rs.getInt(5)));
                ListeCommandeTriee.add(c);
              }  
          } catch (SQLException ex) {
         ex.printStackTrace();         
          }
           return (ArrayList<Commande>) ListeCommandeTriee ;
    }

}
