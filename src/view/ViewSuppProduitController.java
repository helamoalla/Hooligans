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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.Categorie;
import models.Produit;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class ViewSuppProduitController implements Initializable {
    ProduitService produitservice=new ProduitService() ;
    @FXML
    private ListView<Produit> listeprod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifprod(ActionEvent event) {
          Produit selectedProduit= listeprod.getSelectionModel().getSelectedItem();
        
        try {
        
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./ViewUpdateProduit.fxml"));
            Parent view_1=loader.load();
            ViewUpdateProduitController updateproduitController=loader.getController();
            updateproduitController.getProduit(selectedProduit);
            updateproduitController.p=selectedProduit;
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_1);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex);   
    }}

    @FXML
    private void suppprod(ActionEvent event) {
         int selectedId= listeprod.getSelectionModel().getSelectedItem().getId_prod();
        produitservice.delete(selectedId);
    }

    @FXML
    private void home(ActionEvent event) {
           try {
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./HomePage.fxml"));
             Parent view_2=loader.load();
             Scene scene = new Scene(view_2);
             Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(ViewSuppProduitController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void ajoutprod(ActionEvent event) {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./ViewAjoutProduit.fxml"));
        
         try {
             Parent view_2=loader.load();
             ViewAjoutProduitController ajoutproduit=loader.getController();
             
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
         } catch (IOException ex) {
             Logger.getLogger(ViewSuppCategorieController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void afficheprod(ActionEvent event) {
        ObservableList <Produit> produits =FXCollections.observableArrayList(produitservice.readAll());
       listeprod.setItems(produits);
    }
    
}
