/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import interfaces.InterfaceCRUD;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import util.MyConnection;
import models.Commande;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


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
            //        try {
//            //String req = "INSERT INTO `commande`(`montant`,`etat_commande`,`adresse`,`code_postal`,`email`,`id_panier`) VALUES(?,?,?,?,?,?)";
//
//            String req = "INSERT INTO commande(montant,etat_commande,adresse,code_postal,email,id_panier,date_commande) VALUES(?,?,?,?,?,?)";
//            PreparedStatement ps = cnx.prepareStatement(req);
//            ps.setDouble(1, c.getMontant());
//            ps.setString(2, c.getEtat_commande());
//            ps.setString(3, c.getAdresse());
//            ps.setInt(4, c.getCode_postal());
//            ps.setString(5, c.getEmail());
//            ps.setInt(6, c.getPanier().getId_panier());
//            ps.setString(7, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//            ps.executeUpdate();
//            System.out.println("Commande ajoutée avec succée");
//            
//        } catch (SQLException ex) {
//        }     

//String req = "INSERT INTO `commande`(`montant`,`etat_commande,`adresse`,`code_postal`,`email`,`id_panier`) VALUES(?,?,?,?,?,?)";
String req = "INSERT INTO commande(montant,etat_commande,adresse,code_postal,email,id_panier) VALUES(?,?,?,?,?,?)";
     
PreparedStatement ps = cnx.prepareStatement(req);
             ps.setDouble(1, c.getMontant());
             ps.setString(2, c.getEtat_commande());
             ps.setString(3, c.getAdresse());
             ps.setInt(4, c.getCode_postal());
             ps.setString(5, c.getEmail());
             ps.setInt(6, c.getPanier().getId_panier());
             //ps.setString(7, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
             ps.executeUpdate();
             System.out.println("Commande ajoutée avec succée");

        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
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
            String req ="UPDATE `commande` SET  `montant`= ? ,`etat_commande`= ? ,`adresse`= ?,`code_postal`= ?,`email`= ?,`id_panier`= ?   WHERE id_commande = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDouble(1, c.getMontant());
            ps.setString(2, c.getEtat_commande());
            ps.setString(3, c.getAdresse());
            ps.setInt(4, c.getCode_postal());
            ps.setString(5, c.getEmail());
            ps.setInt(6, c.getPanier().getId_panier());
            ps.setInt(7,c.getId_commande());
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
                c.setMontant(rs.getDouble(2));
                c.setEtat_commande(rs.getString(3));
                c.setAdresse(rs.getString(4));
                c.setCode_postal(rs.getInt(5));
                c.setEmail(rs.getString(6));
                c.setPanier(ps.readById(rs.getInt(7)));
                
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
                c.setMontant(rs.getDouble(2));
                c.setEtat_commande(rs.getString(3));
                //c.setDate_commande(rs.getDate(5));
                c.setAdresse(rs.getString(4));
                c.setCode_postal(rs.getInt(5));
                c.setEmail(rs.getString(6));
                c.setPanier(ps.readById(rs.getInt(7)));
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
                c.setMontant(rs.getDouble(2));
                c.setEtat_commande(rs.getString(3));
                //c.setDate_commande(rs.getDate(5));
                c.setAdresse(rs.getString(4));
                c.setCode_postal(rs.getInt(5));
                c.setEmail(rs.getString(6));
                c.setPanier(ps.readById(rs.getInt(7)));
                ListeCommandeTriee.add(c);
              }  
          } catch (SQLException ex) {
         ex.printStackTrace();         
          }
           return (ArrayList<Commande>) ListeCommandeTriee ;
    }
    
    public List<Commande> AfficherCommandesByidpanier(int id_panier) throws SQLException {
     
            List<Commande> listC = new ArrayList<>();
            
            String sql = "SELECT * FROM commande c  WHERE c.id_panier = "+ id_panier ;
            PanierService ps = new PanierService();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Commande cmd =new Commande(); 
                cmd.setId_commande(rs.getInt(1));
                cmd.setMontant(rs.getDouble(2));
                cmd.setEtat_commande(rs.getString(3));
                //c.setDate_commande(rs.getDate(5));
                cmd.setAdresse(rs.getString(4));
                cmd.setCode_postal(rs.getInt(5));
                cmd.setEmail(rs.getString(6));
                cmd.setPanier(ps.readById(rs.getInt(7)));
                listC.add(cmd);
            }
                        return listC;
    }
    
}

