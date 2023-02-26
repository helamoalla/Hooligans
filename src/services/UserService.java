/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.InterfaceCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import models.user;
import util.MyConnection;

/**
 *
 * @author asma
 */
public class UserService implements InterfaceCRUD<user>{
    
     Connection conn = MyConnection.getInstance().getCnx();

  @Override
    public void insert(user t){
        String requete="insert into user  (nom,prenom,mdp,email,datenaissance,cin,quota) values (?,?,?,?,?,?,?) ";
        try {
            PreparedStatement usr=conn.prepareStatement(requete);
            usr.setString(1, t.getNom());
            usr.setString(2, t.getPrenom());
            usr.setString(3, t.getMdp());
            usr.setString(4, t.getEmail());
            usr.setString(5, t.getDatenaissance());
            usr.setString(6, t.getCin());
            usr.setInt(7, t.getQuota());
            //usr.setInt(8, t.getId_role().getId_role());
            usr.executeUpdate();
        } 
        
        
        catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     //delete
    @Override
    public void delete(int id) {
        try {
             String req = "Delete FROM user WHERE id_user ='"+id+"';" ;

             Statement st = conn.createStatement();
              st.executeUpdate(req);
              System.out.println("Utilisateur  supprimé avec succés");
          } catch (SQLException ex) {
                ex.printStackTrace();        
          }
    }

//    @Override
//    public void delete(user t) {
//        String requete="delete from user where id_user = "+t.getId_user();
//        try {
//            Statement st=conn.createStatement();
//            st.executeUpdate(requete);
//        } catch (SQLException ex) {
//            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @Override
    public void update(user t) {
        
          try {
            PreparedStatement ps = conn.prepareStatement("UPDATE user SET nom = ?, prenom = ? ,mdp = ?,email = ? ,datenaissance = ?,cin = ?, quota = ?");
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setString(3, t.getMdp());
            ps.setString(4, t.getEmail());
            ps.setString(5, t.getDatenaissance());
            ps.setString(6, t.getCin());
            ps.setInt(7, t.getQuota());
            //ps.setInt(8, t.getId_role().getId_role());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
    }

//    @Override
//    public List<user> readAll() {
//         String requete = "SELECT user.*, role.id_role, role.type FROM user INNER JOIN role ON user.id_role = role.id_role";
//    List<user> list = new ArrayList<>();
//    try {
//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery(requete);
//        while (rs.next()) {
//            role r = new role();
//            r.setId_role(rs.getInt("id_role"));
//            r.setType(rs.getString("type"));
//            user t;
//            t = new user(rs.getInt("id_user"), rs.getString("nom"),rs.getString("prenom"),rs.getString("mdp"),rs.getString("email"),rs.getString("datenaissance"),rs.getInt("cin"),rs.getInt("quota"),r);
//            list.add(t);
//        }
//    } catch (SQLException ex) {
//        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    return list;
//}
    
        @Override
    public ArrayList<user> readAll() {
   ArrayList<user> users = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM user";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                user u = new user();
                u.setId_user(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setMdp(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setDatenaissance(rs.getString(6));
                u.setCin(rs.getString(7));
                u.setQuota(rs.getInt(8));
                
               
                users.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return  users;

    }


    

//    @Override
//    public user readById(int id_user) {
//       String requete;
//    requete = "SELECT user.*, role.id_role, role.type FROM user INNER JOIN role ON user.id_role = user.id_role where id_user=" + id_user;
//
//    user t = null;
//    try {
//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery(requete);
//        while (rs.next()) {
//            role r = new role();
//            r.setId_role(rs.getInt("id_role"));
//            r.setType(rs.getString("type"));
//           
//            t = new user(rs.getInt("id_user"), rs.getString("nom"),rs.getString("prenom"),rs.getString("mdp"),rs.getString("email"),rs.getString("datenaissance"),rs.getInt("cin"),rs.getInt("quota"),r);
//        }
//    } catch (SQLException ex) {
//        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
//    }
//
//    return t;
//    }
    
    @Override
    public user readById(int id) {
      user u = new user();
        try {
            
       String req="SELECT * FROM user WHERE `id_user`='"+id+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs.beforeFirst();
            rs.next();
                u.setId_user(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setMdp(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setDatenaissance(rs.getString(6));
                u.setCin(rs.getString(7));
                u.setQuota(rs.getInt(8));
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  u;
    }


    @Override
    public ArrayList<user> sortBy(String nom_column, String Asc_Dsc) {
List<user> ListeUserTriee=new ArrayList<>();
        try {
              String req="SELECT * FROM user ORDER BY "+nom_column+" "+Asc_Dsc ;
              Statement ste = conn.createStatement();
              ResultSet rs=ste.executeQuery(req);
              while(rs.next()){
                  user u=new user();
                   u.setId_user(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setMdp(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setDatenaissance(rs.getString(6));
                u.setCin(rs.getString(7));
                u.setQuota(rs.getInt(8));

                  ListeUserTriee.add(u);
              }  
          } catch (SQLException ex) {
         ex.printStackTrace();         
          }
           return (ArrayList<user>) ListeUserTriee ;    }

}
