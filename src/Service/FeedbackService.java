/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.InterfaceCRUD;
import Model.BonPlan;
import Util.MyConnection;
import java.sql.Connection;
import Model.Feedback;
import Util.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            f.setId_user(Data.id_user);
            String req = "INSERT INTO `feedback`(`rate`,`commentaire`,`id_user`,`id_bonplan`,`report`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           
            ps.setInt(1, f.getRate());
            ps.setString(2, f.getCommentaire());
            ps.setInt(3, f.getId_user());
            ps.setInt(4, f.getBonPlan().getId_bonplan());
            
            
            if(!checkIfReported(f)&&f.isReport())
            { ps.setBoolean(5, true);
            }

            else if(checkIfReported(f)|| !f.isReport()){
                System.out.println("You have already report this BonPlan you can't do it again");
                ps.setBoolean(5, false);
            }
            
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
            String req ="UPDATE `feedback` SET `rate`= ? , `commentaire`= ? ,`id_bonplan`= ? WHERE id_feedback = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, f.getRate());
            ps.setString(2, f.getCommentaire());
            //ps.setInt(3, f.getId_user());
            ps.setInt(3, f.getBonPlan().getId_bonplan());
            ps.setInt(4, f.getId_feedback());
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
            //Statement st = cnx.createStatement();
            Statement st =cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
            //Statement st = cnx.createStatement();
            Statement st =cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

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
    public boolean checkIfReported(Feedback f ){
        boolean reported=false;
        try {
            // check if user already reported this bonPlan
            String req = "SELECT report FROM feedback WHERE id_bonplan= "+f.getBonPlan().getId_bonplan()+" AND id_user = "+f.getId_user();
            //Statement st = cnx.createStatement();
            Statement st =cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = st.executeQuery(req);
            //rs.beforeFirst();
            //rs.next();
            
            while(rs.next()){
               System.out.println(rs.getBoolean("report"));
               if(rs.getBoolean("report"))
                   return rs.getBoolean("report");
            }
         
          
                
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackService.class.getName()).log(Level.SEVERE, null, ex);
        }
                return reported;

    }
public void reportBonPlan(Feedback f) {
    if (!checkIfReported(f)) {
        try {
            PreparedStatement updateStatement = cnx.prepareStatement("UPDATE feedback SET report = ? WHERE id_feedback = ?");
            f.setReport(true);
            updateStatement.setBoolean(1, f.isReport());
            updateStatement.setInt(2, f.getId_feedback());
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        System.out.println("This BonPlan is already reported");
    }
}
public void countReports() {
    try {
        String req = "SELECT id_bonplan, COUNT(*) AS report_count FROM feedback WHERE report = true GROUP BY id_bonplan";
            //Statement st = cnx.createStatement();
            Statement st =cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = st.executeQuery(req);
            while (result.next()) {
            int bonplanId = result.getInt("id_bonplan");
            int reportCount = result.getInt("report_count");
                System.out.println(reportCount);
            if (reportCount>=5){
                System.out.println("bon plan deleted "+bonplanId);
                BonPlanService bs=new BonPlanService();
                bs.delete(bonplanId);
            }
    // Print or store the bonplan ID and report count as needed
}
    } catch (SQLException ex) {
        Logger.getLogger(FeedbackService.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}

public ArrayList<Feedback> readAllByBonPlan(BonPlan b) {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        BonPlanService bs= new BonPlanService();

    
        try {
            
            String req = "SELECT * FROM feedback WHERE id_bonplan = "+b.getId_bonplan();
            //Statement st = cnx.createStatement();
            Statement st =cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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