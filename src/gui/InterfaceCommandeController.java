/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Commande;
import models.Panier;
import services.CommandeService;
import services.PanierService;


/**
 * FXML Controller class
 *
 * @author choua
 */
public class InterfaceCommandeController implements Initializable {

    @FXML
    private Button btnretour;
    @FXML
    private TextField tfmontant;
    @FXML
    private Button btnconfirmer;
    @FXML
    private TextField tfnbarticles;
    @FXML
    private TextField tfetat;
    
    CommandeService cs = new CommandeService();
    PanierService pc = new PanierService();
    @FXML
    private ChoiceBox<Panier> listPanier = new ChoiceBox<>() ;
    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ImportPanier();
        
           
    }   
    
    //Retourner tous les panier de la base
    @FXML
    public void ImportPanier(){
       listPanier.getItems().addAll(pc.readAll()); 
    }
    
    //Ajouter Commande
    @FXML
    public void PasserCommande(ActionEvent event) {
    if (tfmontant.getText().length() == 0||tfnbarticles.getText().length() == 0||tfetat.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.show();
        }
    else {
        try {
        Commande c = new Commande();
            c.setMontant(Float.parseFloat(tfmontant.getText()));
            c.setQte_produit(Float.parseFloat(tfnbarticles.getText()));
            c.setEtat_commande(tfetat.getText());
            c.setPanier(listPanier.getValue());
            cs.insert(c);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Commande");
            alert.setHeaderText("Ajout Commande");
            alert.setContentText("Commande Ajoutée avec succés");
            alert.show();
 
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./mainInterface.fxml"));
            Parent view=loader.load();
            Scene scene = new Scene(view);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
 //retour à la page d'accueil
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
            Logger.getLogger(InterfaceCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
  }

    
     

    
}
