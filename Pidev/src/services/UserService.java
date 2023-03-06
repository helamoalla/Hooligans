/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import model.role;
import model.user;
import interfaces.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MaConnexion;


/**
 *
 * @author 21694
 */
public class UserService implements IService<user>{
    private String requete;
 

    
       Connection conn = MaConnexion.getInstance().getCnx();
    

    
    @Override
    public void insert(user t){
         
        System.out.println(t.getId_role());
        try {
            String req = "INSERT INTO `user`(`nom`, `prenom`, `mdp`, `email`, `num_tel`, `cin`, `quota`, `id_role`) VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getMdp()+"','"+t.getEmail()+"',"+t.getNum_tel()+","+t.getCin()+","+t.getQuota()+","+t.getId_role()+")";
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("user ajouter avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
        
        
        
      
        
    
      public user readByEmailrecruteur(String email) {
    user t = null;
      requete = "SELECT user.*, role.id_role FROM user INNER JOIN role ON user.id_role = user.id_role where email= ?" ;
    try (PreparedStatement stmt = conn.prepareStatement(requete)) {
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            role r = new role();
            r.setId_role(rs.getInt("id_role"));
           
             t = new user(rs.getInt("id_user"), rs.getString("nom"),rs.getString("prenom"),rs.getString("mdp"),rs.getString("email"),rs.getInt("num_tel"),rs.getInt("cin"),rs.getDouble("quota"),rs.getInt("id_role"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return t;
}

   public void delete(int id) {
   
    String requete = "DELETE FROM user WHERE id_user = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(requete);
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    @Override
    public void update(user t) {
        
          try {
            PreparedStatement ps = conn.prepareStatement("UPDATE user SET nom = ?, prenom = ? ,mdp = ?,email = ? ,num_tel = ?,cin = ?, quota = ? WHERE id_user = ?");
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setString(3, t.getMdp());
            ps.setString(4, t.getEmail());
            ps.setInt(5, t.getNum_tel());
            ps.setInt(6, t.getCin());
            ps.setDouble(7, t.getQuota());
            ps.setInt(8, t.getId_user());
              
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
    }

    @Override
    public List<user> readAll() {
         String requete = "SELECT user.*, role.id_role, role.type FROM user INNER JOIN role ON user.id_role = role.id_role";
    List<user> list = new ArrayList<>();
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            role r = new role();
            r.setId_role(rs.getInt("id_role"));
            r.setType(rs.getString("type"));
            user t;
            t = new user(rs.getInt("id_user"), rs.getString("nom"),rs.getString("prenom"),rs.getString("mdp"),rs.getString("email"),rs.getInt("num_tel"),rs.getInt("cin"),rs.getDouble("quota"),rs.getInt("id_role"));
            list.add(t);
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}


    

    @Override
    public user readById(int id_user) {
       String requete;
    requete = "SELECT user.*, role.id_role, role.type FROM user INNER JOIN role ON user.id_role = user.id_role where id_user=" + id_user;

    user t = null;
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            role r = new role();
            r.setId_role(rs.getInt("id_role"));
            r.setType(rs.getString("type"));
           
            t = new user(rs.getInt("id_user"), rs.getString("nom"),rs.getString("prenom"),rs.getString("mdp"),rs.getString("email"),rs.getInt("num_tel"),rs.getInt("cin"),rs.getDouble("quota"),rs.getInt("id_role"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return t;
    }

    @Override
    public void delete(user t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void update1(user t,int id_role) {
        try {
          PreparedStatement ps = conn.prepareStatement("UPDATE user SET id_role= ? WHERE id_user = ?");
ps.setInt(2,t.getId_user());
//            ps.setString(2, t.getPrenom());
//            ps.setInt(3, t.getNum_tel());
//            ps.setString(4, t.getAdresse());
//            ps.setString(5, t.getCentre_intere());
//            ps.setString(6, t.getAdresse_entreprise());
//            ps.setString(7, t.getNom_entreprise()); 
            ps.setInt(1, id_role);
//            ps.setString(9, t.getEmail());
//            ps.setString(10,t.getMdp()); 
//            ps.setInt(11, t.getAge()); 
//            ps.setInt(12, t.getNote());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
    
    
    
    
    
    }