/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.InterfaceCRUD;
import Models.Devis;
import Models.GarageC;
import Models.Maintenance;
import static java.lang.Boolean.TRUE;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.Maconnexion;

/**
 *
 * @author helam
 */
public class ServiceDevis implements InterfaceCRUD<Devis>{
//InterfaceCRUD sd=new ServiceDevis();
    Connection cnx = Maconnexion.getInstance().getCnx();
InterfaceCRUD sm=new ServiceMaintenance();
InterfaceCRUD sg=new ServiceGarageC();
Devis d=new Devis();
    @Override
    public void insert(Devis t) {
        //List<Devis> le=new ArrayList<>(); 
         //List<Maintenance> lm=new ArrayList<>(); 
        // List<GarageC> lg=new ArrayList<>(); 
        ArrayList<Maintenance> m=sm.chercher(d.getId_user());
        int s=0;
        ArrayList<GarageC> g=sg.readAll();
        g.add(d.getGarage());
        //  lm.add(m);
        System.out.println("hello");
         for(int i=0;i<g.size();i++)
         {System.out.println("hello for");
             if(m.get(i).isPanne_moteur()==TRUE)
             {
              s = s+ g.get(i).getPanne_moteur();
              System.out.println("hello if");
             }
             if(m.get(i).isPompe_a_eau()==TRUE)
                 {
              s = s+ g.get(i).getPompe_a_eau();
             }
             System.out.println(s);
         }
         
//          ResultSet devis = stmt.executeQuery("SELECT * FROM Devis");
//            
//            while (devis.next()) {
//                int idProduit = devis.getInt("ID_produit");
//                int quantite = devis.getInt("Quantité");
//                
//                ResultSet produit = stmt.executeQuery("SELECT Nom, Prix_unitaire FROM Produits WHERE ID=" + idProduit);
//                produit.next();
//                String nomProduit = produit.getString("Nom");
//                double prixUnitaire = produit.getDouble("Prix_unitaire");
//                
//                double total = prixUnitaire * quantite;
//                System.out.println("Devis " + devis.getInt("ID") + " : " + quantite + " " + nomProduit + " à " + prixUnitaire + " euros l'unité, soit un total de " + total + " euros.");
//            }
         
         
                 //  Devis d= (Devis)sd.readById(res.getInt(4));
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Devis t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Devis> readAll() {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public Devis readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Devis> sortBy(String nom_column, String Asc_Dsc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Devis> chercher(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int size(List<Maintenance> lm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
