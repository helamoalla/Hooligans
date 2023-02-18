/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.InterfaceCRUD;
import Util.MyConnection;
import java.sql.Connection;
import Model.Feedback;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author azizh
 */
public class FeedbackService implements InterfaceCRUD<Feedback>{
    
    //var
    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void insert(Feedback f) {
        try {
            
            String req = "INSERT INTO `feedback`(`rate`,`commentaire`,`id_user`,`id_bonplan`) VALUES (?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           
            ps.setInt(1, f.getRate());
            ps.setString(2, f.getCommentaire());
            ps.setInt(3, f.getId_user());
            ps.setInt(4, f.getBonPlan().getId_bonplan());
            
            ps.executeUpdate();
            System.out.println("Feedback Added Successfully!");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String req ="DELETE FROM `feedback` WHERE id_feedback = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            
            ps.executeUpdate();
            System.out.println("feedback Deleted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Feedback f) {
        try {
            String req ="UPDATE `feedback` SET `rate`= ? , `commentaire`= ? , `id_user`= ? , `id_bonplan`= ? WHERE id_feedback = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, f.getRate());
            ps.setString(2, f.getCommentaire());
            ps.setInt(3, f.getId_user());
            ps.setInt(4, f.getBonPlan().getId_bonplan());
            ps.setInt(5, f.getId_feedback());
            ps.executeUpdate();
            System.out.println("Feedback updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Feedback> readAll() {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        BonPlanService bs= new BonPlanService();

    
        try {
            
            String req = "SELECT * FROM feedback";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                Feedback f = new Feedback();
                f.setId_feedback(rs.getInt(1));
                f.setRate(rs.getInt(2));
                f.setCommentaire(rs.getString(3));
                f.setId_user(rs.getInt(4));
                f.setBonPlan(bs.readById(rs.getInt(5)));
                
                
                feedbacks.add(f);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return feedbacks;
    }

    @Override
    public Feedback readById(int id) {
         Feedback f = new Feedback();
         BonPlanService bs = new BonPlanService();
        
 
             try {
            
            String req = "SELECT * FROM `feedback` WHERE id_feedback= "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs.beforeFirst();
            rs.next();
            f.setId_feedback(rs.getInt(1));
            f.setRate(rs.getInt(2));
            f.setCommentaire(rs.getString(3));
            f.setId_user(rs.getInt(4));
            f.setBonPlan(bs.readById(rs.getInt(5)));

           
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        
        return f;
    }

    @Override
    public ArrayList<Feedback> sortBy(String nom_column, String Asc_Dsc) {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        BonPlanService bs = new BonPlanService();
         
        try {
            
            String req = "SELECT * FROM feedback ORDER BY "+nom_column + " "+Asc_Dsc+" ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
               Feedback f = new Feedback();
                f.setId_feedback(rs.getInt(1));
                f.setRate(rs.getInt(2));
                f.setCommentaire(rs.getString(3));
                f.setId_user(rs.getInt(4));
                f.setBonPlan(bs.readById(rs.getInt(5)));
                
                
                feedbacks.add(f);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return feedbacks;
    }
    
}