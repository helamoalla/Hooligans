/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driftrace;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import service.UserService;
import utils.DataSource;














public class ConxController implements Initializable {
 
    @FXML
    private TextField adresse;
    @FXML
    private Button connect;
    @FXML
    private Button inscription;
private Connection connection;
    @FXML
    private TextField pswd;
    @FXML
    private Label mdpshow;
    @FXML
    private Button mot;
    @FXML
    private ToggleButton showbotton;
    
    
    
   

    
    
    
    
    private boolean labelVisible = false;
    UserService userService = new UserService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       mdpshow.textProperty().bind(pswd.textProperty());

    mdpshow.setVisible(false); 
   

    }    


    @FXML
    private void con(ActionEvent event) throws IOException {
        connection = DataSource.getInstance().getCnx();
    String email = adresse.getText();
    String mdp = pswd.getText();
    // Récupérer l'adresse email de l'utilisateur


// Rechercher l'utilisateur par email
UserService userService = new UserService();


    if (email.isEmpty() || mdp.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir l'email et le mot de passe.");
        alert.showAndWait();
        return;
    } 

    try {
        String requete = "SELECT * FROM user WHERE email = ? AND mdp = ?";
        PreparedStatement statement = connection.prepareStatement(requete);
        statement.setString(1, email);
        statement.setString(2, mdp);
        ResultSet resultSet = statement.executeQuery();


        if (resultSet.next()) {
            
           int etatUser = resultSet.getInt("etat");
        if (etatUser == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Votre compte est suspendu.");
            alert.showAndWait();
            return;}
            // Utilisateur trouvé
            int idRole = resultSet.getInt("id_role");
            if (idRole == 1) {
                Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Vous êtes maintenant connecté!");
                alert.showAndWait();
                Driftrace.idutilisateur = resultSet.getInt("id_user");
                UserService sc = new UserService();
                
               
                FXMLLoader loader = new FXMLLoader(getClass().getResource("connecter.fxml"));
                        Parent root = loader.load();

               
            
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        } 
        resultSet.close();
        statement.close();
      
    } catch (SQLException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Impossible de se connecter à la base de données.");
        alert.showAndWait();
    }  
    
    
    }

    @FXML
    private void insc(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("inscription.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void motdepasseoublier(ActionEvent event) throws IOException {
     // Charger la page de connexion
    Parent root = FXMLLoader.load(getClass().getResource("mdpoublier.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }       
    //
  @FXML
    private void showuserpw(ActionEvent event) {
        labelVisible = !labelVisible; // toggle the visibility
    mdpshow.setVisible(labelVisible);


    }   

  
}

