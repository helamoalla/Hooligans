/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import util.MyConnection;

/**
 *
 * @author choua
 */
public class CodePromoService {
        Connection cnx = MyConnection.getInstance().getCnx();

    public boolean verifierCodePromo (String code){
        boolean exist=false;
        String query = "SELECT code FROM codepromo WHERE code = ?";
        try {
            PreparedStatement p  = cnx.prepareStatement(query);
            p.setString(1,code);
            ResultSet resultSet = p.executeQuery();
            if (resultSet.next()) {
                System.out.println("Le code existe dans la table codepromo.");
                exist =true;
                SupprimerCodePromo(code);

            } else {
                System.out.println("Le code n'existe pas dans la table codepromo.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exist;

    }

    public void SupprimerCodePromo(String code) {
        try {
            String sql ="DELETE from codepromo WHERE code = ?";
            PreparedStatement p  = cnx.prepareStatement(sql);
            p.setString(1,code);
            p.executeUpdate();
            System.out.println(code +" est supprimé");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void genererNouveauCode(){
        int newCode ;
        Random random = new Random();
        int randomNumber = random.nextInt(90000000) + 10000000;
        System.out.println(randomNumber);
        try {
            String sql ="insert into codepromo (code) values (?) ";
            PreparedStatement p  = cnx.prepareStatement(sql);
            p.setInt(1,randomNumber);
            p.executeUpdate();
            System.out.println("new promocode est ajouté");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
}
