/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driftrace;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import Model.User;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import Service.UserService;
import Util.Data;
import View.MenuItemController;
import driftrace.Driftrace;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConnecterController implements Initializable {
    @FXML
    private TextField resnom;
    @FXML
    private TextField resprenom;
    @FXML
    private TextField resadresse;
    @FXML
    private TextField resnum;
    @FXML
    private TextField rescin;
    @FXML
    private Button modif;
    @FXML
    private ImageView cheminimg1;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfpassword1;
    @FXML
    private Button res;
    @FXML
    private Button deco;
    @FXML
    private TextField textfield;
    private Label statusLabel;
    BorderPane borderPane;


 public static final String ACCOUNT_SID = "AC84cee867fc660e687e457a4e5e7ea54a";
    public static final String AUTH_TOKEN = "2cfb90ef34ef5819dbd255e97bc5319e";
    public static final String TWILIO_NUMBER = "+12765308368";

    public void setBorderPane(BorderPane borderPane){
        this.borderPane=borderPane;
    }
    public void initialize(URL url, ResourceBundle rb) {
        
        int utilisateurconnecte =Data.getId_user();
       
       UserService userService = new UserService();
    User utilisateur = userService.readById(utilisateurconnecte);
     
    resnom.setText(utilisateur.getNom());
    resprenom.setText(utilisateur.getPrenom());
    resadresse.setText(utilisateur.getEmail());
    resnum.setText(String.valueOf(utilisateur.getNum_tel()));
    rescin.setText(String.valueOf(utilisateur.getCin()));


        }

    @FXML
    private void modifff(ActionEvent event) {
            int utilisateurconnecte = Driftrace.idutilisateur;
    UserService userService = new UserService();
    User utilisateur = userService.readById(utilisateurconnecte);

  //   récupérer les nouvelles valeurs des champs texte
    String nom = resnom.getText();
    String prenom = resprenom.getText();
    String email = resadresse.getText();
       int num_tel = Integer.parseInt(resnum.getText());
       int cin = Integer.parseInt(rescin.getText());

    

//     mettre à jour l'utilisateur
    utilisateur.setNom(nom);
    utilisateur.setPrenom(prenom);
    utilisateur.setEmail(email);
    utilisateur.setNum_tel(num_tel);
    utilisateur.setCin(cin);
  
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
alert.setHeaderText(null);
alert.setContentText("Êtes-vous sûr de vouloir modifier cet utilisateur ?");
       
ButtonType buttonTypeOK = new ButtonType("OK");
ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
Optional<ButtonType> result = alert.showAndWait();
if (result.get() == buttonTypeOK){
                try {
                    userService.update(utilisateur);
                    // Afficher un message de confirmation
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Confirmation");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Utilisateur modifié avec succès !");
                    alert2.showAndWait();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/SideBar.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ConnecterController.class.getName()).log(Level.SEVERE, null, ex);
                }
} else {
    // User chose CANCEL or closed the dialog
}
    
    }

    @FXML
    private void reset(ActionEvent event) {
    String mdpActuel = tfpassword.getText();
    UserService userService = new UserService();
    int utilisateurConnecteId = Driftrace.idutilisateur;
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
    private void sendSMS(ActionEvent event) {
           String toPhoneNumber = textfield.getText();
    if (toPhoneNumber == null || toPhoneNumber.trim().isEmpty()) {
        statusLabel.setText("Please enter a phone number.");
        return;
    }

    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String messageText = "votre numero de telephone est verifier le" + formatter.format(currentDate);
    Message message = Message.creator(new PhoneNumber(toPhoneNumber),
            new PhoneNumber(TWILIO_NUMBER),
            messageText).create();

    if (message.getSid() != null) {
        statusLabel.setText("SMS sent successfully to " + toPhoneNumber + "!");
    } else {
        statusLabel.setText("Error sending SMS to " + toPhoneNumber + ".");
    }
    }

    @FXML
    private void goToAlimenter(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/AlimenterCompte.fxml"));
            Parent alimenterCarte = loader.load();            
            borderPane.setCenter(null);
            borderPane.setCenter(alimenterCarte);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


  