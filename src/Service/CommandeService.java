/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Util.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Commande;

/**
 *
 * @author Nadia
 */
public class CommandeService {
    
    Connection cnx = MyConnection.getInstance().getCnx();


    //insert
    public void insert(Commande c) {
        try {
String req = "INSERT INTO `commande`(`id_panier`,`montant`,`etat_commande`,`gouvernorat`,`ville`,`rue`,`code_postal`,`date_commande`) VALUES(?,?,?,?,?,?,?,?)";
     
PreparedStatement ps = cnx.prepareStatement(req);
             ps.setInt(1, c.getPanier().getId_panier());
             ps.setDouble(2, c.getMontant());
             ps.setString(3, c.getEtat_commande());
             ps.setString(4, c.getGouvernorat());
             ps.setString(5, c.getVille());
             ps.setString(6, c.getRue());
             ps.setInt(7, c.getCode_postal());
             ps.setDate(8, Date.valueOf(LocalDate.now()));
             ps.executeUpdate();
             System.out.println("Commande ajoutée avec succée");

        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    //delete

    public void delete(int id) {
        try {
             String req = "Delete FROM commande WHERE id_commande ='"+id+"';" ;

             Statement st = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
              st.executeUpdate(req);
              System.out.println("Commande  supprimée avec succés");
          } catch (SQLException ex) {
                ex.printStackTrace();        
          }
    }
    

    
    //update
    public void update(Commande c) {
        try {
            String req ="UPDATE `commande` SET  `id_panier`= ? ,`montant`= ? ,`etat_commande`= ? ,`gouvernorat`= ?,`ville`= ?,`rue`= ?,`code_postal`= ? WHERE id_commande = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, c.getPanier().getId_panier());
            ps.setDouble(2, c.getMontant());
            ps.setString(3, c.getEtat_commande());
            ps.setString(4, c.getGouvernorat());
            ps.setString(5, c.getVille());
            ps.setString(6, c.getRue());
            ps.setInt(7, c.getCode_postal());
            ps.setInt(8,c.getId_commande());
            ps.executeUpdate();
            System.out.println("Commande mise à jour avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Read
    public ArrayList readAll() {
        ArrayList<Commande> commandes = new ArrayList<>();
        PanierService ps = new PanierService();
        try {
            
            String req = "SELECT * FROM commande";
            Statement st = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                Commande c = new Commande();
                c.setId_commande(rs.getInt(1));
                c.setPanier(ps.readById(rs.getInt(2)));
                c.setMontant(rs.getDouble(3));
                c.setEtat_commande(rs.getString(4));
                c.setGouvernorat(rs.getString(5));
                c.setVille(rs.getString(6));
                c.setRue(rs.getString(7));
                c.setCode_postal(rs.getInt(8));
                c.setDate_commande(rs.getDate(9));
              commandes.add(c);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  commandes;

    }

    public Commande readById(int id) {
        Commande c = new Commande();
        PanierService ps = new PanierService();
        try {
            
       String req="SELECT * FROM commande WHERE `id_commande`='"+id+"'";
            Statement st = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(req);
            rs.beforeFirst();
            rs.next();
            c.setId_commande(rs.getInt(1));
            c.setPanier(ps.readById(rs.getInt(2)));
            c.setMontant(rs.getDouble(3));
            c.setEtat_commande(rs.getString(4));
            c.setGouvernorat(rs.getString(5));
            c.setVille(rs.getString(6));
            c.setRue(rs.getString(7));
            c.setCode_postal(rs.getInt(8));
            //c.setDate_commande(rs.getDate(5));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return  c;
    }

    public ArrayList sortBy(String nom_column, String Asc_Dsc) {
    
        List<Commande> ListeCommandeTriee=new ArrayList<>();
        PanierService ps = new PanierService();
        try {
              String req="SELECT * FROM commande ORDER BY "+nom_column+" "+Asc_Dsc ;
              Statement ste = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
              ResultSet rs=ste.executeQuery(req);
              while(rs.next()){
               Commande c=new Commande();
               c.setId_commande(rs.getInt(1));
               c.setPanier(ps.readById(rs.getInt(2)));
               c.setMontant(rs.getDouble(3));
               c.setEtat_commande(rs.getString(4));
               c.setGouvernorat(rs.getString(5));
               c.setVille(rs.getString(6));
               c.setRue(rs.getString(7));
               c.setCode_postal(rs.getInt(8));
                //c.setDate_commande(rs.getDate(5));
                ListeCommandeTriee.add(c);
              }  
          } catch (SQLException ex) {
         ex.printStackTrace();         
          }
           return (ArrayList<Commande>) ListeCommandeTriee ;
    }
    
    //Afficher les commandes du panier d'un user 7
    public List<Commande> AfficherCommandesByidpanier(int id_panier) throws SQLException {
     
            List<Commande> listC = new ArrayList<>();
            
            String sql = "SELECT * FROM commande c  WHERE c.id_panier = "+ id_panier ;
            PanierService ps = new PanierService();
            Statement st = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Commande c =new Commande(); 
               c.setId_commande(rs.getInt(1));
               c.setPanier(ps.readById(rs.getInt(2)));
               c.setMontant(rs.getDouble(3));
               c.setEtat_commande(rs.getString(4));
               c.setGouvernorat(rs.getString(5));
               c.setVille(rs.getString(6));
               c.setRue(rs.getString(7));
               c.setCode_postal(rs.getInt(8));
               c.setDate_commande(rs.getDate(9));
                listC.add(c);
            }
                        return listC;
    }
    
    //Afficher les commandes d'un user 
    public List<Commande> AfficherCommandesByid_user(int id_user) throws SQLException {
     
            List<Commande> listC = new ArrayList<>();
            
            String sql = "SELECT * FROM commande c, panier p  WHERE c.id_panier = p.id_panier AND p.id_user= "+ id_user ;
            PanierService ps = new PanierService();
            Statement st = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               Commande c =new Commande(); 
               c.setId_commande(rs.getInt(1));
               c.setPanier(ps.readById(rs.getInt(2)));
               c.setMontant(rs.getDouble(3));
               c.setEtat_commande(rs.getString(4));
               c.setGouvernorat(rs.getString(5));
               c.setVille(rs.getString(6));
               c.setRue(rs.getString(7));
               c.setCode_postal(rs.getInt(8));
                c.setDate_commande(rs.getDate(9));
               listC.add(c);
            }
                        return listC;
    }
    
}
