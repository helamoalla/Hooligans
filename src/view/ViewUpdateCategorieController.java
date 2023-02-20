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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Categorie;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class ViewUpdateCategorieController implements Initializable {
        CategorieService categorieservice= new CategorieService();
        Categorie c ;
    @FXML
    private TextField updatenom;
    @FXML
    private TextField updatedesc;
    @FXML
    private ChoiceBox<String> updatechoix;
     private String[] choix ={"Pieces de rechange","mode"} ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updatechoix.getItems().addAll(choix);
       
    }    

    void getCategorie(Categorie c){
    updatenom.setText(c.getNom_categorie());
    updatedesc.setText(c.getDescription_categorie());
    updatechoix.setValue(c.getType_categorie());
}    
   
    @FXML
    private void Modifiercat(ActionEvent event) {
         try {
            c.setNom_categorie(updatenom.getText());
            c.setDescription_categorie(updatedesc.getText());
            c.setType_categorie(updatechoix.getValue());
            
            
            categorieservice.update(c);
           
        } catch (Exception ex) {
             System.out.println(ex);        }
        
    }

    @FXML
    private void consulterlist(ActionEvent event) {
        
            try {
                FXMLLoader loader= new FXMLLoader(getClass().getResource("./BonPlan.fxml"));
                Parent view_2=loader.load();
                Scene scene = new Scene(view_2);
                Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ViewUpdateCategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
