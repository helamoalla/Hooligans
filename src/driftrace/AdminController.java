/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driftrace;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import entity.user;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import service.UserService;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;




public class AdminController implements Initializable {

    @FXML
    private TableView<user> afficher;
    @FXML
    private TextField rechercher;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TableColumn nom2;
    @FXML
    private TableColumn prenom2;
    @FXML
    private TableColumn pswd2;
    @FXML
    private TableColumn email2;
    @FXML
    private TableColumn num_tel2;
    @FXML
    private TableColumn cin2;
    @FXML
    private TableColumn id_role2;
    @FXML
    private TableColumn quota2;
 private UserService userService = new UserService();
    @FXML
    private ComboBox changer_id;
    @FXML
    private Button deco;
    @FXML
    private Button act;
    @Override
   public void initialize(URL url, ResourceBundle rb) {
      
   
   
   changer_id.getItems().add("admin");
   changer_id.getItems().add("utilisateur");
   changer_id.getItems().add("pilote");
   changer_id.setPromptText("changer type utilisateur");
   
        nom2.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom2.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        pswd2.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        email2.setCellValueFactory(new PropertyValueFactory<>("email"));
        num_tel2.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        cin2.setCellValueFactory(new PropertyValueFactory<>("cin"));
        id_role2.setCellValueFactory(new PropertyValueFactory<>("id_role"));
        quota2.setCellValueFactory(new PropertyValueFactory<>("quota"));
        List<user> userList = userService.readAll();
        
        // affiche les données dans le tableau
        afficher.getItems().setAll(userList);
    }    

    @FXML
    private void modif(ActionEvent event) {
        user selectedUser = afficher.getSelectionModel().getSelectedItem();
    
    // 2. Afficher un message d'erreur si aucun utilisateur n'est sélectionné
    if (selectedUser == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No user selected");
        alert.setHeaderText(null);
        alert.setContentText("Please select a user in the table.");
        alert.showAndWait();
        return;
    }
    
    // 3. Demander confirmation à l'utilisateur
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm modification");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to modify the selected user?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() != ButtonType.OK) {
        return;
    }
    
    // 4. Récupérer la nouvelle valeur de id_role depuis le ComboBox
    String newRoleId = (String) changer_id.getValue();
    int newRoleIdInt = 0;
    if (newRoleId.equals("admin")) {
        newRoleIdInt = 1;
    } else if (newRoleId.equals("utilisateur")) {
        newRoleIdInt = 2;
    } else if (newRoleId.equals("pilote")) {
        newRoleIdInt = 3;
    }
    
    // 5. Mettre à jour la propriété id_role de l'utilisateur sélectionné
    selectedUser.setId_role(newRoleIdInt);
    
    // 6. Mettre à jour l'utilisateur dans la base de données
    userService.update1(selectedUser,newRoleIdInt);
    
    // 7. Actualiser la liste des utilisateurs affichée dans le tableau
    List<user> userList = userService.readAll();
    afficher.getItems().setAll(userList);    
    }

    @FXML
    private void supp(ActionEvent event) {
       int  etat = 0;
         user selectedUser = afficher.getSelectionModel().getSelectedItem();
       
        if (selectedUser == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No user selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a user in the table.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm ban");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to ban the selected user?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
           
             selectedUser.setId_role(etat);
             userService.updateban(selectedUser,etat);
             List<user> userList = userService.readAll();
        
        // affiche les données dans le tableau
        afficher.getItems().setAll(userList);
        }
    }

    @FXML
    private void rech(KeyEvent event) {
         if (event.getCode() == KeyCode.ENTER) {
        String email = rechercher.getText();
        user searchedUser = userService.readByEmailrecruteur(email);
        if (searchedUser != null) {
            afficher.getItems().setAll(searchedUser);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No user found");
            alert.setHeaderText(null);
            alert.setContentText("No user found with the given e-mail.");
            alert.showAndWait();
        }
    }
    }

    @FXML
    private void dec(ActionEvent event) throws IOException {
            // Charger la page de connexion
    Parent root = FXMLLoader.load(getClass().getResource("conx.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void activer(ActionEvent event) {
        int  etat = 1;
         user selectedUser = afficher.getSelectionModel().getSelectedItem();
       
        if (selectedUser == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No user selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a user in the table.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm ban");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to activate the selected user?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
           
             selectedUser.setId_role(etat);
             userService.updateban(selectedUser,etat);
             List<user> userList = userService.readAll();
        
        // affiche les données dans le tableau
        afficher.getItems().setAll(userList);
        }
    }
    
}
