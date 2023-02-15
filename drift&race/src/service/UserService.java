/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.role;
import entity.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author 21694
 */
public class UserService implements Iservice<user>{
    
    private final Connection conn;

    public UserService() {
        conn=DataSource.getInstance().getCnx();
    }

    
    @Override
    public void insert(user t){
        String requete="insert into user  (nom,prenom,mdp,email,datenaissance,cin,quota,id_role) values (?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement usr=conn.prepareStatement(requete);
            usr.setString(1, t.getNom());
            usr.setString(2, t.getPrenom());
            usr.setString(3, t.getMdp());
            usr.setString(4, t.getEmail());
            usr.setString(5, t.getDatenaissance());
            usr.setInt(6, t.getCin());
            usr.setInt(7, t.getQuota());
            usr.setInt(8, t.getId_role().getId_role());
            usr.executeUpdate();
        } 
        
        
        catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void delete(user t) {
        String requete="delete from user where id_user = "+t.getId_user();
        try {
            Statement st=conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(user t) {
        
          try {
            PreparedStatement ps = conn.prepareStatement("UPDATE user SET nom = ?, prenom = ? ,mdp = ?,email = ? ,datenaissance = ?,cin = ?, quota = ?, id_role = ?, mdp = ?");
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setString(3, t.getMdp());
            ps.setString(4, t.getEmail());
            ps.setString(5, t.getDatenaissance());
            ps.setInt(6, t.getCin());
            ps.setInt(7, t.getQuota());
            ps.setInt(8, t.getId_role().getId_role());
            
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
            t = new user(rs.getInt("id_user"), rs.getString("nom"),rs.getString("prenom"),rs.getString("mdp"),rs.getString("email"),rs.getString("datenaissance"),rs.getInt("cin"),rs.getInt("quota"),r);
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
           
            t = new user(rs.getInt("id_user"), rs.getString("nom"),rs.getString("prenom"),rs.getString("mdp"),rs.getString("email"),rs.getString("datenaissance"),rs.getInt("cin"),rs.getInt("quota"),r);
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return t;
    }


    
    
    
    
    
    
    }


