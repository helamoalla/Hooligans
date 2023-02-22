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
import models.Categorie;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class ViewSuppCategorieController implements Initializable {
     CategorieService categorieservice= new CategorieService();
    @FXML
    private ListView<Categorie> listecategories;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherprod(ActionEvent event) {
        ObservableList<Categorie> categories =FXCollections.observableArrayList(categorieservice.readAll());
       listecategories.setItems(categories);
    }

    @FXML
    private void deleteCat(ActionEvent event) {
         int selectedId= listecategories.getSelectionModel().getSelectedItem().getId_categorie();
        categorieservice.delete(selectedId);
        
    
    }

    @FXML
    private void pageAjout(ActionEvent event) {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./ViewAjoutCategorie.fxml"));
        
         try {
             Parent view_2=loader.load();
             ViewAjoutCategorieController ajoutcategorie=loader.getController();
             
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
         } catch (IOException ex) {
             Logger.getLogger(ViewSuppCategorieController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
//Aziz
    @FXML
    private void modifcat(ActionEvent event) {
        Categorie selectedCategorie= listecategories.getSelectionModel().getSelectedItem();
        
        try {
            //bonPlanService.update(bonPlanService.readById(selectedId));
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./ViewUpdateCategorie.fxml"));
            Parent view_1=loader.load();
            ViewUpdateCategorieController updatecategorieController=loader.getController();
            updatecategorieController.getCategorie(selectedCategorie);
            updatecategorieController.c=selectedCategorie;
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_1);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex);        } 
       
    }

    @FXML
    private void Home(ActionEvent event) {
         try {
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./HomePage.fxml"));
             Parent view_2=loader.load();
             Scene scene = new Scene(view_2);
             Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(ViewSuppCategorieController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
