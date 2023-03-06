/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IService;
import model.role;
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
public class RoleService implements IService<role>{
    
    
    Connection conn;

    public RoleService() {
        Connection conn = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void insert(role t) {
String requete="insert into role (type) values (?)";
        try {
            PreparedStatement usr=conn.prepareStatement(requete);
            usr.setString(1,t.getType());
            usr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(role t) {
        String requete="delete from role where id_role = "+t.getId_role();
        try {
            Statement st=conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(role t) {
         String requete = "update role set  type=? where id_role="+t.getId_role();
        try {
            PreparedStatement pst = conn.prepareStatement(requete);

            pst.setString(1,t.getType()); 
            pst.executeUpdate();
            System.out.println("role mise à jour");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour de le role" + ex.getMessage());
        }
    }

  
    public List<role> readAll() {
        String requete ="select * from role";
        List<role> list=new ArrayList<>();
        try {
            Statement st=conn.createStatement();
           ResultSet rs= st.executeQuery(requete);
           while(rs.next()){
               role t=new role
        ( rs.getInt("id_role"),rs.getString("type"));
               list.add(t);
                       
           }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public role readById(int id) {
        String requete ="select * from role where id_role="+id;
       role r=new role();
       
       try {
             Statement st=conn.createStatement();
           ResultSet rs= st.executeQuery(requete);
           while(rs.next()){
           r.setId_role(rs.getInt("id_role"));
           r.setType(rs.getString("type")); 
          
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return r;
    }
}
