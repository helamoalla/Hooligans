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
import Services.ServiceDevis;
import Services.ServiceGarageC;
import Services.ServiceMaintenance;
import java.sql.Date;
import java.time.LocalDate;
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
        
     GarageC g1=new GarageC(5,"Garage hela","tunis ",98214541,1000,500,600,200,500,300,500,400,1000,2000,500,600,400,100,10,"image");
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
             Maintenance m=new Maintenance(17,4,Date.valueOf(LocalDate.MAX),true,false,true,false,false,true,false,false,true,false,false,true,false,false,"");

      Devis d1=new Devis();
      d1.setId_user(4);
      d1.setGarage(g1);
      d1.setMaintenance(m);
      sd.update(d1);
        
      
    }
}











