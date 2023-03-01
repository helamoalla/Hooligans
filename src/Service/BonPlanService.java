/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.InterfaceCRUD;
import Model.BonPlan;
import Util.Data;
import Util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author azizh
 */
public class BonPlanService implements InterfaceCRUD<BonPlan> {
    
    //var
    Connection cnx = MyConnection.getInstance().getCnx();
    
    
    
    @Override
    public void insert(BonPlan b) {
        
        try {
            b.setId_user(Data.id_user);
            String req = "INSERT INTO `bonplan`(`nom_bonplan`,`adresse`,`type`,`etat`, `image`, `id_user`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           
            ps.setString(1, b.getNom_bonplan());
            ps.setString(2, b.getAdresse());
            ps.setString(3, b.getType());
            ps.setString(4, b.getEtat());
            ps.setString(5, b.getImage());
            //ps.setInt(6, b.getId_user());
            ps.setInt(6, b.getId_user());
            
            ps.executeUpdate();
            System.out.println("Bon Plan Added Successfully!");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }

    @Override
    public ArrayList<BonPlan> readAll() {
        ArrayList<BonPlan> bonPlans = new ArrayList<>();

    
        try {
            
            String req = "SELECT * FROM bonplan";
            //Statement st = cnx.createStatement();
            Statement st =cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                BonPlan b = new BonPlan();
                b.setId_bonplan(rs.getInt(1));
                b.setNom_bonplan(rs.getString(2));
                b.setAdresse(rs.getString(3));
                b.setType(rs.getString(4));
                b.setEtat(rs.getString("etat"));
                b.setImage(rs.getString(6));
                b.setId_user(rs.getInt(7));
                
                bonPlans.add(b);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return bonPlans;
    
    }


    @Override
    public void delete(int id) {
        try {
            String req ="DELETE FROM `bonplan` WHERE id_bonplan = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            
            ps.executeUpdate();
            System.out.println("bonPlan Deleted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList sortBy(String nom_column, String Asc_Dsc) {
         ArrayList<BonPlan> bonPlans = new ArrayList<>();
         
        try {
            
            String req = "SELECT * FROM bonplan ORDER BY "+nom_column + " "+Asc_Dsc+" ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                BonPlan b = new BonPlan();
                 b.setId_bonplan(rs.getInt(1));
                 b.setNom_bonplan(rs.getString(2));
                 b.setAdresse(rs.getString(3));
                 b.setType(rs.getString(4));
                 b.setEtat(rs.getString("etat"));
                b.setImage(rs.getString(6));
                b.setId_user(rs.getInt(7));
                bonPlans.add(b);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return bonPlans;
    
    }

    @Override
    public void update(BonPlan b) {
           b.setId_user(Data.id_user);

          try {
            String req ="UPDATE `bonplan` SET `nom_bonplan`= ? , `adresse`= ? , `type`= ? , `image`= ?  WHERE id_bonplan = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
             ps.setString(1, b.getNom_bonplan());
            ps.setString(2, b.getAdresse());
            ps.setString(3, b.getType());
            ps.setString(4, b.getImage());
            //ps.setInt(5, b.getId_user());
            ps.setInt(5, b.getId_bonplan());
            ps.executeUpdate();
            System.out.println("Bon Plan updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public BonPlan readById(int id) {
        BonPlan b = new BonPlan();
        
 
             try {
            
            String req = "SELECT * FROM `bonplan` WHERE id_bonplan= "+id;
            //Statement st = cnx.createStatement();
            Statement st =cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(req);
            rs.beforeFirst();
            rs.next();
            b.setId_bonplan(rs.getInt(1));
            b.setNom_bonplan(rs.getString(2));
            b.setAdresse(rs.getString(3));
            b.setType(rs.getString(4));
            b.setEtat(rs.getString("etat"));
            b.setImage(rs.getString(6));
            b.setId_user(rs.getInt(7));

           
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        
        return b;
    
        
    }
    public void validateBonPlan(BonPlan b) {
          try {
            String req ="UPDATE `bonplan` SET `etat`= 'accepté' WHERE id_bonplan = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            b.setEtat("accepté");
            //ps.setString(1, b.getEtat());
            ps.setInt(1, b.getId_bonplan());
            ps.executeUpdate();
            System.out.println("Bon Plan validated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    


    
}