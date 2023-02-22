/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Interfaces.InterfaceCRUD;
import Models.GarageC;
import Models.Maintenance;
import Services.ServiceGarageC;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author helam
 */
public class MaintenanceController implements Initializable {
InterfaceCRUD sg=new ServiceGarageC();
InterfaceCRUD sm=new ServiceMaintenance();

Maintenance m=new Maintenance();
    @FXML
    private Button id_demande;
    @FXML
    private ListView<Maintenance> id_list;
    @FXML
    private Button id_modifier;
    @FXML
    private Button id_afficher;
   // private List<Maintenance> id_list_M=new ArrayList<>();
    @FXML
    private TextField id_user;
    @FXML
    private Button id_retour;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//         ObservableList<GarageC> g=FXCollections.observableArrayList(sg.readAll());
//      id_list.setItems(g);
    }    

    @FXML
    private void demander_maintenance(ActionEvent event) {
        try {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./demander_maintenance.fxml"));
        Parent view_2=loader.load();
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(GarageController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void modifier_demande(ActionEvent event) {
       
    try {
        Maintenance selectedMaintenance=id_list.getSelectionModel().getSelectedItem();
        
        
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./Modifier_Demande.fxml"));
        Parent view_2=loader.load();
        Modifier_DemandeController Modifier_DemandeController=loader.getController();
        Modifier_DemandeController.getMaintenance(selectedMaintenance);
        Modifier_DemandeController.m=selectedMaintenance;
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(MaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
    }
   
    }

    @FXML
    private void afficher_demande(ActionEvent event) {
        ObservableList<Maintenance> d=FXCollections.observableArrayList(sm.chercher(setId_user(Integer.parseInt(id_user.getText()))));
      // ArrayList<Maintenance> g=sm.chercher(2);
     id_list.setItems(d);
        
       }

    private int setId_user(int parseInt) {
       return parseInt;
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
