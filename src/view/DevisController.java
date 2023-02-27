/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Interfaces.InterfaceCRUD;
import Models.Devis;
import Models.GarageC;
import Services.ServiceDevis;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class DevisController implements Initializable {
InterfaceCRUD sd=new ServiceDevis();
    @FXML
    private ListView<Devis> id_list;
    @FXML
    private Button id_confirmer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ObservableList<Devis> devis = FXCollections.observableArrayList(sd.chercher(3));
        id_list.setItems(devis);
    }    

    @FXML
    private void confirmer(ActionEvent event) {
    
       Devis selectedDevis=id_list.getSelectionModel().getSelectedItem();
        
        
       sd.insert(selectedDevis);
     
   
        
    }
    
}
