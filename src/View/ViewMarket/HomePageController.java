/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.ViewMarket;

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
import Service.LignePanierService;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class HomePageController implements Initializable {
    LignePanierService lp =new LignePanierService() ;
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
        public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;}    

    @FXML
    private void MoveCat(ActionEvent event) {
        
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./GestionCategorie.fxml"));
            Parent view_2=loader.load();
            GestionCategorieController gestionCategorieController = loader.getController();
            gestionCategorieController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
           
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void MoveProd(ActionEvent event) {
        
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./GestionProduit.fxml"));
            Parent view_2=loader.load();
           GestionProduitController homePageController = loader.getController();
            homePageController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Marketplace(ActionEvent event) {
         try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./market.fxml"));
            Parent view_2=loader.load();
            MarketController homePageController = loader.getController();
            homePageController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
