/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.viewhela;

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
import javafx.scene.layout.BorderPane;
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
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
          public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GarageAdmin(ActionEvent event) {
         try {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./affichageGarage.fxml"));
        Parent view_2=loader.load();
        
             AffichageGarageController affichageGarageController = loader.getController();
            affichageGarageController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
    } catch (IOException ex) {
        Logger.getLogger(GarageController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
   

    @FXML
    private void MaintenanceAdmin(ActionEvent event) {
 try {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./afficher_Maintenance.fxml"));
        Parent view_2=loader.load();
        
                    Afficher_MaintenanceController afficherMaintenanceController = loader.getController();
            afficherMaintenanceController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
    } catch (IOException ex) {
        Logger.getLogger(Afficher_MaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    

    @FXML
    private void MaintenanceUser(ActionEvent event) {
 try {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./Maintenance.fxml"));
        Parent view_2=loader.load();
        
                    MaintenanceController maintenanceController = loader.getController();
            maintenanceController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
    } catch (IOException ex) {
        Logger.getLogger(MaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    
 }
