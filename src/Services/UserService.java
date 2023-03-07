/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Role;
import Models.User;
import util.Data;
import util.Maconnexion;
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
 * @author helam
 */
public class UserService implements Interfaces.InterfaceCRUD<User>{
    
    //var
    Connection conn = Maconnexion.getInstance().getCnx();
    RoleService roleService=new RoleService();
    @Override
    public void insert(User t) {
        String requete="insert into user  (nom,prenom,mdp,email,num_tel,cin,quota,img,id_role,etat) values (?,?,?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement usr=conn.prepareStatement(requete);
            usr.setString(1, t.getNom());
            usr.setString(2, t.getPrenom());
            usr.setString(3, t.getMdp());
            usr.setString(4, t.getEmail());
            usr.setInt(5, t.getNum_tel());
            usr.setInt(6, t.getCin());
            usr.setInt(7, t.getQuota());
            usr.setString(8, t.getImg());
            usr.setInt(9, t.getRole().getId_role());
       
            usr.setInt(10, 1);
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
       String request="SELECT * FROM user where id_user <> "+Data.getId_user();
        String requete = "SELECT user.*, role.id_role, role.type FROM user INNER JOIN role ON user.id_role = role.id_role";
    ArrayList<User> list = new ArrayList<>();
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(request);
        while (rs.next()) {
            /*Role r = new Role();
            r.setId_role(rs.getInt("id_role"));
            r.setType(rs.getString("type"));*/
            
            User t = new User(rs.getInt("id_user"), rs.getString("nom"),rs.getString("prenom"),rs.getString("mdp"),rs.getString("email"),rs.getInt("num_tel"),rs.getInt("cin"),rs.getInt("quota"),roleService.readById(rs.getInt("id_role")));
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
    String request ="SELECT * from user WHERE id_user= "+id;
    User t = new User();
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(request);
        while (rs.next()) {
            //Role r = new Role();
            //r.setId_role(rs.getInt("id_role"));
            //r.setType(rs.getString("type"));
           
            t = new User(rs.getInt("id_user"), rs.getString("nom"),rs.getString("prenom"),rs.getString("mdp"),rs.getString("email"),rs.getInt("num_tel"),rs.getInt("cin"),rs.getInt("quota"),roleService.readById(rs.getInt("id_role")),rs.getString("img"));
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
    public boolean checkEmailExists(String email) {
    //Connection cnx = DataSource.getInstance().getCnx();
    boolean result = false;

    try {
        String req = "SELECT * FROM user WHERE email = ?";
        PreparedStatement st = conn.prepareStatement(req);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        result = rs.next();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    return result;
}
    
    public void updateRole(User t,Role role) {
        try {
          PreparedStatement ps = conn.prepareStatement("UPDATE user SET id_role= ? WHERE id_user = ?");
            
            ps.setInt(1, role.getId_role());
            ps.setInt(2,t.getId_user());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
     public void updateban(User t,int etat_user) {
        try {
          PreparedStatement ps = conn.prepareStatement("UPDATE user SET etat= ? WHERE id_user = ?");
            ps.setInt(1, etat_user);
            ps.setInt(2,t.getId_user());

            

            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
     public User readByEmailrecruteur(String email) {
    User t = null;
      String requete = "SELECT user.*, role.id_role FROM user INNER JOIN role ON user.id_role = user.id_role where email= ?" ;
    try (PreparedStatement stmt = conn.prepareStatement(requete)) {
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Role r = new Role();
            r.setId_role(rs.getInt("id_role"));
           
             t = new User(rs.getInt("id_user"), rs.getString("nom"),rs.getString("prenom"),rs.getString("mdp"),rs.getString("email"),rs.getInt("num_tel"),rs.getInt("cin"),rs.getInt("quota"),roleService.readById(rs.getInt("id_role")));
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return t;
}
    
    
public void setMotDePasse(int id_user, String nouveauMotDePasse) {
     String query = "UPDATE user SET mdp = ? WHERE id_user = ?";
        try (
           // Connection conn = My.getInstance().getCnx();
        PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, nouveauMotDePasse);
        ps.setInt(2, id_user);
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    @Override
    public ArrayList<User> chercher(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
