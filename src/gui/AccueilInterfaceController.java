/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author choua
 */
public class AccueilInterfaceController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private VBox sideArea;
    @FXML
    private HBox sideControls;
    @FXML
    private Label closeButton;
    @FXML
    private VBox sideNavPanier;
    @FXML
    private Region Accueil;
    @FXML
    private Region MonPanier;
    @FXML
    private Pane handPaneMac;
    @FXML
    private Button btncommandes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void MonPanier(MouseEvent event) {
        
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./ConsulterPanierInterface.fxml"));
            Parent view_2=loader.load();
            ConsulterPanierInterfaceController i =loader.getController();
            
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AccueilInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @FXML
    private void MontreMesCommandes(MouseEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./MesCommandesInterfaces.fxml"));
            Parent view_2=loader.load();
            MesCommandesInterfacesController i =loader.getController();  
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AccueilInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
