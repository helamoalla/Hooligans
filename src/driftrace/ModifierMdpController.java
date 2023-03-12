/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package driftrace;

import Model.User;
import Service.UserService;
import Util.Data;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author azizh
 */
public class ModifierMdpController implements Initializable {

    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfpassword1;
    @FXML
    private Button res;
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
     public void setBorderPane(BorderPane borderPane){
        this.borderPane=borderPane;
    }

    @FXML
    private void reset(ActionEvent event) {
        String mdpActuel = tfpassword.getText();
    UserService userService = new UserService();
    int utilisateurConnecteId = Data.getId_user();
    User utilisateurConnecte = userService.readById(utilisateurConnecteId);
    
    if (utilisateurConnecte.getMdp().equals(mdpActuel)) {
    String nouveauMdp = tfpassword1.getText();
userService.setMotDePasse(utilisateurConnecteId, nouveauMdp);
tfpassword.setText("");
        tfpassword1.setText("");
// Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText("Le mot de passe a été modifié avec succès.");
        alert.showAndWait();
    } else {
        // Le mot de passe actuel est incorrect, afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Le mot de passe actuel est incorrect.");
        alert.showAndWait();
    }
    
    
    }
    
}
