/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Interfaces.InterfaceCRUD;
import Models.GarageC;
import Models.Maintenance;
import Services.ServiceMaintenance;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class Afficher_MaintenanceController implements Initializable {
InterfaceCRUD sm=new ServiceMaintenance();
    @FXML
    private ListView<Maintenance> id_list;
    @FXML
    private Button id_afficher;
    @FXML
    private Button id_supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficher_maintenance(ActionEvent event) {
          ObservableList<Maintenance> g=FXCollections.observableArrayList(sm.readAll());
       id_list.setItems(g);
    }

    @FXML
    private void supprimer_maintenance(ActionEvent event) {
         int selectedId= id_list.getSelectionModel().getSelectedItem().getId_maintenance();
        sm.delete(selectedId);
       afficher_maintenance(event);
    }
    
}
