/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewhela;

import Interfaces.InterfaceCRUD;
import Models.GarageC;
import Models.Maintenance;
import Services.ServiceMaintenance;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * FXML Controller class
 *
 * @author helam
 */
public class Afficher_MaintenanceController implements Initializable {
InterfaceCRUD sm=new ServiceMaintenance();
    private List<Maintenance> id_list= new ArrayList<>();
    @FXML
    private Button id_retour;
    @FXML
    private GridPane grid;
    @FXML
    private HBox Vboxe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        int column = 0;
        int row = 0;
        id_list.addAll(sm.readAll());
        System.out.println(id_list);
        try { 
            for (int i = 0; i < id_list.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
               fxmlLoader.setLocation(getClass().getResource("../viewhela/itemMaintenance.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

               ItemMaintenanceController itemController = fxmlLoader.getController();
               itemController.m=id_list.get(i);
                itemController.setData(id_list.get(i));

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

  

   

    @FXML
    private void retour(ActionEvent event) {
         try{
         FXMLLoader loader= new FXMLLoader(getClass().getResource("./GESTION.fxml"));
        Parent view_2=loader.load();
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(GESTIONController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
