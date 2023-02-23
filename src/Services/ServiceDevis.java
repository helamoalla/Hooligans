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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            String req = "INSERT INTO `devis`(`id_user`, `TVA`, `total` , `id_garage`, `id_maintenance`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getId_user());
             ps.setInt(2, t.getTVA());
               ps.setInt(3, t.getTotal());
            ps.setInt(4, t.getGarage().getId_garage());
             ps.setInt(5, t.getMaintenance().getId_maintenance());
            ps.executeUpdate();
            System.out.println("DEVIS ajouter avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDevis.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Devis t) {
           
       ArrayList<Maintenance> lm=sm.chercher(t.getId_user());
       
        Maintenance m=lm.get(0);
           System.out.println(m);
         ArrayList<GarageC> lg=sg.readAll();
        
         ArrayList<Devis> ld=new ArrayList<>();
         
        int somme=0;
           
         for(GarageC g:lg)
         {
             if(m.isFeu_d_eclairage())
         {
             somme=somme+g.getFeu_d_eclairage();
           
         }
               if(m.isAmortisseur())
         {
             somme=somme+g.getAmortisseur();
      
         }
                if(m.isBatterie())
         {
             somme=somme+g.getBatterie();
         
         }
                 if(m.isDuride())
         {
             somme=somme+g.getDuride();
       
         }
                  if(m.isEssuie_glace())
         {
             somme=somme+g.getEssuie_glace();
         
         }
                   if(m.isFiltre())
         {
             somme=somme+g.getFiltre();
         
         }
                    if(m.isFrein_main())
         {
             somme=somme+g.getFrein_main();
           
         }
                     if(m.isFuite_d_huile())
         {
             somme=somme+g.getFuite_d_huile();
          
         }
                      if(m.isPanne_moteur())
         {
             somme=somme+g.getPanne_moteur();
            
         }
                       if(m.isPatin())
         {
             somme=somme+g.getPatin();

         }
                        if(m.isPompe_a_eau())
         {
             somme=somme+g.getPompe_a_eau();
           
         }
                 if(m.isRadiateur())
         {
             somme=somme+g.getRadiateur();
           
         }
                 if(m.isVentilateur())
         {
             somme=somme+g.getVentilateur();
           
         }
              if(m.isVidange())
         {
             somme=somme+g.getVidange();
           
         }
      t.setGarage(g);
      t.setMaintenance(m);
      t.setId_user(m.getId_user());
      t.setTVA(19);
           t.setTotal(somme);
           insert(t);
           System.out.println(somme);
           somme=0;
       
         }
         
      
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
