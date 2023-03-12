/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.viewhela;


import Interface.InterfaceCRUD;
import Model.GarageC;
import Model.Maintenance;
import Service.ServiceMaintenance;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import Util.Data;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class MesDemandesController implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private HBox Vboxe;
 List<Maintenance> id_list = new ArrayList<>();
ServiceMaintenance sm=new ServiceMaintenance();
Maintenance m;
int id;
ObservableList<Maintenance> d ;
    private BorderPane borderPane;
    /**
     * Initializes the controller class.
     */
//      public List<Maintenance> data(ObservableList<Maintenance> om){
//          System.out.println(om);
//        this.d=om;
//        d.addAll(om);
     //  id_list.addAll(om);
    //   return id_list;
//   // Maintenance m =new Maintenance();
//    m.setId_user(m.getId_user());
//    m.setDate_maintenance(m.getDate_maintenance());
//     if(m.isAmortisseur()==true )
//           m.setAmortisseur(m.isAmortisseur());
//        if(m.isBatterie()==true)
//             m.setBatterie(m.isBatterie());
//        if(m.isDuride()==true)
//            m.setDuride(m.isDuride());
//        if(m.isEssuie_glace()==true)
//             m.setEssuie_glace(m.isEssuie_glace());
//        if(m.isFeu_d_eclairage()==true)
//            m.setFeu_d_eclairage(m.isFeu_d_eclairage());
//        if(m.isFiltre()==true)
//            m.setFiltre(m.isFiltre());
//        if(m.isFrein_main()==true)
//            m.setFrein_main(m.isFrein_main());
//        if(m.isFuite_d_huile()==true)
//             m.setFuite_d_huile(m.isFuite_d_huile());
//        if(m.isPanne_moteur()==true)
//            m.setPanne_moteur(m.isPanne_moteur());
//        if(m.isPatin()==true)
//              m.setPatin(m.isPatin());
//        if(m.isPompe_a_eau()==true)
//             m.setPompe_a_eau(m.isPompe_a_eau());
//        if(m.isRadiateur()==true)
//            m.setRadiateur(m.isRadiateur());
//        if(m.isVentilateur()==true)
//            m.setVentilateur(m.isVentilateur());
//           if(m.isVidange()==true)
//             m.setVidange(m.isVidange());
//  
//  
//    return m;
    
   // }
           public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
        int column = 0;
        int row = 0;
        id_list.addAll(sm.chercher(Data.getId_user()));
        System.out.println(id_list);
        try { 
            for (int i = 0; i < id_list.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
               fxmlLoader.setLocation(getClass().getResource("../viewhela/itemMaintenanceU.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemMaintenanceUController itemController = fxmlLoader.getController();
                itemController.setData(id_list.get(i));
                          
            itemController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(anchorPane);

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
    } 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//         int column = 0;
//        int row = 0;
//        id_list.addAll(sm.chercher(Data.getId_user()));
//        System.out.println(id_list);
//        try { 
//            for (int i = 0; i < id_list.size(); i++) {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//               fxmlLoader.setLocation(getClass().getResource("../viewhela/itemMaintenanceU.fxml"));
//                AnchorPane anchorPane = fxmlLoader.load();
//
//                ItemMaintenanceUController itemController = fxmlLoader.getController();
//                itemController.setData(id_list.get(i));
//                          
//            itemController.setBorderPane(borderPane);
//            borderPane.setCenter(null);
//            borderPane.setCenter(anchorPane);
//
//                if (column == 3) {
//                    column = 0;
//                    row++;
//                }
//
//             
//               grid.add(anchorPane, column++, row); //(child,column,row)
//            //set grid width
//             //  column++;
//            
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);
//
//               GridPane.setMargin(anchorPane,new Insets(5));
//             GridPane.setColumnIndex(anchorPane, column);
//              
//                   
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

 private void refreshNodes()
    {
        Vboxe.getChildren().clear();
         List<Maintenance> lg=sm.readAll();
     //  id_list = new ArrayList<>(data());      
        Node [] nodes = new  Node[15];
        
        for(int i = 0; i<lg.size(); i++)
        {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../view/itemMaintenanceU.fxml"));
                AnchorPane abc = fxmlLoader.load();
                ItemMaintenanceController itemcontroller = fxmlLoader.getController();
                itemcontroller.setData(lg.get(i));
                
                
                Vboxe.getChildren().add(abc);
                            
            itemcontroller.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(abc);
                
                //////////
                
                
//                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("../view/lesGarages.fxml"));
//               Vboxe.getChildren().add(nodes[i]);
//                
            } catch (IOException ex) {
                Logger.getLogger(MesDemandesController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           
       
    }
    }    

    @FXML
    private void retour(ActionEvent event) {
     
         try{
         FXMLLoader loader= new FXMLLoader(getClass().getResource("./Maintenance.fxml"));
        Parent view_2=loader.load();
        
                    MaintenanceController mainteController = loader.getController();
            mainteController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
    } catch (IOException ex) {
        Logger.getLogger(GESTIONController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    }
    

