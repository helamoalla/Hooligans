
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Categorie;
import models.Produit;
import services.CategorieService;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class ViewAjoutProduitController implements Initializable {
         
    
    @FXML
    private ChoiceBox<Categorie> choixCP;
    @FXML
    private TextField nomprod;
    @FXML
    private TextField prixprod;
    @FXML
    private TextArea descprod;
    @FXML
    private TextField quantiteprod;

    /**
     * Initializes the controller class.
     */
      ProduitService prodser= new ProduitService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         CategorieService catser = new CategorieService();
      ArrayList <Categorie> liste = catser.readAll();
      
    /*for (Categorie c :liste){
       choixCP.getItems().add(c);  
    } */ 
     ObservableList<Categorie> categories =FXCollections.observableArrayList(catser.readAll());
    choixCP.setItems(categories);
    
}

    @FXML
    private void ajoutproduit(ActionEvent event) {
       
        Produit p =new Produit();
            p.setNom_prod(nomprod.getText());
            p.setPrix_prod(Double.parseDouble(prixprod.getText()));
            p.setDescription_prod(descprod.getText());
            p.setQuantite(Integer.parseInt(quantiteprod.getText()));
            p.setCategorie(choixCP.getSelectionModel().getSelectedItem());
            prodser.insert(p);
    }

    @FXML
    private void afficherproduits(ActionEvent event) {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./ViewSuppProduit.fxml"));
        
         try {
             Parent view_2=loader.load();
             ViewSuppProduitController supprimeproduit=loader.getController();
             
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
         } catch (IOException ex) {
             Logger.getLogger(ViewSuppCategorieController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
