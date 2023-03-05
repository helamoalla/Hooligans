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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class DevisController implements Initializable {
InterfaceCRUD sd=new ServiceDevis();
 InterfaceCRUD sm=new ServiceMaintenance();
         InterfaceCRUD sg=new ServiceGarageC();
    private ListView<Devis> id_list;
    @FXML
    private GridPane grid;
    @FXML
    private HBox Vboxe;
private List<Devis> id_list_d = new ArrayList<>();
Devis d=new Devis();
Maintenance m;
GarageC g;
private List<Maintenance> id_list_m = new ArrayList<>();
private List<GarageC> id_list_g = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
   public void getMaintenance(Maintenance m)
{this.m=m;

    int column = 0;
        int row = 0;
        id_list_d.addAll(sd.readAll());
        id_list_m.addAll(sm.readAll());
        id_list_g.addAll(sg.readAll());
       // System.out.println(id_list_m);
        try { 
            for (int i = 0; i < id_list_g.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
               fxmlLoader.setLocation(getClass().getResource("../view/itemDevis.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                //System.out.println(m);
               // System.out.println(id_list_g.get(i));
                 ItemDevisController ItemDevisController =fxmlLoader.getController();
          
  
     GarageC g1=ItemDevisController.getGarage(id_list_g.get(i));
     ItemDevisController.g=g1;
     ItemDevisController.m=m;
   Devis d1=new Devis();
          System.out.println(m);
      d1.setId_user(m.getId_user());
      d1.setGarage(id_list_g.get(i));
      d1.setMaintenance(m);
      sd.update(d1);
       ItemDevisController.getDevis(d1);
        ItemDevisController.d=d1;
       
                System.out.println(m);
     
     

              //  ItemDevisController itemController = fxmlLoader.getController();
       //itemController.setData(id_list_d.get(i),(sm.readById(7)),sg.readById(1);
                         //     itemController.setData(id_list_d.get(i),sm.chercher(2).get(1),g);

                if (column == 3) {
                    column = 0;
                    row++;
                }

             
               grid.add(anchorPane, column++, row); //(child,column,row)
            //set grid width
             //  column++;
            
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

               GridPane.setMargin(anchorPane,new Insets(5));
             GridPane.setColumnIndex(anchorPane, column);
              
                   
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    

    private void confirmer(ActionEvent event) {
    
       Devis selectedDevis=id_list.getSelectionModel().getSelectedItem();
        
        
       sd.insert(selectedDevis);
     
   
        
    }
    
}
