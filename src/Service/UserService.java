/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Role;
import Model.User;
import Util.MyConnection;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azizh
 */
public class UserService implements Interface.InterfaceCRUD<User>{
    
    //var
    Connection conn = MyConnection.getInstance().getCnx();

    @Override
    public void insert(User t) {
        String requete="insert into user  (nom,prenom,mdp,email,num_tel,cin,quota,id_role,etat) values (?,?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement usr=conn.prepareStatement(requete);
            usr.setString(1, t.getNom());
            usr.setString(2, t.getPrenom());
            usr.setString(3, t.getMdp());
            usr.setString(4, t.getEmail());
            usr.setInt(5, t.getNum_tel());
            usr.setInt(6, t.getCin());
            usr.setInt(7, t.getQuota());
            usr.setInt(8, t.id_role);
       
            usr.setInt(9, 1);
            usr.executeUpdate();
        } 
        
        
        catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
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
    public void update(User t) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE user SET nom = ?, prenom = ? ,mdp = ?,email = ? ,num_tel = ?,cin = ?, quota = ? WHERE id_user = ?");
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setString(3, t.getMdp());
            ps.setString(4, t.getEmail());
            ps.setInt(5, t.getNum_tel());
            ps.setInt(6, t.getCin());
            ps.setInt(7, t.getQuota());
            ps.setInt(8, t.getId_user());
                            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    

    @Override
    public ArrayList<User> readAll() {
        String requete = "SELECT user.*, role.id_role, role.type FROM user INNER JOIN role ON user.id_role = role.id_role";
    ArrayList<User> list = new ArrayList<>();
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Role r = new Role();
            r.setId_role(rs.getInt("id_role"));
            r.setType(rs.getString("type"));
            User t;
            t = new User(rs.getInt("id_user"), rs.getString("nom"),rs.getString("prenom"),rs.getString("mdp"),rs.getString("email"),rs.getInt("num_tel"),rs.getInt("cin"),rs.getInt("quota"),rs.getInt("id_role"));
            list.add(t);
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
    }

    @Override
    public User readById(int id) {
        String requete;
    requete = "SELECT user.*, role.id_role, role.type FROM user INNER JOIN role ON user.id_role = user.id_role where id_user=" + id;

    User t = null;
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Role r = new Role();
            r.setId_role(rs.getInt("id_role"));
            r.setType(rs.getString("type"));
           
            t = new User(rs.getInt("id_user"), rs.getString("nom"),rs.getString("prenom"),rs.getString("mdp"),rs.getString("email"),rs.getInt("num_tel"),rs.getInt("cin"),rs.getInt("quota"),rs.getInt("id_role"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return t;
    }

    @Override
    public ArrayList<User> sortBy(String nom_column, String Asc_Dsc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
