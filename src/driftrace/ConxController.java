/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driftrace;
import javax.mail.PasswordAuthentication;
import com.mysql.cj.Session;

import service.UserService;
import utils.DataSource;
import javax.mail.Message;
import javax.mail.Transport;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


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
    
    
   

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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

    


   
    
}
