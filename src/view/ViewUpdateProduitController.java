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
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Categorie;
import models.Produit;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class ViewUpdateProduitController implements Initializable {
    ProduitService produitservice=new ProduitService() ;
    Produit p ;
    @FXML
    private TextField modifnomprod;
    @FXML
    private TextField modifprixprod;
    @FXML
    private TextArea modifdescprod;
    @FXML
    private TextField modifquantiteprod;
    @FXML
    private ChoiceBox<Categorie> choixP;

    /**
     * Initializes the controller class.
     */
    
    
    void getProduit(Produit p){
    modifnomprod.setText(p.getNom_prod());
    modifdescprod.setText(p.getDescription_prod());
    modifprixprod.setText(String.valueOf(p.getPrix_prod()));
    modifquantiteprod.setText(String.valueOf(p.getQuantite()));
    choixP.setValue(p.getCategorie());
}    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifproduit(ActionEvent event) {
        if (modifnomprod.getText().length() == 0||modifdescprod.getText().length() == 0||modifprixprod.getText().length() == 0||modifquantiteprod.getText().length() == 0|| choixP.valueProperty() ==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Veuillez remplir tous les champs"+ "");
            alert.show();

        }else {
         try {
            p.setNom_prod(modifnomprod.getText());
            p.setPrix_prod(Double.parseDouble(modifprixprod.getText()));
            p.setDescription_prod(modifdescprod.getText());
            p.setQuantite(Integer.parseInt(modifquantiteprod.getText()));
            p.setCategorie(choixP.getSelectionModel().getSelectedItem());
            
            
            produitservice.update(p);
           
        } catch (Exception ex) {
             System.out.println(ex);        }
        
    }
    }

    @FXML
    private void afficherproduits(ActionEvent event) {
              try {
                FXMLLoader loader= new FXMLLoader(getClass().getResource("./ViewSuppProduit.fxml"));
                Parent view_2=loader.load();
                Scene scene = new Scene(view_2);
                Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ViewUpdateProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
    
}
