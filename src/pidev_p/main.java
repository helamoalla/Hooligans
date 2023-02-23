/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Pidev_p;

import java.util.ArrayList;
import util.MyConnection;
import java.util.Date;
import models.Commande;
import models.Panier;
import services.CommandeService;
import services.InterfaceCRUD;
import services.PanierService;


/**
 *
 * @author choua
 */
public class main {

    public static void main(String[] args) {
      // MyConnection m =new MyConnection();
       
       Panier p = new Panier();
//       p.setId_panier(2);
       p.setMontant(54f);
       p.setNb_articles(600);
      
       //InterfaceCRUD sc = new CommandeService();
       InterfaceCRUD pc = new PanierService();
       Commande c = new Commande(50f,10,"en cours", p);
       

       
    
// pc.insert(p);
//       pc.delete(2);
//       pc.update(p);
//       ArrayList<Panier> listPaniers = new ArrayList<>();
//       listPaniers = pc.readAll();
//       System.out.println(pc.sortBy("montant", "ASC"));
//        System.out.println(pc.readById(1));
       
     //sc.insert(c);
//sc.delete(4);
//c.setId_commande(7);
//    // sc.update(c);
// ArrayList<Commande> listCommandes = new ArrayList<>();
//listCommandes = sc.readAll();
//System.out.println(listCommandes);
//      System.out.println(sc.sortBy("montant", "ASC"));
//      System.out.println(sc.readById(1));
       
       
    
       
    }
}
