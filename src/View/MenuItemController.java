/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package View;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author azizh
 */
public class MenuItemController implements Initializable {

    @FXML
    private JFXButton goback;
    
    private BorderPane borderPane;
    @FXML
    private JFXButton BonPlanLink;
    @FXML
    private JFXButton AddBonPlanLink;
    @FXML
    private JFXButton Hiiii;
    @FXML
    private VBox btns;
    
    private boolean isVboxVisible = false;
    @FXML
    private ImageView arrow;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        btns.setVisible(isVboxVisible);
    }    
        public void setBorderPane(BorderPane borderPane) {
        try {
            this.borderPane = borderPane;
            Parent newView = FXMLLoader.load(getClass().getResource("Home.fxml"));
            borderPane.setCenter(null);
            borderPane.setCenter(newView);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoBack(ActionEvent event) {
        try {
           
            Parent newView = FXMLLoader.load(getClass().getResource("Home.fxml"));
            borderPane.setCenter(null);
            borderPane.setCenter(newView);
        } catch (IOException ex) {
            Logger.getLogger(AjouterBonPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void bonPlanLink(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BonPlan.fxml"));
            Parent AllBonPlans = loader.load();            
            FXMLController fxmlController = loader.getController();
            fxmlController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(AllBonPlans);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   /* private void feedBackLink(ActionEvent event) {
        try {
            Parent newView = FXMLLoader.load(getClass().getResource("Feedback.fxml"));
            borderPane.setCenter(null);
            borderPane.setCenter(newView);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    @FXML
    private void addBonPlanLink(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterBonPlan.fxml"));
            Parent ajouterBonPlan = loader.load();            
            AjouterBonPlanController ajouterController = loader.getController();
            ajouterController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(ajouterBonPlan);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showbtns(ActionEvent event) {
        if (!isVboxVisible) {
                    // Show the VBox
                    btns.setVisible(true);
                    // Create a FadeTransition to add a fade animation
                    FadeTransition ft = new FadeTransition(Duration.millis(1000), btns);
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();

                    isVboxVisible = true;
                } else {
                    // Hide the VBox
                    btns.setVisible(false);
                    isVboxVisible = false;
                    
                    

                }
    }
    
}
