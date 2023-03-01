/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class GESTIONController implements Initializable {

    @FXML
    private Button id_GA;
    @FXML
    private Button id_MA;
    @FXML
    private Button id_MU;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GarageAdmin(ActionEvent event) {
         try {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./affichageGarage.fxml"));
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
    private void MaintenanceAdmin(ActionEvent event) {
 try {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./afficher_Maintenance.fxml"));
        Parent view_2=loader.load();
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(Afficher_MaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    

    @FXML
    private void MaintenanceUser(ActionEvent event) {
 try {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./Maintenance.fxml"));
        Parent view_2=loader.load();
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(MaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    
 }
