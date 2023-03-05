/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Interfaces.InterfaceCRUD;
import Models.Devis;
import Models.GarageC;
import Models.Maintenance;
import Services.ServiceDevis;
import Services.ServiceGarageC;
import Services.ServiceMaintenance;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class ItemDevisController implements Initializable {

    @FXML
    private Label TTC;
    @FXML
    private Label reduction;
    @FXML
    private Label total;
    @FXML
    private Label panne;
    @FXML
    private Label prix;
Maintenance m;
        Devis d=new Devis();
        GarageC g;
        InterfaceCRUD sm=new ServiceMaintenance();
        ServiceDevis sd=new ServiceDevis();
         InterfaceCRUD sg=new ServiceGarageC();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    
    }    
    public void getDevis(Devis d)
{this.d=d;
 total.setText(String.valueOf(d.getTotal()));
       reduction.setText(String.valueOf(g.getTaux_de_reduction()));
       TTC.setText(String.valueOf(sd.TTC));
         String a="";
         String p="";
     
       if(this.m.isAmortisseur()==true )
       {  p=p+String.valueOf(g.getAmortisseur());
            a= a+"Amortisseur ";
       }
        if(this.m.isBatterie()==true)
        {
            p=p+String.valueOf(g.getBatterie());
            a=a+" Batterie ";
        }
        if(this.m.isDuride()==true)
        {
             p=p+String.valueOf(g.getDuride());
            a=a+" Duride ";
        }
        if(this.m.isEssuie_glace()==true)
        { p=p+String.valueOf(g.getEssuie_glace());
            a=a+" Essuie_glace ";
        }
        if(this.m.isFeu_d_eclairage()==true)
        {  p=p+String.valueOf(g.getFeu_d_eclairage());
            a=a+" Feu_d_eclairage ";
        }
        if(this.m.isFiltre()==true)
        { p=p+String.valueOf(g.getFiltre());
            a=a+" Filtre ";
        }
        if(this.m.isFrein_main()==true)
        {  p=p+String.valueOf(g.getFrein_main());
            a=a+" Frein_main ";
        }
        if(this.m.isFuite_d_huile()==true)
        { p=p+String.valueOf(g.getFuite_d_huile());
            a=a+" Fuite_d_huile ";
        }
        if(this.m.isPanne_moteur()==true)
        { p=p+String.valueOf(g.getPanne_moteur());
            a=a+" Panne_moteur ";
        }
        if(this.m.isPatin()==true)
        {p=p+String.valueOf(g.getPatin());
            a=a+" Patin ";
        }
        if(this.m.isPompe_a_eau()==true)
        {p=p+String.valueOf(g.getPompe_a_eau());
            a=a+" Pompe_a_eau ";
        }
        if(this.m.isRadiateur()==true)
        {p=p+String.valueOf(g.getRadiateur());
            a=a+" Radiateur ";
        }
        if(this.m.isVentilateur()==true)
        {p=p+String.valueOf(g.getVentilateur());
            a=a+" Ventilateur ";
        }
           if(this.m.isVidange()==true)
           {p=p+String.valueOf(g.getVidange());
            a=a+" Vidange "; 
           }
       panne.setText(a);
       prix.setText(p);
    


//     Maintenance m1=new Maintenance();
//    m1.setId_maintenance(m.getId_maintenance());
//                  m1.setId_user(m.getId_user());
//                  m1.setDate_maintenance(m.getDate_maintenance());
//                  m1.setPanne_moteur(m.isPanne_moteur());
//                  m1.setPompe_a_eau(m.isPompe_a_eau());
//                  m1.setPatin(m.isPatin());
//                  m1.setEssuie_glace(m.isEssuie_glace());
//                  m1.setRadiateur(m.isRadiateur());
//                  m1.setVentilateur(m.isVentilateur());
//                  m1.setDuride(m.isDuride());
//                  m1.setFuite_d_huile(m.isFuite_d_huile());
//                  m1.setVidange(m.isVidange());
//                  m1.setFiltre(m.isFiltre());
//                  m1.setBatterie(m.isBatterie());
//                  m1.setAmortisseur(m.isAmortisseur());
//                  m1.setFrein_main(m.isFrein_main());
//                  m1.setFeu_d_eclairage(m.isFeu_d_eclairage());
//                  m1.setAutre(m.getAutre());
                  
                  
}
        public GarageC getGarage(GarageC g)
{
     GarageC g1=new GarageC();
    g1.setId_garage(g.getId_garage());
                  g1.setNom_garage(g.getNom_garage());
                   g1.setAdresse(g.getAdresse());
                    g1.setNumero(g.getNumero());
                  g1.setPanne_moteur(g.getPanne_moteur());
                  g1.setPompe_a_eau(g.getPompe_a_eau());
                  g1.setPatin(g.getPatin());
                  g1.setEssuie_glace(g.getEssuie_glace());
                  g1.setRadiateur(g.getRadiateur());
                  g1.setVentilateur(g.getVentilateur());
                  g1.setDuride(g.getDuride());
                  g1.setFuite_d_huile(g.getFuite_d_huile());
                  g1.setVidange(g.getVidange());
                  g1.setFiltre(g.getFiltre());
                  g1.setBatterie(g.getBatterie());
                  g1.setAmortisseur(g.getAmortisseur());
                  g1.setFrein_main(g.getFrein_main());
                  g1.setFeu_d_eclairage(g.getFeu_d_eclairage());
            this.g=g1;
                  return g1;
                  
}
    
     public void setData(Maintenance m,GarageC g)
    {  
//          Devis d1=new Devis();
//          System.out.println(m);
//      d1.setId_user(m.getId_user());
//      d1.setGarage(g);
//      d1.setMaintenance(m);
//      sd.update(d1);
       
//       total.setText(String.valueOf(d1.getTotal()));
//       reduction.setText(String.valueOf(g.getTaux_de_reduction()));
//       TTC.setText(String.valueOf(sd.TTC));
//         String a="";
//         String p="";
//     
//       if(this.m.isAmortisseur()==true )
//       {  p=p+String.valueOf(g.getAmortisseur());
//            a= a+"Amortisseur ";
//       }
//        if(this.m.isBatterie()==true)
//        {
//            p=p+String.valueOf(g.getBatterie());
//            a=a+" Batterie ";
//        }
//        if(this.m.isDuride()==true)
//        {
//             p=p+String.valueOf(g.getDuride());
//            a=a+" Duride ";
//        }
//        if(this.m.isEssuie_glace()==true)
//        { p=p+String.valueOf(g.getEssuie_glace());
//            a=a+" Essuie_glace ";
//        }
//        if(this.m.isFeu_d_eclairage()==true)
//        {  p=p+String.valueOf(g.getFeu_d_eclairage());
//            a=a+" Feu_d_eclairage ";
//        }
//        if(this.m.isFiltre()==true)
//        { p=p+String.valueOf(g.getFiltre());
//            a=a+" Filtre ";
//        }
//        if(this.m.isFrein_main()==true)
//        {  p=p+String.valueOf(g.getFrein_main());
//            a=a+" Frein_main ";
//        }
//        if(this.m.isFuite_d_huile()==true)
//        { p=p+String.valueOf(g.getFuite_d_huile());
//            a=a+" Fuite_d_huile ";
//        }
//        if(this.m.isPanne_moteur()==true)
//        { p=p+String.valueOf(g.getPanne_moteur());
//            a=a+" Panne_moteur ";
//        }
//        if(this.m.isPatin()==true)
//        {p=p+String.valueOf(g.getPatin());
//            a=a+" Patin ";
//        }
//        if(this.m.isPompe_a_eau()==true)
//        {p=p+String.valueOf(g.getPompe_a_eau());
//            a=a+" Pompe_a_eau ";
//        }
//        if(this.m.isRadiateur()==true)
//        {p=p+String.valueOf(g.getRadiateur());
//            a=a+" Radiateur ";
//        }
//        if(this.m.isVentilateur()==true)
//        {p=p+String.valueOf(g.getVentilateur());
//            a=a+" Ventilateur ";
//        }
//           if(this.m.isVidange()==true)
//           {p=p+String.valueOf(g.getVidange());
//            a=a+" Vidange "; 
//           }
//       panne.setText(a);
//       prix.setText(p);
//    
}
}