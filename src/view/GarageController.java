/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Interfaces.InterfaceCRUD;
import Models.GarageC;
import Services.ServiceGarageC;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class GarageController implements Initializable {
InterfaceCRUD sg=new ServiceGarageC();
    @FXML
    private ListView<GarageC> id_list;
    @FXML
    private Button id_afficher;
    @FXML
    private Button id_suppr;
    @FXML
    private Button id_ajout;
    @FXML
    private Button id_modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficher_garage(ActionEvent event) {
        ObservableList<GarageC> g=FXCollections.observableArrayList(sg.readAll());
       id_list.setItems(g);
    }

    @FXML
    private void supprimer_Garage(ActionEvent event) {
        int selectedId= id_list.getSelectionModel().getSelectedItem().getId_garage();
        sg.delete(selectedId);
       afficher_garage(event);
        
    }

    @FXML
    private void ajouter_garage(ActionEvent event) {
    try {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./Ajout_Garage.fxml"));
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
    private void modifier_garage(ActionEvent event) {
    try {
        GarageC selectedGarage=id_list.getSelectionModel().getSelectedItem();
        
        
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./modifier_Garage.fxml"));
        Parent view_2=loader.load();
        Modifier_GarageController Modifier_GarageController=loader.getController();
        Modifier_GarageController.getGarage(selectedGarage);
        Modifier_GarageController.g=selectedGarage;
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(GarageController.class.getName()).log(Level.SEVERE, null, ex);
    }
   
    }
    
}
