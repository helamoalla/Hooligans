/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pidevdr;

import Interfaces.InterfaceCRUD;
import Models.GarageC;
import Models.Maintenance;
import Util.Maconnexion;
import Services.ServiceGarageC;
import Services.ServiceMaintenance;

/**
 *
 * @author helam
 */
public class Appli {
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
        
        GarageC g1=new GarageC("Garage hela","tunis ",98214541,1000,500,600,200,500,300,500,400,1000,2000,500,600,400,100," ",10,"image");
      sg.insert(g1);
        
      //affichage
        System.out.println(sg.readAll());
       
       
      //update garage
       g1.setId_garage(1);
       g1.setAdresse("menzah 9");
        sg.update(g1);
       
        //affichage
        System.out.println(sg.readAll());
       
       //supprimer garage
       sg.delete(1);
       
       //affichage
        System.out.println(sg.readAll());
       
      
       
       //affichage
       //sg.afficherGarage(); //hedhi affichage bel void
       // System.out.println(sg.readAll());
       
       ///recherche par id 
   System.out.println(sg.readById(11));


 //trier 
      System.out.println(sg.sortBy("panne_moteur","ASC"));
       
      
      /////////////////Maintenance///////////////////
      InterfaceCRUD sm = new ServiceMaintenance();
      
      ////demander maintenance
       //Maintenance m1=new Maintenance(2,false,false,false,false,false,true,false,false,true,false,false,false,false,false,"");
      // sm.insert(m1);
       
      //affichage
       //System.out.println(sm.readAll());
       
        //update maintenance
     /*m1.setId_user(2);
       m1.setPompe_a_eau(true);
        sm.update(m1);*/
       
     //affichage
       //System.out.println(sm.readAll());
       
       
     //supprimer maintenance
       //sm.delete(5);
        
        
     //affichage
       //System.out.println(sm.readAll());
      
      ///recherche par id 
    // System.out.println(sm.readById(5));
     
     
     //trier 
      //System.out.println(sm.sortBy("id_user","DESC"));
      
    }
}
