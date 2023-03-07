/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driftrace;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entity.user;
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
import service.UserService;
import driftrace.Driftrace;
import service.UserService;
import entity.user;
//import static edu.khadamni.gui.MessageController.ACCOUNT_SID;
//import static edu.khadamni.gui.MessageController.AUTH_TOKEN;
//import static edu.khadamni.gui.MessageController.TWILIO_NUMBER;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

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
    private Button deco;
    private TextField textfield;
    private Label statusLabel;


 public static final String ACCOUNT_SID = "AC84cee867fc660e687e457a4e5e7ea54a";
    public static final String AUTH_TOKEN = "b92a3ef637b22bd813895df3a0856bdd";
    public static final String TWILIO_NUMBER = "+12765308368";
    @FXML
    private Button mdpreset;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int utilisateurconnecte =Driftrace.idutilisateur;
       
       UserService userService = new UserService();
    user utilisateur = userService.readById(utilisateurconnecte);
     
    resnom.setText(utilisateur.getNom());
    resprenom.setText(utilisateur.getPrenom());
    resadresse.setText(utilisateur.getEmail());
    resnum.setText(String.valueOf(utilisateur.getNum_tel()));
    rescin.setText(String.valueOf(utilisateur.getCin()));

 String dossierImages = "C:\\Users\\21694\\Desktop\\driftrace\\src\\image\\";

        File dossier = new File(dossierImages);
        File[] fichiers = dossier.listFiles();
        for (File fichier : fichiers) {
        String nomFichier = fichier.getName();
        if (nomFichier.startsWith(utilisateur.getEmail()) && nomFichier.endsWith(".jpg")||nomFichier.startsWith(utilisateur.getEmail()) && nomFichier.endsWith(".png")) {
            String cheminImage1 = fichier.toURI().toString();
            Image image = new Image(cheminImage1);
            cheminimg1.setImage(image);
            break;
        }

        }
        }

    @FXML
    private void modifff(ActionEvent event) {
            int utilisateurconnecte = Driftrace.idutilisateur;
    UserService userService = new UserService();
    user utilisateur = userService.readById(utilisateurconnecte);

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
    userService.update(utilisateur);
    // Afficher un message de confirmation
    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
    alert2.setTitle("Confirmation");
    alert2.setHeaderText(null);
    alert2.setContentText("Utilisateur modifié avec succès !");
    alert2.showAndWait();
} else {
    // User chose CANCEL or closed the dialog
}
    
    }

   //
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
    private void mdpr(ActionEvent event) throws IOException{ 
             // Charger la page de connexion
    Parent root = FXMLLoader.load(getClass().getResource("resetmdp.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();     
    }
}


  