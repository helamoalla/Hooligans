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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Panier;
import services.PanierService;

/**
 * FXML Controller class
 *
 * @author choua
 */
public class InterfaceajoutPanierController implements Initializable {
    @FXML
    private TextField tfmontant;
    @FXML
    private TextField tfnbarticles;
    @FXML 
    private Button btn_ajout;
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_annuler;
    
     PanierService ps = new PanierService();

@FXML    
private void ajouterPanier(ActionEvent event) {
    if (tfmontant.getText().length() == 0||tfnbarticles.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.show();
        }
    else {
        try {
        Panier p = new Panier();
            p.setMontant(Float.parseFloat(tfmontant.getText()));
            p.setNb_articles(Integer.parseInt(tfnbarticles.getText()));
            ps.insert(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Panier");
            alert.setHeaderText("Ajout Panier");
            alert.setContentText("Panier Ajouté avec succés");
            alert.show();
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./mainInterface.fxml"));
            Parent view=loader.load();
            Scene scene = new Scene(view);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceajoutPanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
//
  @FXML  
  public void annuler(){
      tfmontant.setText("");
      tfnbarticles.setText("");
  }
  
  //revenir à la page d'acueil
    @FXML
  public void retour(ActionEvent event) {
    
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./mainInterface.fxml"));
            Parent view_2=loader.load();
            Scene scene = new Scene(view_2);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);     
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceajoutPanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
