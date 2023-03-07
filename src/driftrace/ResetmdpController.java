/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package driftrace;

import entity.user;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author 21694
 */
public class ResetmdpController implements Initializable {

    @FXML
    private Button res;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfpassword1;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reset(ActionEvent event) {

    String mdpActuel = tfpassword.getText();
    UserService userService = new UserService();
    int utilisateurConnecteId = Driftrace.idutilisateur;
    user utilisateurConnecte = userService.readById(utilisateurConnecteId);
    
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

    @FXML
    private void baack(ActionEvent event) throws IOException {
           // Charger la page de connexion
    Parent root = FXMLLoader.load(getClass().getResource("connecter.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
}
