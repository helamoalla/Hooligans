/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Util.Maconnexion;
import interfaces.InterfaceCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Role;

/**
 *
 * @author Nadia
 */
public class RoleService  implements InterfaceCRUD <Role> {
    Connection cnx = Maconnexion.getInstance().getCnx();


    @Override
    public void insert(Role t) {
        try {
            String requete="insert into role (type) values (?)";
            
            PreparedStatement usr=cnx.prepareStatement(requete);
            usr.setString(1,t.getType());
            usr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @Override
    public void delete(int id) {
        try {
            String requete="delete from role where id_role = "+id;
            
            Statement st=cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @Override
    public void update(Role t) {
         String requete = "update role set  type=? where id_role="+t.getId_role();
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1,t.getType()); 
            pst.executeUpdate();
            System.out.println("role mise à jour");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour de le role" + ex.getMessage());
        }
    }

    @Override
    public ArrayList<Role> readAll() {
        String requete ="select * from role";
        ArrayList<Role> list=new ArrayList<>();
        try {
            Statement st=cnx.createStatement();
           ResultSet rs= st.executeQuery(requete);
           while(rs.next()){
               Role t=new Role
        ( rs.getInt("id_role"),rs.getString("type"));
               list.add(t);
                       
           }
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Role readById(int id) {
        String requete ="select * from role where id_role="+id;
       Role r=new Role();
       
       try {
             Statement st=cnx.createStatement();
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

    @Override
    public ArrayList<Role> sortBy(String nom_column, String Asc_Dsc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Role> chercher(String nom_column, String valeur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Role RetournerT(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
