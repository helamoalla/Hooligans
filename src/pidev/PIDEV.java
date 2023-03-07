/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Interfaces.InterfaceCRUD;
import Models.Devis;
import Models.GarageC;
import Models.Maintenance;
import Models.Role;
import Models.User;
import Services.RoleService;
import Services.ServiceDevis;
import Services.ServiceGarageC;
import Services.ServiceMaintenance;
import Services.UserService;
import java.net.PasswordAuthentication;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import util.Data;

/**
 *
 * @author helam
 */
public class PIDEV {

    public static void main(String[] args) {

        //Maconnexion mac=new Maconnexion();
        InterfaceCRUD sg = new ServiceGarageC();
   

        ///////////garage//////////////////
        //initialisation d'un garage
        /* GarageC g=new GarageC();
            g.setNom_garage("Garage Auto Mohsen");
            g.setAdresse("Ariana rue salem n°2");
            g.setNumero(98665235);
            g.setPanne_moteur(500);*/
        //ajout garage methode 1
        //sg.insert(g);
        GarageC g1 = new GarageC(5, "Garage hela", "tunis ", 98214541, 1000, 500, 600, 200, 500, 300, 500, 400, 1000, 2000, 500, 600, 400, 100, 10, "image");
        // sg.insert(g1);

        //supprimer garage
        // sg.delete(9);
        //update garage
        /*g1.setId_garage(11);
            g1.setAdresse("menzah 9");
            sg.update(g1);*/
        //affichage
        //sg.afficherGarage(); //hedhi affichage bel void
        // System.out.println(sg.readAll());
        ///recherche par id
        //System.out.println(sg.readById(11));
        //trier
        // System.out.println(sg.sortBy("panne_moteur","ASC"));
        /////////////////Maintenance///////////////////
        InterfaceCRUD sm = new ServiceMaintenance();

        //   System.out.println(sm.chercher(2));
        ////demander maintenance
        //  Maintenance m1=new Maintenance(14,5,Date.valueOf(LocalDate.MAX),false,false,true,false,false,true,false,false,true,false,false,true,false,false,"");
        // sm.insert(m1);
        //supprimer maintenance
        //sm.delete(5);
        //update maintenance
        /*m1.setId_user(2);
            m1.setPompe_a_eau(true);
            sm.update(m1);*/
        //affichage
        //System.out.println(sm.readAll());
        ///recherche par id
        // System.out.println(sm.readById(5));
        //trier
        //System.out.println(sm.sortBy("id_user","DESC"));
        //////////////////////DEVIS //////////
        InterfaceCRUD sd = new ServiceDevis();
        //GarageC g=new GarageC();
        // Maintenance m=new Maintenance();
        //  Maintenance m=new Maintenance(17,4,Date.valueOf(LocalDate.MAX),true,false,true,false,false,true,false,false,true,false,false,true,false,false,"");

//            Devis d1=new Devis();
//            d1.setId_user(4);
//            d1.setGarage(g1);
//            d1.setMaintenance(m);
//            sd.update(d1);
//            System.out.println(sd.readAll());
//        
///////////////user//////////
        Role r1 = new Role(1, "admin");
        Role r2 = new Role(2, "spectateur");
        Role r3 = new Role(1, "pilote");
        RoleService rs = new RoleService();
      rs.insert(r1);
        rs.insert(r2);
        rs.insert(r3);
        System.out.println(rs.readAll());

        User u1=new User("Aziz", "Hajjem", "aziz", "aziz@gmail.com", 20223666, 111222333, 0, r2,"hh",0);
         User u2=new User("Ayoub", "Barnat", "aziz", "aziz@gmail.com", 20223666, 111222333, 0, r2,"ff",0);
//         User u3=new User("Aziz", "Haj", "aziz", "aziz@gmail.com", 20223666, 111222333, 0, r2);
//         User u4=new User("Nadia", "karboul", "aziz", "aziz@gmail.com", 20223666, 111222333, 0, r2);
//         User u5=new User("Hela", "Moalla", "aziz", "aziz@gmail.com", 20223666, 111222333, 0, r2);
        UserService us = new UserService();
        us.insert(u1);
        us.insert(u2);
//        us.insert(u3);
//        us.insert(u4);
//        us.insert(u5);
        Data.setId_user(1);
        System.out.println(us.readAll());

    }
}
